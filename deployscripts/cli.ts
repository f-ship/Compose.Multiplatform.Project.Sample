import { join } from "https://deno.land/std/path/mod.ts";
import { exists } from "https://deno.land/std/fs/mod.ts";
import { parse } from "https://deno.land/std@0.200.0/flags/mod.ts";
import type { Args } from "https://deno.land/std@0.200.0/flags/mod.ts";
/**
 * Executes a command and returns its output
 */
async function execCommand(cmd: string[], cwd: string): Promise<string> {
  const outputArray: string[] = [];

  const process = new Deno.Command(cmd[0], {
    args: cmd.slice(1),
    cwd: cwd,
    stderr: "piped",
    stdout: "piped",
  });

  const { stdout, stderr } = await process.output();
  const output = new TextDecoder().decode(stdout);
  const error = new TextDecoder().decode(stderr);

  if (output) {
    console.log(output)
    outputArray.push(output)
  }

  if (error) {
    console.log(error)
    outputArray.push(error)
  }

  // Only treat as error if it contains actual error indicators
  if (error && (error.includes("error:") || error.includes("fatal:"))) {
    throw new Error(`Command failed: ${error}`);
  }

  // Combine stdout and stderr for the complete output
  return (output + (error ? "\n" + error : "")).trim();
}

class Submodule {
  constructor(
    public path: string,
    public name: string,
    public status: Status = Status.Unknown,
  ) {}
}

enum Status {
  Unchanged,
  Added,
  Modified,
  Deleted,
  Renamed,
  Copied,
  Untracked,
  Ignored,
  Unknown,
}

function parseGitStatus(statusLine: string): Status {
  if (!statusLine || statusLine.length < 2) {
    return Status.Unknown;
  }

  const [index, working] = statusLine.slice(0, 2);

  // Check both index and working tree status
  const status = working !== " " ? working : index;

  switch (status) {
    case "M":
      return Status.Modified;
    case "A":
      return Status.Added;
    case "D":
      return Status.Deleted;
    case "R":
      return Status.Renamed;
    case "C":
      return Status.Copied;
    case "?":
      return Status.Untracked;
    case "!":
      return Status.Ignored;
    case " ":
      return Status.Unchanged;
    default:
      return Status.Unknown;
  }
}

async function getSubmoduleStatus(submodulePath: string): Promise<Status> {
  try {
    const status = await execCommand(
      ["git", "status", "--porcelain"],
      submodulePath,
    );

    if (!status) {
      return Status.Unchanged;
    }

    // If multiple files are changed, prioritize the "most significant" change
    const statuses = status
      .split("\n")
      .map((line) => parseGitStatus(line));

    // Priority order for multiple changes
    const priorityOrder = [
      Status.Modified,
      Status.Added,
      Status.Deleted,
      Status.Renamed,
      Status.Copied,
      Status.Untracked,
      Status.Ignored,
      Status.Unchanged,
      Status.Unknown,
    ];

    return statuses.reduce((highest, current) => {
      const highestPriority = priorityOrder.indexOf(highest);
      const currentPriority = priorityOrder.indexOf(current);
      return currentPriority < highestPriority ? current : highest;
    }, Status.Unknown);
  } catch (error) {
    console.error(`Error getting status for ${submodulePath}:`, error);
    return Status.Unknown;
  }
}

async function findSubmodules(repoPath: string): Promise<Submodule[]> {
  const gitmodulesPath = join(repoPath, ".gitmodules");

  try {
    if (await exists(gitmodulesPath)) {
      const gitmodulesContent = await execCommand(
        ["git", "config", "--file", ".gitmodules", "--get-regexp", "path"],
        repoPath,
      );

      const submodules = await Promise.all(
        gitmodulesContent
          .split("\n")
          .filter((line) => line.trim())
          .map((line) => line.split(" ")[1])
          .map(async (line) =>
            new Submodule(
              join(repoPath, line),
              line,
              await getSubmoduleStatus(join(repoPath, line)),
            )
          ),
      );

      const result: Submodule[] = [...submodules];

      for (const submodule of submodules) {
        if (await exists(join(repoPath, submodule.name))) {
          const childSubmodules = await findSubmodules(submodule.path);
          result.push(...childSubmodules);
        }
      }

      return result;
    }

    return [];
  } catch (e) {
    console.error("Error processing submodules in", repoPath, e);
    return [];
  }
}

export function parseArguments(args: string[]): Args {
  const parsedArgs = parse(
      args,
      {
        alias: {"force": "f", "save": "s", "status":"st", "push": "p", "amend": "a", "commit": "c", "path": "p"},
        boolean: ["force", "save", "status", "push", "amend", "deploy"],
        string: ["commit", "path"],
        default: { "force": false, "save": false, "push": false, "amend": false },
      }
  )
  return parsedArgs;
}
// Main execution
async function main(inputArgs: string[]): Promise<void> {
  const parsedArgs = parseArguments(inputArgs)
  const repoPath = await getRepoPath(parsedArgs)
  // console.log(parsedArgs)
  if (parsedArgs.commit || parsedArgs.amend) {
    const submodules = await findAllSubmodules(repoPath)
    const modifiedSubmodules = submodules.filter((s) => s.status !== Status.Unchanged)
    console.log("Modified Modules", modifiedSubmodules);
    for (const s of modifiedSubmodules.reverse()) {
      console.log(s.path, s.status)
      if (s.status !== Status.Unchanged && s.status !== Status.Ignored && s.status !== Status.Unknown) {
        await execCommand(["git", "add", "."], s.path)
        if (parsedArgs.amend) {
          await execCommand(["git", "commit", "--amend", "--no-edit"], s.path)
          await execCommand(["git", "push", "--force"], s.path)
        } else {
          await execCommand(["git", "commit", "-m", parsedArgs.commit], s.path)
          await execCommand(["git", "push"], s.path)
        }
      }
    }
  } else if (parsedArgs.status) {
    const submodules = await findAllSubmodules(repoPath)
    const modifiedSubmodules = submodules.filter((s) => s.status !== Status.Unchanged)
    console.log("Modified Modules", modifiedSubmodules)
  } else if (parsedArgs.deploy) {
    await execCommand(["cd", "server"], repoPath)
    await execCommand(["./gradlew", ":server:fatJar"], repoPath)
    await execCommand(["cp", "server/build/libs/ktor-app-1.0.0.jar", "server"], repoPath)
    await execCommand(["gcloud", "app", "deploy"], join(repoPath, "server"))
  }
}

async function getRepoPath(parsedArgs: Args): Promise<string> {
  let save: boolean = parsedArgs.save;
  let path: string | null = parsedArgs.path;

  const kv = await Deno.openKv("/tmp/kv.db");
  let askToSave: boolean = false;

  if (!path) {
    path = (await kv.get(["path"])).value as string;
    if (!path) {
      path = prompt("What is the path to the repo?");
      askToSave = true;
    }
  }

  if (!save && askToSave) {
    const savePrompt: string | null = prompt(
        "Do you want to save this path? Y/n",
    );
    if (savePrompt?.toUpperCase() === "Y") save = true;
  }

  if (save) {
    await kv.set(["path"], path);
  }
  return path ?? Deno.cwd();
}

async function findAllSubmodules(repoPath: string): Promise<Submodule[]> {
  const startingDir = repoPath;
  console.log("🔍 Starting to process submodules in", startingDir);
  Deno.chdir(startingDir);
  const submodules = await findSubmodules(startingDir);
  console.log("🔍 Found", submodules.length, "submodules");
  return submodules;
}

if (import.meta.main) {
  main(Deno.args);
}
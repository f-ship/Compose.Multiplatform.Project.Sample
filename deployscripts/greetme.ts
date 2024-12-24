import greetings from "./greetings.json" with { type: "json" };
import { parse } from "https://deno.land/std@0.200.0/flags/mod.ts";
import type { Args } from "https://deno.land/std@0.200.0/flags/mod.ts";

export function parseArguments(args: string[]): Args {
  // All boolean arguments
  const booleanArgs = [
    "help",
    "save",
  ];

  // All string arguments
  const stringArgs = [
    "name",
    "color",
  ];

  // And a list of aliases
  const alias = {
    "help": "h",
    "save": "s",
    "name": "n",
    "color": "c",
  };

  return parse(args, {
    alias,
    boolean: booleanArgs,
    string: stringArgs,
    stopEarly: false,
    "--": true,
  });
}

function printHelp(): void {
  console.log(`Usage: greetme [OPTIONS...]`);
  console.log("\nOptional flags:");
  console.log("  -h, --help                Display this help and exit");
  console.log("  -s, --save                Save settings for future greetings");
  console.log("  -n, --name                Set your name for the greeting");
  console.log("  -c, --color               Set the color of the greeting");
}

/**
 * Main logic of CLI.
 */

async function main(inputArgs: string[]): Promise<void> {
  const args = parseArguments(inputArgs);

  // If help flag enabled, print help.
  if (args.help) {
    printHelp();
    Deno.exit(0);
  }

  let name: string | null = args.name;
  let color: string | null = args.color;
  let save: boolean = args.save;

  const kv = await Deno.openKv("/tmp/kv.db");
  let askToSave = false;

  if (!name) {
    name = (await kv.get(["name"])).value as string;
    if (!name) {
      name = prompt("What is your name?");
      askToSave = true;
    }
  }
  if (!color) {
    color = (await kv.get(["color"])).value as string;
    if (!color) {
      color = prompt("What is your favorite color?");
      askToSave = true;
    }
  }
  if (!save && askToSave) {
    const savePrompt: string | null = prompt(
      "Do you want to save these settings? Y/n",
    );
    if (savePrompt?.toUpperCase() === "Y") save = true;
  }

  if (save) {
    await kv.set(["name"], name);
    await kv.set(["color"], color);
  }

  console.log(
    `%c${
      greetings[Math.floor(Math.random() * greetings.length) - 1]
    }, ${name}!`,
    `color: ${color}; font-weight: bold`,
  );
}

/**
 * Run CLI.
 */

main(Deno.args);

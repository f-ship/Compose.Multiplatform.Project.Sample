# CLI
Deploying changes to a repo with 42 git modules can get cumbersome when wanting to make changes to main quickly across deeply nested modules or multiple modules at once.

To make things more straight forward **deployscripts/cli** can be executed to make all the changes required in the correct order (starting from leaf modules working up to the root)

This script is a work in progress, so will improve in time and potentially receive a UI at a later stage.

## Usage
Currently cli is not included in the path by default so needs to be run from the **deployscripts** directory. The first time you use the script you will prompted to give the absolute path to the repo. Then you will be prompted to save which is the recommended choice to avoid further prompts in the future.

### commit
**cli -commit "Message"** | **cli -c "Message"**

Will create a commit and push to the remote repo
*(Should probably disable the push by default behaviour)*

**cli -amend"** | **cli -a"**

Will amend the last commit and push to the remote repo *(Should probably disable the push by default behaviour)*

### status
**cli -status** | **cli -st**

Will give a breakdown on the status of each submodule

### deploy
**cli deploy**

Will deploy the latest version of the server to google cloud

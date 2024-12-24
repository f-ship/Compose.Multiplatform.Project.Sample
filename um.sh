#!/bin/bash

# Recursively commit/push submodules from deepest to root
function process_submodules_bottom_up() {
  local repo_path="$1"
  cd "$repo_path" || return

  # If in detached HEAD, check out 'main' directly
  if [ "$(git symbolic-ref --short -q HEAD)" == "" ]; then
    echo "⚠️  Detached HEAD in $repo_path — checking out 'main'"
    git checkout main || echo "❌ Failed to checkout 'main' in $repo_path"
  fi

  # If there are any submodules, process them first
  if [ -f ".gitmodules" ]; then
    git config --file .gitmodules --get-regexp path | while read -r key path; do
      if [ -d "$path" ]; then
        process_submodules_bottom_up "$path"
      fi
    done
  fi

  echo "🔍 Checking $repo_path"
  git branch

  # Stage, commit, and push any changes in this repo
  if [[ -n $(git status --porcelain) ]]; then
    echo "✅ Changes found in $repo_path"
    git add .
    git commit --amend --no-edit
    git push -f
  else
    echo "➖ No changes in $repo_path"
  fi

  cd - > /dev/null || exit
}

# Ensure submodules are initialized and updated first
git submodule update --recursive

# Start recursive bottom-up commit
process_submodules_bottom_up "$(pwd)"

echo "🚀 All done!"
#!/bin/bash

# Recursively commit/push submodules from deepest to root
function process_submodules_bottom_up() {
  local repo_path="$1"
  local commit_msg="$2"

  # Use pushd/popd for proper directory navigation
  pushd "$repo_path" > /dev/null || return

  # If .gitmodules exists, recurse into submodules first
  if [ -f ".gitmodules" ]; then
    git config --file .gitmodules --get-regexp path | while read -r _ path; do
      if [ -d "$path" ]; then
        process_submodules_bottom_up "$path" "$commit_msg"
      fi
    done
  fi

  echo "🔍 Checking $repo_path"

  # Ensure we're on the main branch before committing and pushing
  git checkout main 2>/dev/null || git checkout -b main
  git pull origin main --rebase

  # Check for changes
  if [[ -n $(git status --porcelain) ]]; then
    echo "✅ Changes found in $repo_path"
    git add .

    if [[ -n "$commit_msg" ]]; then
      git commit -m "$commit_msg"
      git push origin main
    else
      git commit --amend --no-edit
      git push -f origin main
    fi

  else
    echo "➖ No changes in $repo_path"
  fi

  popd > /dev/null
}

# Usage: ./script.sh "optional commit message"
process_submodules_bottom_up "$(pwd)" "$1"

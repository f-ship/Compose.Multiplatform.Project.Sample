#!/bin/bash

# Recursively commit/push submodules from deepest to root
function process_submodules_bottom_up() {
  local repo_path="$1"
  local commit_msg="$2"
  cd "$repo_path" || return

  # If there are any submodules, process them first
  if [ -f ".gitmodules" ]; then
    git config --file .gitmodules --get-regexp path | while read -r key path; do
      if [ -d "$path" ]; then
        process_submodules_bottom_up "$path" "$commit_msg"
      fi
    done
  fi

  echo "🔍 Checking $repo_path"
  git branch
  # Stage, commit, and push any changes in this repo
  if [[ -n $(git status --porcelain) ]]; then
    echo "✅ Changes found in $repo_path"
    git add .
    if [[ -n "$commit_msg" ]]; then
      git commit -m "$commit_msg"
    else
      git commit --amend --no-edit
    fi
    git push -f
  else
    echo "➖ No changes in $repo_path"
  fi
}

# Usage: ./script.sh "optional commit message"
process_submodules_bottom_up "$(pwd)" "$1"

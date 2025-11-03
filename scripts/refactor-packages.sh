#!/usr/bin/env bash
set -euo pipefail

# Safe refactor script for package rename from
# com.mbaigo.trainingTools.training_tools -> com.mbaigo.trainingtools
# Usage:
#   ./scripts/refactor-packages.sh         # dry-run (shows planned changes)
#   ./scripts/refactor-packages.sh --apply # performs changes (creates a git branch and git-mv)

DRY_RUN=1
if [[ "${1:-}" == "--apply" ]]; then
  DRY_RUN=0
fi

OLD_PREFIX="com.mbaigo.trainingTools.training_tools"
NEW_PREFIX="com.mbaigo.trainingtools"

# Targeted subpackage cleanups (apply after root rename)
declare -A REPLACEMENTS
REPLACEMENTS[
  ".dao.dao.factory.daoImpl.domaine.userRole"
]=".repository.userrole"
REPLACEMENTS[".dao.dao.factory.daoImpl.domaine"]=".repository"
REPLACEMENTS[".config.mapper.jwtConfig"]=".config.jwt"
REPLACEMENTS[".app"]=".controller"

# Find java files that declare a package starting with the old prefix
mapfile -t FILES < <(grep -R -l "package ${OLD_PREFIX}" src 2>/dev/null || true)

if [[ ${#FILES[@]} -eq 0 ]]; then
  echo "No Java files found with package ${OLD_PREFIX}. Nothing to do."
  exit 0
fi

echo "Found ${#FILES[@]} files using package ${OLD_PREFIX} (or subpackages)."

if [[ $DRY_RUN -eq 1 ]]; then
  echo "DRY RUN (no changes will be made). Run with --apply to perform the refactor."
else
  # ensure working tree is clean
  if [[ -n "$(git status --porcelain)" ]]; then
    echo "Git working tree is not clean. Please commit or stash changes before running with --apply." >&2
    exit 1
  fi
  BRANCH="chore/refactor-packages-$(date +%s)"
  git checkout -b "$BRANCH"
  echo "Created branch $BRANCH"
fi

# Process each file
for f in "${FILES[@]}"; do
  # detect whether file is under src/main/java or src/test/java (or other src/*/java)
  if [[ "$f" == src/main/java/* ]]; then
    BASE=src/main/java
  elif [[ "$f" == src/test/java/* ]]; then
    BASE=src/test/java
  else
    # fallback: extract src/<module>/java prefix
    BASE=$(echo "$f" | sed -E 's|(src/[^/]+/java)/.*|\1|')
  fi

  # read the package declaration (first few lines)
  pkg_line=$(sed -n '1,6p' "$f" | grep "^package " | head -n1 || true)
  if [[ -z "$pkg_line" ]]; then
    echo "Warning: no package line found in $f; skipping"
    continue
  fi
  old_pkg=$(echo "$pkg_line" | sed -E 's/^package[[:space:]]+([^;]+);/\1/')

  # compute the new package
  new_pkg="$old_pkg"
  # replace the root prefix
  if [[ "$new_pkg" == ${OLD_PREFIX}* ]]; then
    new_pkg="${NEW_PREFIX}${new_pkg#${OLD_PREFIX}}"
  else
    echo "Skipping $f: package does not start with ${OLD_PREFIX}" >&2
    continue
  fi

  # apply additional replacements (ordered)
  for k in "${!REPLACEMENTS[@]}"; do
    v=${REPLACEMENTS[$k]}
    new_pkg=${new_pkg//$k/$v}
  done

  # compute target dir
  pkg_path=$(echo "$new_pkg" | tr '.' '/')
  target_dir="$BASE/$pkg_path"
  target_path="$target_dir/$(basename "$f")"

  echo "\nFile: $f"
  echo "  old package: $old_pkg"
  echo "  new package: $new_pkg"
  echo "  target path: $target_path"

  if [[ $DRY_RUN -eq 1 ]]; then
    # show what would be changed
    echo "  (dry-run) would create dir: $target_dir"
    echo "  (dry-run) would update package line to: package $new_pkg;"
    echo "  (dry-run) would git mv $f -> $target_path"
  else
    # make target dir
    mkdir -p "$target_dir"
    # perform git mv
    git mv "$f" "$target_path"
    # update package declaration
    # use perl to replace the first package line robustly
    perl -0777 -pe "s/^package\s+\Q$old_pkg\E\s*;/package $new_pkg;/m" -i "$target_path"
    echo "  moved and updated package"
  fi

done

if [[ $DRY_RUN -eq 1 ]]; then
  echo "\nDRY RUN complete. If the plan looks good, run:\n  ./scripts/refactor-packages.sh --apply\nThis will create a new branch and perform git moves. Review changes and run ./mvnw clean test afterwards."
else
  echo "\nRefactor applied on branch $BRANCH. Run 'git status' and 'git diff' to review, then run './mvnw clean test'."
fi


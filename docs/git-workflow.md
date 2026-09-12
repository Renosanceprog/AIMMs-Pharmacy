# Git Workflow

This document defines the Git workflow for the AIMMs Pharmacy project.

The goal is to keep `main` stable while allowing team members to work independently on features, fixes, refactors, and other changes.

---

## 1. Branch Structure

The project uses `main` as the primary and protected branch.

```text
main
├── feature/<short-description>
├── fix/<short-description>
└── refactor/<short-description>
```

### Main branch

`main` should contain code that is considered stable and integrated.

Team members should **not directly push to `main`**.

Changes to `main` should normally happen through a Pull Request (PR).

### Feature branches

Use `feature/` for new functionality.

```text
feature/csv-manager
feature/transaction-repository
feature/authentication
feature/inventory-service
feature/prescription-validation
```

### Fix branches

Use `fix/` for correcting bugs or unintended behavior.

```text
fix/csv-parsing
fix/balance-calculation
fix/fefo-selection
fix/login-validation
```

### Refactor branches

Use `refactor/` when changing the structure or implementation without intentionally changing functionality.

```text
refactor/repository-structure
refactor/csv-manager
refactor/customer-repository
```

Keep branch names short and descriptive.

---

## 2. Starting New Work

Always start from the latest `main`.

```bash
git switch main
git pull
git switch -c feature/<short-description>
```

Example:

```bash
git switch main
git pull
git switch -c feature/csv-manager
```

This keeps the new branch based on the latest integrated version of the project.

---

## 3. Working on a Branch

Make changes only related to the purpose of the branch.

For example:

```text
feature/transaction-repository
```

should primarily contain changes related to the transaction repository.

Avoid mixing unrelated work into the same branch.

### Check your changes

```bash
git status
```

Review changed files before committing.

```bash
git diff
```

---

## 4. Commits

Commits should describe what was changed.

Use short, clear, imperative-style messages.

### Good

```text
Add CSV manager
Implement transaction repository
Add prescription validation
Fix FEFO batch selection
Update transaction documentation
Refactor customer repository
```

### Avoid

```text
asdfgh
update
changes
final
final final
please work
```

Commits do not need to follow an elaborate commit-message convention for this project.

---

## 5. Push Your Branch

Push the branch to GitHub.

```bash
git push -u origin feature/<short-description>
```

Example:

```bash
git push -u origin feature/csv-manager
```

After the first push, normal updates can use:

```bash
git push
```

---

## 6. Pull Requests

When the work is ready, open a Pull Request from the working branch into `main`.

```text
feature/<branch>
        ↓
      Pull Request
        ↓
      main
```

Before opening the PR:

1. Make sure the project compiles.
2. Test the changes.
3. Review your own diff.
4. Make sure unrelated files or changes are not included.
5. Push the latest changes.

---

## 7. Pull Request Review

The `main` branch is protected.

The current rules require:

* Pull Request before merging
* Two approvals
* The PR author cannot approve their own PR
* Required approvals must remain valid after the latest changes
* Stale approvals are dismissed when new commits are pushed
* Requested changes prevent merging
* Required conversations must be resolved
* Direct pushes to `main` are blocked
* Force pushes to `main` are blocked
* Branch deletion protections are enabled where applicable

Do not merge a PR until all required requirements are satisfied.

---

## 8. Updating a Branch Before Merge

If `main` has changed while your branch is being reviewed, update your branch when necessary.

First fetch the latest changes:

```bash
git fetch origin
```

Then update your branch according to the team's agreed approach.

For a straightforward merge-based workflow:

```bash
git merge origin/main
```

Resolve any conflicts, test the project again, then push:

```bash
git push
```

If the branch receives new commits, previously given approvals may need to be obtained again because stale approvals are dismissed by the branch rules.

---

## 9. After a PR Is Merged

Once the PR has been successfully merged into `main`, the feature branch is no longer needed.

The branch can be deleted from GitHub.

If the local branch is also no longer needed:

```bash
git switch main
git pull
git branch -d feature/<short-description>
```

Example:

```bash
git switch main
git pull
git branch -d feature/csv-manager
```

---

## 10. Recommended Team Workflow

The normal workflow is:

```text
Update main
    ↓
Create branch
    ↓
Work on feature/fix/refactor
    ↓
Commit changes
    ↓
Push branch
    ↓
Open Pull Request
    ↓
Code review
    ↓
2 approvals
    ↓
Resolve required conversations
    ↓
Merge into main
    ↓
Delete branch
```

In command form:

```bash
git switch main
git pull

git switch -c feature/my-feature

# make changes

git status
git diff

git add .
git commit -m "Implement my feature"

git push -u origin feature/my-feature
```

Then create a Pull Request on GitHub.

---

## 11. Important Rules

### Do

* Work on a separate branch.
* Start new work from the latest `main`.
* Keep branches focused.
* Make meaningful commits.
* Test before opening a PR.
* Review your own changes.
* Keep `main` stable.
* Delete merged branches when they are no longer needed.

### Don't

* Directly push to `main`.
* Force-push to `main`.
* Mix unrelated features into one branch.
* Commit generated build output.
* Commit temporary files.
* Use vague commit messages.
* Bypass the Pull Request process.

---

## 12. Branch Naming Quick Reference

| Purpose                           | Prefix      | Example                         |
| --------------------------------- | ----------- | ------------------------------- |
| New functionality                 | `feature/`  | `feature/csv-manager`           |
| Bug fix                           | `fix/`      | `fix/csv-parsing`               |
| Structural/implementation cleanup | `refactor/` | `refactor/repository-structure` |

Use lowercase names with hyphens.

```text
feature/authentication
feature/transaction-service
fix/balance-calculation
fix/prescription-validation
refactor/csv-manager
```

---

## 13. Scope of This Workflow

This project intentionally uses a simple Git workflow.

The project does **not** require:

* Git Flow
* A `develop` branch
* Release branches
* Separate staging branches
* Complex commit conventions
* Merge queues
* Signed commits

These may be considered in a future project if the project's needs change, but they are unnecessary for the current AIMMs Pharmacy workflow.

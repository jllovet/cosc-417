---
name: feature-branch
description: Start a new feature branch following the naming convention
user-invocable: true
disable-model-invocation: true
argument-hint: "<issue-id> <short-description>"
allowed-tools: Bash(git:*)
---

# Feature Branch Workflow

Create a feature branch for development.

## CRITICAL RULE

**NEVER write code while on `main` branch.** Always create a feature branch first.

## Branch Naming

Format: `feature/<issue-id>-<short-description>`

Examples:
- `feature/TU-123-assignment-1`
- `feature/TU-456-dfa-minimization`
- `docs/TU-789-lecture-notes`

## Steps

1. **Verify current branch:**
   ```bash
   git branch --show-current
   ```
   If on `main`, proceed. If on another branch, decide whether to switch.

2. **Sync with remote:**
   ```bash
   git checkout main
   git fetch origin main
   git pull origin main
   ```

3. **Create and switch to branch:**
   ```bash
   git checkout -b feature/$ARGUMENTS
   ```
   Example: `git checkout -b feature/TU-123-add-auth`

4. **Verify:**
   ```bash
   git branch --show-current
   ```

## After Creating Branch

1. Update the issue to "In Progress" in your issue tracker
2. Post a comment noting you've started work

<!-- CUSTOMIZE: Add your issue tracker commands here -->

## Arguments

Pass the issue ID and description as arguments:
- `/feature-branch TU-123 add-auth`
- Creates: `feature/TU-123-add-auth`

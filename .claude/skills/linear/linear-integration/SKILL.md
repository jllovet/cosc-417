---
name: linear-integration
description: Reference guide for using the Linear CLI integration
allowed-tools: Bash(linear:*), Bash(direnv:*)
---

# Linear CLI Integration

Reference for using Linear CLI for issue tracking.

## Setup

```bash
# Install Linear CLI (requires Go)
# See: https://github.com/jllovet/linear-cli
go install github.com/jllovet/linear-cli@latest

# Environment variables (add to .envrc)
export LINEAR_API_TOKEN="your-api-token"
export LINEAR_DEFAULT_TEAM="TU"

# Load environment
direnv allow && eval "$(direnv export bash)"
```

## Quick Reference

### View Commands
```bash
linear tree                              # Show all commands
```

### Issues

```bash
# List
linear issues list --team TU
linear issues list --state "In Progress"

# Get details
linear issues get --id TU-123

# Create
linear issues create --title "Task name" --team TU

# Update state
linear issues assign state --issue TU-123 --to "In Progress"
linear issues assign state --issue TU-123 --to "Done"

# Add/remove labels
linear issues assign label --issue TU-123 --to "type-assignment"
linear issues unassign label --issue TU-123 --from "type-assignment"

# Comments
linear issues comment --id TU-123 --body "Progress update..."
linear issues comments --id TU-123

# Sub-issues
linear issues children --id TU-123

# Statistics
linear issues stats --team TU --since 7d
```

### Projects & Cycles
```bash
linear projects list
linear cycles list --team TU
```

## Important Notes

- **Use explicit flags** (not positional arguments)
- **Load environment first:** `direnv allow && eval "$(direnv export bash)"`

## Labels Convention

### Type Labels
- `type-assignment` - Course assignments
- `type-notes` - Lecture notes
- `type-project` - Programming projects
- `type-reading` - Reading assignments
- `type-exam` - Exam prep

### Course Labels
- `course-cosc417` - Theory of Computer Science

### LOE Labels
- `loe-xs` - Extra small (< 1 hour)
- `loe-small` - Small (1-4 hours)
- `loe-med` - Medium (1-2 days)
- `loe-large` - Large (3-5 days)
- `loe-xl` - Extra large (1+ week)

---
name: conventional-commit
description: Create a conventional commit with proper formatting
user-invocable: true
disable-model-invocation: true
argument-hint: "[optional commit message]"
allowed-tools: Bash(git:*)
---

# Conventional Commit Helper

Create a commit with proper formatting following the Conventional Commits specification.

## Prefixes

| Prefix | Use For |
|--------|---------|
| `feat:` | New functionality or feature |
| `fix:` | Bug fix |
| `docs:` | Documentation only changes |
| `chore:` | Maintenance (deps, config, cleanup) |
| `refactor:` | Code restructuring without behavior change |
| `test:` | Adding or updating tests |
| `style:` | Formatting, whitespace (no code change) |
| `perf:` | Performance improvements |
| `ci:` | CI/CD configuration changes |
| `build:` | Build system or dependencies |

## Format

```
<prefix>(<scope>): <description>

<body - optional>

Fixes: TU-XXX
```

## Scopes (Optional)

Scopes for this academic project:

- `assignment-N` - Specific assignment (e.g., `assignment-1`)
- `notes` - Lecture notes
- `latex` - LaTeX configuration/templates
- `java` - Java code
- `python` - Python code

## Steps

1. **Review staged changes:**
   ```bash
   git diff --staged
   ```

2. **Check you're NOT on main:**
   ```bash
   git branch --show-current
   ```

3. **Run pre-commit checks** (see `_shared/pre-commit-checks.md`)

4. **Write message with proper prefix**

5. **If fixing an issue, reference it:**
   ```
   Fixes: TU-123
   ```

6. **Commit using HEREDOC for multi-line messages**

## Examples

### Simple commit
```bash
git commit -m "docs: complete assignment 1 problems 1-3"
```

### With scope
```bash
git commit -m "docs(assignment-1): add pumping lemma proof"
```

### Multi-line with HEREDOC
```bash
git commit -m "$(cat <<'EOF'
feat(assignment-2): implement DFA minimization in Java

- Read DFA from input file
- Apply table-filling algorithm
- Output minimized DFA

Fixes: TU-123
EOF
)"
```

## Co-Author

When Claude helps write code, end commits with:
```
Co-Authored-By: Claude <noreply@anthropic.com>
```

## Specification Reference

See [conventionalcommits.org](https://www.conventionalcommits.org/) for the full specification.

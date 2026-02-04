# COSC 417 - Theory of Computer Science - Claude Context

Course assignments, notes, and projects for Introduction to Theory of Computer Science at Towson University.

---

## Critical Rules

| Rule | Details |
|------|---------|
| **Read before edit** | ALWAYS read existing code/documents before modifying |
| **Conventional commits** | Use `feat:`, `fix:`, `chore:`, `docs:` prefixes |
| **ISO-8601 dates** | Use ISO-8601 format for dates (YYYY-MM-DD) |
| **Compile before commit** | LaTeX documents must compile successfully |

---

## Project Overview

**Course:** COSC 417 - Introduction to Theory of Computer Science
**Semester:** Spring 2026
**Professor:** Marius Zimand
**Tech Stack:** LaTeX (Docker-based builds), Java, Python
**Repository Type:** Academic coursework (assignments, notes, projects)

### Course Topics

- Finite automata (DFA, NFA)
- Regular languages and expressions
- Context-free grammars
- Pushdown automata
- Turing machines
- Computability and decidability
- Complexity theory (P, NP)

---

## Project Structure

```
.
├── assignment-X/          # Assignment submissions
│   ├── main.tex          # LaTeX source
│   ├── main.pdf          # Compiled output
│   └── src/              # Code (if applicable)
├── resources/
│   └── assignment-template.tex
├── Dockerfile            # TeX Live 2025 image
├── docker-compose.yml
└── Makefile              # Build helpers
```

---

## LaTeX Workflow

### Building Documents

```bash
# Build with Docker
make build FILE=assignment-X/main.tex

# Watch for changes
make watch FILE=assignment-X/main.tex

# Clean auxiliary files
make clean
```

### VSCode Integration

- Save `.tex` file to trigger auto-build
- `Ctrl+Alt+V` / `Cmd+Alt+V` for PDF preview
- Double-click in PDF for SyncTeX jump

### Common LaTeX Patterns

For automata diagrams, use TikZ:
```latex
\begin{tikzpicture}[>=stealth',shorten >=1pt,auto,node distance=2.8cm]
  \node[initial, state] (q0) {$q_0$};
  \node[state, accepting] (q1) [right of=q0] {$q_1$};
  \path[->] (q0) edge node {a} (q1);
\end{tikzpicture}
```

For proofs, use amsthm:
```latex
\begin{proof}
  ...
  \qedhere
\end{proof}
```

---

## Issue Tracker Integration (Linear)

### Setup

```bash
# Install Linear CLI
go install github.com/jllovet/linear-cli@latest

# Environment variables
export LINEAR_API_TOKEN="your-token"
export LINEAR_DEFAULT_TEAM="TU"

# Load environment
direnv allow && eval "$(direnv export bash)"
```

### Common Commands

```bash
# List assignments/tasks
linear issues list --team TU

# Get issue details
linear issues get --id TU-123

# Update state
linear issues assign state --issue TU-123 --to "In Progress"
linear issues assign state --issue TU-123 --to "Done"

# Add comment
linear issues comment --id TU-123 --body "Completed problem 1-3"
```

### Labels

| Label | Use For |
|-------|---------|
| `type-assignment` | Course assignments |
| `type-notes` | Lecture notes |
| `type-project` | Programming projects |
| `course-cosc417` | This course |

---

## Autonomous Permissions

Claude may perform these operations **without asking**:

### Environment & Git (Safe)
| Action | Examples |
|--------|----------|
| **direnv** | `direnv allow`, `eval "$(direnv export bash)"` |
| **Fetch/Pull** | `git fetch`, `git pull` |
| **Status** | `git status`, `git log`, `git diff` |
| **Commit** | `git add`, `git commit` |
| **Push** | `git push` |

### LaTeX Operations
| Action | Examples |
|--------|----------|
| **Build** | `make build FILE=...` |
| **Clean** | `make clean` |

### Linear (Read/Update)
| Action | Examples |
|--------|----------|
| **Read** | List, get, search issues |
| **Update** | Update states, add comments |

**Requires confirmation:**
- Creating new Linear issues
- Deleting files
- Any destructive operations

---

## Development Patterns

### Commits

Use conventional commits:
- `docs:` - Assignment writeups, notes
- `feat:` - New code/solutions
- `fix:` - Corrections to solutions
- `chore:` - Build config, cleanup

Example:
```
docs(assignment-1): complete problems 1-4

Added proofs for DFA minimization and pumping lemma.

Co-Authored-By: Claude <noreply@anthropic.com>
```

### Pre-Commit Checks

See `skills/_shared/pre-commit-checks.md` for language-specific checks.

---

## Workflow Tips

### For Assignments

1. Create assignment directory: `assignment-X/`
2. Copy template: `cp resources/assignment-template.tex assignment-X/main.tex`
3. Update header (name, date, assignment number)
4. Build incrementally as you work
5. Update Linear issue when complete

### For Proofs

- State the claim clearly
- Use proper proof structure (direct, contradiction, induction)
- Reference theorems/lemmas by name
- End with QED symbol

### For Code

- Include clear comments
- Test with provided examples
- Document time/space complexity when relevant

---

## Core Principles

- **Correctness First**: Academic work requires rigorous correctness
- **Clear Explanations**: Show your work and reasoning
- **Proper Citations**: Reference sources appropriately

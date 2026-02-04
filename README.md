# Towson University - COSC 417 - Intro to Theory of Computer Science

This repository contains selections of my notes and projects for COSC-417, taken 2026-Spring.

Professor: Marius Zimand, who is the author of many of the resources provided.

## LaTeX Setup

This project uses Docker for LaTeX compilation, providing a consistent build environment without requiring a local TeX installation.

### Prerequisites

- [Docker](https://docs.docker.com/get-docker/)
- [VSCode](https://code.visualstudio.com/) with [LaTeX Workshop](https://marketplace.visualstudio.com/items?itemName=James-Yu.latex-workshop) extension (recommended for IDE integration)

### First-Time Setup

Build the Docker image:

```bash
make build-image
```

### Usage

#### VSCode (Recommended)

1. Open any `.tex` file
2. Save the file to trigger auto-build
3. Press `Ctrl+Alt+V` (Mac: `Cmd+Opt+V`) to open PDF preview
4. Double-click in the PDF to jump to the corresponding source line

#### Command Line

```bash
# Compile a .tex file
make build FILE=assignment-0/main.tex

# Watch for changes and auto-rebuild
make watch FILE=assignment-0/main.tex

# Clean auxiliary files
make clean
```

#### Direct Docker

```bash
docker compose run --rm latex path/to/file.tex
```

### Project Structure

```
.
├── assignment-0/          # Assignment submissions
├── resources/
│   └── assignment-template.tex  # LaTeX template with examples
├── Dockerfile             # TeX Live 2025 image
├── docker-compose.yml     # Container orchestration
└── Makefile               # Build helpers
```

### Template Features

The included template (`resources/assignment-template.tex`) demonstrates:

- Mathematical notation (equations, matrices, Greek letters)
- Tables
- TikZ diagrams (automata, graphs)
- Verbatim blocks for pseudocode
- Including PDFs and images

# Pre-Commit Checks

Run these checks **before** `git add` / `git commit`. Fix any issues before committing.

## Quick Reference

| Changed files in... | Run before committing |
|---------------------|----------------------|
| LaTeX (`.tex`) | `make build FILE=path/to/file.tex` |
| Java (`.java`) | `javac *.java` or IDE build |
| Python | `black . && isort . && flake8` |
| JSON/YAML config | Validate syntax |

## Language-Specific Checks

### LaTeX Documents

Run compilation to catch errors before committing:

```bash
# Using the project's Docker setup
make build FILE=assignment-X/main.tex

# Or direct Docker
docker compose run --rm latex path/to/file.tex
```

Common LaTeX errors to watch for:
- Missing `\end{}` tags
- Undefined control sequences
- Missing packages
- Math mode errors (unmatched `$`)
- BibTeX citation errors

### Java Code

```bash
# Compile to check for errors
javac -d out src/*.java

# If using Maven
mvn compile

# If using Gradle
./gradlew compileJava
```

### Python

```bash
black .
isort .
flake8
# Or using ruff:
ruff check --fix .
ruff format .
```

## General Rules

1. **Fix issues in files you modified** - Don't commit code that fails compilation
2. **LaTeX warnings are OK** - Overfull/underfull boxes can often be ignored
3. **Run tests if applicable** - `make test`, `mvn test`, `pytest`, etc.
4. **Check for secrets** - Never commit API keys, passwords, or credentials
5. **Verify PDF output** - For LaTeX, check the PDF looks correct

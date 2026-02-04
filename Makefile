.PHONY: build build-image clean watch help

# Default target
help:
	@echo "LaTeX Docker Build Commands:"
	@echo "  make build-image  - Build the Docker image (run once)"
	@echo "  make build FILE=path/to/file.tex  - Compile a .tex file"
	@echo "  make watch FILE=path/to/file.tex  - Watch and rebuild on changes"
	@echo "  make clean        - Remove auxiliary files"
	@echo ""
	@echo "Example:"
	@echo "  make build FILE=assignment-0/main.tex"

# Build the Docker image
build-image:
	docker compose build

# Compile a specific .tex file
build:
ifndef FILE
	$(error FILE is required. Usage: make build FILE=path/to/file.tex)
endif
	docker compose run --rm latex $(FILE)

# Watch for changes and rebuild
watch:
ifndef FILE
	$(error FILE is required. Usage: make watch FILE=path/to/file.tex)
endif
	docker compose run --rm latex -pvc -view=none $(FILE)

# Clean auxiliary files
clean:
	find . -type f \( \
		-name "*.aux" -o \
		-name "*.bbl" -o \
		-name "*.blg" -o \
		-name "*.log" -o \
		-name "*.out" -o \
		-name "*.toc" -o \
		-name "*.lof" -o \
		-name "*.lot" -o \
		-name "*.fls" -o \
		-name "*.fdb_latexmk" -o \
		-name "*.synctex.gz" -o \
		-name "*.nav" -o \
		-name "*.snm" \
	\) -delete

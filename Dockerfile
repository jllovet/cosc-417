FROM texlive/texlive:latest

# The texlive/texlive:latest image includes a full TeX Live installation
# with all packages needed for the assignment template (amsmath, tikz, etc.)

WORKDIR /workspace

# Default command: compile a .tex file passed as argument
ENTRYPOINT ["latexmk", "-pdf", "-interaction=nonstopmode", "-file-line-error"]
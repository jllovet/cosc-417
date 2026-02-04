FROM texlive/texlive:latest

# The texlive/texlive:latest image includes a full TeX Live installation
# with all packages needed for the assignment template (amsmath, tikz, etc.)

WORKDIR /workspace

# No fixed entrypoint - allows running any TeX tool (latexmk, latexindent, chktex, etc.)
# LaTeX Workshop will specify the command directly
package model;

public class Livro {

    private String titulo;
    private String autor;
    private String isbn;
    private String categoria;
    private int exemplares;
    private int disponiveis;

    public Livro(String titulo, String autor, String isbn, String categoria,
                 int exemplares, int disponiveis) {
        this.titulo = titulo;
        this.autor = autor;
        setIsbn(isbn);
        this.categoria = categoria;
        setExemplares(exemplares);
        setDisponiveis(disponiveis);
    }

    public String filtrarIsbn(String isbn) {
        if (isbn == null) {
            return null;
        }

        String isbnFiltrado = isbn.replaceAll("[^0-9]", "");

        if (isbnFiltrado.length() != 13) {
            return null;
        }

        return isbnFiltrado;
    }

    public boolean calcularIsbn(String isbn) {
        if (isbn == null || isbn.length() != 13) {
            return false;
        }

        int soma = 0;

        for (int i = 0; i < 12; i++) {
            int numero = Character.getNumericValue(isbn.charAt(i));

            if (i % 2 == 0) {
                soma += numero;
            } else {
                soma += numero * 3;
            }
        }

        int resto = (10 - (soma % 10)) % 10;
        return Character.getNumericValue(isbn.charAt(12)) == resto;
    }

    public boolean temDisponivel() {
        return disponiveis > 0;
    }

    public void emprestar() {
        if (temDisponivel()) {
            disponiveis--;
        }
    }

    public void devolver() {
        if (disponiveis < exemplares) {
            disponiveis++;
        }
    }

    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getIsbn() { return isbn; }
    public String getCategoria() { return categoria; }
    public int getExemplares() { return exemplares; }
    public int getDisponiveis() { return disponiveis; }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("TITULO INVALIDO");
        }
        this.titulo = titulo.trim();
    }

    public void setAutor(String autor) {
        if (autor == null || autor.isBlank()) {
            throw new IllegalArgumentException("AUTOR INVALIDO");
        }
        this.autor = autor.trim();
    }

    public void setCategoria(String categoria) {
        if (categoria == null || categoria.isBlank()) {
            throw new IllegalArgumentException("CATEGORIA INVALIDA");
        }
        this.categoria = categoria.trim();
    }

    public void setExemplares(int exemplares) {
        if (exemplares < 0) {
            throw new IllegalArgumentException("QUANTIDADE INVALIDA");
        }
        this.exemplares = exemplares;
    }

    public void setDisponiveis(int disponiveis) {
        if (disponiveis < 0 || disponiveis > exemplares) {
            throw new IllegalArgumentException("QUANTIDADE DE EXEMPLARES DISPONIVEIS INVALIDA");
        }
        this.disponiveis = disponiveis;
    }

    public void setIsbn(String isbn) {
        String isbnFiltrado = filtrarIsbn(isbn);

        if (!calcularIsbn(isbnFiltrado)) {
            throw new IllegalArgumentException("ISBN INVALIDO");
        }

        this.isbn = isbnFiltrado;
    }
}

package model;

public class Livro {

    private String titulo;
    private String autor;
    private String isbn;
    private String categoria;
    private int exemplares;
    private int disponiveis;

    public Livro(String titulo, String autor, String isbn, String categoria, int exemplares, int disponiveis) {
        this.titulo = titulo;
        this.autor = autor;
        setIsbn(isbn);
        this.categoria = categoria;
        this.exemplares = exemplares;
        this.disponiveis = disponiveis;
    }

    //Validar ISBN

    public String filtrarIsbn(String isbn) {
        isbn = isbn.replaceAll("[^0-9]", "");

        if (isbn.length() != 13) {
            return null;
        }

        return isbn;
    }

    public boolean calcularIsbn(String isbn) {
        int soma = 0;
        int resto = 0;

        for (int i = 0; i < 12; i++) {
            int numero = Character.getNumericValue(isbn.charAt(i));

            if (i % 2 == 0) {
                soma += numero;
            } else {
                soma += numero * 3;
            }

            resto = (10 - (soma % 10)) % 10;

        }

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

    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setAutor(String autor) { this.autor = autor; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setExemplares(int exemplares) { this.exemplares = exemplares; }
    public void setDisponiveis(int disponiveis) { this.disponiveis = disponiveis; }

    public void setIsbn(String isbn) {
        if (!calcularIsbn(isbn)) {
            throw new IllegalArgumentException("ISBN INVALIDO");
        }
        this.isbn = isbn;
    }
}
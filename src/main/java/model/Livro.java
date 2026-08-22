package model;

public class Livro {

    private String titulo;
    private String autor;
    private String isbn;
    private String categoria;
    private int exemplares;
    private int disponiveis;

    //Construtor
    public Livro(String titulo, String autor, String isbn, String categoria, int exemplares, int disponiveis) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.categoria = categoria;
        this.exemplares = exemplares;
        this.disponiveis = disponiveis;
    }

    //Metodos
    public boolean temDisponivel() {
        return disponiveis > 0;
    }

    public void emprestar() {
        if (temDisponivel()) {
            disponiveis--;
        }
    }

    public void devolver() {
        if (disponiveis + 1 <= exemplares) {
            disponiveis++;
        }
    }

    //Getters
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getExemplares() {
        return exemplares;
    }

    public int getDisponiveis() {
        return disponiveis;
    }

    //Setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setExemplares(int exemplares) {
        this.exemplares = exemplares;
    }

    public void setDisponiveis(int disponiveis) {
        this.disponiveis = disponiveis;
    }
}




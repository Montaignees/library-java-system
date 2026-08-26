package service;

import model.Livro;

import java.util.ArrayList;
import java.util.List;

public class LivroService {
    enum ResultadoOperacao {
        SUCESSO,
        LIVRO_NAO_ENCONTRADO,
        QUANTIDADE_INVALIDA
    }

    private List<Livro> livros = new ArrayList<>();

    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
    }

    public Livro buscarLivro(String isbn) {
        for (Livro livro : livros) {
            if (livro.getIsbn().equals(isbn)) {
                return livro;
            }
        }
        return null;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public ResultadoOperacao addExemplares(String isbn, int exemplares) {

        Livro livroAtual = buscarLivro(isbn);

        if (exemplares <= 0) {
            return ResultadoOperacao.QUANTIDADE_INVALIDA;
        }
        if (livroAtual == null) {
            return ResultadoOperacao.LIVRO_NAO_ENCONTRADO;
        }

        livroAtual.setExemplares(livroAtual.getExemplares() + exemplares);
        return ResultadoOperacao.SUCESSO;
    }

    public ResultadoOperacao alterarTitulo(String isbn, String titulo) {
        Livro livroAtual = buscarLivro(isbn);

        if (livroAtual == null) {
            return ResultadoOperacao.LIVRO_NAO_ENCONTRADO;
        }

        livroAtual.setTitulo(titulo);
        return ResultadoOperacao.SUCESSO;
    }

    public ResultadoOperacao alterarAutor(String isbn, String autor) {
        Livro livroAtual = buscarLivro(isbn);

        if (livroAtual == null) {
            return ResultadoOperacao.LIVRO_NAO_ENCONTRADO;
        }

        livroAtual.setAutor(autor);
        return ResultadoOperacao.SUCESSO;
    }

    public ResultadoOperacao alterarCategoria(String isbn, String categoria) {
        Livro livroAtual = buscarLivro(isbn);

        if (livroAtual == null) {
            return ResultadoOperacao.LIVRO_NAO_ENCONTRADO;
        }

        livroAtual.setCategoria(categoria);
        return ResultadoOperacao.SUCESSO;
    }


}
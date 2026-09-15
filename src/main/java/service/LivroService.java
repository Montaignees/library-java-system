package service;

import model.Livro;

import java.util.ArrayList;
import java.util.List;

public class LivroService {

    public enum ResultadoOperacao {
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

    public List<Livro> buscarPorTitulo(String titulo) {
        List<Livro> resultados = new ArrayList<>();

        for (Livro livro : livros) {
            if (livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                resultados.add(livro);
            }
        }

        return resultados;
    }

    public Livro selecionarLivro(List<Livro> livros, int indice) {
        if (livros == null || livros.isEmpty()) {
            return null;
        }

        if (indice < 0 || indice >= livros.size()) {
            return null;
        }

        return livros.get(indice);
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public ResultadoOperacao addExemplares(String isbn, int exemplares) {
        if (exemplares <= 0) {
            return ResultadoOperacao.QUANTIDADE_INVALIDA;
        }

        Livro livroAtual = buscarLivro(isbn);

        if (livroAtual == null) {
            return ResultadoOperacao.LIVRO_NAO_ENCONTRADO;
        }

        livroAtual.setExemplares(livroAtual.getExemplares() + exemplares);
        livroAtual.setDisponiveis(livroAtual.getDisponiveis() + exemplares);

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

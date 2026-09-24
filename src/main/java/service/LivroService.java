package service;

import model.Livro;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LivroService {

    private final List<Livro> livros = new ArrayList<>();

    public enum ResultadoOperacao {
        SUCESSO,
        LIVRO_NAO_ENCONTRADO,
        QUANTIDADE_INVALIDA
    }

    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
    }

    // Busca por ISBN é usada para identificar um livro de forma única.
    public Livro buscarPorIsbn(String isbn) {
        for (Livro livro : livros) {
            if (livro.getIsbn().equals(isbn)) {
                return livro;
            }
        }
        return null;
    }

    // Motor de busca usado pela interface: permite encontrar pelo título.
    public List<Livro> buscarPorTitulo(String titulo) {
        List<Livro> resultados = new ArrayList<>();
        String busca = titulo.toLowerCase(Locale.ROOT);

        for (Livro livro : livros) {
            if (livro.getTitulo().toLowerCase(Locale.ROOT).contains(busca)) {
                resultados.add(livro);
            }
        }

        return resultados;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public ResultadoOperacao addExemplares(String isbn, int quantidade) {
        if (quantidade <= 0) {
            return ResultadoOperacao.QUANTIDADE_INVALIDA;
        }

        Livro livro = buscarPorIsbn(isbn);

        if (livro == null) {
            return ResultadoOperacao.LIVRO_NAO_ENCONTRADO;
        }

        livro.setExemplares(livro.getExemplares() + quantidade);
        livro.setDisponiveis(livro.getDisponiveis() + quantidade);

        return ResultadoOperacao.SUCESSO;
    }

    public ResultadoOperacao alterarTitulo(String isbn, String titulo) {
        Livro livro = buscarPorIsbn(isbn);

        if (livro == null) {
            return ResultadoOperacao.LIVRO_NAO_ENCONTRADO;
        }

        livro.setTitulo(titulo);
        return ResultadoOperacao.SUCESSO;
    }

    public ResultadoOperacao alterarAutor(String isbn, String autor) {
        Livro livro = buscarPorIsbn(isbn);

        if (livro == null) {
            return ResultadoOperacao.LIVRO_NAO_ENCONTRADO;
        }

        livro.setAutor(autor);
        return ResultadoOperacao.SUCESSO;
    }

    public ResultadoOperacao alterarCategoria(String isbn, String categoria) {
        Livro livro = buscarPorIsbn(isbn);

        if (livro == null) {
            return ResultadoOperacao.LIVRO_NAO_ENCONTRADO;
        }

        livro.setCategoria(categoria);
        return ResultadoOperacao.SUCESSO;
    }
}

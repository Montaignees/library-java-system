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
        try {
            livros.add(livro);
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar livro: " + e.getMessage());
        }
    }

    // Busca por ISBN é usada para identificar um livro de forma única.
    public Livro buscarPorIsbn(String isbn) {
        try {
            for (Livro livro : livros) {
                if (livro.getIsbn().equals(isbn)) {
                    return livro;
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar livro: " + e.getMessage());
        }

        return null;
    }

    public List<Livro> buscarPorTitulo(String titulo) {
        List<Livro> resultados = new ArrayList<>();

        try {
            String busca = titulo.toLowerCase(Locale.ROOT);

            for (Livro livro : livros) {
                if (livro.getTitulo().toLowerCase(Locale.ROOT).contains(busca)) {
                    resultados.add(livro);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar por título: " + e.getMessage());
        }

        return resultados;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public ResultadoOperacao addExemplares(String isbn, int quantidade) {
        try {
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

        } catch (Exception e) {
            System.out.println("Erro ao adicionar exemplares: " + e.getMessage());
            return ResultadoOperacao.QUANTIDADE_INVALIDA;
        }
    }

    public ResultadoOperacao alterarTitulo(String isbn, String titulo) {
        try {
            Livro livro = buscarPorIsbn(isbn);

            if (livro == null) {
                return ResultadoOperacao.LIVRO_NAO_ENCONTRADO;
            }

            livro.setTitulo(titulo);
            return ResultadoOperacao.SUCESSO;

        } catch (Exception e) {
            System.out.println("Erro ao alterar título: " + e.getMessage());
            return ResultadoOperacao.LIVRO_NAO_ENCONTRADO;
        }
    }

    public ResultadoOperacao alterarAutor(String isbn, String autor) {
        try {
            Livro livro = buscarPorIsbn(isbn);

            if (livro == null) {
                return ResultadoOperacao.LIVRO_NAO_ENCONTRADO;
            }

            livro.setAutor(autor);
            return ResultadoOperacao.SUCESSO;

        } catch (Exception e) {
            System.out.println("Erro ao alterar autor: " + e.getMessage());
            return ResultadoOperacao.LIVRO_NAO_ENCONTRADO;
        }
    }

    public ResultadoOperacao alterarCategoria(String isbn, String categoria) {
        try {
            Livro livro = buscarPorIsbn(isbn);

            if (livro == null) {
                return ResultadoOperacao.LIVRO_NAO_ENCONTRADO;
            }

            livro.setCategoria(categoria);
            return ResultadoOperacao.SUCESSO;

        } catch (Exception e) {
            System.out.println("Erro ao alterar categoria: " + e.getMessage());
            return ResultadoOperacao.LIVRO_NAO_ENCONTRADO;
        }
    }
}
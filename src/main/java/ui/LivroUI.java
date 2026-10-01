package ui;

import model.Livro;
import service.LivroService;

import java.util.List;
import java.util.Scanner;

public class LivroUI {

    private Scanner scanner;
    private LivroService livroService;

    public LivroUI(Scanner scanner, LivroService livroService) {
        this.scanner = scanner;
        this.livroService = livroService;
    }

    public void cadastrarLivro() {
        UI.caixa("CADASTRAR LIVRO");

        String titulo = UI.entrada(scanner, "Título");
        String autor = UI.entrada(scanner, "Autor");
        String isbn = UI.entrada(scanner, "ISBN");
        String categoria = UI.entrada(scanner, "Categoria");
        int quantidade = UI.inteiro(scanner, "Quantidade");

        if (quantidade <= 0) {
            UI.mensagem("QUANTIDADE INVALIDA");
            return;
        }

        try {
            if (livroService.buscarPorIsbn(isbn) != null) {
                UI.mensagem("ISBN JA CADASTRADO");
                return;
            }

            livroService.cadastrarLivro(
                    new Livro(titulo, autor, isbn, categoria, quantidade, quantidade)
            );

            UI.mensagem("LIVRO CADASTRADO COM SUCESSO");
        } catch (IllegalArgumentException e) {
            UI.mensagem(e.getMessage());
        }
    }

    public void buscarLivro() {
        UI.caixa("BUSCAR LIVRO POR TITULO");

        String titulo = UI.entrada(scanner, "Título");
        List<Livro> livros = livroService.buscarPorTitulo(titulo);

        if (livros.isEmpty()) {
            UI.mensagem("LIVRO NAO ENCONTRADO");
            return;
        }

        Livro livro = selecionarLivro(livros);

        if (livro != null) {
            exibirLivro(livro);
        }
    }

    public void listarLivros() {
        List<Livro> livros = livroService.getLivros();

        if (livros.isEmpty()) {
            UI.caixa("LISTA DE LIVROS");
            UI.mensagem("NENHUM LIVRO CADASTRADO");
            return;
        }

        UI.caixa("LISTA DE LIVROS (" + livros.size() + ")");

        for (Livro livro : livros) {
            exibirLivro(livro);
        }
    }

    public void adicionarExemplares() {
        UI.caixa("ADICIONAR EXEMPLARES");

        String titulo = UI.entrada(scanner, "Título");
        List<Livro> livros = livroService.buscarPorTitulo(titulo);

        if (livros.isEmpty()) {
            UI.mensagem("LIVRO NAO ENCONTRADO");
            return;
        }

        Livro livro = selecionarLivro(livros);

        if (livro == null) {
            return;
        }

        int quantidade = UI.inteiro(scanner, "Quantidade");

        LivroService.ResultadoOperacao resultado =
                livroService.addExemplares(livro.getIsbn(), quantidade);

        switch (resultado) {
            case SUCESSO -> UI.mensagem("EXEMPLARES ADICIONADOS COM SUCESSO");
            case QUANTIDADE_INVALIDA -> UI.mensagem("QUANTIDADE INVALIDA");
            default -> UI.mensagem("LIVRO NAO ENCONTRADO");
        }
    }

    public Livro selecionarLivro(List<Livro> livros) {
        System.out.println("Selecione um livro:");

        for (int i = 0; i < livros.size(); i++) {
            System.out.println("[" + (i + 1) + "] "
                    + livros.get(i).getTitulo()
                    + " - " + livros.get(i).getIsbn());
        }

        int opcao = UI.inteiro(scanner, "Opção");

        if (opcao < 1 || opcao > livros.size()) {
            UI.mensagem("OPCAO INVALIDA");
            return null;
        }

        return livros.get(opcao - 1);
    }

    public void exibirLivro(Livro livro) {
        UI.caixa("LIVRO",
                "Título: " + livro.getTitulo(),
                "Autor: " + livro.getAutor(),
                "ISBN: " + livro.getIsbn(),
                "Categoria: " + livro.getCategoria(),
                "Exemplares: " + livro.getExemplares(),
                "Disponíveis: " + livro.getDisponiveis()
        );
    }
}

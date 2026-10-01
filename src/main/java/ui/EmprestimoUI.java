package ui;

import model.Emprestimo;
import model.Leitor;
import model.Livro;
import service.EmprestimoService;
import service.LeitorService;
import service.LivroService;
import service.MultaService;

import java.util.List;
import java.util.Scanner;

public class EmprestimoUI {

    private Scanner scanner;
    private EmprestimoService emprestimoService;
    private LivroService livroService;
    private LeitorService leitorService;
    private MultaService multaService;
    private LivroUI livroUI;
    private LeitorUI leitorUI;

    public EmprestimoUI(Scanner scanner,
                        EmprestimoService emprestimoService,
                        LivroService livroService,
                        LeitorService leitorService,
                        MultaService multaService) {
        this.scanner = scanner;
        this.emprestimoService = emprestimoService;
        this.livroService = livroService;
        this.leitorService = leitorService;
        this.multaService = multaService;
        this.livroUI = new LivroUI(scanner, livroService);
        this.leitorUI = new LeitorUI(scanner, leitorService, multaService);
    }

    public void realizarEmprestimo() {
        UI.caixa("REALIZAR EMPRESTIMO");

        String titulo = UI.entrada(scanner, "Título do livro");
        String nome = UI.entrada(scanner, "Nome do leitor");

        List<Livro> livros = livroService.buscarPorTitulo(titulo);
        List<Leitor> leitores = leitorService.buscarPorNome(nome);

        if (livros.isEmpty()) {
            UI.mensagem("LIVRO NAO ENCONTRADO");
            return;
        }

        if (leitores.isEmpty()) {
            UI.mensagem("LEITOR NAO ENCONTRADO");
            return;
        }

        Livro livro = livroUI.selecionarLivro(livros);
        Leitor leitor = leitorUI.selecionarLeitor(leitores);

        if (livro == null || leitor == null) {
            return;
        }

        if (multaService.calcularDivida(leitor.getCpf()) > 0) {
            UI.mensagem("LEITOR POSSUI MULTA PENDENTE");
            return;
        }

        int id = proximoId();

        EmprestimoService.ResultadoOperacao resultado =
                emprestimoService.cadastrarEmprestimo(id, livro, leitor);

        switch (resultado) {
            case SUCESSO ->
                    UI.caixa("EMPRESTIMO REALIZADO", "ID do empréstimo: " + id);
            case LIVRO_INDISPONIVEL ->
                    UI.mensagem("LIVRO INDISPONIVEL");
            case LIMITE_ATINGIDO ->
                    UI.mensagem("LIMITE DE 3 EMPRESTIMOS ATIVOS ATINGIDO");
            default ->
                    UI.mensagem("NAO FOI POSSIVEL REALIZAR O EMPRESTIMO");
        }
    }

    public void realizarDevolucao() {
        UI.caixa("REALIZAR DEVOLUCAO");

        int id = UI.inteiro(scanner, "ID do empréstimo");
        Emprestimo emprestimo = emprestimoService.buscarEmprestimo(id);

        if (emprestimo == null) {
            UI.mensagem("EMPRESTIMO NAO ENCONTRADO");
            return;
        }

        EmprestimoService.ResultadoOperacao resultado =
                emprestimoService.devolverEmprestimo(id);

        if (resultado != EmprestimoService.ResultadoOperacao.SUCESSO) {
            UI.mensagem("NAO FOI POSSIVEL REALIZAR A DEVOLUCAO");
            return;
        }

        if (emprestimo.estaAtrasado()) {
            multaService.criarMulta(emprestimo);
            UI.mensagem("DEVOLUCAO REALIZADA COM ATRASO");
        } else {
            UI.mensagem("DEVOLUCAO REALIZADA COM SUCESSO");
        }
    }

    private int proximoId() {
        int maiorId = 0;

        for (Emprestimo emprestimo : emprestimoService.getEmprestimos()) {
            if (emprestimo.getId() > maiorId) {
                maiorId = emprestimo.getId();
            }
        }

        return maiorId + 1;
    }
}

package ui;

import model.Leitor;
import service.LeitorService;
import service.MultaService;

import java.util.List;
import java.util.Scanner;

public class LeitorUI {

    private Scanner scanner;
    private LeitorService leitorService;
    private MultaService multaService;

    public LeitorUI(Scanner scanner, LeitorService leitorService,
                    MultaService multaService) {
        this.scanner = scanner;
        this.leitorService = leitorService;
        this.multaService = multaService;
    }

    public void cadastrarLeitor() {
        UI.caixa("CADASTRAR LEITOR");

        String nome = UI.entrada(scanner, "Nome");
        String telefone = UI.entrada(scanner, "Telefone");
        String email = UI.entrada(scanner, "E-mail");
        String cpf = UI.entrada(scanner, "CPF");

        try {
            if (leitorService.verificarDuplicidade(cpf, email, telefone) != null) {
                UI.mensagem("LEITOR JA CADASTRADO");
                return;
            }

            leitorService.cadastrarLeitor(
                    new Leitor(nome, telefone, email, cpf)
            );

            UI.mensagem("LEITOR CADASTRADO COM SUCESSO");
        } catch (IllegalArgumentException e) {
            UI.mensagem(e.getMessage());
        }
    }

    public void buscarLeitor() {
        UI.caixa("BUSCAR LEITOR POR NOME");

        String nome = UI.entrada(scanner, "Nome");
        List<Leitor> leitores = leitorService.buscarPorNome(nome);

        if (leitores.isEmpty()) {
            UI.mensagem("LEITOR NAO ENCONTRADO");
            return;
        }

        Leitor leitor = selecionarLeitor(leitores);

        if (leitor != null) {
            exibirLeitor(leitor);
        }
    }

    public Leitor selecionarLeitor(List<Leitor> leitores) {
        System.out.println("Selecione um leitor:");

        for (int i = 0; i < leitores.size(); i++) {
            System.out.println("[" + (i + 1) + "] "
                    + leitores.get(i).getNome()
                    + " - " + leitores.get(i).getCpf());
        }

        int opcao = UI.inteiro(scanner, "Opção");

        if (opcao < 1 || opcao > leitores.size()) {
            UI.mensagem("OPCAO INVALIDA");
            return null;
        }

        return leitores.get(opcao - 1);
    }

    public void exibirLeitor(Leitor leitor) {
        UI.caixa("DADOS DO LEITOR",
                "Nome: " + leitor.getNome(),
                "Telefone: " + leitor.getTelefone(),
                "E-mail: " + leitor.getEmail(),
                "CPF: " + leitor.getCpf(),
                String.format("Dívida: R$ %.2f",
                        multaService.calcularDivida(leitor.getCpf()))
        );
    }
}

package ui;

import model.Emprestimo;
import model.Multa;
import service.EmprestimoService;
import service.LeitorService;
import service.MultaService;

import java.util.List;
import java.util.Scanner;

public class MultaUI {

    private Scanner scanner;
    private MultaService multaService;
    private LeitorService leitorService;
    private EmprestimoService emprestimoService;

    public MultaUI(
            Scanner scanner,
            MultaService multaService,
            LeitorService leitorService,
            EmprestimoService emprestimoService
            ) {

        this.scanner = scanner;
        this.multaService = multaService;
        this.leitorService = leitorService;
        this.emprestimoService = emprestimoService;
    }

    public void consultarMultas() {
        UI.caixa("CONSULTAR MULTAS");

        String cpf = UI.entrada(scanner, "CPF");

        if (leitorService.buscarPorCpf(cpf) == null) {
            UI.mensagem("LEITOR NAO ENCONTRADO");
            return;
        }

        List<Multa> multas = multaService.consultarMultasDoLeitor(cpf);

        if (multas.isEmpty()) {
            UI.mensagem("NENHUMA MULTA ENCONTRADA");
            return;
        }

        for (Multa multa : multas) {
            Emprestimo emprestimo = multa.getEmprestimo();

            UI.caixa("MULTA",
                    "Empréstimo: " + emprestimo.getId(),
                    String.format("Valor: R$ %.2f", multa.getValor()),
                    "Data: " + multa.getDataMulta(),
                    "Status: " + (multa.isPago() ? "Paga" : "Pendente")
            );
        }

        UI.mensagem(String.format(
                "DIVIDA TOTAL: R$ %.2f",
                multaService.calcularDivida(cpf)
        ));
    }

    public void pagarMulta() {
        UI.caixa("PAGAR MULTA");

        int id = UI.inteiro(scanner, "ID do empréstimo");
        Emprestimo emprestimo = emprestimoService.buscarEmprestimo(id);

        if (emprestimo == null) {
            UI.mensagem("EMPRESTIMO NAO ENCONTRADO");
            return;
        }

        MultaService.ResultadoOperacao resultado =
                multaService.pagarMulta(emprestimo);

        switch (resultado) {
            case SUCESSO -> UI.mensagem("MULTA PAGA COM SUCESSO");
            case MULTA_JA_PAGA -> UI.mensagem("MULTA JA FOI PAGA");
            default -> UI.mensagem("MULTA NAO ENCONTRADA");
        }
    }
}

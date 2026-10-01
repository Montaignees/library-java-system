package ui;

import service.EmprestimoService;
import service.LeitorService;
import service.LivroService;
import service.MultaService;

import java.util.Scanner;

public class Menu {

    private Scanner scanner = new Scanner(System.in);
    private LivroUI livroUI;
    private LeitorUI leitorUI;
    private EmprestimoUI emprestimoUI;
    private MultaUI multaUI;

    public Menu(LivroService livroService,
                LeitorService leitorService,
                EmprestimoService emprestimoService,
                MultaService multaService) {

        this.livroUI = new LivroUI(scanner, livroService);
        this.leitorUI = new LeitorUI(scanner, leitorService, multaService);
        this.emprestimoUI = new EmprestimoUI(
                scanner,
                emprestimoService,
                livroService,
                leitorService,
                multaService
        );
        this.multaUI = new MultaUI(
                scanner,
                multaService,
                leitorService,
                emprestimoService
        );
    }

    public void iniciar() {
        String VERDE = "\u001B[32m";
        System.out.println(VERDE);

        int opcao;

        do {
            UI.caixa("SISTEMA DE BIBLIOTECA",
                    "[1]  Cadastrar livro",
                    "[2]  Buscar livro por título",
                    "[3]  Listar livros",
                    "[4]  Adicionar exemplares",
                    "[5]  Cadastrar leitor",
                    "[6]  Buscar leitor por nome",
                    "[7]  Realizar empréstimo",
                    "[8]  Realizar devolução",
                    "[9]  Consultar multas",
                    "[10] Registrar pagamento de multa",
                    "[0]  Sair"
            );

            opcao = UI.inteiro(scanner, "Opção");

            switch (opcao) {
                case 1 -> livroUI.cadastrarLivro();
                case 2 -> livroUI.buscarLivro();
                case 3 -> livroUI.listarLivros();
                case 4 -> livroUI.adicionarExemplares();
                case 5 -> leitorUI.cadastrarLeitor();
                case 6 -> leitorUI.buscarLeitor();
                case 7 -> emprestimoUI.realizarEmprestimo();
                case 8 -> emprestimoUI.realizarDevolucao();
                case 9 -> multaUI.consultarMultas();
                case 10 -> multaUI.pagarMulta();
                case 0 -> UI.mensagem("SISTEMA ENCERRADO");
                default -> UI.mensagem("OPCAO INVALIDA");
            }

        } while (opcao != 0);
    }
}

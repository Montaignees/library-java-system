package ui;

import model.Emprestimo;
import model.Leitor;
import model.Livro;
import model.Multa;
import service.EmprestimoService;
import service.LeitorService;
import service.LivroService;
import service.MultaService;

import java.util.List;
import java.util.Scanner;

public class Menu {

    private static final int LARGURA = 48;
    private static final String VERDE = "\u001B[32m";

    private final Scanner scanner = new Scanner(System.in);
    private final LivroService livroService;
    private final LeitorService leitorService;
    private final EmprestimoService emprestimoService;
    private final MultaService multaService;

    public Menu(LivroService livroService, LeitorService leitorService,
                EmprestimoService emprestimoService, MultaService multaService) {
        this.livroService = livroService;
        this.leitorService = leitorService;
        this.emprestimoService = emprestimoService;
        this.multaService = multaService;
    }

    public void iniciar() {
        System.out.println(VERDE);

        int opcao;

        do {
            caixa("SISTEMA DE BIBLIOTECA",
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

            System.out.print("Opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarLivro();
                case 2 -> buscarLivro();
                case 3 -> listarLivros();
                case 4 -> adicionarExemplares();
                case 5 -> cadastrarLeitor();
                case 6 -> buscarLeitor();
                case 7 -> realizarEmprestimo();
                case 8 -> realizarDevolucao();
                case 9 -> consultarMultas();
                case 10 -> pagarMulta();
                case 0 -> mensagem("SISTEMA ENCERRADO");
                default -> mensagem("OPÇÃO INVÁLIDA");
            }
        } while (opcao != 0);
    }

    private void cadastrarLivro() {
        cabecalho("CADASTRAR LIVRO");

        String titulo = entrada("Título");
        String autor = entrada("Autor");
        String isbn = entrada("ISBN");
        String categoria = entrada("Categoria");

        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        if (quantidade <= 0) {
            mensagem("QUANTIDADE INVÁLIDA");
            return;
        }

        if (livroService.buscarPorIsbn(isbn) != null) {
            mensagem("ISBN JÁ CADASTRADO");
            return;
        }

        livroService.cadastrarLivro(
                new Livro(titulo, autor, isbn, categoria, quantidade, quantidade)
        );

        mensagem("LIVRO CADASTRADO COM SUCESSO");
    }

    private void buscarLivro() {
        cabecalho("BUSCAR LIVRO POR TÍTULO");

        String titulo = entrada("Título");
        List<Livro> livros = livroService.buscarPorTitulo(titulo);

        if (livros.isEmpty()) {
            mensagem("LIVRO NÃO ENCONTRADO");
            return;
        }

        Livro livro = selecionarLivro(livros);

        if (livro != null) {
            exibirLivro(livro);
        }
    }

    private void listarLivros() {
        List<Livro> livros = livroService.getLivros();

        if (livros.isEmpty()) {
            cabecalho("LISTA DE LIVROS");
            mensagem("NENHUM LIVRO CADASTRADO");
            return;
        }

        cabecalho("LISTA DE LIVROS (" + livros.size() + ")");

        for (Livro livro : livros) {
            exibirLivro(livro);
        }
    }

    private void adicionarExemplares() {
        cabecalho("ADICIONAR EXEMPLARES");

        String titulo = entrada("Título");
        List<Livro> livros = livroService.buscarPorTitulo(titulo);

        if (livros.isEmpty()) {
            mensagem("LIVRO NÃO ENCONTRADO");
            return;
        }

        Livro livro = selecionarLivro(livros);

        if (livro == null) {
            return;
        }

        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        LivroService.ResultadoOperacao resultado =
                livroService.addExemplares(livro.getIsbn(), quantidade);

        switch (resultado) {
            case SUCESSO -> mensagem("EXEMPLARES ADICIONADOS COM SUCESSO");
            case QUANTIDADE_INVALIDA -> mensagem("QUANTIDADE INVÁLIDA");
            default -> mensagem("LIVRO NÃO ENCONTRADO");
        }
    }

    private void cadastrarLeitor() {
        cabecalho("CADASTRAR LEITOR");

        String nome = entrada("Nome");
        String telefone = entrada("Telefone");
        String email = entrada("E-mail");
        String cpf = entrada("CPF");

        if (leitorService.verificarDuplicidade(cpf, email, telefone) != null) {
            mensagem("LEITOR JÁ CADASTRADO");
            return;
        }

        leitorService.cadastrarLeitor(
                new Leitor(nome, telefone, email, cpf)
        );

        mensagem("LEITOR CADASTRADO COM SUCESSO");
    }

    private void buscarLeitor() {
        cabecalho("BUSCAR LEITOR POR NOME");

        String nome = entrada("Nome");
        List<Leitor> leitores = leitorService.buscarPorNome(nome);

        if (leitores.isEmpty()) {
            mensagem("LEITOR NÃO ENCONTRADO");
            return;
        }

        Leitor leitor = selecionarLeitor(leitores);

        if (leitor != null) {
            exibirLeitor(leitor);
        }
    }

    private void realizarEmprestimo() {
        cabecalho("REALIZAR EMPRÉSTIMO");

        String titulo = entrada("Título do livro");
        String nome = entrada("Nome do leitor");

        List<Livro> livros = livroService.buscarPorTitulo(titulo);
        List<Leitor> leitores = leitorService.buscarPorNome(nome);

        if (livros.isEmpty()) {
            mensagem("LIVRO NÃO ENCONTRADO");
            return;
        }

        if (leitores.isEmpty()) {
            mensagem("LEITOR NÃO ENCONTRADO");
            return;
        }

        Livro livro = selecionarLivro(livros);
        Leitor leitor = selecionarLeitor(leitores);

        if (livro == null || leitor == null) {
            return;
        }

        if (multaService.calcularDivida(leitor.getCpf()) > 0) {
            mensagem("LEITOR POSSUI MULTA PENDENTE");
            return;
        }

        int id = proximoId();

        EmprestimoService.ResultadoOperacao resultado =
                emprestimoService.cadastrarEmprestimo(id, livro, leitor);

        switch (resultado) {
            case SUCESSO -> caixa("EMPRÉSTIMO REALIZADO", "ID do empréstimo: " + id);
            case LIVRO_INDISPONIVEL -> mensagem("LIVRO INDISPONÍVEL");
            case LIMITE_ATINGIDO -> mensagem("LIMITE DE 3 EMPRÉSTIMOS ATIVOS ATINGIDO");
            default -> mensagem("NÃO FOI POSSÍVEL REALIZAR O EMPRÉSTIMO");
        }
    }

    private void realizarDevolucao() {
        cabecalho("REALIZAR DEVOLUÇÃO");

        System.out.print("ID do empréstimo: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Emprestimo emprestimo = emprestimoService.buscarEmprestimo(id);

        if (emprestimo == null) {
            mensagem("EMPRÉSTIMO NÃO ENCONTRADO");
            return;
        }

        EmprestimoService.ResultadoOperacao resultado =
                emprestimoService.devolverEmprestimo(id);

        if (resultado != EmprestimoService.ResultadoOperacao.SUCESSO) {
            mensagem("NÃO FOI POSSÍVEL REALIZAR A DEVOLUÇÃO");
            return;
        }

        if (emprestimo.estaAtrasado()) {
            multaService.criarMulta(emprestimo);
            Multa multa = multaService.buscarMulta(emprestimo);

            caixa("DEVOLUÇÃO REALIZADA COM ATRASO",
                    String.format("Multa: R$ %.2f", multa.getValor()));
        } else {
            mensagem("DEVOLUÇÃO REALIZADA COM SUCESSO");
        }
    }

    private void consultarMultas() {
        cabecalho("CONSULTAR MULTAS");

        String cpf = entrada("CPF");

        if (leitorService.buscarPorCpf(cpf) == null) {
            mensagem("LEITOR NÃO ENCONTRADO");
            return;
        }

        List<Multa> multas = multaService.consultarMultasDoLeitor(cpf);

        if (multas.isEmpty()) {
            mensagem("NENHUMA MULTA ENCONTRADA");
            return;
        }

        for (Multa multa : multas) {
            Emprestimo emprestimo = multa.getEmprestimo();

            caixa("MULTA",
                    "Empréstimo: " + emprestimo.getId(),
                    String.format("Valor: R$ %.2f", multa.getValor()),
                    "Data: " + multa.getDataMulta(),
                    "Status: " + (multa.isPago() ? "Paga" : "Pendente")
            );
        }

        System.out.printf(
                "%nDívida total: R$ %.2f%n%n",
                multaService.calcularDivida(cpf)
        );
    }

    private void pagarMulta() {
        cabecalho("PAGAR MULTA");

        System.out.print("ID do empréstimo: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Emprestimo emprestimo = emprestimoService.buscarEmprestimo(id);

        if (emprestimo == null) {
            mensagem("EMPRÉSTIMO NÃO ENCONTRADO");
            return;
        }

        MultaService.ResultadoOperacao resultado =
                multaService.pagarMulta(emprestimo);

        switch (resultado) {
            case SUCESSO -> mensagem("MULTA PAGA COM SUCESSO");
            case MULTA_JA_PAGA -> mensagem("MULTA JÁ FOI PAGA");
            default -> mensagem("MULTA NÃO ENCONTRADA");
        }
    }

    private Livro selecionarLivro(List<Livro> livros) {
        System.out.println("Selecione um livro:");

        for (int i = 0; i < livros.size(); i++) {
            System.out.println("[" + (i + 1) + "] "
                    + livros.get(i).getTitulo()
                    + " - " + livros.get(i).getIsbn());
        }

        System.out.print("Opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao < 1 || opcao > livros.size()) {
            mensagem("OPÇÃO INVÁLIDA");
            return null;
        }

        return livros.get(opcao - 1);
    }

    private Leitor selecionarLeitor(List<Leitor> leitores) {
        System.out.println("Selecione um leitor:");

        for (int i = 0; i < leitores.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + leitores.get(i).getNome()
                    + " - " + leitores.get(i).getCpf());
        }

        System.out.print("Opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao < 1 || opcao > leitores.size()) {
            mensagem("OPÇÃO INVÁLIDA");
            return null;
        }

        return leitores.get(opcao - 1);
    }

    private void exibirLivro(Livro livro) {
        caixa("LIVRO",
                "Título: " + livro.getTitulo(),
                "Autor: " + livro.getAutor(),
                "ISBN: " + livro.getIsbn(),
                "Categoria: " + livro.getCategoria(),
                "Exemplares: " + livro.getExemplares(),
                "Disponíveis: " + livro.getDisponiveis()
        );
    }

    private void exibirLeitor(Leitor leitor) {
        caixa("DADOS DO LEITOR",
                "Nome: " + leitor.getNome(),
                "Telefone: " + leitor.getTelefone(),
                "E-mail: " + leitor.getEmail(),
                "CPF: " + leitor.getCpf(),
                String.format("Dívida: R$ %.2f",
                        multaService.calcularDivida(leitor.getCpf()))
        );
    }

    private String entrada(String campo) {
        System.out.print(campo + ": ");
        return scanner.nextLine();
    }

    private void cabecalho(String titulo) {
        caixa(titulo);
    }

    private void mensagem(String texto) {
        caixa(texto);
    }

    private void caixa(String titulo, String... linhas) {
        System.out.println();
        System.out.println("┌" + "─".repeat(LARGURA) + "┐");
        System.out.println("│" + centralizar(titulo) + "│");

        if (linhas.length > 0) {
            System.out.println("├" + "─".repeat(LARGURA) + "┤");

            for (String linha : linhas) {
                System.out.println("│" + formatarLinha(linha) + "│");
            }
        }

        System.out.println("└" + "─".repeat(LARGURA) + "┘");
    }

    private String centralizar(String texto) {
        if (texto.length() >= LARGURA) {
            return texto.substring(0, LARGURA);
        }

        int espacoTotal = LARGURA - texto.length();
        int esquerda = espacoTotal / 2;
        int direita = espacoTotal - esquerda;

        return " ".repeat(esquerda) + texto + " ".repeat(direita);
    }

    private String formatarLinha(String texto) {
        String conteudo = " " + texto;

        if (conteudo.length() >= LARGURA) {
            return conteudo.substring(0, LARGURA - 1) + " ";
        }

        return conteudo + " ".repeat(LARGURA - conteudo.length());
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

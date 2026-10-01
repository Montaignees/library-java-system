package ui;

import java.util.Scanner;

public class UI {

    public int LARGURA = 48;

    private UI() {
    }

    public static String entrada(Scanner scanner, String campo) {
        System.out.print(campo + ": ");
        return scanner.nextLine().trim();
    }

    public static int inteiro(Scanner scanner, String campo) {
        while (true) {
            try {
                System.out.print(campo + ": ");
                int valor = scanner.nextInt();
                scanner.nextLine();
                return valor;
            } catch (Exception e) {
                System.out.println("VALOR INVALIDO");
                scanner.nextLine();
            }
        }
    }

    public static void caixa(String titulo, String... linhas) {
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

    public static void mensagem(String texto) {
        caixa(texto);
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
}

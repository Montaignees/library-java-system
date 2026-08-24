package model;

import java.time.LocalDate;

public class Emprestimo {

    private Leitor leitor;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataLimite;
    private LocalDate dataDevolucao;

    public Emprestimo(Leitor leitor, Livro livro) {
        this.leitor = leitor;
        this.livro = livro;
        this.dataEmprestimo = LocalDate.now();
        this.dataLimite = dataEmprestimo.plusDays(28);
        this.dataDevolucao = null;
    }

    public void devolver() {
        this.dataDevolucao = LocalDate.now();
    }

    public boolean estaAtrasado() {
        if (dataDevolucao != null) {
            return dataDevolucao.isAfter(dataLimite);
        }

        return LocalDate.now().isAfter(dataLimite);
    }

    // Getters

    public Leitor getLeitor() {
        return leitor;
    }

    public Livro getLivro() {
        return livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataLimite() {
        return dataLimite;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }
}
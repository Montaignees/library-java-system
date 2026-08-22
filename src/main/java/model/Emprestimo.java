package model;

import java.time.LocalDate;

public class Emprestimo {

    private int leitor;
    private LocalDate dataEmprestimo;
    private LocalDate dataLimite;
    private LocalDate dataDevolucao;

    public Emprestimo(int leitor) {
        this.leitor = leitor;
        this.dataEmprestimo = LocalDate.now();
        this.dataLimite = dataEmprestimo.plusDays(28);
        this.dataDevolucao = null;


    }

    public void devolver() {
        dataDevolucao = LocalDate.now();
    }

    public boolean estaAtrasado() {
        if (dataDevolucao != null) {
            return dataDevolucao.isAfter(dataLimite);
        }

        return LocalDate.now().isAfter(dataLimite);
    }

    //Getters
    public int getLeitor() {
        return leitor;
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

    //Setters

    public void setLeitor(int leitor) {
        this.leitor = leitor;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public void setDataLimite(LocalDate dataLimite) {
        this.dataLimite = dataLimite;
    }


}
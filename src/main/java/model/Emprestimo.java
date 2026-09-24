package model;

import java.time.LocalDate;

public class Emprestimo {

    private int id;
    private Leitor leitor;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataLimite;
    private LocalDate dataDevolucao;
    private boolean ativo;

    public Emprestimo(Leitor leitor, Livro livro, int id) {
        this.id = id;
        this.leitor = leitor;
        this.livro = livro;
        this.dataEmprestimo = LocalDate.now();
        this.dataLimite = dataEmprestimo.plusDays(28);
        this.ativo = true;
    }

    public void devolver() {
        if (ativo) {
            dataDevolucao = LocalDate.now();
            ativo = false;
        }
    }

    public boolean estaAtrasado() {
        LocalDate dataReferencia = dataDevolucao != null ? dataDevolucao : LocalDate.now();
        return dataReferencia.isAfter(dataLimite);
    }

    public int getId() { return id; }
    public Leitor getLeitor() { return leitor; }
    public Livro getLivro() { return livro; }
    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public LocalDate getDataLimite() { return dataLimite; }
    public LocalDate getDataDevolucao() { return dataDevolucao; }
    public boolean isAtivo() { return ativo; }
}

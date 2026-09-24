package model;

import java.time.LocalDate;

public class Multa {

    private double valor;
    private LocalDate dataMulta;
    private boolean pago;
    private Emprestimo emprestimo;

    public Multa(double valor, LocalDate dataMulta, boolean pago, Emprestimo emprestimo) {
        this.valor = valor;
        this.dataMulta = dataMulta;
        this.pago = pago;
        this.emprestimo = emprestimo;
    }

    public void pagar() {
        pago = true;
    }

    public double getValor() { return valor; }
    public LocalDate getDataMulta() { return dataMulta; }
    public boolean isPago() { return pago; }
    public Emprestimo getEmprestimo() { return emprestimo; }

}

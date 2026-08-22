package model;
import java.time.LocalDate;

public class Multa {
    private double valor;
    private LocalDate dataMulta;
    private boolean pago;

    //Construtor
    public Multa(double valor, LocalDate dataMulta, boolean pago) {
        this.valor = valor;
        this.dataMulta = LocalDate.now();
        this.pago = pago;
    }

    //métodos

    public void pagar() {
        pago = true;
    }


    //Getters
    public double getValor() {
        return valor;
    }

    public LocalDate getDataMulta() {
        return dataMulta;
    }

    public boolean isPago() {
        return pago;
    }

    //Setters

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setDataMulta(LocalDate dataMulta) {
        this.dataMulta = dataMulta;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }
}

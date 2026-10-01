package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class RegistroPonto {

    private LocalDate data;
    private LocalTime entrada;
    private LocalTime saida;

    public RegistroPonto() {
        data = LocalDate.now();
        entrada = LocalTime.now();
    }

    public void registrarSaida() {
        if (saida == null) {
            saida = LocalTime.now();
        }
    }

    public LocalTime getEntrada() { return entrada; }
    public LocalTime getSaida() { return saida; }
    public LocalDate getData() { return data; }
}

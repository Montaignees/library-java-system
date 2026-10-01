package service;

import model.Emprestimo;
import model.Multa;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class MultaService {

    private double VALOR_POR_DIA = 1.00;
    private List<Multa> multas = new ArrayList<>();

    public enum ResultadoOperacao {
        SUCESSO,
        EMPRESTIMO_NAO_ENCONTRADO,
        EMPRESTIMO_NAO_ATRASADO,
        MULTA_NAO_ENCONTRADA,
        MULTA_JA_PAGA
    }

    public ResultadoOperacao criarMulta(Emprestimo emprestimo) {
        if (emprestimo == null) {
            return ResultadoOperacao.EMPRESTIMO_NAO_ENCONTRADO;
        }

        if (!emprestimo.estaAtrasado()) {
            return ResultadoOperacao.EMPRESTIMO_NAO_ATRASADO;
        }

        if (buscarMulta(emprestimo) != null) {
            return ResultadoOperacao.SUCESSO;
        }

        long diasAtraso = calcularDiasAtraso(emprestimo);
        double valor = diasAtraso * VALOR_POR_DIA;

        multas.add(new Multa(
                valor,
                LocalDate.now(),
                false,
                emprestimo
        ));

        return ResultadoOperacao.SUCESSO;
    }

    private long calcularDiasAtraso(Emprestimo emprestimo) {
        LocalDate dataFinal = emprestimo.getDataDevolucao();

        if (dataFinal == null) {
            dataFinal = LocalDate.now();
        }

        return ChronoUnit.DAYS.between(
                emprestimo.getDataLimite(),
                dataFinal
        );
    }

    public Multa buscarMulta(Emprestimo emprestimo) {
        if (emprestimo == null) {
            return null;
        }

        for (Multa multa : multas) {
            if (multa.getEmprestimo() == emprestimo) {
                return multa;
            }
        }

        return null;
    }

    public List<Multa> consultarMultasDoLeitor(String cpf) {
        List<Multa> resultados = new ArrayList<>();

        if (cpf == null) {
            return resultados;
        }

        String cpfFiltrado = cpf.replaceAll("[^0-9]", "");

        for (Multa multa : multas) {
            if (multa.getEmprestimo().getLeitor().getCpf().equals(cpfFiltrado)) {
                resultados.add(multa);
            }
        }

        return resultados;
    }

    public ResultadoOperacao pagarMulta(Emprestimo emprestimo) {
        Multa multa = buscarMulta(emprestimo);

        if (multa == null) {
            return ResultadoOperacao.MULTA_NAO_ENCONTRADA;
        }

        if (multa.isPago()) {
            return ResultadoOperacao.MULTA_JA_PAGA;
        }

        multa.pagar();
        return ResultadoOperacao.SUCESSO;
    }

    public double calcularDivida(String cpf) {
        double divida = 0;

        if (cpf == null) {
            return divida;
        }

        String cpfFiltrado = cpf.replaceAll("[^0-9]", "");

        for (Multa multa : multas) {
            if (multa.getEmprestimo().getLeitor().getCpf().equals(cpfFiltrado)
                    && !multa.isPago()) {
                divida += multa.getValor();
            }
        }

        return divida;
    }

    public List<Multa> getMultas() {
        return multas;
    }
}

package service;

import model.Emprestimo;
import model.Multa;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class MultaService {

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

        if (diasAtraso <= 0) {
            return ResultadoOperacao.EMPRESTIMO_NAO_ATRASADO;
        }

        Multa multa = new Multa(
                diasAtraso * 1.0,
                LocalDate.now(),
                false,
                emprestimo
        );

        multas.add(multa);

        return ResultadoOperacao.SUCESSO;
    }

    public long calcularDiasAtraso(Emprestimo emprestimo) {
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
        for (Multa multa : multas) {
            if (multa.getEmprestimo() == emprestimo) {
                return multa;
            }
        }

        return null;
    }

    public List<Multa> consultarMultas() {
        return multas;
    }

    public List<Multa> consultarMultasDoLeitor(String cpf) {
        List<Multa> multasLeitor = new ArrayList<>();

        for (Multa multa : multas) {
            if (multa.getEmprestimo().getLeitor().getCpf().equals(cpf)) {
                multasLeitor.add(multa);
            }
        }

        return multasLeitor;
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

        for (Multa multa : multas) {
            if (multa.getEmprestimo().getLeitor().getCpf().equals(cpf)
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

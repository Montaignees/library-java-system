package service;

import model.Emprestimo;
import model.Multa;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class MultaService {

    private final List<Multa> multas = new ArrayList<>();

    public enum ResultadoOperacao {
        SUCESSO,
        EMPRESTIMO_NAO_ENCONTRADO,
        EMPRESTIMO_NAO_ATRASADO,
        MULTA_NAO_ENCONTRADA,
        MULTA_JA_PAGA
    }

    public ResultadoOperacao criarMulta(Emprestimo emprestimo) {
        try {
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

            multas.add(new Multa(
                    diasAtraso,
                    LocalDate.now(),
                    false,
                    emprestimo
            ));

            return ResultadoOperacao.SUCESSO;

        } catch (Exception e) {
            System.out.println("Erro ao criar multa: " + e.getMessage());
            return ResultadoOperacao.EMPRESTIMO_NAO_ENCONTRADO;
        }
    }

    private long calcularDiasAtraso(Emprestimo emprestimo) {
        try {
            LocalDate dataFinal = emprestimo.getDataDevolucao();

            if (dataFinal == null) {
                dataFinal = LocalDate.now();
            }

            return ChronoUnit.DAYS.between(emprestimo.getDataLimite(), dataFinal);

        } catch (Exception e) {
            System.out.println("Erro ao calcular dias de atraso: " + e.getMessage());
            return 0;
        }
    }

    public Multa buscarMulta(Emprestimo emprestimo) {
        try {
            for (Multa multa : multas) {
                if (multa.getEmprestimo() == emprestimo) {
                    return multa;
                }
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar multa: " + e.getMessage());
        }

        return null;
    }

    public List<Multa> consultarMultasDoLeitor(String cpf) {
        List<Multa> resultados = new ArrayList<>();

        try {
            for (Multa multa : multas) {
                if (multa.getEmprestimo().getLeitor().getCpf().equals(cpf)) {
                    resultados.add(multa);
                }
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar multas: " + e.getMessage());
        }

        return resultados;
    }

    public ResultadoOperacao pagarMulta(Emprestimo emprestimo) {
        try {
            Multa multa = buscarMulta(emprestimo);

            if (multa == null) {
                return ResultadoOperacao.MULTA_NAO_ENCONTRADA;
            }

            if (multa.isPago()) {
                return ResultadoOperacao.MULTA_JA_PAGA;
            }

            multa.pagar();
            return ResultadoOperacao.SUCESSO;

        } catch (Exception e) {
            System.out.println("Erro ao pagar multa: " + e.getMessage());
            return ResultadoOperacao.MULTA_NAO_ENCONTRADA;
        }
    }

    public double calcularDivida(String cpf) {
        double divida = 0;

        try {
            for (Multa multa : multas) {
                if (multa.getEmprestimo().getLeitor().getCpf().equals(cpf)
                        && !multa.isPago()) {
                    divida += multa.getValor();
                }
            }

        } catch (Exception e) {
            System.out.println("Erro ao calcular dívida: " + e.getMessage());
        }

        return divida;
    }

    public List<Multa> getMultas() {
        return multas;
    }
}
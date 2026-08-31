package service;

import model.Emprestimo;
import model.Leitor;
import model.Livro;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class EmprestimoService {

    private List<Emprestimo> emprestimos = new ArrayList<>();

    public enum ResultadoOperacao {
        SUCESSO,
        LIVRO_NAO_ENCONTRADO,
        LEITOR_NAO_ENCONTRADO,
        LIVRO_INDISPONIVEL,
        LIMITE_ATINGIDO,
        LEITOR_BLOQUEADO,
        EMPRESTIMO_NAO_ENCONTRADO,
        EMPRESTIMO_JA_DEVOLVIDO
    }


    public List<Emprestimo> emprestimosAtivos(String cpf) {

        List<Emprestimo> ativos = new ArrayList<>();

        for (Emprestimo emprestimo : emprestimos) {

            if (emprestimo.getLeitor().getCpf().equals(cpf)
                    && emprestimo.isAtivo()) {

                ativos.add(emprestimo);
            }
        }

        return ativos;
    }

    public ResultadoOperacao estaDentroDoLimite(String cpf) {
        List ativos = emprestimosAtivos(cpf);

        if (ativos.size() < 3) {
            return ResultadoOperacao.SUCESSO;
        }
        return ResultadoOperacao.LIMITE_ATINGIDO;
    }

    public ResultadoOperacao cadastrarEmprestimo(int proximoId, Livro livro, Leitor leitor) {
        if (livro == null) {
            return ResultadoOperacao.LIVRO_NAO_ENCONTRADO;
        }

        if (leitor == null) {
            return ResultadoOperacao.LEITOR_NAO_ENCONTRADO;
        }

        if (livro.getDisponiveis() <= 0) {
            return ResultadoOperacao.LIVRO_INDISPONIVEL;
        }

        if (estaDentroDoLimite(leitor.getCpf()) != ResultadoOperacao.SUCESSO) {
            return ResultadoOperacao.LIMITE_ATINGIDO;
        }

        Emprestimo emprestimo = new Emprestimo(leitor, livro, proximoId);
        emprestimos.add(emprestimo);

        livro.setDisponiveis(livro.getDisponiveis() - 1);

        proximoId++;

        return ResultadoOperacao.SUCESSO;
    }

    //Finalizar emprestimo()
}
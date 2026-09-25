package service;

import model.Emprestimo;
import model.Leitor;
import model.Livro;
import model.Multa;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class EmprestimoService {

    private final List<Emprestimo> emprestimos = new ArrayList<>();

    public enum ResultadoOperacao {
        SUCESSO,
        LIVRO_NAO_ENCONTRADO,
        LEITOR_NAO_ENCONTRADO,
        LIVRO_INDISPONIVEL,
        LIMITE_ATINGIDO,
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

    public ResultadoOperacao cadastrarEmprestimo(int id, Livro livro, Leitor leitor) {
        try {
            if (livro == null) {
                return ResultadoOperacao.LIVRO_NAO_ENCONTRADO;
            }

            if (leitor == null) {
                return ResultadoOperacao.LEITOR_NAO_ENCONTRADO;
            }

            if (!livro.temDisponivel()) {
                return ResultadoOperacao.LIVRO_INDISPONIVEL;
            }

            if (emprestimosAtivos(leitor.getCpf()).size() >= 3) {
                return ResultadoOperacao.LIMITE_ATINGIDO;
            }

            Emprestimo emprestimo = new Emprestimo(leitor, livro, id);
            emprestimos.add(emprestimo);
            livro.emprestar();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultadoOperacao.SUCESSO;
    }

    public ResultadoOperacao devolverEmprestimo(int id) {
        Emprestimo emprestimo = buscarEmprestimo(id);
        try {
            if (emprestimo == null) {
                return ResultadoOperacao.EMPRESTIMO_NAO_ENCONTRADO;
            }

            if (!emprestimo.isAtivo()) {
                return ResultadoOperacao.EMPRESTIMO_JA_DEVOLVIDO;
            }

            emprestimo.devolver();
            emprestimo.getLivro().devolver();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultadoOperacao.SUCESSO;
    }

    public Emprestimo buscarEmprestimo(int id) {
        try {
            for (Emprestimo emprestimo : emprestimos) {
                if (emprestimo.getId() == id) {
                    return emprestimo;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }
}
package service;

import model.Emprestimo;
import model.Leitor;
import model.Livro;

import java.util.ArrayList;
import java.util.List;

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
        if (emprestimosAtivos(cpf).size() < 3) {
            return ResultadoOperacao.SUCESSO;
        }

        return ResultadoOperacao.LIMITE_ATINGIDO;
    }

    public ResultadoOperacao cadastrarEmprestimo(int id, Livro livro, Leitor leitor) {
        if (livro == null) {
            return ResultadoOperacao.LIVRO_NAO_ENCONTRADO;
        }

        if (leitor == null) {
            return ResultadoOperacao.LEITOR_NAO_ENCONTRADO;
        }

        if (!livro.temDisponivel()) {
            return ResultadoOperacao.LIVRO_INDISPONIVEL;
        }

        if (estaDentroDoLimite(leitor.getCpf()) != ResultadoOperacao.SUCESSO) {
            return ResultadoOperacao.LIMITE_ATINGIDO;
        }

        Emprestimo emprestimo = new Emprestimo(leitor, livro, id);
        emprestimos.add(emprestimo);
        livro.emprestar();

        return ResultadoOperacao.SUCESSO;
    }

    public ResultadoOperacao devolverEmprestimo(int id) {
        Emprestimo emprestimo = buscarEmprestimo(id);

        if (emprestimo == null) {
            return ResultadoOperacao.EMPRESTIMO_NAO_ENCONTRADO;
        }

        if (!emprestimo.isAtivo()) {
            return ResultadoOperacao.EMPRESTIMO_JA_DEVOLVIDO;
        }

        emprestimo.devolver();
        emprestimo.getLivro().devolver();

        return ResultadoOperacao.SUCESSO;
    }

    public Emprestimo buscarEmprestimo(int id) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getId() == id) {
                return emprestimo;
            }
        }

        return null;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }
}

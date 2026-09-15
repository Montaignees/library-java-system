package service;

import model.Leitor;

import java.util.ArrayList;
import java.util.List;

public class LeitorService {

    public enum ResultadoOperacao {
        SUCESSO,
        LEITOR_NAO_ENCONTRADO
    }

    private List<Leitor> leitores = new ArrayList<>();

    public void cadastrarLeitor(Leitor leitor) {
        leitores.add(leitor);
    }

    public Leitor buscarPorCpf(String cpf) {
        for (Leitor leitor : leitores) {
            if (leitor.getCpf().equals(cpf)) {
                return leitor;
            }
        }

        return null;
    }

    public List<Leitor> buscarPorNome(String nome) {
        List<Leitor> resultados = new ArrayList<>();

        for (Leitor leitor : leitores) {
            if (leitor.getNome().toLowerCase().contains(nome.toLowerCase())) {
                resultados.add(leitor);
            }
        }

        return resultados;
    }

    public Leitor selecionarLeitor(List<Leitor> leitores, int indice) {
        if (leitores == null || leitores.isEmpty()) {
            return null;
        }

        if (indice < 0 || indice >= leitores.size()) {
            return null;
        }

        return leitores.get(indice);
    }

    public List<Leitor> getLeitores() {
        return leitores;
    }

    public ResultadoOperacao alterarNome(String cpf, String nome) {
        Leitor leitorAtual = buscarPorCpf(cpf);

        if (leitorAtual == null) {
            return ResultadoOperacao.LEITOR_NAO_ENCONTRADO;
        }

        leitorAtual.setNome(nome);
        return ResultadoOperacao.SUCESSO;
    }

    public ResultadoOperacao alterarTelefone(String cpf, String telefone) {
        Leitor leitorAtual = buscarPorCpf(cpf);

        if (leitorAtual == null) {
            return ResultadoOperacao.LEITOR_NAO_ENCONTRADO;
        }

        leitorAtual.setTelefone(telefone);
        return ResultadoOperacao.SUCESSO;
    }

    public ResultadoOperacao alterarEmail(String cpf, String email) {
        Leitor leitorAtual = buscarPorCpf(cpf);

        if (leitorAtual == null) {
            return ResultadoOperacao.LEITOR_NAO_ENCONTRADO;
        }

        leitorAtual.setEmail(email);
        return ResultadoOperacao.SUCESSO;
    }
}

package service;

import model.Leitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LeitorService {

    private List<Leitor> leitores = new ArrayList<>();

    public enum ResultadoOperacao {
        SUCESSO,
        LEITOR_NAO_ENCONTRADO
    }

    public void cadastrarLeitor(Leitor leitor) {
        if (leitor != null) {
            leitores.add(leitor);
        }
    }

    public Leitor verificarDuplicidade(String cpf, String email, String telefone) {
        for (Leitor leitor : leitores) {
            if (leitor.getCpf().equals(cpf)
                    || leitor.getEmail().equalsIgnoreCase(email)
                    || leitor.getTelefone().equals(telefone)) {
                return leitor;
            }
        }

        return null;
    }

    public List<Leitor> buscarPorNome(String nome) {
        List<Leitor> resultados = new ArrayList<>();

        if (nome == null) {
            return resultados;
        }

        String busca = nome.trim().toLowerCase(Locale.ROOT);

        for (Leitor leitor : leitores) {
            if (leitor.getNome().toLowerCase(Locale.ROOT).contains(busca)) {
                resultados.add(leitor);
            }
        }

        return resultados;
    }

    public Leitor buscarPorCpf(String cpf) {
        if (cpf == null) {
            return null;
        }

        String cpfFiltrado = cpf.replaceAll("[^0-9]", "");

        for (Leitor leitor : leitores) {
            if (leitor.getCpf().equals(cpfFiltrado)) {
                return leitor;
            }
        }

        return null;
    }

    public ResultadoOperacao alterarNome(String cpf, String nome) {
        Leitor leitor = buscarPorCpf(cpf);

        if (leitor == null) {
            return ResultadoOperacao.LEITOR_NAO_ENCONTRADO;
        }

        leitor.setNome(nome);
        return ResultadoOperacao.SUCESSO;
    }

    public ResultadoOperacao alterarTelefone(String cpf, String telefone) {
        Leitor leitor = buscarPorCpf(cpf);

        if (leitor == null) {
            return ResultadoOperacao.LEITOR_NAO_ENCONTRADO;
        }

        leitor.setTelefone(telefone);
        return ResultadoOperacao.SUCESSO;
    }

    public ResultadoOperacao alterarEmail(String cpf, String email) {
        Leitor leitor = buscarPorCpf(cpf);

        if (leitor == null) {
            return ResultadoOperacao.LEITOR_NAO_ENCONTRADO;
        }

        leitor.setEmail(email);
        return ResultadoOperacao.SUCESSO;
    }

    public List<Leitor> getLeitores() {
        return leitores;
    }
}

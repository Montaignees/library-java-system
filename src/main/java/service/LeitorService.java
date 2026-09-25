package service;

import model.Leitor;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;

public class LeitorService {

    private final List<Leitor> leitores = new ArrayList<>();

    public enum ResultadoOperacao {
        SUCESSO,
        LEITOR_NAO_ENCONTRADO
    }

    public void cadastrarLeitor(Leitor leitor) {
        leitores.add(leitor);
    }

    public Leitor verificarDuplicidade(String cpf, String email, String telefone) {
        try {
            for (Leitor leitor : leitores) {
                if (leitor.getCpf().equals(cpf)
                        || leitor.getEmail().equals(email)
                        || leitor.getTelefone().equals(telefone)) {
                    return leitor;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Leitor> buscarPorNome(String nome) {
        List<Leitor> resultados = new ArrayList<>();
        String busca = nome.toLowerCase(Locale.ROOT);

        try {
            for (Leitor leitor : leitores) {
                if (leitor.getNome().toLowerCase(Locale.ROOT).contains(busca)) {
                    resultados.add(leitor);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultados;
    }

    public Leitor buscarPorCpf(String cpf) {
        try {
            for (Leitor leitor : leitores) {
                if (leitor.getCpf().equals(cpf)) {
                    return leitor;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultadoOperacao alterarNome(String cpf, String nome) {
        Leitor leitor = buscarPorCpf(cpf);

        try {
            if (leitor == null) {
                return ResultadoOperacao.LEITOR_NAO_ENCONTRADO;
            }
            leitor.setNome(nome);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultadoOperacao.SUCESSO;
    }

    public ResultadoOperacao alterarTelefone(String cpf, String telefone) {
        Leitor leitor = buscarPorCpf(cpf);

        try {
            if (leitor == null) {
                return ResultadoOperacao.LEITOR_NAO_ENCONTRADO;
            }

            leitor.setTelefone(telefone);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultadoOperacao.SUCESSO;
    }

    public ResultadoOperacao alterarEmail(String cpf, String email) {
        Leitor leitor = buscarPorCpf(cpf);

        try {
            if (leitor == null) {
                return ResultadoOperacao.LEITOR_NAO_ENCONTRADO;
            }


            leitor.setEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultadoOperacao.SUCESSO;
    }
}
package service;

import model.Leitor;
import model.Livro;

import java.util.ArrayList;
import java.util.List;

public class LeitorService {
    enum ResultadoOperacao {
        SUCESSO,
        LEITOR_NAO_ENCONTRADO,

    }

    private List<Leitor> leitores = new ArrayList<>();

    public void cadastrarLeitor(Leitor leitor) {
        leitores.add(leitor);
    }

    public Leitor buscarLeitor(String cpf) {
        for (Leitor leitor : leitores) {
            if (leitor.getCpf().equals(cpf)) {
                return leitor;
            }
        }
        return null;
    }

    public List<Leitor> getLeitores() {
        return leitores;
    }

    public ResultadoOperacao alterarNome(String cpf, String nome) {
        Leitor leitorAtual = buscarLeitor(cpf);

        if (leitorAtual == null) {
            return ResultadoOperacao.LEITOR_NAO_ENCONTRADO;
        }

        leitorAtual.setNome(nome);
        return ResultadoOperacao.SUCESSO;
    }

    public ResultadoOperacao alterarTelefone(String cpf, String telefone) {
        Leitor leitorAtual = buscarLeitor(cpf);

        if (leitorAtual == null) {
            return ResultadoOperacao.LEITOR_NAO_ENCONTRADO;
        }

        leitorAtual.setTelefone(telefone);
        return ResultadoOperacao.SUCESSO;
    }

    public ResultadoOperacao alterarEmail(String cpf, String email) {
        Leitor leitorAtual = buscarLeitor(cpf);

        if (leitorAtual == null) {
            return ResultadoOperacao.LEITOR_NAO_ENCONTRADO;
        }

        leitorAtual.setEmail(email);
        return ResultadoOperacao.SUCESSO;
    }
}

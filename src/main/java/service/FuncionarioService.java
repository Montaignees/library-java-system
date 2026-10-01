package service;

import model.Funcionario;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FuncionarioService {

    private  List<Funcionario> funcionarios = new ArrayList<>();

    public enum ResultadoOperacao {
        SUCESSO,
        FUNCIONARIO_NAO_ENCONTRADO,
        FUNCIONARIO_DUPLICADO
    }

    public ResultadoOperacao cadastrarFuncionario(Funcionario funcionario) {
        if (funcionario == null) {
            return ResultadoOperacao.FUNCIONARIO_NAO_ENCONTRADO;
        }

        if (!verificarDuplicidade(funcionario.getCpf(),
                funcionario.getEmail(),
                funcionario.getTelefone())) {
            return ResultadoOperacao.FUNCIONARIO_DUPLICADO;
        }

        funcionarios.add(funcionario);
        return ResultadoOperacao.SUCESSO;
    }

    public boolean verificarDuplicidade(String cpf, String email, String telefone) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getCpf().equals(cpf)
                    || funcionario.getEmail().equalsIgnoreCase(email)
                    || funcionario.getTelefone().equals(telefone)) {
                return false;
            }
        }

        return true;
    }

    public List<Funcionario> buscarPorNome(String nome) {
        List<Funcionario> resultados = new ArrayList<>();

        if (nome == null) {
            return resultados;
        }

        String busca = nome.trim().toLowerCase(Locale.ROOT);

        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getNome().toLowerCase(Locale.ROOT).contains(busca)) {
                resultados.add(funcionario);
            }
        }

        return resultados;
    }

    public ResultadoOperacao demitirFuncionario(String cpf) {
        Funcionario funcionario = buscarPorCpf(cpf);

        if (funcionario == null) {
            return ResultadoOperacao.FUNCIONARIO_NAO_ENCONTRADO;
        }

        funcionarios.remove(funcionario);
        return ResultadoOperacao.SUCESSO;
    }

    public Funcionario buscarPorCpf(String cpf) {
        if (cpf == null) {
            return null;
        }

        String cpfFiltrado = cpf.replaceAll("[^0-9]", "");

        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getCpf().equals(cpfFiltrado)) {
                return funcionario;
            }
        }

        return null;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }
}

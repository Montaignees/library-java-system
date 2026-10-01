package model;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Funcionario extends Pessoa {

    private String senha;
    private BigDecimal salario;
    private List<RegistroPonto> registros = new ArrayList<>();

    public Funcionario(String nome, String telefone, String email, String cpf,
                       String senha, BigDecimal salario) {
        super(nome, telefone, email, cpf);
        setSenha(senha);
        setSalario(salario);
    }

    public List<RegistroPonto> getRegistros() {
        return registros;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        if (salario == null || salario.compareTo(new BigDecimal("1621.00")) < 0) {
            throw new IllegalArgumentException("SALARIO INVALIDO");
        }

        this.salario = salario;
    }

    public void setSenha(String senha) {
        if (senha == null || senha.length() < 4) {
            throw new IllegalArgumentException("A SENHA DEVE TER PELO MENOS 4 CARACTERES");
        }

        this.senha = gerarHash(senha);
    }

    public boolean verificarSenha(String senhaDigitada) {
        if (senhaDigitada == null) {
            return false;
        }

        return this.senha.equals(gerarHash(senhaDigitada));
    }

    public void registrarEntrada() {
        registros.add(new RegistroPonto());
    }

    public void registrarSaida() {
        if (registros.isEmpty()) {
            return;
        }

        RegistroPonto registro = registros.get(registros.size() - 1);
        registro.registrarSaida();
    }

    private String gerarHash(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(senha.getBytes(StandardCharsets.UTF_8));
            StringBuilder resultado = new StringBuilder();

            for (byte b : hash) {
                resultado.append(String.format("%02x", b));
            }

            return resultado.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("ERRO AO GERAR SENHA");
        }
    }
}

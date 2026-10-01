package model;

public class Pessoa {

    protected String nome;
    protected String telefone;
    protected String email;
    protected String cpf;

    public Pessoa(String nome, String telefone, String email, String cpf) {
        setNome(nome);
        setTelefone(telefone);
        setEmail(email);
        setCpf(cpf);
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("NOME INVALIDO");
        }
        this.nome = nome.trim();
    }

    public void setTelefone(String telefone) {
        if (telefone == null || telefone.isBlank()) {
            throw new IllegalArgumentException("TELEFONE INVALIDO");
        }
        this.telefone = telefone.trim();
    }

    public void setEmail(String email) {
        if (!validarEmail(email)) {
            throw new IllegalArgumentException("EMAIL INVALIDO");
        }
        this.email = email.trim();
    }

    public void setCpf(String cpf) {
        String cpfFiltrado = filtrarCpf(cpf);

        if (!validarCpf(cpfFiltrado)) {
            throw new IllegalArgumentException("CPF INVALIDO");
        }

        this.cpf = cpfFiltrado;
    }

    public String filtrarCpf(String cpf) {
        if (cpf == null) {
            return null;
        }

        String cpfFiltrado = cpf.replaceAll("[^0-9]", "");

        if (cpfFiltrado.length() != 11) {
            return null;
        }

        return cpfFiltrado;
    }

    public boolean numerosIguais(String cpf) {
        if (cpf == null) {
            return false;
        }

        for (int i = 1; i < cpf.length(); i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) {
                return false;
            }
        }

        return true;
    }

    public int calcularDigito1Cpf(String cpf) {
        int soma = 0;

        for (int i = 0; i < 9; i++) {
            int numero = Character.getNumericValue(cpf.charAt(i));
            soma += numero * (10 - i);
        }

        int resto = soma % 11;

        if (resto < 2) {
            return 0;
        }

        return 11 - resto;
    }

    public int calcularDigito2Cpf(String cpf) {
        int soma = 0;

        for (int i = 0; i < 10; i++) {
            int numero = Character.getNumericValue(cpf.charAt(i));
            soma += numero * (11 - i);
        }

        int resto = soma % 11;

        if (resto < 2) {
            return 0;
        }

        return 11 - resto;
    }

    public boolean validarCpf(String cpf) {
        if (cpf == null || cpf.length() != 11 || numerosIguais(cpf)) {
            return false;
        }

        int primeiroDigito = calcularDigito1Cpf(cpf);
        int digitoInformado1 = Character.getNumericValue(cpf.charAt(9));

        if (primeiroDigito != digitoInformado1) {
            return false;
        }

        int segundoDigito = calcularDigito2Cpf(cpf);
        int digitoInformado2 = Character.getNumericValue(cpf.charAt(10));

        return segundoDigito == digitoInformado2;
    }

    public boolean validarEmail(String email) {
        if (email == null) {
            return false;
        }

        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return email.trim().matches(regex);
    }
}

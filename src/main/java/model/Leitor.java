package model;

public class Leitor {

    private String nome;
    private String telefone;
    private String email;
    private String cpf;

    public Leitor(String nome, String telefone, String email, String cpf) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
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
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //Validação de CPF
    public void setCpf(String cpf) {
        cpf = filtrarCpf(cpf);

        if (!validarCpf(cpf)) {
            throw new IllegalArgumentException("CPF inválido.");
        }

        this.cpf = cpf;
    }

    public String filtrarCpf(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11) {
            return null;
        }

        return cpf;
    }

    public boolean numerosIguais(String cpf) {
        for (int i = 1; i < cpf.length(); i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) {
                return false;
            }
        }

        return true;
    }

    public int calcularDigito1Cpf(String cpf) {
        int soma = 0;
        int resto;

        for (int i = 0; i < 9; i++) {
            int numero = Character.getNumericValue(cpf.charAt(i));
            soma += numero * (10 - i);
        }

        resto = soma % 11;

        if (resto < 2) {
            return 0;
        } else {
            return 11 - resto;
        }
    }

    public int calcularDigito2Cpf(String cpf) {
        int soma = 0;
        int resto;

        for (int i = 0; i < 10; i++) {
            int numero = Character.getNumericValue(cpf.charAt(i));
            soma += numero * (11 - i);
        }

        resto = soma % 11;

        if (resto < 2) {
            return 0;
        } else {
            return 11 - resto;
        }
    }

    public boolean validarCpf(String cpf) {

        if (cpf == null) {
            return false;
        }

        if (numerosIguais(cpf)) {
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

    // Validação de Email
}


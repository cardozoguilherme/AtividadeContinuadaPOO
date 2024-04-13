package br.edu.cesarschool.cc.poo.ac.utils;

import java.lang.String;
public class ValidadorCPF {
    //TODO: construtor não funciona com as especificações passadas
    private ValidadorCPF() {
    }

    public static boolean isCpfValido(String cpf) {
        // Verifica se o CPF é nulo, vazio, não consiste apenas de dígitos ou não tem exatamente 11 caracteres
        if (cpf == null || cpf.trim().isEmpty() || !cpf.matches("\\d+") || cpf.length() != 11) {
            return false;
        }

        if (cpf.equals("00000000000") ||
                cpf.equals("11111111111") ||
                cpf.equals("22222222222") || cpf.equals("33333333333") ||
                cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") ||
                cpf.equals("88888888888") || cpf.equals("99999999999")){
            return false;
        }

        // Remove todos os caracteres que não são dígitos
        cpf = cpf.replaceAll("\\D", "");

        // Calcula o primeiro dígito verificador do CPF
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int resto = soma % 11;
        int digitoVerificador1 = resto < 2 ? 0 : 11 - resto;

        // Verifica se o primeiro dígito verificador está correto
        if (Character.getNumericValue(cpf.charAt(9)) != digitoVerificador1) {
            return false;
        }

        // Calcula o segundo dígito verificador do CPF
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        resto = soma % 11;
        int digitoVerificador2 = resto < 2 ? 0 : 11 - resto;

        // Verifica se o segundo dígito verificador está correto
        return Character.getNumericValue(cpf.charAt(10)) == digitoVerificador2;

        // Se todos os critérios forem atendidos, o CPF é válido
    }
}
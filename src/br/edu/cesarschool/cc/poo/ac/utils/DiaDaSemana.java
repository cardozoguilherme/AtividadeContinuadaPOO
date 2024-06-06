package br.edu.cesarschool.cc.poo.ac.utils;

public enum DiaDaSemana {
    SEGUNDA_FEIRA(1, "segunda-feira"),
    TERCA_FEIRA(2, "terça-feira"),
    QUARTA_FEIRA(3, "quarta-feira"),
    QUINTA_FEIRA(4, "quinta-feira"),
    SEXTA_FEIRA(5, "sexta-feira"),
    SABADO(6, "sábado"),
    DOMINGO(7, "domingo");

    private final int codigo;
    private final String nome;

    private DiaDaSemana(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public static DiaDaSemana getDiaDaSemana(int codigo) {
        for (DiaDaSemana dia : DiaDaSemana.values()) {
            if (dia.getCodigo() == codigo) {
                return dia;
            }
        }
        return null;
    }
}

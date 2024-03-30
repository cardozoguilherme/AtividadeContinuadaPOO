package br.edu.cesarschool.cc.poo.ac.cliente;

import br.edu.cesarschool.cc.poo.ac.utils.Registro;

public class Cliente extends Registro {
    private String cpf;
    private String nome;
    private double saldoPontos;

    public Cliente(String cpf, String nome, double saldoPontos) {
        this.cpf = cpf;
        this.nome = nome;
        this.saldoPontos = saldoPontos;
    }

    public String getCpf() {
        return cpf;
    }

//    public void setCpf(String cpf) {
//        this.cpf = cpf;
//    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSaldoPontos() {
        return saldoPontos;
    }

//    public void setSaldoPontos(double saldoPontos) {
//        this.saldoPontos = saldoPontos;
//    }

    public void creditarPontos(double valor){
        saldoPontos += valor;
    }

    public void debitarPontos(double valor){
        saldoPontos -= valor;
    }
}

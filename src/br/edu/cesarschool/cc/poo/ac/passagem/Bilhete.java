package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.cc.poo.ac.cliente.Cliente;
import br.edu.cesarschool.cc.poo.ac.utils.Registro;
import br.edu.cesarschool.cc.poo.ac.passagem.Voo;

import java.time.LocalDateTime;

public class Bilhete extends Registro {
    private Cliente cliente;
    private Voo voo;
    private double preco;
    private double pagamentoEmPontos;
    private java.time.LocalDateTime dataHora;

    public Bilhete(Cliente cliente, Voo voo, double preco, double pagamentoEmPontos, LocalDateTime dataHora) {
        this.cliente = cliente;
        this.voo = voo;
        this.preco = preco;
        this.pagamentoEmPontos = pagamentoEmPontos;
        this.dataHora = dataHora;
    }

    public Cliente getCliente() {
        return cliente;
    }

//    public void setCliente(Cliente cliente) {
//        this.cliente = cliente;
//    }

    public Voo getVoo() {
        return voo;
    }

//    public void setVoo(Voo voo) {
//        this.voo = voo;
//    }

    public double getPreco() {
        return preco;
    }

//    public void setPreco(double preco) {
//        this.preco = preco;
//    }

    public double getPagamentoEmPontos() {
        return pagamentoEmPontos;
    }

    public void setPagamentoEmPontos(double pagamentoEmPontos) {
        this.pagamentoEmPontos = pagamentoEmPontos;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

//    public void setDataHora(LocalDateTime dataHora) {
//        this.dataHora = dataHora;
//    }

    public double obterValorPago(){
        return preco - pagamentoEmPontos;
    }

    public double obterValorPontuacao(){
        return obterValorPago() / 20;
    }

    public String gerarNumero() {
        return cliente.getCpf() + ", " + voo.getNumeroVoo() + ", " + dataHora.getYear() + ", " + dataHora.getMonth() + ", " + dataHora.getDayOfMonth();
    }
}
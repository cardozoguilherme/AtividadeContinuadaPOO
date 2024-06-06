package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.cc.poo.ac.cliente.Cliente;
import br.edu.cesarschool.cc.poo.ac.utils.Registro;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Bilhete extends Registro {
	private Cliente cliente;
	private Voo voo;
	private double preco;
	private double pagamentoEmPontos;
	private LocalDateTime dataHora;

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

	public Voo getVoo() {
		return voo;
	}

	public double getPreco() {
		return preco;
	}

	public double getPagamentoEmPontos() {
		return pagamentoEmPontos;
	}

	public void setPagamentoEmPontos(double pagamentoEmPontos) {
		this.pagamentoEmPontos = pagamentoEmPontos;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public double obterValorPontuacao() {
		return (preco - pagamentoEmPontos) / 20;
	}

	public double obterValorPago() {
		return (preco - pagamentoEmPontos);
	}

	public String gerarNumero() {
		return cliente.getCpf() + voo.getNumeroVoo() + dataHora.getYear() + dataHora.getMonthValue() + dataHora.getDayOfMonth();
	}

	@Override
	public String getIdUnico() {
		return gerarNumero();
	}

	@Override
	public String toString() {
		return preco + " , " + voo.getAeroportoOrigem() + " , " + voo.getAeroportoDestino() + " , " + dataHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
	}
}

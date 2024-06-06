package br.edu.cesarschool.cc.poo.ac.cliente;

import br.edu.cesarschool.cc.poo.ac.utils.Registro;
import br.edu.cesarschool.cc.poo.ac.utils.ordenacao.Comparavel;

public class Cliente extends Registro implements Comparavel {
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getSaldoPontos() {
		return saldoPontos;
	}

	public void creditarPontos(double valor) {
		saldoPontos += valor;
	}

	public void debitarPontos(double valor) {
		saldoPontos -= valor;
	}

	@Override
	public String getIdUnico() {
		return cpf;
	}

	@Override
	public int comparar(Object o) {
		Cliente outro = (Cliente) o;
		return this.nome.compareTo(outro.nome);
	}

	@Override
	public String toString() {
		return nome + " , " + cpf + " , " + saldoPontos;
	}
}

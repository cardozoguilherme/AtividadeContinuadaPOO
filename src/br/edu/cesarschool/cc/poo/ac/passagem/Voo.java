package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.cc.poo.ac.utils.DiaDaSemana;
import br.edu.cesarschool.cc.poo.ac.utils.Registro;
import java.time.LocalTime;

public class Voo extends Registro {
	private String aeroportoOrigem;
	private String aeroportoDestino;
	private String companhiaAerea;
	private int numeroVoo;
	private DiaDaSemana[] diasDaSemana;
	private LocalTime hora;

	public Voo(String aeroportoOrigem, String aeroportoDestino, String companhiaAerea, int numeroVoo) {
		this.aeroportoOrigem = aeroportoOrigem;
		this.aeroportoDestino = aeroportoDestino;
		this.companhiaAerea = companhiaAerea;
		this.numeroVoo = numeroVoo;
	}

	public Voo(String aeroportoOrigem, String aeroportoDestino, String companhiaAerea, int numeroVoo, DiaDaSemana[] diasDaSemana, LocalTime hora) {
		this(aeroportoOrigem, aeroportoDestino, companhiaAerea, numeroVoo);
		this.diasDaSemana = diasDaSemana;
		this.hora = hora;
	}

	public String getAeroportoOrigem() {
		return aeroportoOrigem;
	}

	public String getAeroportoDestino() {
		return aeroportoDestino;
	}

	public String getCompanhiaAerea() {
		return companhiaAerea;
	}

	public int getNumeroVoo() {
		return numeroVoo;
	}

	public DiaDaSemana[] getDiasDaSemana() {
		return diasDaSemana;
	}

	public void setDiasDaSemana(DiaDaSemana[] diasDaSemana) {
		this.diasDaSemana = diasDaSemana;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public String obterIdVoo() {
		return companhiaAerea + numeroVoo;
	}

	@Override
	public String getIdUnico() {
		return obterIdVoo();
	}
}

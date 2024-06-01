package br.edu.cesarschool.cc.poo.ac.passagem;

public class ResultadoGeracaoBilhete {
	private Bilhete bilhete;
	private BilheteVip bilheteVip;
	private String mensagemErro;
	public ResultadoGeracaoBilhete(Bilhete bilhete, BilheteVip bilheteVip, String mensagemErro) {
		this.bilhete = bilhete;
		this.bilheteVip = bilheteVip;
		this.mensagemErro = mensagemErro;
	}
	public Bilhete getBilhete() {
		return bilhete;
	}
	public BilheteVip getBilheteVip() {
		return bilheteVip;
	}
	public String getMensagemErro() {
		return mensagemErro;
	}
	
}

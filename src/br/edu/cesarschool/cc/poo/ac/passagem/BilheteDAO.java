package br.edu.cesarschool.cc.poo.ac.passagem;

import java.io.Serializable;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;

public class BilheteDAO {
	private CadastroObjetos cadastro = new CadastroObjetos(Bilhete.class);
	public Bilhete buscar(String numero) {
		return (Bilhete)cadastro.buscar(numero);
	}
	public boolean incluir(Bilhete bilhete) {
		Bilhete cli = buscar(bilhete.gerarNumero());
		if (cli == null) {
			cadastro.incluir(bilhete, bilhete.gerarNumero());
			return true;
		} 
		return false; 
	}
	public boolean alterar(Bilhete bilhete) {
		Bilhete cli = buscar(bilhete.gerarNumero());
		if (cli != null) {
			cadastro.alterar(bilhete, bilhete.gerarNumero());
			return true;
		} 
		return false; 
	}
	public boolean excluir(String numero) {
		Bilhete cli = buscar(numero);
		if (cli != null) {
			cadastro.excluir(numero);
			return true;
		} 
		return false; 
	}	
	public Bilhete[] buscarTodos() {
		Serializable[] res = cadastro.buscarTodos();
		if (res == null) {
			return null;
		} else {
			Bilhete[] bilhetes = new Bilhete[res.length];
			int i = 0;
			for (Serializable reg : res) {
				bilhetes[i] = (Bilhete)reg;
				i++;
			}
			return bilhetes;
		}
	}
}

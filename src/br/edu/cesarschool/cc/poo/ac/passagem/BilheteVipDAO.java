package br.edu.cesarschool.cc.poo.ac.passagem;

import java.io.Serializable;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;

public class BilheteVipDAO {
	private CadastroObjetos cadastro = new CadastroObjetos(BilheteVip.class);
	public BilheteVip buscar(String cpf) {
		return (BilheteVip)cadastro.buscar(cpf);
	}
	public boolean incluir(BilheteVip bilhete) {
		BilheteVip cli = buscar(bilhete.gerarNumero());
		if (cli == null) {
			cadastro.incluir(bilhete, bilhete.gerarNumero());
			return true;
		} 
		return false; 
	}
	public boolean alterar(BilheteVip bilhete) {
		BilheteVip cli = buscar(bilhete.gerarNumero());
		if (cli != null) {
			cadastro.alterar(bilhete, bilhete.gerarNumero());
			return true;
		} 
		return false; 
	}
	public boolean excluir(String numero) {
		BilheteVip cli = buscar(numero);
		if (cli != null) {
			cadastro.excluir(numero);
			return true;
		} 
		return false; 
	}	
	public BilheteVip[] buscarTodos() {
		Serializable[] res = cadastro.buscarTodos();
		if (res == null) {
			return null;
		} else {
			BilheteVip[] bilhetes = new BilheteVip[res.length];
			int i = 0;
			for (Serializable reg : res) {
				bilhetes[i] = (BilheteVip)reg;
				i++;
			}
			return bilhetes;
		}
	}
}

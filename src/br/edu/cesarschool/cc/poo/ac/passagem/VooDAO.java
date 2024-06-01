package br.edu.cesarschool.cc.poo.ac.passagem;

import java.io.Serializable;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;

public class VooDAO {
	private CadastroObjetos cadastro = new CadastroObjetos(Voo.class);
	public Voo buscar(String idVoo) {
		return (Voo)cadastro.buscar(idVoo);
	}
	private String obterIdUnicoVoo(Voo voo) {
		return voo.getCompanhiaAerea() + voo.getNumeroVoo();
	}
	public boolean incluir(Voo voo) {
		Voo cli = buscar(obterIdUnicoVoo(voo));
		if (cli == null) {
			cadastro.incluir(voo, obterIdUnicoVoo(voo));
			return true;
		} 
		return false; 
	}
	public boolean alterar(Voo voo) {
		Voo cli = buscar(obterIdUnicoVoo(voo));
		if (cli != null) {
			cadastro.alterar(voo, obterIdUnicoVoo(voo));
			return true;
		} 
		return false; 
	}
	public boolean excluir(String idVoo) {
		Voo cli = buscar(idVoo);
		if (cli != null) {
			cadastro.excluir(idVoo);
			return true;
		} 
		return false; 
	}	
	public Voo[] buscarTodos() {
		Serializable[] res = cadastro.buscarTodos();
		if (res == null) {
			return null;
		} else {
			Voo[] voos = new Voo[res.length];
			int i = 0;
			for (Serializable reg : res) {
				voos[i] = (Voo)reg;
				i++;
			}
			return voos;
		}
	}
}

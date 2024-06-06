package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.cc.poo.ac.utils.SuperDAO;
import br.edu.cesarschool.cc.poo.ac.utils.Registro;

public class BilheteDAO extends SuperDAO {

	@Override
	public Class<?> obterTipo() {
		return Bilhete.class;
	}

	public Bilhete buscar(String numero) {
		return (Bilhete) daoGenerico.buscar(numero);
	}

	public boolean incluir(Bilhete bilhete) {
		return daoGenerico.incluir(bilhete);
	}

	public boolean alterar(Bilhete bilhete) {
		return daoGenerico.alterar(bilhete);
	}

	public boolean excluir(String numero) {
		return daoGenerico.excluir(numero);
	}

	public Bilhete[] buscarTodos() {
		Registro[] registros = daoGenerico.buscarTodos();
		if (registros == null) {
			return null;
		}
		Bilhete[] bilhetes = new Bilhete[registros.length];
		for (int i = 0; i < registros.length; i++) {
			bilhetes[i] = (Bilhete) registros[i];
		}
		return bilhetes;
	}
}

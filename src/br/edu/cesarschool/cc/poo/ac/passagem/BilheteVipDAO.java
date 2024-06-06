package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.cc.poo.ac.utils.SuperDAO;
import br.edu.cesarschool.cc.poo.ac.utils.Registro;

public class BilheteVipDAO extends SuperDAO {

	@Override
	public Class<?> obterTipo() {
		return BilheteVip.class;
	}

	public BilheteVip buscar(String numero) {
		return (BilheteVip) daoGenerico.buscar(numero);
	}

	public boolean incluir(BilheteVip bilhete) {
		return daoGenerico.incluir(bilhete);
	}

	public boolean alterar(BilheteVip bilhete) {
		return daoGenerico.alterar(bilhete);
	}

	public boolean excluir(String numero) {
		return daoGenerico.excluir(numero);
	}

	public BilheteVip[] buscarTodos() {
		Registro[] registros = daoGenerico.buscarTodos();
		if (registros == null) {
			return null;
		}
		BilheteVip[] bilhetes = new BilheteVip[registros.length];
		for (int i = 0; i < registros.length; i++) {
			bilhetes[i] = (BilheteVip) registros[i];
		}
		return bilhetes;
	}
}

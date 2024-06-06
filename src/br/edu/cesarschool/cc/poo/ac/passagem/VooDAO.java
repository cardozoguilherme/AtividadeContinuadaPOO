package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.cc.poo.ac.utils.SuperDAO;
import br.edu.cesarschool.cc.poo.ac.utils.Registro;

public class VooDAO extends SuperDAO {

	@Override
	public Class<?> obterTipo() {
		return Voo.class;
	}

	public Voo buscar(String idVoo) {
		return (Voo) daoGenerico.buscar(idVoo);
	}

	public boolean incluir(Voo voo) {
		return daoGenerico.incluir(voo);
	}

	public boolean alterar(Voo voo) {
		return daoGenerico.alterar(voo);
	}

	public boolean excluir(String idVoo) {
		return daoGenerico.excluir(idVoo);
	}

	public Voo[] buscarTodos() {
		Registro[] registros = daoGenerico.buscarTodos();
		if (registros == null) {
			return null;
		}
		Voo[] voos = new Voo[registros.length];
		for (int i = 0; i < registros.length; i++) {
			voos[i] = (Voo) registros[i];
		}
		return voos;
	}
}

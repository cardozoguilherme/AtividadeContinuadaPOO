package br.edu.cesarschool.cc.poo.ac.cliente;

import br.edu.cesarschool.cc.poo.ac.utils.SuperDAO;
import br.edu.cesarschool.cc.poo.ac.utils.Registro;

public class ClienteDAO extends SuperDAO {

	@Override
	public Class<?> obterTipo() {
		return Cliente.class;
	}

	public Cliente buscar(String cpf) {
		return (Cliente) daoGenerico.buscar(cpf);
	}

	public boolean incluir(Cliente cliente) {
		return daoGenerico.incluir(cliente);
	}

	public boolean alterar(Cliente cliente) {
		return daoGenerico.alterar(cliente);
	}

	public boolean excluir(String cpf) {
		return daoGenerico.excluir(cpf);
	}

	public Cliente[] buscarTodos() {
		Registro[] registros = daoGenerico.buscarTodos();
		if (registros == null) {
			return null;
		}
		Cliente[] clientes = new Cliente[registros.length];
		for (int i = 0; i < registros.length; i++) {
			clientes[i] = (Cliente) registros[i];
		}
		return clientes;
	}
}

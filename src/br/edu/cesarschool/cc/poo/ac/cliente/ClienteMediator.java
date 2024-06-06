package br.edu.cesarschool.cc.poo.ac.cliente;

import br.edu.cesarschool.cc.poo.ac.utils.ordenacao.Ordenadora;
import br.edu.cesarschool.cc.poo.ac.utils.ValidadorCPF;

public class ClienteMediator {
	private static ClienteMediator instancia;
	private ClienteDAO clienteDAO = new ClienteDAO();

	public static ClienteMediator obterInstancia() {
		if (instancia == null) {
			instancia = new ClienteMediator();
		}
		return instancia;
	}

	private ClienteMediator() {
	}

	public String validar(Cliente cliente) {
		if (!ValidadorCPF.isCpfValido(cliente.getCpf())) {
			return "CPF errado";
		}
		if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
			return "nome errado";
		}
		if (cliente.getSaldoPontos() < 0) {
			return "saldo errado";
		}
		return null;
	}

	public Cliente buscar(String cpf) {
		return clienteDAO.buscar(cpf);
	}

	public String incluir(Cliente cliente) {
		String validacao = validar(cliente);
		if (validacao != null) {
			return validacao;
		}
		return clienteDAO.incluir(cliente) ? null : "Cliente já existente";
	}

	public String alterar(Cliente cliente) {
		String validacao = validar(cliente);
		if (validacao != null) {
			return validacao;
		}
		return clienteDAO.alterar(cliente) ? null : "Cliente inexistente";
	}

	public String excluir(String cpf) {
		if (!ValidadorCPF.isCpfValido(cpf)) {
			return "CPF errado";
		}
		return clienteDAO.excluir(cpf) ? null : "Cliente inexistente";
	}

	public Cliente[] obterClientesPorNome() {
		Cliente[] clientes = clienteDAO.buscarTodos();
		if (clientes != null) {
			Ordenadora.ordenar(clientes);
		}
		return clientes;
	}
}

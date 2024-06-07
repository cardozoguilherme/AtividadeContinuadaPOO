package br.edu.cesarschool.cc.poo.ac.cliente.comexcecao;

import br.edu.cesarschool.cc.poo.ac.cliente.Cliente;
import br.edu.cesarschool.cc.poo.ac.utils.SuperDAO;
import br.edu.cesarschool.cc.poo.ac.utils.Registro;

public class ClienteDAO extends SuperDAO {

    @Override
    public Class<?> obterTipo() {
        return Cliente.class;
    }

    public Cliente buscar(String cpf) throws ExcecaoRegistroInexistente {
        Cliente cliente = (Cliente) daoGenerico.buscar(cpf);
        if (cliente == null) {
            throw new ExcecaoRegistroInexistente("Cliente inexistente");
        }
        return cliente;
    }

    public boolean incluir(Cliente cliente) throws ExcecaoRegistroJaExistente {
        if (!daoGenerico.incluir(cliente)) {
            throw new ExcecaoRegistroJaExistente("Cliente existente");
        }
        return true;
    }

    public boolean alterar(Cliente cliente) throws ExcecaoRegistroInexistente {
        if (!daoGenerico.alterar(cliente)) {
            throw new ExcecaoRegistroInexistente("Cliente inexistente");
        }
        return true;
    }

    public boolean excluir(String cpf) throws ExcecaoRegistroInexistente {
        if (!daoGenerico.excluir(cpf)) {
            throw new ExcecaoRegistroInexistente("Cliente inexistente");
        }
        return true;
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

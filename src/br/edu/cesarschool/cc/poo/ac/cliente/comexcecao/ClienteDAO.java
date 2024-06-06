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
            throw new ExcecaoRegistroInexistente("Cliente não encontrado");
        }
        return cliente;
    }

    public void incluir(Cliente cliente) throws ExcecaoRegistroJaExistente {
        if (daoGenerico.buscar(cliente.getCpf()) != null) {
            throw new ExcecaoRegistroJaExistente("Cliente já existe");
        }
        daoGenerico.incluir(cliente);
    }

    public void alterar(Cliente cliente) throws ExcecaoRegistroInexistente {
        if (daoGenerico.buscar(cliente.getCpf()) == null) {
            throw new ExcecaoRegistroInexistente("Cliente não encontrado");
        }
        daoGenerico.alterar(cliente);
    }

    public void excluir(String cpf) throws ExcecaoRegistroInexistente {
        if (daoGenerico.buscar(cpf) == null) {
            throw new ExcecaoRegistroInexistente("Cliente não encontrado");
        }
        daoGenerico.excluir(cpf);
    }
}

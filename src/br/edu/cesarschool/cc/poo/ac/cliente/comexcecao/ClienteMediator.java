package br.edu.cesarschool.cc.poo.ac.cliente.comexcecao;

import br.edu.cesarschool.cc.poo.ac.cliente.Cliente;
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

    public void validar(Cliente cliente) throws ExcecaoValidacao {
        ExcecaoValidacao excecaoValidacao = new ExcecaoValidacao();
        if (!ValidadorCPF.isCpfValido(cliente.getCpf())) {
            excecaoValidacao.adicionarMensagem("CPF errado");
        }
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            excecaoValidacao.adicionarMensagem("nome errado");
        }
        if (cliente.getSaldoPontos() < 0) {
            excecaoValidacao.adicionarMensagem("saldo errado");
        }
        if (!excecaoValidacao.getMensagens().isEmpty()) {
            throw excecaoValidacao;
        }
    }

    public Cliente buscar(String cpf) throws ExcecaoRegistroInexistente {
        Cliente cliente = clienteDAO.buscar(cpf);
        if (cliente == null) {
            throw new ExcecaoRegistroInexistente("Cliente inexistente");
        }
        return cliente;
    }

    public void incluir(Cliente cliente) throws ExcecaoRegistroJaExistente, ExcecaoValidacao {
        validar(cliente);
        if (!clienteDAO.incluir(cliente)) {
            throw new ExcecaoRegistroJaExistente("Cliente existente");
        }
    }

    public void alterar(Cliente cliente) throws ExcecaoRegistroInexistente, ExcecaoValidacao {
        validar(cliente);
        if (!clienteDAO.alterar(cliente)) {
            throw new ExcecaoRegistroInexistente("Cliente inexistente");
        }
    }

    public void excluir(String cpf) throws ExcecaoRegistroInexistente, ExcecaoValidacao {
        if (!ValidadorCPF.isCpfValido(cpf)) {
            ExcecaoValidacao excecaoValidacao = new ExcecaoValidacao();
            excecaoValidacao.adicionarMensagem("CPF errado");
            throw excecaoValidacao;
        }
        if (!clienteDAO.excluir(cpf)) {
            throw new ExcecaoRegistroInexistente("Cliente inexistente");
        }
    }
}

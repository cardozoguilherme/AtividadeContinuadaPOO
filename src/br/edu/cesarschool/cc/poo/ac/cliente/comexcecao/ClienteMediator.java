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
            excecaoValidacao.adicionarMensagem("CPF inválido");
        }
        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            excecaoValidacao.adicionarMensagem("Nome inválido");
        }
        if (cliente.getSaldoPontos() < 0) {
            excecaoValidacao.adicionarMensagem("Saldo de pontos inválido");
        }
        if (!excecaoValidacao.getMensagens().isEmpty()) {
            throw excecaoValidacao;
        }
    }

    public Cliente buscar(String cpf) throws ExcecaoRegistroInexistente {
        return clienteDAO.buscar(cpf);
    }

    public void incluir(Cliente cliente) throws ExcecaoRegistroJaExistente, ExcecaoValidacao {
        validar(cliente);
        clienteDAO.incluir(cliente);
    }

    public void alterar(Cliente cliente) throws ExcecaoRegistroInexistente, ExcecaoValidacao {
        validar(cliente);
        clienteDAO.alterar(cliente);
    }

    public void excluir(String cpf) throws ExcecaoRegistroInexistente, ExcecaoValidacao {
        if (!ValidadorCPF.isCpfValido(cpf)) {
            ExcecaoValidacao excecaoValidacao = new ExcecaoValidacao();
            excecaoValidacao.adicionarMensagem("CPF inválido");
            throw excecaoValidacao;
        }
        clienteDAO.excluir(cpf);
    }
}

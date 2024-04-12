package br.edu.cesarschool.cc.poo.ac.cliente;

import br.edu.cesarschool.cc.poo.ac.utils.StringUtils;
import br.edu.cesarschool.cc.poo.ac.utils.ValidadorCPF;

public class ClienteMediator {
    private static ClienteMediator instancia;

    private ClienteDAO clienteDao = new ClienteDAO();

    ClienteMediator() {
    }

    public static ClienteMediator obterInstancia() {
        if (instancia == null) {
            instancia = new ClienteMediator();
        }
        return instancia;
    }

    public Cliente buscar(String cpf) {
        return clienteDao.buscar(cpf);
    }

    public String validar(Cliente cliente) {
        if (!ValidadorCPF.isCpfValido(cliente.getCpf())) {
            return "CPF errado";
        } else if (StringUtils.isVaziaOuNula(cliente.getNome()) || cliente.getNome().length() < 2){
            return "nome errado";
        } else if (cliente.getSaldoPontos() < 0) {
            return "saldo errado";
        }
        return null;
    }

    public String incluir(Cliente cliente) {
        if (validar(cliente) != null) {
            return validar(cliente);
        } else {
            boolean resposta = clienteDao.incluir(cliente);

            if (!resposta) {
                return "Cliente ja existente";
            }
            return null;
        }
    }
}

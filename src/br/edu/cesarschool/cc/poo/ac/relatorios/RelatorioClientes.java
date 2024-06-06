package br.edu.cesarschool.cc.poo.ac.relatorios;

import br.edu.cesarschool.cc.poo.ac.cliente.Cliente;
import br.edu.cesarschool.cc.poo.ac.cliente.ClienteMediator;

public class RelatorioClientes {
    public static void gerarRelatorioClientes() {
        ClienteMediator mediator = ClienteMediator.obterInstancia();
        Cliente[] clientes = mediator.obterClientesPorNome();
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }
}

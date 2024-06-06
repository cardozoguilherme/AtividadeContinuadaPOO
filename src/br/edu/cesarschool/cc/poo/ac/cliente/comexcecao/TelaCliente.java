package br.edu.cesarschool.cc.poo.ac.cliente.comexcecao;

import br.edu.cesarschool.cc.poo.ac.cliente.Cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class TelaCliente {
    private static final Scanner ENTRADA = new Scanner(System.in);
    private static final BufferedReader ENTRADA_STR = new BufferedReader(new InputStreamReader(System.in));

    private ClienteMediator clienteMediator = ClienteMediator.obterInstancia();

    public void inicializaTelasCadastroCliente() {
        while (true) {
            imprimeMenuPrincipal();
            int opcao = ENTRADA.nextInt();
            if (opcao == 1) {
                processaInclusao();
            } else if (opcao == 2) {
                processaAlteracao();
            } else if (opcao == 3) {
                processaExclusao();
            } else if (opcao == 4) {
                processaBusca();
            } else if (opcao == 5) {
                System.out.println("Saindo do cadastro de clientes");
                System.exit(0);
            } else {
                System.out.println("Opção inválida!!");
            }
        }
    }

    private void imprimeMenuPrincipal() {
        System.out.println("1- Incluir");
        System.out.println("2- Alterar");
        System.out.println("3- Excluir");
        System.out.println("4- Buscar");
        System.out.println("5- Sair");
        System.out.print("Digite a opção: ");
    }

    private void processaInclusao() {
        Cliente cliente = capturaCliente();
        try {
            clienteMediator.incluir(cliente);
            System.out.println("Cliente incluído com sucesso!");
        } catch (ExcecaoRegistroJaExistente e) {
            System.out.println(e.getMessage());
        } catch (ExcecaoValidacao e) {
            for (String mensagem : e.getMensagens()) {
                System.out.println(mensagem);
            }
        }
    }

    private void processaAlteracao() {
        Cliente cliente = capturaCliente();
        try {
            clienteMediator.alterar(cliente);
            System.out.println("Cliente alterado com sucesso!");
        } catch (ExcecaoRegistroInexistente e) {
            System.out.println(e.getMessage());
        } catch (ExcecaoValidacao e) {
            for (String mensagem : e.getMensagens()) {
                System.out.println(mensagem);
            }
        }
    }

    private void processaExclusao() {
        System.out.print("Digite o CPF do cliente a ser excluído: ");
        String cpf = ENTRADA.next();
        try {
            clienteMediator.excluir(cpf);
            System.out.println("Cliente excluído com sucesso!");
        } catch (ExcecaoRegistroInexistente e) {
            System.out.println(e.getMessage());
        } catch (ExcecaoValidacao e) {
            for (String mensagem : e.getMensagens()) {
                System.out.println(mensagem);
            }
        }
    }

    private void processaBusca() {
        System.out.print("Digite o CPF do cliente a ser buscado: ");
        String cpf = ENTRADA.next();
        try {
            Cliente cliente = clienteMediator.buscar(cpf);
            System.out.println("Cliente encontrado: " + cliente);
        } catch (ExcecaoRegistroInexistente e) {
            System.out.println(e.getMessage());
        }
    }

    private Cliente capturaCliente() {
        System.out.print("Digite o CPF: ");
        String cpf = ENTRADA.next();
        System.out.print("Digite o nome: ");
        String nome = lerString();
        System.out.print("Digite o saldo de pontos: ");
        double saldoPontos = ENTRADA.nextDouble();
        return new Cliente(cpf, nome, saldoPontos);
    }

    private String lerString() {
        try {
            return ENTRADA_STR.readLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

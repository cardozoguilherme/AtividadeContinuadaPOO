package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.cc.poo.ac.utils.DiaDaSemana;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.Scanner;

public class TelaVoo {

    private static final String DIGITE_O_CODIGO = "Digite o código: ";
    private static final String VOO_NAO_ENCONTRADO = "Voo não encontrado!";
    private static final int CODIGO_DESCONHECIDO = -1;
    private static final Scanner ENTRADA = new Scanner(System.in);
    private static final BufferedReader ENTRADA_STR = new BufferedReader(new InputStreamReader(System.in));

    private VooMediator vooMediator = VooMediator.obterInstancia();

    public void inicializaTelasCadastroVoo() {
        while (true) {
            imprimeMenuPrincipal();
            int opcao = ENTRADA.nextInt();
            if (opcao == 1) {
                processaInclusao();
            } else if (opcao == 2) {
                long codigo = processaBusca();
                if (codigo != CODIGO_DESCONHECIDO) {
                    processaAlteracao(codigo);
                }
            } else if (opcao == 3) {
                long codigo = processaBusca();
                if (codigo != CODIGO_DESCONHECIDO) {
                    processaExclusao(codigo);
                }
            } else if (opcao == 4) {
                processaBusca();
            } else if (opcao == 5) {
                System.out.println("Saindo do cadastro de voos");
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
        Voo voo = capturaVoo(CODIGO_DESCONHECIDO);
        String retorno = vooMediator.incluir(voo);
        if (retorno == null) {
            System.out.println("Voo incluído com sucesso!");
        } else {
            System.out.println(retorno);
        }
    }

    private void processaAlteracao(long codigo) {
        Voo voo = capturaVoo(codigo);
        String retorno = vooMediator.alterar(voo);
        if (retorno == null) {
            System.out.println("Voo alterado com sucesso!");
        } else {
            System.out.println(retorno);
        }
    }

    private long processaBusca() {
        System.out.print(DIGITE_O_CODIGO);
        String idVoo = ENTRADA.next();
        Voo voo = vooMediator.buscar(idVoo);
        if (voo == null) {
            System.out.println(VOO_NAO_ENCONTRADO);
            return CODIGO_DESCONHECIDO;
        } else {
            // Mostrar todos os atributos do Voo
            System.out.println("Aeroporto Origem: " + voo.getAeroportoOrigem());
            System.out.println("Aeroporto Destino: " + voo.getAeroportoDestino());
            System.out.println("Companhia Aérea: " + voo.getCompanhiaAerea());
            System.out.println("Número do Voo: " + voo.getNumeroVoo());
            System.out.print("Dias da Semana: ");
            for (DiaDaSemana dia : voo.getDiasDaSemana()) {
                System.out.print(dia.getNome() + " ");
            }
            System.out.println();
            System.out.println("Hora: " + voo.getHora().toString());
            return Long.parseLong(idVoo);
        }
    }

    private void processaExclusao(long codigo) {
        String retorno = vooMediator.excluir(String.valueOf(codigo));
        if (retorno == null) {
            System.out.println("Voo excluído com sucesso!");
        } else {
            System.out.println(retorno);
        }
    }

    private Voo capturaVoo(long codigoDaAlteracao) {
        Voo voo = null;
        long codigo;
        if (codigoDaAlteracao == CODIGO_DESCONHECIDO) {
            System.out.print(DIGITE_O_CODIGO);
            codigo = ENTRADA.nextLong();
        } else {
            codigo = codigoDaAlteracao;
        }
        // Lê todos os atributos do Voo
        System.out.print("Digite o aeroporto de origem: ");
        String aeroportoOrigem = lerString();
        System.out.print("Digite o aeroporto de destino: ");
        String aeroportoDestino = lerString();
        System.out.print("Digite a companhia aérea: ");
        String companhiaAerea = lerString();
        System.out.print("Digite o número do voo: ");
        int numeroVoo = ENTRADA.nextInt();

        // Captura os novos atributos
        System.out.print("Digite os dias da semana (1-Segunda, 2-Terça, ... 7-Domingo, separados por espaço): ");
        String diasInput = ENTRADA.next();
        String[] diasStrings = diasInput.split(" ");
        DiaDaSemana[] diasDaSemana = new DiaDaSemana[diasStrings.length];
        for (int i = 0; i < diasStrings.length; i++) {
            int codigoDia = Integer.parseInt(diasStrings[i]);
            diasDaSemana[i] = DiaDaSemana.getDiaDaSemana(codigoDia);
        }

        System.out.print("Digite a hora (HH:MM): ");
        String horaInput = ENTRADA.next();
        LocalTime hora = LocalTime.parse(horaInput + ":00");

        // Cria instância de Voo com todos os atributos
        voo = new Voo(aeroportoOrigem, aeroportoDestino, companhiaAerea, numeroVoo, diasDaSemana, hora);
        return voo;
    }

    private String lerString() {
        try {
            return ENTRADA_STR.readLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

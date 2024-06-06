package br.edu.cesarschool.cc.poo.ac.relatorios;

import br.edu.cesarschool.cc.poo.ac.passagem.Bilhete;
import br.edu.cesarschool.cc.poo.ac.passagem.BilheteMediator;

import java.time.format.DateTimeFormatter;

public class RelatorioBilhetes {
    public static void gerarRelatorioBilhetesPorPreco() {
        BilheteMediator mediator = BilheteMediator.obterInstancia();
        Bilhete[] bilhetes = mediator.obterBilhetesPorPreco();
        for (Bilhete bilhete : bilhetes) {
            System.out.println(bilhete);
        }
    }

    public static void gerarRelatorioBilhetesPorDH(double precoMin) {
        BilheteMediator mediator = BilheteMediator.obterInstancia();
        Bilhete[] bilhetes = mediator.obterBilhetesPorDataHora(precoMin);
        for (Bilhete bilhete : bilhetes) {
            System.out.println(bilhete.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + " , " + bilhete.getVoo().getAeroportoOrigem() + " , " + bilhete.getVoo().getAeroportoDestino());
        }
    }
}

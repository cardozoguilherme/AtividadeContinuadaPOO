package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.cc.poo.ac.cliente.ClienteMediator;
import br.edu.cesarschool.cc.poo.ac.utils.ValidadorCPF;
import br.edu.cesarschool.cc.poo.ac.passagem.VooMediator;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BilheteMediator {
    private static BilheteMediator instancia;

    private BilheteDAO bilheteDao = new BilheteDAO();

    private BilheteVipDAO bilheteVipDao = new BilheteVipDAO();

    private VooMediator vooMediator = VooMediator.obterInstancia();

    private ClienteMediator clienteMediator = ClienteMediator.obterInstancia();

    private BilheteMediator() {
    }

    public static BilheteMediator obterInstancia() {
        if (instancia == null) {
            instancia = new BilheteMediator();
        }
        return instancia;
    }

    // ATENÇÃO !!!
    public Bilhete buscar(String numeroBilhete) {
        // TODO: problema
        return bilheteDao.buscar(numeroBilhete);
    }

    // ATENÇÃO !!!
    public BilheteVip buscarVip(String numeroBilhete) {
        // TODO: problema
        return bilheteVipDao.buscar(numeroBilhete);
    }

    public String validar(String cpf, String ciaAerea, int numeroVoo, double preco, double
            pagamentoEmPontos, LocalDateTime dataHora) {
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        LocalDateTime dataHora1 = dataHoraAtual.plusHours(1);

        if (!ValidadorCPF.isCpfValido(cpf)) {
            return "CPF errado";
        }

        vooMediator.validarCiaNumero(ciaAerea, numeroVoo);

        if (preco <= 0) {
            return "Preco errado";
        } else if (pagamentoEmPontos < 0) {
            return "Pagamento pontos errado";
        } else if (preco < pagamentoEmPontos) {
            return "Preco menor que pagamento em pontos";
        } else if (!(dataHora.isAfter(dataHora1) || dataHora.equals(dataHora1))) {
            return "deve ser maior ou igual à data e hora atual mais 1h."; // TODO: sem mensagem de erro especificada
        }
        return null;
    }

    public ResultadoGeracaoBilhete gerarBilhete(String cpf, String ciaAerea, int numeroVoo, double
            preco, double pagamentoEmPontos, LocalDateTime dataHora) {

        if (validar(cpf, ciaAerea, numeroVoo, preco, pagamentoEmPontos, dataHora) != null) {
            return new ResultadoGeracaoBilhete(); //TODO: não entendi
        } else {
            
        }
    }
}

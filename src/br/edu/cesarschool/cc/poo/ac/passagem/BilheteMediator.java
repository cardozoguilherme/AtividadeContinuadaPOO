package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.cc.poo.ac.cliente.Cliente;
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

    public Bilhete buscar(String numeroBilhete) {
        return bilheteDao.buscar(numeroBilhete);
    }

    public BilheteVip buscarVip(String numeroBilhete) {
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
            return "data hora invalida";
        }
        return null;
    }

    public ResultadoGeracaoBilhete gerarBilhete(String cpf, String ciaAerea, int numeroVoo, double
            preco, double pagamentoEmPontos, LocalDateTime dataHora) {

        String mensagemDeErro = validar(cpf, ciaAerea, numeroVoo, preco, pagamentoEmPontos, dataHora);

        if (mensagemDeErro != null) {
            return new ResultadoGeracaoBilhete(null, null, mensagemDeErro);
        } else {
            Voo voo = new Voo(null, null, ciaAerea, numeroVoo);
            String idVoo = voo.obterIdVoo();
            Voo vooObtido = vooMediator.buscar(idVoo);

            if(vooObtido == null){
                return new ResultadoGeracaoBilhete(null, null, "Voo nao encontrado");
            } else {
                Cliente clienteBuscado = clienteMediator.buscar(cpf);

                if(clienteBuscado == null){
                    return new ResultadoGeracaoBilhete(null, null, "Cliente nao encontrado");
                } else if(pagamentoEmPontos * 20 > clienteBuscado.getSaldoPontos()) {
                    return new ResultadoGeracaoBilhete(null, null, "Pontos insuficientes");
                } else {
                    Bilhete bilhete = new Bilhete(clienteBuscado, vooObtido, preco, pagamentoEmPontos, dataHora);
                    clienteBuscado.debitarPontos(pagamentoEmPontos * 20);
                    clienteBuscado.creditarPontos(bilhete.obterValorPontuacao());
                    if(!bilheteDao.incluir(bilhete)) {
                        return new ResultadoGeracaoBilhete(null, null, "Bilhete ja existente");
                    } else {
                        String resposta = clienteMediator.alterar(clienteBuscado);
                        if(resposta != null) {
                            return new ResultadoGeracaoBilhete(null, null, resposta);
                        } else {
                            return new ResultadoGeracaoBilhete(bilhete, null, null);
                        }
                    }
                }
            }
        }
    }

    public ResultadoGeracaoBilhete gerarBilheteVip(String cpf, String ciaAerea, int numeroVoo, double
            preco, double pagamentoEmPontos, LocalDateTime dataHora, double bonusPontuacao) {
        String mensagemDeErro = validar(cpf, ciaAerea, numeroVoo, preco, pagamentoEmPontos, dataHora);

        if (mensagemDeErro != null) {
            return new ResultadoGeracaoBilhete(null, null, mensagemDeErro);
        } else {
            if(!(bonusPontuacao <= 0 || bonusPontuacao > 100)){
                return new ResultadoGeracaoBilhete(null, null, "Bonus errado");
            } else {
                Voo voo = new Voo(null, null, ciaAerea, numeroVoo);
                String idVoo = voo.obterIdVoo();
                Voo vooObtido = vooMediator.buscar(idVoo);

                if(vooObtido == null){
                    return new ResultadoGeracaoBilhete(null, null, "Voo nao encontrado");
                } else {
                    Cliente clienteBuscado = clienteMediator.buscar(cpf);

                    if(clienteBuscado == null){
                        return new ResultadoGeracaoBilhete(null, null, "Cliente nao encontrado");
                    } else if(pagamentoEmPontos * 20 > clienteBuscado.getSaldoPontos()) {
                        return new ResultadoGeracaoBilhete(null, null, "Pontos insuficientes");
                    } else {
                        BilheteVip bilheteVip = new BilheteVip(clienteBuscado, vooObtido, preco, pagamentoEmPontos, dataHora, bonusPontuacao);
                        clienteBuscado.debitarPontos(pagamentoEmPontos * 20);
                        clienteBuscado.creditarPontos(bilheteVip.obterValorPontuacaoVip());
                        if(!bilheteVipDao.incluir(bilheteVip)) {
                            return new ResultadoGeracaoBilhete(null, null, "Bilhete vip ja existente");
                        } else {
                            String resposta = clienteMediator.alterar(clienteBuscado);
                            if(resposta != null) {
                                return new ResultadoGeracaoBilhete(null, null, resposta);
                            } else {
                                return new ResultadoGeracaoBilhete(null, bilheteVip, null);
                            }
                        }
                    }
                }
            }
        }
    }
}
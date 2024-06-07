package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.cc.poo.ac.cliente.Cliente;
import br.edu.cesarschool.cc.poo.ac.cliente.ClienteMediator;
import br.edu.cesarschool.cc.poo.ac.negocio.comparadores.ComparadorBilheteDataHora;
import br.edu.cesarschool.cc.poo.ac.negocio.comparadores.ComparadorBilhetePreco;
import br.edu.cesarschool.cc.poo.ac.utils.DiaDaSemana;
import br.edu.cesarschool.cc.poo.ac.utils.ValidadorCPF;
import br.edu.cesarschool.cc.poo.ac.utils.ordenacao.Ordenadora;

import java.time.LocalDateTime;
import java.util.Arrays;

public class BilheteMediator {
	private static final String BILHETE_INEXISTENTE = "Bilhete inexistente";
	private static BilheteMediator instancia;

	private BilheteDAO bilheteDao = new BilheteDAO();
	private BilheteVipDAO bilheteVipDao = new BilheteVipDAO();
	private ClienteMediator clienteMediator = ClienteMediator.obterInstancia();
	private VooMediator vooMediator = VooMediator.obterInstancia();

	public static BilheteMediator obterInstancia() {
		if (instancia == null) {
			instancia = new BilheteMediator();
		}
		return instancia;
	}

	private BilheteMediator() {
	}

	public Bilhete buscar(String numeroBilhete) {
		return bilheteDao.buscar(numeroBilhete);
	}

	public BilheteVip buscarVip(String numeroBilhete) {
		return bilheteVipDao.buscar(numeroBilhete);
	}

	public String validar(String cpf, String ciaAerea, int numeroVoo, double preco, double pagamentoEmPontos, LocalDateTime dataHora) {
		if (!ValidadorCPF.isCpfValido(cpf)) {
			return "CPF errado";
		} else {
			String msg = vooMediator.validarCiaNumero(ciaAerea, numeroVoo);
			if (msg != null) {
				return msg;
			}
		}
		if (preco <= 0) {
			return "Preco errado";
		}
		if (pagamentoEmPontos < 0) {
			return "Pagamento pontos errado";
		}
		if (preco < pagamentoEmPontos) {
			return "Preco menor que pagamento em pontos";
		}
		if (dataHora == null || LocalDateTime.now().plusHours(1).isAfter(dataHora)) {
			return "data hora invalida";
		}

		Voo voo = vooMediator.buscar(ciaAerea + numeroVoo);
		if (voo == null) {
			return "Voo nao encontrado";
		}

		DiaDaSemana[] diasDaSemana = voo.getDiasDaSemana();
		if (diasDaSemana != null && diasDaSemana.length > 0) {
			boolean diaDisponivel = false;
			for (DiaDaSemana dia : diasDaSemana) {
				if (dia.getCodigo() == dataHora.getDayOfWeek().getValue()) {
					diaDisponivel = true;
					break;
				}
			}
			if (!diaDisponivel) {
				return "Voo nao disponivel na data";
			}
		}

		if (voo.getHora() != null) {
			if (voo.getHora().getHour() != dataHora.getHour() || voo.getHora().getMinute() != dataHora.getMinute()) {
				return "Hora diferente da especificada no voo";
			}
		}

		return null;
	}

	public ResultadoGeracaoBilhete gerarBilhete(String cpf, String ciaAerea, int numeroVoo, double preco, double pagamentoEmPontos, LocalDateTime dataHora) {
		String msg = validar(cpf, ciaAerea, numeroVoo, preco, pagamentoEmPontos, dataHora);
		if (msg != null) {
			return new ResultadoGeracaoBilhete(null, null, msg);
		}
		Voo voo = vooMediator.buscar(ciaAerea + numeroVoo);
		Cliente cliente = clienteMediator.buscar(cpf);
		if (cliente == null) {
			return new ResultadoGeracaoBilhete(null, null, "Cliente nao encontrado");
		}

		double pontosNecessarios = pagamentoEmPontos * 20;
		if (cliente.getSaldoPontos() < pontosNecessarios) {
			return new ResultadoGeracaoBilhete(null, null, "Pontos insuficientes");
		}

		Bilhete bilhete = new Bilhete(cliente, voo, preco, pagamentoEmPontos, dataHora);
		cliente.debitarPontos(pontosNecessarios);
		cliente.creditarPontos(bilhete.obterValorPontuacao());
		if (!bilheteDao.incluir(bilhete)) {
			return new ResultadoGeracaoBilhete(null, null, "Bilhete ja existente");
		}
		String alteracaoMsg = clienteMediator.alterar(cliente);
		if (alteracaoMsg != null) {
			return new ResultadoGeracaoBilhete(null, null, alteracaoMsg);
		}
		return new ResultadoGeracaoBilhete(bilhete, null, null);
	}

	public ResultadoGeracaoBilhete gerarBilheteVip(String cpf, String ciaAerea, int numeroVoo, double preco, double pagamentoEmPontos, LocalDateTime dataHora, double bonusPontuacao) {
		String msg = validar(cpf, ciaAerea, numeroVoo, preco, pagamentoEmPontos, dataHora);
		if (msg != null) {
			return new ResultadoGeracaoBilhete(null, null, msg);
		}
		Voo voo = vooMediator.buscar(ciaAerea + numeroVoo);
		Cliente cliente = clienteMediator.buscar(cpf);
		if (cliente == null) {
			return new ResultadoGeracaoBilhete(null, null, "Cliente nao encontrado");
		}

		double pontosNecessarios = pagamentoEmPontos * 20;
		if (cliente.getSaldoPontos() < pontosNecessarios) {
			return new ResultadoGeracaoBilhete(null, null, "Pontos insuficientes");
		}

		BilheteVip bilheteVip = new BilheteVip(cliente, voo, preco, pagamentoEmPontos, dataHora, bonusPontuacao);
		cliente.debitarPontos(pontosNecessarios);
		cliente.creditarPontos(bilheteVip.obterValorPontuacaoVip());
		if (!bilheteVipDao.incluir(bilheteVip)) {
			return new ResultadoGeracaoBilhete(null, null, "Bilhete ja existente");
		}
		String alteracaoMsg = clienteMediator.alterar(cliente);
		if (alteracaoMsg != null) {
			return new ResultadoGeracaoBilhete(null, null, alteracaoMsg);
		}
		return new ResultadoGeracaoBilhete(null, bilheteVip, null);
	}

	public Bilhete[] obterBilhetesPorPreco() {
		Bilhete[] bilhetes = bilheteDao.buscarTodos();
		if (bilhetes != null) {
			Ordenadora.ordenar(bilhetes, new ComparadorBilhetePreco());
		}
		return bilhetes;
	}

	public Bilhete[] obterBilhetesPorDataHora(double precoMin) {
		Bilhete[] bilhetes = bilheteDao.buscarTodos();
		if (bilhetes != null) {
			bilhetes = Arrays.stream(bilhetes)
					.filter(b -> b.getPreco() >= precoMin)
					.toArray(Bilhete[]::new);
			Arrays.sort(bilhetes, (b1, b2) -> b2.getDataHora().compareTo(b1.getDataHora()));
		}
		return bilhetes;
	}
}

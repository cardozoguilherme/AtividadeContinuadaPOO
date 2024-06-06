package br.edu.cesarschool.cc.poo.ac.testes;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.edu.cesarschool.cc.poo.ac.cliente.Cliente;
import br.edu.cesarschool.cc.poo.ac.passagem.ResultadoGeracaoBilhete;
import br.edu.cesarschool.cc.poo.ac.passagem.Voo;
import br.edu.cesarschool.cc.poo.ac.utils.DiaDaSemana;

public class TestesAc06 extends TesteGeral {
	
	private static final String HORA_INVALIDA = "Hora invalida";
	private static final String DIA_DA_SEMANA_NAO_INFORMADO = "Dias da semana nao informados";
	private static final String HORA_DIFERENTE_DA_ESPECIFICADA_NO_VOO = "Hora diferente da especificada no voo";
	private static final String VOO_NAO_DISPONIVEL_NA_DATA = "Voo nao disponivel na data";
	
	@Test
	public void testCadVoo13() {		
		excluirCadastros();
		Voo voo = new Voo(VCP, PNZ, "LK", 8877);
		Assertions.assertEquals(DIA_DA_SEMANA_NAO_INFORMADO, vooMed.validar(voo));
		Assertions.assertEquals(0, obterQtdArquivosDir(DIR_VOO));
		voo = new Voo(VCP, PNZ, "LK", 8877, new DiaDaSemana[0], HORA);
		Assertions.assertEquals(DIA_DA_SEMANA_NAO_INFORMADO, vooMed.incluir(voo));
		Assertions.assertEquals(0, obterQtdArquivosDir(DIR_VOO));		
		voo = new Voo(VCP, PNZ, "KA", 8878, 
				new DiaDaSemana[] {DiaDaSemana.DOMINGO, DiaDaSemana.SABADO, DiaDaSemana.DOMINGO}, HORA) ;
		Assertions.assertEquals("Dia da semana repetido", vooMed.incluir(voo));
		Assertions.assertEquals(0, obterQtdArquivosDir(DIR_VOO));
	}
	@Test
	public void testCadVoo14() {		
		excluirCadastros();
		Voo voo = new Voo(VCP, PNZ, "LU", 8876, DIAS, null);
		Assertions.assertEquals("Hora nao informada", vooMed.alterar(voo));
		Assertions.assertEquals(0, obterQtdArquivosDir(DIR_VOO));
		voo = new Voo(VCP, PNZ, "II", 8872, DIAS, LocalTime.of(0, 0, 11)) ;
		Assertions.assertEquals(HORA_INVALIDA, vooMed.incluir(voo));
		Assertions.assertEquals(0, obterQtdArquivosDir(DIR_VOO));
		voo = new Voo(VCP, PNZ, "OU", 8874, DIAS, LocalTime.of(1, 33, 0, 23)) ;
		Assertions.assertEquals(HORA_INVALIDA, vooMed.validar(voo));
		Assertions.assertEquals(0, obterQtdArquivosDir(DIR_VOO));		
	}
	@Test
	public void testBilhete16() {
		excluirCadastros();
		String cpf = CPF_VALIDO;
		String ciaAerea = "XX";
		int numeroVoo = 2001;
		double preco = 7800;
		double pagamentoEmPontos = 0;
		double bonus = 25;
		LocalDateTime dataHora = LocalDateTime.parse("2240-04-10T11:00:00");
		Voo voo = new Voo("FOR", "BEL", ciaAerea, numeroVoo, DIAS, HORA);
		Cliente cli = new Cliente(cpf, "CLAUDIO", 67000);
		cadastroVoo.incluir(voo, voo.obterIdVoo());
		cadastroCli.incluir(cli, cli.getCpf());
		ResultadoGeracaoBilhete res = biMed.gerarBilhete(cpf, ciaAerea, numeroVoo, 
				preco, pagamentoEmPontos, dataHora);
		ResultadoGeracaoBilhete res1 = biMed.gerarBilheteVip(cpf, ciaAerea, numeroVoo, 
				preco, pagamentoEmPontos, dataHora, bonus);
		assertResErro(res, VOO_NAO_DISPONIVEL_NA_DATA);
		assertResErro(res1, VOO_NAO_DISPONIVEL_NA_DATA);
		Assertions.assertEquals(0, obterQtdArquivosDir(DIR_BILHETE));
		Assertions.assertEquals(0, obterQtdArquivosDir(DIR_BILHETE_VIP));
	}
	@Test
	public void testBilhete17() {
		excluirCadastros();
		String cpf = CPF_VALIDO;
		String ciaAerea = "ZZ";
		int numeroVoo = 2002;
		double preco = 2001;
		double pagamentoEmPontos = 100;
		double bonus = 11;
		LocalDateTime dataHora1 = LocalDateTime.parse("2240-04-12T11:02:00");
		LocalDateTime dataHora2 = LocalDateTime.parse("2240-04-12T12:00:00");
		Voo voo = new Voo("REC", "GRU", ciaAerea, numeroVoo, DIAS, HORA);
		Cliente cli = new Cliente(cpf, "MARCILIO", 70000);
		cadastroVoo.incluir(voo, voo.obterIdVoo());
		cadastroCli.incluir(cli, cli.getCpf());
		ResultadoGeracaoBilhete res = biMed.gerarBilhete(cpf, ciaAerea, numeroVoo, 
				preco, pagamentoEmPontos, dataHora1);
		ResultadoGeracaoBilhete res1 = biMed.gerarBilheteVip(cpf, ciaAerea, numeroVoo, 
				preco, pagamentoEmPontos, dataHora1, bonus);
		assertResErro(res, HORA_DIFERENTE_DA_ESPECIFICADA_NO_VOO);
		assertResErro(res1, HORA_DIFERENTE_DA_ESPECIFICADA_NO_VOO);
		res = biMed.gerarBilhete(cpf, ciaAerea, numeroVoo, 
				preco, pagamentoEmPontos, dataHora2);
		res1 = biMed.gerarBilheteVip(cpf, ciaAerea, numeroVoo, 
				preco, pagamentoEmPontos, dataHora2, bonus);
		assertResErro(res, HORA_DIFERENTE_DA_ESPECIFICADA_NO_VOO);
		assertResErro(res1, HORA_DIFERENTE_DA_ESPECIFICADA_NO_VOO);		
		Assertions.assertEquals(0, obterQtdArquivosDir(DIR_BILHETE));
		Assertions.assertEquals(0, obterQtdArquivosDir(DIR_BILHETE_VIP));
	}
}

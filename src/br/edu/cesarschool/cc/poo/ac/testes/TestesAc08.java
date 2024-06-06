package br.edu.cesarschool.cc.poo.ac.testes;

import java.lang.reflect.Modifier;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.edu.cesarschool.cc.poo.ac.cliente.Cliente;
import br.edu.cesarschool.cc.poo.ac.cliente.ClienteMediator;
import br.edu.cesarschool.cc.poo.ac.negocio.comparadores.ComparadorBilheteDataHora;
import br.edu.cesarschool.cc.poo.ac.negocio.comparadores.ComparadorBilhetePreco;
import br.edu.cesarschool.cc.poo.ac.passagem.Bilhete;
import br.edu.cesarschool.cc.poo.ac.passagem.BilheteMediator;
import br.edu.cesarschool.cc.poo.ac.passagem.Voo;
import br.edu.cesarschool.cc.poo.ac.utils.ordenacao.Comparador;
import br.edu.cesarschool.cc.poo.ac.utils.ordenacao.Comparavel;
import br.edu.cesarschool.cc.poo.ac.utils.ordenacao.Ordenadora;

public class TestesAc08 extends TesteGeral {
	private static final String ZOROBABEL = "ZOROBABEL";
	private static final String MARIA = "MARIA";
	private static final String CLAUDIA = "CLAUDIA";
	private static final String ABIMAEL = "ABIMAEL";
	private RegistroOrd[] regs = {
			new RegistroOrd(3,"EDUARDO"),
			new RegistroOrd(4,"ZOROASTRO"),
			new RegistroOrd(1,"ALBERTO"),
			new RegistroOrd(2,"CIENTO"),
	};
	private Cliente cli = new Cliente(CPF_VALIDO, "MARIO", 1);
	private Voo voo = new Voo("REC", "GRU", "JJ", 2020);
	private Voo voo1 = new Voo("GIG", "SSA", "HH", 2021);
	private Voo voo2 = new Voo("SDU", "POA", "XX", 2022);
	private Voo voo3 = new Voo("CGH", "CWB", "ZZ", 2023);
	private ClienteMediator cliMediator = ClienteMediator.obterInstancia();
	private BilheteMediator bilMediator = BilheteMediator.obterInstancia();
	@Test
	public void test01() {
		ComparadorBilhetePreco cbp = new ComparadorBilhetePreco();
		ComparadorBilheteDataHora cbdh = new ComparadorBilheteDataHora();
		
		Assertions.assertTrue(cbp instanceof Comparador);
		Assertions.assertTrue(cbdh instanceof Comparador);
		Assertions.assertTrue(cli instanceof Comparavel);
		Assertions.assertTrue(Modifier.isInterface(Comparador.class.getModifiers()));
		Assertions.assertTrue(Modifier.isInterface(Comparavel.class.getModifiers()));
	}
	@Test
	public void test02() {
		Ordenadora.ordenar(regs);
		Assertions.assertEquals(1,regs[0].codigo);
		Assertions.assertEquals(2,regs[1].codigo);
		Assertions.assertEquals(3,regs[2].codigo);
		Assertions.assertEquals(4,regs[3].codigo);		
	}
	@Test
	public void test03() {
		Ordenadora.ordenar(regs, new ComparadorCodigo());
		Assertions.assertEquals(1,regs[0].codigo);
		Assertions.assertEquals(2,regs[1].codigo);
		Assertions.assertEquals(3,regs[2].codigo);
		Assertions.assertEquals(4,regs[3].codigo);		
	}
	@Test
	public void test04() {
		Cliente[] clis = {new Cliente("1", ZOROBABEL, 1000.0),
				new Cliente("2", CLAUDIA, 1000.0),
				new Cliente("3", ABIMAEL, 1000.0),
				new Cliente("4", MARIA, 1000.0)};
		Ordenadora.ordenar(clis);
		Assertions.assertEquals(ABIMAEL, clis[0].getNome());
		Assertions.assertEquals(CLAUDIA, clis[1].getNome());
		Assertions.assertEquals(MARIA, clis[2].getNome());
		Assertions.assertEquals(ZOROBABEL, clis[3].getNome());
	}	
	@Test
	public void test05() {
		Bilhete[] bils = {
				new Bilhete(cli, voo, 1000, 0, LocalDateTime.parse("2023-01-01T15:20:00")),
				new Bilhete(cli, voo1, 3000, 0, LocalDateTime.parse("2024-06-01T23:00:00")),
				new Bilhete(cli, voo2, 500, 0, LocalDateTime.parse("2024-08-22T00:30:00")),
				new Bilhete(cli, voo3, 2000, 0, LocalDateTime.parse("2024-01-01T07:45:00")),
		};
		Ordenadora.ordenar(bils, new ComparadorBilheteDataHora());
		Assertions.assertEquals(500, bils[0].getPreco());
		Assertions.assertEquals(3000, bils[1].getPreco());
		Assertions.assertEquals(2000, bils[2].getPreco());
		Assertions.assertEquals(1000, bils[3].getPreco());
	}	
	@Test
	public void test06() {
		Bilhete[] bils = {
				new Bilhete(cli, voo, 1000, 0, LocalDateTime.now()),
				new Bilhete(cli, voo1, 3000, 0, LocalDateTime.now()),
				new Bilhete(cli, voo2, 500, 0, LocalDateTime.now()),
				new Bilhete(cli, voo3, 2000, 0, LocalDateTime.now()),
		};
		Ordenadora.ordenar(bils, new ComparadorBilhetePreco());
		Assertions.assertEquals(500, bils[0].getPreco());
		Assertions.assertEquals(1000, bils[1].getPreco());
		Assertions.assertEquals(2000, bils[2].getPreco());
		Assertions.assertEquals(3000, bils[3].getPreco());
	}	
	@Test
	public void test07() {
		excluirCadastros();
		String cpf1 = "01";
		String cpf2 = "02";
		String cpf3 = "03";
		String cpf4 = "04";
		cadastroCli.incluir(new Cliente(cpf1, ZOROBABEL, 100.0), cpf1);
		cadastroCli.incluir(new Cliente(cpf2, CLAUDIA, 200.0), cpf2);
		cadastroCli.incluir(new Cliente(cpf3, ABIMAEL, 700.0), cpf3);
		cadastroCli.incluir(new Cliente(cpf4, MARIA, 300.0), cpf4);
		Cliente[] clis = cliMediator.obterClientesPorNome();
		Assertions.assertEquals(ABIMAEL, clis[0].getNome());
		Assertions.assertEquals(CLAUDIA, clis[1].getNome());
		Assertions.assertEquals(MARIA, clis[2].getNome());
		Assertions.assertEquals(ZOROBABEL, clis[3].getNome());
	}		
	@Test
	public void test08() {
		excluirCadastros();
		double p1 = 3200;
		double p2 = 5500;
		double p3 = 10000;
		double p4 = 15500;
		Bilhete b1 = new Bilhete(cli, voo, p3, 0, LocalDateTime.now());
		Bilhete b2 = new Bilhete(cli, voo1, p2, 0, LocalDateTime.now());
		Bilhete b3 = new Bilhete(cli, voo2, p4, 0, LocalDateTime.now());
		Bilhete b4 = new Bilhete(cli, voo3, p1, 0, LocalDateTime.now());
		cadastroBil.incluir(b1, b1.getIdUnico());
		cadastroBil.incluir(b2, b2.getIdUnico());
		cadastroBil.incluir(b3, b3.getIdUnico());
		cadastroBil.incluir(b4, b4.getIdUnico());
		Bilhete[] bils = bilMediator.obterBilhetesPorPreco();
		Assertions.assertEquals(p1, bils[0].getPreco());
		Assertions.assertEquals(p2, bils[1].getPreco());
		Assertions.assertEquals(p3, bils[2].getPreco());
		Assertions.assertEquals(p4, bils[3].getPreco());
	}		
	@Test
	public void test09() {
		excluirCadastros();
		double p1 = 3100;
		double p2 = 5100;
		double p3 = 11000;
		double p4 = 17500;
		double p5 = 18500;
		LocalDateTime dh1 = LocalDateTime.parse("2020-01-01T15:20:00");
		LocalDateTime dh2 = LocalDateTime.parse("2020-09-23T12:00:00");
		LocalDateTime dh3 = LocalDateTime.parse("2021-02-14T11:00:00");
		LocalDateTime dh4 = LocalDateTime.parse("2021-02-14T22:30:00");
		LocalDateTime dh5 = LocalDateTime.parse("2024-05-03T05:00:00");
		Bilhete b1 = new Bilhete(cli, voo, p1, 0, dh1);
		Bilhete b2 = new Bilhete(cli, voo1, p2, 0, dh3);
		Bilhete b3 = new Bilhete(cli, voo2, p3, 0, dh2);
		Bilhete b4 = new Bilhete(cli, voo3, p4, 0, dh5);
		Bilhete b5 = new Bilhete(cli, voo3, p5, 0, dh4);
		cadastroBil.incluir(b1, b1.getIdUnico());
		cadastroBil.incluir(b2, b2.getIdUnico());
		cadastroBil.incluir(b3, b3.getIdUnico());
		cadastroBil.incluir(b4, b4.getIdUnico());
		cadastroBil.incluir(b5, b5.getIdUnico());
		Bilhete[] bils = bilMediator.obterBilhetesPorDataHora(p2);
		Assertions.assertEquals(4, bils.length);
		Assertions.assertEquals(dh5, bils[0].getDataHora());
		Assertions.assertEquals(dh4, bils[1].getDataHora());
		Assertions.assertEquals(dh3, bils[2].getDataHora());
		Assertions.assertEquals(dh2, bils[3].getDataHora());
	}	
	static class RegistroOrd implements Comparavel {
		int codigo;
		String nome;
		RegistroOrd(int codigo, String nome) {
			this.codigo = codigo;
			this.nome = nome;
		}
		@Override
		public int comparar(Object o1) {			
			return nome.compareTo(((RegistroOrd)o1).nome);
		}		
	}
	static class ComparadorCodigo implements Comparador {
		@Override
		public int comparar(Object o1, Object o2) {
			RegistroOrd r1 = (RegistroOrd)o1;
			RegistroOrd r2 = (RegistroOrd)o2;
			if (r1.codigo > r2.codigo) {
				return 1;
			} else if (r1.codigo < r2.codigo) {
				return -1;
			} else {
				return 0;
			}
		}		
	}
}

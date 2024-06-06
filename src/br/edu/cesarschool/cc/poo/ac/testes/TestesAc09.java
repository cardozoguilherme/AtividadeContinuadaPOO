package br.edu.cesarschool.cc.poo.ac.testes;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.edu.cesarschool.cc.poo.ac.cliente.Cliente;
import br.edu.cesarschool.cc.poo.ac.cliente.comexcecao.ClienteDAO;
import br.edu.cesarschool.cc.poo.ac.cliente.comexcecao.ClienteMediator;
import br.edu.cesarschool.cc.poo.ac.cliente.comexcecao.ExcecaoRegistroInexistente;
import br.edu.cesarschool.cc.poo.ac.cliente.comexcecao.ExcecaoRegistroJaExistente;
import br.edu.cesarschool.cc.poo.ac.cliente.comexcecao.ExcecaoValidacao;
public class TestesAc09 extends TesteGeral {
	private static final String CLIENTE_EXISTENTE = "Cliente existente";
	private static final String SALDO_ERRADO = "saldo errado";
	private static final String NOME_ERRADO = "nome errado";
	private static final String CPF_ERRADO = "CPF errado";
	private static final String CLIENTE_INEXISTENTE = "Cliente inexistente";
	private ClienteDAO dao = new ClienteDAO();
	private ClienteMediator med = ClienteMediator.obterInstancia();
	@Test
	public void test01() {
		excluirCadastros();
		Cliente cli = new Cliente(CPF_VALIDO, "MARIO", 1);
		cadastroCli.incluir(cli, cli.getIdUnico());
		try {
			dao.incluir(cli);
			Assertions.fail();
		} catch (ExcecaoRegistroJaExistente e) {
			Assertions.assertEquals(CLIENTE_EXISTENTE, e.getMessage());
			Assertions.assertEquals(1, obterQtdArquivosDir(DIR_CLIENTE));
		}
	}
	@Test
	public void test02() {
		excluirCadastros();
		Cliente cli1 = new Cliente(OUTRO_CPF_VALIDO, "CARLOS", 1);
		Cliente cli2 = new Cliente(CPF_VALIDO, "JOSA", 2);
		cadastroCli.incluir(cli1, cli1.getIdUnico());
		try {
			dao.incluir(cli2);
			Assertions.assertEquals(2, obterQtdArquivosDir(DIR_CLIENTE));
			Cliente cliNew = (Cliente)cadastroCli.buscar(cli2.getIdUnico());
			Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(cli2, cliNew));
		} catch (ExcecaoRegistroJaExistente e) {
			Assertions.fail();						
		}
	}
	@Test
	public void test03() {
		excluirCadastros();
		Cliente cli = new Cliente(CPF_VALIDO, "ERALDO", 3);
		Cliente cl2 = new Cliente(OUTRO_CPF_VALIDO, "CARLA", 4);
		cadastroCli.incluir(cli, cli.getIdUnico());
		try {
			dao.alterar(cl2);
			Assertions.fail();
		} catch (ExcecaoRegistroInexistente e) {
			Assertions.assertEquals(CLIENTE_INEXISTENTE, e.getMessage());
			Assertions.assertEquals(1, obterQtdArquivosDir(DIR_CLIENTE));
		}
	}
	@Test
	public void test04() {
		excluirCadastros();
		Cliente cli = new Cliente(CPF_VALIDO, "BIBIANA", 5);
		Cliente cl2 = new Cliente(CPF_VALIDO, "SERGE", 6);
		cadastroCli.incluir(cli, cli.getIdUnico());
		try {
			dao.alterar(cl2);
			Assertions.assertEquals(1, obterQtdArquivosDir(DIR_CLIENTE));
			Cliente cliNew = (Cliente)cadastroCli.buscar(cl2.getIdUnico());
			Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(cl2, cliNew));
		} catch (ExcecaoRegistroInexistente e) {
			Assertions.fail();			
		}
	}
	@Test
	public void test05() {
		excluirCadastros();
		Cliente cli = new Cliente(CPF_VALIDO, "BRAZAO", 7);
		cadastroCli.incluir(cli, cli.getIdUnico());
		try {
			dao.excluir(OUTRO_CPF_VALIDO);
			Assertions.fail();
		} catch (ExcecaoRegistroInexistente e) {
			Assertions.assertEquals(CLIENTE_INEXISTENTE, e.getMessage());
			Assertions.assertEquals(1, obterQtdArquivosDir(DIR_CLIENTE));
		}
	}
	@Test
	public void test06() {
		excluirCadastros();
		Cliente cli = new Cliente(CPF_VALIDO, "VANJA", 8);
		cadastroCli.incluir(cli, cli.getIdUnico());
		try {
			dao.excluir(CPF_VALIDO);
			Assertions.assertEquals(0, obterQtdArquivosDir(DIR_CLIENTE));
			Cliente cliNew = (Cliente)cadastroCli.buscar(cli.getIdUnico());
			Assertions.assertNull(cliNew);
		} catch (ExcecaoRegistroInexistente e) {
			Assertions.fail();			
			
		}
	}
	@Test
	public void test07() {
		excluirCadastros();
		Cliente cli = new Cliente(CPF_VALIDO, "MARLY", 9);
		cadastroCli.incluir(cli, cli.getIdUnico());
		try {
			dao.buscar(OUTRO_CPF_VALIDO);
			Assertions.fail();
		} catch (ExcecaoRegistroInexistente e) {
			Assertions.assertEquals(CLIENTE_INEXISTENTE, e.getMessage());
			Assertions.assertEquals(1, obterQtdArquivosDir(DIR_CLIENTE));
		}
	}
	@Test
	public void test08() {
		excluirCadastros();
		Cliente cli = new Cliente(CPF_VALIDO, "BERNO", 10);
		cadastroCli.incluir(cli, cli.getIdUnico());
		try {
			Cliente cliBus = dao.buscar(CPF_VALIDO);
			Assertions.assertNotNull(cliBus);
			Assertions.assertEquals(1, obterQtdArquivosDir(DIR_CLIENTE));
			Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(cli, cliBus));
		} catch (ExcecaoRegistroInexistente e) {
			Assertions.fail();						
		}
	}
	@Test
	public void test09() {
		excluirCadastros();
		Cliente cli = new Cliente(null, "", -20);
		try {
			med.incluir(cli);
			Assertions.fail();
		} catch (ExcecaoValidacao e) {
			List<String> mensagens = e.getMensagens();
			Assertions.assertNotNull(mensagens);
			Assertions.assertEquals(3, mensagens.size());
			Assertions.assertEquals(CPF_ERRADO, mensagens.get(0));
			Assertions.assertEquals(NOME_ERRADO, mensagens.get(1));
			Assertions.assertEquals(SALDO_ERRADO, mensagens.get(2));
		} catch (ExcecaoRegistroJaExistente e) {
			Assertions.fail();
		}
	}	
	@Test
	public void test11() {
		excluirCadastros();
		Cliente cli = new Cliente(null, "  ", -50);
		try {
			med.alterar(cli);
			Assertions.fail();
		} catch (ExcecaoValidacao e) {
			List<String> mensagens = e.getMensagens();
			Assertions.assertNotNull(mensagens);
			Assertions.assertEquals(3, mensagens.size());
			Assertions.assertEquals(CPF_ERRADO, mensagens.get(0));
			Assertions.assertEquals(NOME_ERRADO, mensagens.get(1));
			Assertions.assertEquals(SALDO_ERRADO, mensagens.get(2));
		} catch (ExcecaoRegistroInexistente e) {
			Assertions.fail();
		}
	}	
	@Test
	public void test12() {
		excluirCadastros();
		Cliente cli = new Cliente(CPF_VALIDO, "PAULO", 11);
		cadastroCli.incluir(cli, cli.getIdUnico());
		try {
			med.incluir(cli);
			Assertions.fail();
		} catch (ExcecaoRegistroJaExistente e) {
			Assertions.assertEquals(CLIENTE_EXISTENTE, e.getMessage());
			Assertions.assertEquals(1, obterQtdArquivosDir(DIR_CLIENTE));
		} catch (ExcecaoValidacao e) {
			Assertions.fail();
		}
	}
	@Test
	public void test13() {
		excluirCadastros();
		Cliente cli = new Cliente(CPF_VALIDO, "KARINO", 12);
		Cliente cli2 = new Cliente(OUTRO_CPF_VALIDO, "BARNEY", 13);
		cadastroCli.incluir(cli, cli.getIdUnico());
		try {
			med.alterar(cli2);
			Assertions.fail();
		} catch (ExcecaoRegistroInexistente e) {
			Assertions.assertEquals(CLIENTE_INEXISTENTE, e.getMessage());
			Assertions.assertEquals(1, obterQtdArquivosDir(DIR_CLIENTE));
		} catch (ExcecaoValidacao e) {
			Assertions.fail();
		}
	}
	@Test
	public void test14() {
		excluirCadastros();
		Cliente cli = new Cliente(CPF_VALIDO, "MARIANO", 14);
		Cliente cli2 = new Cliente(OUTRO_CPF_VALIDO, "GERMANA", 15);
		cadastroCli.incluir(cli, cli.getIdUnico());
		try {
			med.incluir(cli2);
			Assertions.assertEquals(2, obterQtdArquivosDir(DIR_CLIENTE));
			Cliente cliNew = (Cliente)cadastroCli.buscar(cli2.getIdUnico());
			Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(cli2, cliNew));			
		} catch (ExcecaoRegistroJaExistente e) {
			Assertions.fail();			
		} catch (ExcecaoValidacao e) {
			Assertions.fail();
		}
	}
	@Test
	public void test15() {
		excluirCadastros();
		Cliente cli = new Cliente(CPF_VALIDO, "KAMILA", 16);
		Cliente cli2 = new Cliente(CPF_VALIDO, "CLAUDIO", 17);
		cadastroCli.incluir(cli, cli.getIdUnico());
		try {
			med.alterar(cli2);
			Assertions.assertEquals(1, obterQtdArquivosDir(DIR_CLIENTE));
			Cliente cliNew = (Cliente)cadastroCli.buscar(cli2.getIdUnico());
			Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(cli2, cliNew));			
		} catch (ExcecaoRegistroInexistente e) {
			Assertions.fail();			
		} catch (ExcecaoValidacao e) {
			Assertions.fail();
		}
	}
	@Test
	public void test16() {
		excluirCadastros();
		Cliente cli = new Cliente(CPF_VALIDO, "ADO", 18);
		cadastroCli.incluir(cli, cli.getIdUnico());
		try {
			med.excluir(OUTRO_CPF_VALIDO);
			Assertions.fail();
		} catch (ExcecaoRegistroInexistente e) {
			Assertions.assertEquals(CLIENTE_INEXISTENTE, e.getMessage());
			Assertions.assertEquals(1, obterQtdArquivosDir(DIR_CLIENTE));
		} catch (ExcecaoValidacao e) {
			Assertions.fail();
		}
	}
	@Test
	public void test17() {
		excluirCadastros();
		Cliente cli = new Cliente(CPF_VALIDO, "MELL", 19);
		cadastroCli.incluir(cli, cli.getIdUnico());
		try {
			med.excluir("ASSC121");
			Assertions.fail();
		} catch (ExcecaoRegistroInexistente e) {
			Assertions.fail();
		} catch (ExcecaoValidacao e) {
			Assertions.assertEquals(1, obterQtdArquivosDir(DIR_CLIENTE));
			List<String> mensagens = e.getMensagens();
			Assertions.assertNotNull(mensagens);
			Assertions.assertEquals(1, mensagens.size());
			Assertions.assertEquals(CPF_ERRADO, mensagens.get(0));			
		}
	}
	@Test
	public void test18() {
		excluirCadastros();
		Cliente cli = new Cliente(CPF_VALIDO, "GILBERTO", 20);
		cadastroCli.incluir(cli, cli.getIdUnico());
		try {
			med.excluir(CPF_VALIDO);
			Assertions.assertEquals(0, obterQtdArquivosDir(DIR_CLIENTE));
			Cliente cliNew = (Cliente)cadastroCli.buscar(cli.getIdUnico());
			Assertions.assertNull(cliNew);			
		} catch (ExcecaoRegistroInexistente e) {
			Assertions.fail();			
		} catch (ExcecaoValidacao e) {
			Assertions.fail();
		}
	}
	@Test
	public void test19() {
		excluirCadastros();
		Cliente cli = new Cliente(CPF_VALIDO, "VANZO", 21);
		cadastroCli.incluir(cli, cli.getIdUnico());
		try {
			med.buscar(OUTRO_CPF_VALIDO);
			Assertions.fail();			
		} catch (ExcecaoRegistroInexistente e) {
			Assertions.assertEquals(CLIENTE_INEXISTENTE, e.getMessage());
			Assertions.assertEquals(1, obterQtdArquivosDir(DIR_CLIENTE));			
		}
	}
	@Test
	public void test20() {
		excluirCadastros();
		Cliente cli = new Cliente(CPF_VALIDO, "MARCELO", 22);
		cadastroCli.incluir(cli, cli.getIdUnico());
		try {
			Cliente cliBus = med.buscar(CPF_VALIDO);
			Assertions.assertNotNull(cliBus);
			Assertions.assertEquals(1, obterQtdArquivosDir(DIR_CLIENTE));
			Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(cli, cliBus));
		} catch (ExcecaoRegistroInexistente e) {
			Assertions.fail();						
		}
	}
}

package br.edu.cesarschool.cc.poo.ac.testes;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.edu.cesarschool.cc.poo.ac.cliente.Cliente;
import br.edu.cesarschool.cc.poo.ac.cliente.ClienteDAO;
import br.edu.cesarschool.cc.poo.ac.passagem.Bilhete;
import br.edu.cesarschool.cc.poo.ac.passagem.BilheteDAO;
import br.edu.cesarschool.cc.poo.ac.passagem.BilheteVip;
import br.edu.cesarschool.cc.poo.ac.passagem.BilheteVipDAO;
import br.edu.cesarschool.cc.poo.ac.passagem.Voo;
import br.edu.cesarschool.cc.poo.ac.passagem.VooDAO;
import br.edu.cesarschool.cc.poo.ac.utils.DAOGenerico;
import br.edu.cesarschool.cc.poo.ac.utils.Registro;
import br.edu.cesarschool.cc.poo.ac.utils.SuperDAO;
import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;

public class TestesAc07 extends TesteGeral {
	private static final String REG2 = "REG2";
	private static final String COD_1 = "1";
	private static final String REG1 = "REG1";
	protected static final String DIR_REG = IND_CUR_DIR + FILE_SEP + "RegistroAx";
	private DAOGenerico daoGen = new DAOGenerico(RegistroAx.class);
	private CadastroObjetos cad = new CadastroObjetos(RegistroAx.class);
	
	@Test
	public void test01() {
		ClienteDAO cliDao = new ClienteDAO();
		Assertions.assertEquals(Cliente.class, cliDao.obterTipo());
		VooDAO vooDao = new VooDAO();
		Assertions.assertEquals(Voo.class, vooDao.obterTipo());
		BilheteDAO biDao = new BilheteDAO();
		Assertions.assertEquals(Bilhete.class, biDao.obterTipo());
		BilheteVipDAO bivDao = new BilheteVipDAO();
		Assertions.assertEquals(BilheteVip.class, bivDao.obterTipo());
		Assertions.assertTrue(cliDao instanceof SuperDAO);
		Assertions.assertTrue(vooDao instanceof SuperDAO);
		Assertions.assertTrue(biDao instanceof SuperDAO);
		Assertions.assertTrue(bivDao instanceof SuperDAO);
		Assertions.assertTrue(Modifier.isAbstract(Registro.class.getModifiers()));
		Assertions.assertTrue(Modifier.isAbstract(SuperDAO.class.getModifiers()));
		try {
			Method m1 = Registro.class.getMethod("getIdUnico", new Class[0]);
			Assertions.assertTrue(Modifier.isAbstract(m1.getModifiers()));
			Method m2 = SuperDAO.class.getMethod("obterTipo", new Class[0]);
			Assertions.assertTrue(Modifier.isAbstract(m2.getModifiers()));
		} catch (Exception e) {
			Assertions.fail(e);
		}
	}
	@Test
	public void test02() {
		excluirCadastros();
		RegistroAx reg = new RegistroAx();
		reg.codigo = 1;
		reg.nome = REG1;
		cad.incluir(reg, reg.getIdUnico());		
		Assertions.assertFalse(daoGen.incluir(reg));		
		Assertions.assertEquals(1, obterQtdArquivosDir(DIR_REG));
	}
	@Test
	public void test03() {
		excluirCadastros();
		RegistroAx reg = new RegistroAx();
		reg.codigo = 1;
		reg.nome = REG1;
		cad.incluir(reg, reg.getIdUnico());		
		RegistroAx reg1 = new RegistroAx();
		reg1.codigo = 2;
		reg1.nome = REG2;		
		Assertions.assertTrue(daoGen.incluir(reg1));	
		RegistroAx reg2 = (RegistroAx)cad.buscar("2"); 
		Assertions.assertEquals(2, obterQtdArquivosDir(DIR_REG));
		Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(reg1, reg2));		
	}	
	@Test
	public void test04() {
		excluirCadastros();
		RegistroAx reg = new RegistroAx();
		reg.codigo = 1;
		reg.nome = REG1;
		cad.incluir(reg, reg.getIdUnico());
		RegistroAx reg1 = new RegistroAx();
		reg1.codigo = 2;
		reg1.nome = REG2;		
		Assertions.assertFalse(daoGen.alterar(reg1));		
		Assertions.assertEquals(1, obterQtdArquivosDir(DIR_REG));
	}
	@Test
	public void test05() {
		excluirCadastros();
		RegistroAx reg = new RegistroAx();
		reg.codigo = 1;
		reg.nome = REG1;
		cad.incluir(reg, reg.getIdUnico());		
		RegistroAx reg1 = new RegistroAx();
		reg1.codigo = 1;
		reg1.nome = "REGXXX";		
		Assertions.assertTrue(daoGen.alterar(reg1));	
		RegistroAx reg2 = (RegistroAx)cad.buscar(COD_1); 
		Assertions.assertEquals(1, obterQtdArquivosDir(DIR_REG));
		Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(reg1, reg2));		
	}	
	
	@Test
	public void test06() {
		excluirCadastros();
		RegistroAx reg = new RegistroAx();
		reg.codigo = 1;
		reg.nome = REG1;
		cad.incluir(reg, reg.getIdUnico());
		Assertions.assertFalse(daoGen.excluir("2"));		
		Assertions.assertEquals(1, obterQtdArquivosDir(DIR_REG));
	}
	@Test
	public void test07() {
		excluirCadastros();
		RegistroAx reg = new RegistroAx();
		reg.codigo = 1;
		reg.nome = REG1;
		cad.incluir(reg, reg.getIdUnico());		
		Assertions.assertTrue(daoGen.excluir(COD_1));	
		RegistroAx reg2 = (RegistroAx)cad.buscar(COD_1);
		Assertions.assertNull(reg2);
		Assertions.assertEquals(0, obterQtdArquivosDir(DIR_REG));		
	}	
	@Test
	public void test08() {
		excluirCadastros();
		RegistroAx reg = new RegistroAx();
		reg.codigo = 1;
		reg.nome = REG1;
		cad.incluir(reg, reg.getIdUnico());
		Assertions.assertNull(daoGen.buscar("2"));		
		Assertions.assertEquals(1, obterQtdArquivosDir(DIR_REG));
	}
	@Test
	public void test09() {
		excluirCadastros();
		RegistroAx reg = new RegistroAx();
		reg.codigo = 1;
		reg.nome = REG1;
		cad.incluir(reg, reg.getIdUnico());
		RegistroAx reg1 = (RegistroAx)daoGen.buscar(COD_1);
		Assertions.assertNotNull(reg1);		
		Assertions.assertEquals(1, obterQtdArquivosDir(DIR_REG));
		Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(reg, reg1));
	}
	@Test
	public void test10() {
		excluirCadastros();
		Registro[] regs = daoGen.buscarTodos();
		Assertions.assertNotNull(regs);
		Assertions.assertEquals(0, regs.length); 
		Assertions.assertEquals(0, obterQtdArquivosDir(DIR_REG));
	}
	@Test
	public void test11() {
		excluirCadastros();
		RegistroAx reg = new RegistroAx();
		reg.codigo = 1;
		reg.nome = REG1;
		RegistroAx reg1 = new RegistroAx();
		reg1.codigo = 2;
		reg1.nome = REG2;		
		cad.incluir(reg, reg.getIdUnico());
		cad.incluir(reg1, reg1.getIdUnico());	
		Registro[] regs = daoGen.buscarTodos();
		Assertions.assertNotNull(regs);
		Assertions.assertEquals(2, regs.length);
		Assertions.assertTrue(regs[0] instanceof RegistroAx);
		Assertions.assertTrue(regs[1] instanceof RegistroAx);		
		Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(reg, regs[0]));
		Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(reg1, regs[1]));
		Assertions.assertEquals(2, obterQtdArquivosDir(DIR_REG));
	}	
	protected void excluirCadastros() {
		super.excluirCadastros();
		excluirArquivosDiretorio(new File(DIR_REG));
	}
	static class RegistroAx extends Registro {
		int codigo;
		String nome; 
		public String getIdUnico() {
			return "" + codigo;
		}
		public String toString() {
			return codigo + "," + nome;
		}
	}
}

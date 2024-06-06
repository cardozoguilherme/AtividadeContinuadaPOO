package br.edu.cesarschool.cc.poo.ac.testes;

import java.io.File;
import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;

import br.edu.cesarschool.cc.poo.ac.cliente.Cliente;
import br.edu.cesarschool.cc.poo.ac.passagem.Bilhete;
import br.edu.cesarschool.cc.poo.ac.passagem.BilheteMediator;
import br.edu.cesarschool.cc.poo.ac.passagem.ResultadoGeracaoBilhete;
import br.edu.cesarschool.cc.poo.ac.passagem.Voo;
import br.edu.cesarschool.cc.poo.ac.passagem.VooMediator;
import br.edu.cesarschool.cc.poo.ac.utils.DiaDaSemana;
import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;

public class TesteGeral {
	protected static final String IND_CUR_DIR = ".";
	protected static final String FILE_SEP = File.separator;
	protected static final String DIR_CLIENTE = IND_CUR_DIR + FILE_SEP + "Cliente";
	protected static final String DIR_VOO = IND_CUR_DIR + FILE_SEP + "Voo";
	protected static final String DIR_BILHETE = IND_CUR_DIR + FILE_SEP + "Bilhete";
	protected static final String DIR_BILHETE_VIP = IND_CUR_DIR + FILE_SEP + "BilheteVip";
	protected static final String DIR_FORMA = IND_CUR_DIR + FILE_SEP + "FormaPagamento";
	protected static final String BRANCO = "";
	protected static final String CPF_VALIDO = "91775395839";
	protected static final String OUTRO_CPF_VALIDO = "69025183514";
	protected static final String PNZ = "PNZ";
	protected static final String VCP = "VCP";
	protected static final DiaDaSemana[] DIAS = {DiaDaSemana.DOMINGO, DiaDaSemana.QUINTA_FEIRA};
	protected static final LocalTime HORA = LocalTime.of(11, 0, 0); 	
	protected VooMediator vooMed = VooMediator.obterInstancia();
	protected CadastroObjetos cadastroVoo = new CadastroObjetos(Voo.class);
	protected BilheteMediator biMed = BilheteMediator.obterInstancia();
	protected CadastroObjetos cadastroCli = new CadastroObjetos(Cliente.class);
	protected CadastroObjetos cadastroBil = new CadastroObjetos(Bilhete.class);


	protected void excluirCadastros() {
		excluirArquivosDiretorio(new File(DIR_CLIENTE));
		excluirArquivosDiretorio(new File(DIR_VOO));
		excluirArquivosDiretorio(new File(DIR_BILHETE));
		excluirArquivosDiretorio(new File(DIR_BILHETE_VIP));
		excluirArquivosDiretorio(new File(DIR_FORMA));
	}
	protected void excluirArquivosDiretorio(File dir) {
		File[] arqs = dir.listFiles();
		if (arqs != null && arqs.length > 0) {
			for (File file : arqs) {
				file.delete();
			}
		}		
	}
	protected int obterQtdArquivosDir(String caminhoDir) {
		File[] files = (new File(caminhoDir)).listFiles();
		if (files == null) {
			return 0;
		} else {
			return files.length;
		}
	}
	protected void assertResErro(ResultadoGeracaoBilhete res, String msg) {
		Assertions.assertNotNull(res);		
		Assertions.assertNull(res.getBilhete());
		Assertions.assertNull(res.getBilheteVip());
		Assertions.assertNotNull(res.getMensagemErro());
		Assertions.assertEquals(msg, res.getMensagemErro());
	}
}

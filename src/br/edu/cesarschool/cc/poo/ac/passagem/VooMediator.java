package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.cc.poo.ac.utils.StringUtils;

public class VooMediator {
	private static final String VOO_INEXISTENTE = "Voo inexistente";
	private static VooMediator instancia;
	private static final String[] AEROPORTOS = {"GRU", "CGH", "GIG", "SDU", 
			"REC", "CWB", "POA", "BSB", "SSA", "FOR", "MAO", "SLZ", "CNF", 
			"BEL", "JPA", "PNZ", "CAU", "FEN", "SET", "NAT", "PVH", "BVB", 
			"FLN", "AJU", "PMW", "MCZ", "MCP", "VIX", "GYN", "CGB", "CGR", 
			"THE", "RBR", "VCP", "RAO"};
	private VooDAO vooDao = new VooDAO();
	public static VooMediator obterInstancia() {
		if (instancia == null) {
			instancia = new VooMediator();
		}
		return instancia;
	}
	private VooMediator() {}
	public String validarCiaNumero(String companhiaAerea, int numeroVoo) {
		if (StringUtils.isVaziaOuNula(companhiaAerea) || 
				companhiaAerea.length() != 2) {
			return "CIA aerea errada";			
		} else if (numeroVoo < 1000 || numeroVoo > 9999) {
			return "Numero voo errado";
		}
		return null;
	}
	private boolean estaNaLista(String ae) {
		for (String aeLista : AEROPORTOS) {
			if (aeLista.equals(ae)) {
				return true;
			}
		}
		return false;
	}
	public String validar(Voo voo) {		
		String aeOrigem = voo.getAeroportoOrigem();
		String aeDestino = voo.getAeroportoDestino();
		if (StringUtils.isVaziaOuNula(aeOrigem) || 
				!estaNaLista(aeOrigem)) {
			return "Aeroporto origem errado";			
		}
		if (StringUtils.isVaziaOuNula(aeDestino) || 
				!estaNaLista(aeDestino)) {
			return "Aeroporto destino errado";			
		}		
		if (aeOrigem.equals(aeDestino)) {
			return "Aeroporto origem igual a aeroporto destino";
		}
		return validarCiaNumero(voo.getCompanhiaAerea(), voo.getNumeroVoo());
	}
	public String incluir(Voo voo) {
		String msg = validar(voo);
		if (msg == null) {
			if (!vooDao.incluir(voo)) {
				msg = "Voo ja existente";
			}
		}
		return msg;
	}
	public String alterar(Voo voo) {
		String msg = validar(voo);
		if (msg == null) {
			if (!vooDao.alterar(voo)) {
				msg = VOO_INEXISTENTE;
			}
		}
		return msg;
	}
	public String excluir(String idVoo) {
		String msg = null;
		if(StringUtils.isVaziaOuNula(idVoo)) {
			msg = "Id voo errado";
		} else if (!vooDao.excluir(idVoo)) {
			msg = VOO_INEXISTENTE;
		}
		return msg;
	}
	public Voo buscar(String idVoo) {
		return vooDao.buscar(idVoo);
	}
}

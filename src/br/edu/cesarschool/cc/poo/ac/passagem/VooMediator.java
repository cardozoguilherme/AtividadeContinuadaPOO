package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.cc.poo.ac.utils.StringUtils;

import java.util.Arrays;
import java.util.Objects;

public class VooMediator {
    private static VooMediator instancia;

    private VooDAO vooDao = new VooDAO();

    private VooMediator() {
    }

    public static VooMediator obterInstancia() {
        if (instancia == null) {
            instancia = new VooMediator();
        }
        return instancia;
    }

    public Voo buscar(String IdVoo) {
        return vooDao.buscar(IdVoo);
    }

    public String validarCiaNumero(String companhiaAerea, int numeroVoo) {
        if (StringUtils.isVaziaOuNula(companhiaAerea) || companhiaAerea.length() != 2) {
            return "CIA aerea errada";
        } else if (numeroVoo < 1000 || numeroVoo > 9999) {
            return "Numero voo errado";
        }
        return null;
    }

    public String validar(Voo voo){
        String[] aeroportos = {"GRU", "CGH", "GIG", "SDU", "REC", "CWB", "POA", "BSB", "SSA", "FOR", "MAO", "SLZ", "CNF", "BEL", "JPA", "PNZ", "CAU", "FEN", "SET", "NAT", "PVH", "BVB", "FLN", "AJU", "PMW", "MCZ", "MCP", "VIX", "GYN", "CGB", "CGR", "THE", "RBR", "VCP", "RAO"};

        if (StringUtils.isVaziaOuNula(voo.getAeroportoOrigem()) || !Arrays.asList(aeroportos).contains(voo.getAeroportoOrigem())) {
            return "Aeroporto origem errado";
        } else if (StringUtils.isVaziaOuNula(voo.getAeroportoDestino()) || !Arrays.asList(aeroportos).contains(voo.getAeroportoDestino())){
            return "Aeroporto destino errado";
        } else if (Objects.equals(voo.getAeroportoOrigem(), voo.getAeroportoDestino())) {
            return "Aeroporto origem igual a aeroporto destino";
        }

        return validarCiaNumero(voo.getCompanhiaAerea(), voo.getNumeroVoo());
    }

    public String incluir(Voo voo) {
        String mensagem = validar(voo);

        if (mensagem == null) {
            if (!vooDao.incluir(voo)) {
                return "Voo ja existente";
            } else {
                return null;
            }
        } else {
            return mensagem;
        }
    }

    public String alterar(Voo voo) {
        if (validar(voo) != null) {
            return validar(voo);
        } else {
            boolean resposta = vooDao.alterar(voo);

            if (!resposta) {
                return "Voo inexistente";
            }
            return null;
        }
    }

    public String excluir(String idVoo) {
        if (StringUtils.isVaziaOuNula(idVoo)) {
            return "Id voo errado";
        } else {
            boolean resposta = vooDao.excluir(idVoo);

            if (!resposta) {
                return "Voo inexistente";
            }
            return null;
        }
    }
}

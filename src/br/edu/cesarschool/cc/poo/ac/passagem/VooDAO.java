package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;

public class VooDAO {
    private final CadastroObjetos cadastro = new CadastroObjetos(VooDAO.class);

    VooDAO() {
    }
    private String obterIdUnico(Voo voo) {
        return voo.getCompanhiaAerea() + voo.getNumeroVoo();
    }

    public Voo buscar(String idVoo) {
        return (Voo)cadastro.buscar(idVoo);
    }

    public boolean incluir(Voo voo) {
        String idUnico = obterIdUnico(voo);
        Voo vo = buscar(idUnico);
        if (vo == null) {
            cadastro.incluir(voo, idUnico);
            return true;
        }
        return false;
    }

    public boolean alterar(Voo voo) {
        String idUnico = obterIdUnico(voo);
        Voo vo = buscar(idUnico);
        if (vo != null) {
            cadastro.alterar(voo, idUnico);
            return true;
        }
        return false;
    }

    public boolean excluir(String idVoo) {
        Voo vo = buscar(idVoo);
        if (vo != null) {
            cadastro.excluir(idVoo);
            return true;
        }
        return false;
    }

}

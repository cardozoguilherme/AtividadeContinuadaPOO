package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;

public class BilheteVipDAO {
    private final CadastroObjetos cadastro = new CadastroObjetos(BilheteDAO.class);

    public BilheteVipDAO() {

    }

    private String obterIdUnico(BilheteVip bilheteVip){
        return bilheteVip.gerarNumero();
    }

    public Voo buscar(String numeroBilhete) {
        return (Voo) cadastro.buscar(numeroBilhete);
    }

    public boolean incluir(BilheteVip bilheteVip) {
        String idUnico = obterIdUnico(bilheteVip);
        Voo voo = buscar(idUnico);

        if (voo != null) {
            cadastro.incluir(voo, idUnico);
            return true;
        }
        return false;
    }

    public boolean alterar(BilheteVip bilheteVip) {
        String idUnico = obterIdUnico(bilheteVip);
        Voo voo = buscar(idUnico);

        if (idUnico != null) {
            cadastro.alterar(voo, idUnico);
            return true;
        }
        return false;
    }

    public boolean excluir(String numeroBilhete) {
        Voo voo = buscar(numeroBilhete);

        if (voo != null) {
            cadastro.excluir(numeroBilhete);
            return true;
        }
        return false;
    }
}

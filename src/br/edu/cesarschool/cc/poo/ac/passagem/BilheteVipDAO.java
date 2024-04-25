package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;

public class BilheteVipDAO {
    private CadastroObjetos cadastro = new CadastroObjetos(BilheteVip.class);

    public BilheteVipDAO() {

    }

    private String obterIdUnico(BilheteVip bilheteVip){
        return bilheteVip.gerarNumero();
    }

    public BilheteVip buscar(String numeroBilhete) {

        return (BilheteVip)cadastro.buscar(numeroBilhete);
    }

    public boolean incluir(BilheteVip bilheteVip) {
        String idUnico = obterIdUnico(bilheteVip);
        BilheteVip bilVip = buscar(idUnico);

        if (bilVip == null) {
            cadastro.incluir(bilheteVip, idUnico);
            return true;
        }
        return false;
    }

    public boolean alterar(BilheteVip bilheteVip) {
        String idUnico = obterIdUnico(bilheteVip);
        BilheteVip bilVip = buscar(idUnico);

        if (idUnico != null) {
            cadastro.alterar(bilheteVip, idUnico);
            return true;
        }
        return false;
    }

    public boolean excluir(String numeroBilhete) {
        BilheteVip bilVip = buscar(numeroBilhete);

        if (bilVip != null) {
            cadastro.excluir(numeroBilhete);
            return true;
        }
        return false;
    }
}

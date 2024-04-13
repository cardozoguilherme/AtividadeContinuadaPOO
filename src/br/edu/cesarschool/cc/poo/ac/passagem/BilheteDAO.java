package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;

public class BilheteDAO {
    private final CadastroObjetos cadastro = new CadastroObjetos(BilheteDAO.class);

    public BilheteDAO() {

    }

    private String obterIdUnico(Bilhete bilhete){
        return bilhete.gerarNumero();
    }

    public Voo buscar(String numeroBilhete) {

        return (Voo)cadastro.buscar(numeroBilhete);
    }

    public boolean incluir(Bilhete bilhete) {
        String idUnico = obterIdUnico(bilhete);
        Voo voo = buscar(idUnico);

        if (voo != null) {
            cadastro.incluir(voo, idUnico);
            return true;
        }
        return false;
    }

    public boolean alterar(Bilhete bilhete) {
        String idUnico = obterIdUnico(bilhete);
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
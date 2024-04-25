package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;

public class BilheteDAO {
    private CadastroObjetos cadastro = new CadastroObjetos(Bilhete.class);

    public BilheteDAO() {

    }

    private String obterIdUnico(Bilhete bilhete){
        return bilhete.gerarNumero();
    }

    public Bilhete buscar(String numeroBilhete) {

        return (Bilhete)cadastro.buscar(numeroBilhete);
    }

    public boolean incluir(Bilhete bilhete) {
        String idUnico = obterIdUnico(bilhete);
        Bilhete bil = buscar(idUnico);

        if (bil == null) {
            cadastro.incluir(bilhete, idUnico);
            return true;
        }
        return false;
    }

    public boolean alterar(Bilhete bilhete) {
        String idUnico = obterIdUnico(bilhete);
        Bilhete bil = buscar(idUnico);

        if (bil != null) {
            cadastro.alterar(bilhete, idUnico);
            return true;
        }
        return false;
    }

    public boolean excluir(String numeroBilhete) {
        Bilhete bil = buscar(numeroBilhete);

        if (bil != null) {
            cadastro.excluir(numeroBilhete);
            return true;
        }
        return false;
    }
}
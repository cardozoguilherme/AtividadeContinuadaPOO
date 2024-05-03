package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;

import java.io.Serializable;

public class VooDAO {
    private CadastroObjetos cadastro = new CadastroObjetos(Voo.class);

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

    // NEW!!!
    public Voo[] buscarTodos() {
        Serializable[] res = cadastro.buscarTodos();
        if (res == null) {
            return null;
        } else {
            Voo[] voos = new Voo[res.length];
            int i = 0;
            for (Serializable reg : res) {
                voos[i] = (Voo)reg;
                i++;
            }
            return voos;
        }
    }
}

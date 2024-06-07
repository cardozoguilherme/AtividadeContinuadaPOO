package br.edu.cesarschool.cc.poo.ac.utils;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;

import java.io.Serializable;

public class DAOGenerico {
    private CadastroObjetos cadastro;

    public DAOGenerico(Class<?> tipo) {
        cadastro = new CadastroObjetos(tipo);
    }

    public boolean incluir(Registro reg) {
        String idUnico = reg.getIdUnico();
        Registro encontrado = (Registro) cadastro.buscar(idUnico);
        if (encontrado == null) {
            cadastro.incluir(reg, idUnico);
            return true;
        }
        return false;
    }

    public boolean alterar(Registro reg) {
        String idUnico = reg.getIdUnico();
        Registro encontrado = (Registro) cadastro.buscar(idUnico);
        if (encontrado != null) {
            cadastro.alterar(reg, idUnico);
            return true;
        }
        return false;
    }

    public Registro buscar(String id) {
        return (Registro) cadastro.buscar(id);
    }

    public Registro[] buscarTodos() {
        Serializable[] registros = cadastro.buscarTodos();
        if (registros == null) {
            return null;
        }
        Registro[] resultado = new Registro[registros.length];
        for (int i = 0; i < registros.length; i++) {
            resultado[i] = (Registro) registros[i];
        }
        return resultado;
    }

    public boolean excluir(String id) {
        Registro encontrado = (Registro) cadastro.buscar(id);
        if (encontrado != null) {
            cadastro.excluir(id);
            return true;
        }
        return false;
    }
}

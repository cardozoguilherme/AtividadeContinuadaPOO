package br.edu.cesarschool.cc.poo.ac.utils;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;

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
        return (Registro[]) cadastro.buscarTodos();
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

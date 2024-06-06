package br.edu.cesarschool.cc.poo.ac.cliente.comexcecao;

import java.util.ArrayList;
import java.util.List;

public class ExcecaoValidacao extends Exception {
    private List<String> mensagens;

    public ExcecaoValidacao() {
        this.mensagens = new ArrayList<>();
    }

    public List<String> getMensagens() {
        return mensagens;
    }

    public void adicionarMensagem(String mensagem) {
        mensagens.add(mensagem);
    }
}

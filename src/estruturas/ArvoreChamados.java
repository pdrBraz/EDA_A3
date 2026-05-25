package estruturas;

import model.Chamado;

public class ArvoreChamados {
    private class No {
        Chamado chamado;
        No esquerda, direita;

        No(Chamado chamado) {
            this.chamado = chamado;
        }
    }

    private No raiz;

    public void inserir(Chamado chamado) {
        raiz = inserirRec(raiz, chamado);
    }

    private No inserirRec(No atual, Chamado chamado) {
        if (atual == null) return new No(chamado);

        if (chamado.getCodigo() < atual.chamado.getCodigo()) {
            atual.esquerda = inserirRec(atual.esquerda, chamado);
        } else {
            atual.direita = inserirRec(atual.direita, chamado);
        }
        return atual;
    }

    public Chamado buscar(int codigo) {
        return buscarRec(raiz, codigo);
    }

    private Chamado buscarRec(No atual, int codigo) {
        if (atual == null) return null;
        if (atual.chamado.getCodigo() == codigo) return atual.chamado;

        if (codigo < atual.chamado.getCodigo()) {
            return buscarRec(atual.esquerda, codigo);
        }
        return buscarRec(atual.direita, codigo);
    }
}

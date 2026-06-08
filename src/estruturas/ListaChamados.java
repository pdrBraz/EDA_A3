package estruturas;

import model.Chamado;

public class ListaChamados {
    private Node ini;

    private static class Node {
        Chamado chamado;
        Node prox;

        Node(Chamado c) {
            this.chamado = c;
            this.prox = null;
        }
    }

    private int pesoPrioridade(String prioridade) {
        if (prioridade.equalsIgnoreCase("Alta"))  return 3;
        if (prioridade.equalsIgnoreCase("Média")) return 2;
        return 1;
    }

    public void adicionar(Chamado chamado) {
        Node novo = new Node(chamado);
        if (ini == null) {
            ini = novo;
            return;
        }
        // Anda até o final da lista e conecta o novo nó
        Node t = ini;
        while (t.prox != null) {
            t = t.prox;
        }
        t.prox = novo;
    }

    public Chamado atender() {
        if (estaVazia()) return null;

        // Busca o nó de maior prioridade e seu anterior
        Node tMaior   = ini;
        Node antMaior = null;
        Node ant      = null;
        Node t        = ini;

        while (t != null) {
            if (pesoPrioridade(t.chamado.getPrioridade()) > pesoPrioridade(tMaior.chamado.getPrioridade())) {
                tMaior   = t;
                antMaior = ant;
            }
            ant = t;
            t   = t.prox;
        }

        // Remove o nó de maior prioridade da lista ligada
        if (antMaior == null) {
            ini = tMaior.prox; // era o primeiro nó
        } else {
            antMaior.prox = tMaior.prox; // pula o nó removido
        }

        return tMaior.chamado;
    }

    public void removerPorCodigo(int codigo) {
        if (estaVazia()) return;

        Node ant = null;
        Node t   = ini;

        while (t != null) {
            if (t.chamado.getCodigo() == codigo) {
                if (ant == null) {
                    ini = t.prox; // era o primeiro nó
                } else {
                    ant.prox = t.prox; // pula o nó removido
                }
                return;
            }
            ant = t;
            t   = t.prox;
        }
    }

    public boolean estaVazia() {
        return ini == null;
    }

    public void listar() {
        if (estaVazia()) {
            System.out.println("Nenhum chamado pendente.");
            return;
        }
        Node t = ini;
        while (t != null) {
            System.out.println(t.chamado);
            t = t.prox;
        }
    }
}
package estruturas;

import model.Chamado;

public class FilaChamados {
    private Chamado chamados[] = new Chamado[100];
    private int quantidade = 0;

    public void adicionar(Chamado chamado) {
        if (quantidade >= chamados.length) {
            System.out.println("Fila de chamados cheia.");
            return;
        }

        chamados[quantidade] = chamado;
        quantidade++;
    }

    public Chamado atender() {
        if (estaVazia()) {
            return null;
        }

        Chamado atendido = chamados[0];

        for (int i = 0; i < quantidade - 1; i++) {
            chamados[i] = chamados[i + 1];
        }

        quantidade--;
        chamados[quantidade] = null;

        return atendido;
    }

    public boolean estaVazia() {
        return quantidade == 0;
    }

    public void listar() {
        for (int i = 0; i < quantidade; i++) {
            System.out.println(chamados[i]);
        }
    }
}

package estruturas;

import model.Chamado;

public class PilhaHistorico {
    private Chamado chamados[] = new Chamado[100];
    private int topo = -1;

    public void registrar(Chamado chamado) {
        if (topo >= chamados.length - 1) {
            System.out.println("Pilha de historico cheia.");
            return;
        }

        topo++;
        chamados[topo] = chamado;
    }

    public void listar() {
        for (int i = topo; i >= 0; i--) {
            System.out.println(chamados[i]);
        }
    }
}

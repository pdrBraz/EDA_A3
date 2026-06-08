package estruturas;

import model.Chamado;

public class FilaHistorico {
    private Chamado chamados[] = new Chamado[100];
    private int ini = 0;
    private int fim = -1;
    private int tam = 0;

    public void registrar(Chamado chamado) {
        if (tam == chamados.length) {
            System.out.println("Histórico cheio.");
            return;
        }
        fim++;
        tam++;
        if (fim >= chamados.length)
            fim = 0; // Circular
        chamados[fim] = chamado;
    }

    public void listar() {
        if (tam == 0) {
            System.out.println("Nenhum atendimento no histórico.");
            return;
        }
        int j = ini;
        for (int i = 1; i <= tam; i++) {
            System.out.println(chamados[j]);
            if (j == chamados.length - 1)
                j = 0; // Circular
            else
                j++;
        }
    }
}
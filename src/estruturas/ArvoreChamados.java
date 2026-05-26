package estruturas;

import model.Chamado;

public class ArvoreChamados {
    private Chamado chamados[] = new Chamado[100];

    public void inserir(Chamado chamado) {
        int i = 0;

        while (i < chamados.length) {
            if (chamados[i] == null) {
                chamados[i] = chamado;
                return;
            }

            if (chamado.getCodigo() < chamados[i].getCodigo()) {
                i = 2 * i + 1;
            } else {
                i = 2 * i + 2;
            }
        }

        System.out.println("Arvore de chamados cheia.");
    }

    public Chamado buscar(int codigo) {
        int i = 0;

        while (i < chamados.length && chamados[i] != null) {
            if (chamados[i].getCodigo() == codigo) {
                return chamados[i];
            }

            if (codigo < chamados[i].getCodigo()) {
                i = 2 * i + 1;
            } else {
                i = 2 * i + 2;
            }
        }

        return null;
    }
}

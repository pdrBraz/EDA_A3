package estruturas;

import model.Chamado;

public class ListaChamados {
    private Chamado chamados[] = new Chamado[100];
    private int quantidade = 0;

    private int pesoPrioridade(String prioridade) {
        if (prioridade.equalsIgnoreCase("Alta"))  return 3;
        if (prioridade.equalsIgnoreCase("Média")) return 2;
        return 1;
    }

    public void adicionar(Chamado chamado) {
        if (quantidade >= chamados.length) {
            System.out.println("Lista de chamados cheia.");
            return;
        }
        chamados[quantidade] = chamado;
        quantidade++;
    }

    public void removerPorCodigo(int codigo) {
        for (int i = 0; i < quantidade; i++) {
            if (chamados[i].getCodigo() == codigo) {
                for (int j = i; j < quantidade - 1; j++) {
                    chamados[j] = chamados[j + 1];
                }
                quantidade--;
                chamados[quantidade] = null;
                return;
            }
        }
    }

    public Chamado atender() {
        if (estaVazia()) return null;

        int indicePrioridade = 0;
        for (int i = 1; i < quantidade; i++) {
            if (pesoPrioridade(chamados[i].getPrioridade()) > pesoPrioridade(chamados[indicePrioridade].getPrioridade())) {
                indicePrioridade = i;
            }
        }

        Chamado atendido = chamados[indicePrioridade];

        for (int i = indicePrioridade; i < quantidade - 1; i++) {
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
        if (quantidade == 0) {
            System.out.println("Nenhum chamado pendente.");
            return;
        }
        for (int i = 0; i < quantidade; i++) {
            System.out.println(chamados[i]);
        }
    }
}
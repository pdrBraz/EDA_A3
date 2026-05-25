package estruturas;

import java.util.Stack;
import model.Chamado;

public class PilhaHistorico {
    private Stack<Chamado> pilha = new Stack<>();

    public void registrar(Chamado chamado) {
        pilha.push(chamado);
    }

    public void listar() {
        for (int i = pilha.size() - 1; i >= 0; i--) {
            System.out.println(pilha.get(i));
        }
    }
}

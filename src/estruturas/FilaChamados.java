package estruturas;

import java.util.LinkedList;
import java.util.Queue;
import model.Chamado;

public class FilaChamados {
    private Queue<Chamado> fila = new LinkedList<>();

    public void adicionar(Chamado chamado) {
        fila.add(chamado);
    }

    public Chamado atender() {
        return fila.poll();
    }

    public boolean estaVazia() {
        return fila.isEmpty();
    }

    public void listar() {
        for (Chamado c : fila) {
            System.out.println(c);
        }
    }
}

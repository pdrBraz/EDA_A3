package estruturas;

public class PilhaDesfazer {
    private Object objetos[] = new Object[100];
    private String tipos[]   = new String[100];
    private int topo = -1;

    public void registrar(String tipo, Object objeto) {
        if (topo >= objetos.length - 1) {
            System.out.println("Pilha de ações cheia.");
            return;
        }
        topo++;
        tipos[topo]   = tipo;
        objetos[topo] = objeto;
    }

    public String getTipoTopo() {
        if (topo == -1) return null;
        return tipos[topo];
    }

    public Object getObjetoTopo() {
        if (topo == -1) return null;
        return objetos[topo];
    }

    public void removerTopo() {
        if (topo == -1) return;
        objetos[topo] = null;
        tipos[topo]   = null;
        topo--;
    }

    public boolean estaVazia() {
        return topo == -1;
    }
}
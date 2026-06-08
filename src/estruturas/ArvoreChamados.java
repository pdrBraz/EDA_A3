package estruturas;

import model.Chamado;

public class ArvoreChamados {
    private NodeChamado raiz;

    private static class NodeChamado {
        Chamado chamado;
        NodeChamado e, d;

        NodeChamado(Chamado c) {
            this.chamado = c;
            this.e = null;
            this.d = null;
        }
    }

    public void inserir(Chamado chamado) {
        if (raiz == null) {
            raiz = new NodeChamado(chamado);
            return;
        }
        NodeChamado t = raiz;
        while (true) {
            if (chamado.getCodigo() == t.chamado.getCodigo()) {
                System.out.println("Chamado já existe com código: " + chamado.getCodigo());
                return;
            }
            if (chamado.getCodigo() < t.chamado.getCodigo()) {
                if (t.e == null) { t.e = new NodeChamado(chamado); return; }
                t = t.e;
            } else {
                if (t.d == null) { t.d = new NodeChamado(chamado); return; }
                t = t.d;
            }
        }
    }

    public void remover(int codigo) {
        raiz = removerNo(raiz, codigo);
    }

    private NodeChamado removerNo(NodeChamado t, int codigo) {
        if (t == null) return null;

        if (codigo < t.chamado.getCodigo()) {
            t.e = removerNo(t.e, codigo);
        } else if (codigo > t.chamado.getCodigo()) {
            t.d = removerNo(t.d, codigo);
        } else {
            if (t.e == null) return t.d;
            if (t.d == null) return t.e;
            NodeChamado sucessor = t.d;
            while (sucessor.e != null) sucessor = sucessor.e;
            t.chamado = sucessor.chamado;
            t.d = removerNo(t.d, sucessor.chamado.getCodigo());
        }
        return t;
    }

    public Chamado buscar(int codigo) {
        NodeChamado t = raiz;
        while (t != null) {
            if (codigo == t.chamado.getCodigo()) return t.chamado;
            if (codigo < t.chamado.getCodigo()) t = t.e;
            else t = t.d;
        }
        return null;
    }
}
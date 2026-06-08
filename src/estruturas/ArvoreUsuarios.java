package estruturas;

import model.Usuario;

public class ArvoreUsuarios {
    private NodeUsuario raiz;

    private static class NodeUsuario {
        Usuario usuario;
        NodeUsuario e, d;

        NodeUsuario(Usuario u) {
            this.usuario = u;
            this.e = null;
            this.d = null;
        }
    }

    public void inserir(Usuario usuario) {
        if (raiz == null) {
            raiz = new NodeUsuario(usuario);
            return;
        }
        NodeUsuario t = raiz;
        while (true) {
            if (usuario.getId() == t.usuario.getId()) {
                System.out.println("Usuário já existe com ID: " + usuario.getId());
                return;
            }
            if (usuario.getId() < t.usuario.getId()) {
                if (t.e == null) { t.e = new NodeUsuario(usuario); return; }
                t = t.e;
            } else {
                if (t.d == null) { t.d = new NodeUsuario(usuario); return; }
                t = t.d;
            }
        }
    }

    public void remover(int id) {
        raiz = removerNo(raiz, id);
    }

    private NodeUsuario removerNo(NodeUsuario t, int id) {
        if (t == null) return null;

        if (id < t.usuario.getId()) {
            t.e = removerNo(t.e, id);
        } else if (id > t.usuario.getId()) {
            t.d = removerNo(t.d, id);
        } else {
            // Encontrou o nó — três casos:
            if (t.e == null) return t.d; // Sem filho esquerdo
            if (t.d == null) return t.e; // Sem filho direito
            // Dois filhos: substitui pelo menor da direita (sucessor)
            NodeUsuario sucessor = t.d;
            while (sucessor.e != null) sucessor = sucessor.e;
            t.usuario = sucessor.usuario;
            t.d = removerNo(t.d, sucessor.usuario.getId());
        }
        return t;
    }

    public Usuario buscarPorId(int id) {
        NodeUsuario t = raiz;
        while (t != null) {
            if (id == t.usuario.getId()) return t.usuario;
            if (id < t.usuario.getId()) t = t.e;
            else t = t.d;
        }
        return null;
    }

    public void listar() {
        if (raiz == null) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }
        listarEmOrdem(raiz);
    }

    private void listarEmOrdem(NodeUsuario t) {
        if (t == null) return;
        listarEmOrdem(t.e);
        System.out.println(t.usuario);
        listarEmOrdem(t.d);
    }
}
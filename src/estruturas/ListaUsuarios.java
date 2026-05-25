package estruturas;

import model.Usuario;

public class ListaUsuarios {
    private class No {
        Usuario usuario;
        No proximo;

        No(Usuario usuario) {
            this.usuario = usuario;
        }
    }

    private No inicio;

    public void adicionar(Usuario usuario) {
        No novo = new No(usuario);
        if (inicio == null) {
            inicio = novo;
        } else {
            No atual = inicio;
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = novo;
        }
    }

    public Usuario buscarPorId(int id) {
        No atual = inicio;
        while (atual != null) {
            if (atual.usuario.getId() == id) {
                return atual.usuario;
            }
            atual = atual.proximo;
        }
        return null;
    }

    public void listar() {
        No atual = inicio;
        while (atual != null) {
            System.out.println(atual.usuario);
            atual = atual.proximo;
        }
    }
}

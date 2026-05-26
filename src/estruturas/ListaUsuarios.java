package estruturas;

import model.Usuario;

public class ListaUsuarios {
    private Usuario usuarios[] = new Usuario[100];
    private int quantidade = 0;

    public void adicionar(Usuario usuario) {
        if (quantidade >= usuarios.length) {
            System.out.println("Lista de usuarios cheia.");
            return;
        }

        usuarios[quantidade] = usuario;
        quantidade++;
    }

    public Usuario buscarPorId(int id) {
        for (int i = 0; i < quantidade; i++) {
            if (usuarios[i].getId() == id) {
                return usuarios[i];
            }
        }
        return null;
    }

    public void listar() {
        for (int i = 0; i < quantidade; i++) {
            System.out.println(usuarios[i]);
        }
    }
}

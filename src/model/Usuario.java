package model;

public class Usuario {
    private int id;
    private String nome;
    private String setor;

    public Usuario(int id, String nome, String setor) {
        this.id = id;
        this.nome = nome;
        this.setor = setor;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getSetor() { return setor; }

    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | Setor: " + setor;
    }
}

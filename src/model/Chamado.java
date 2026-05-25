package model;

public class Chamado {
    private int codigo;
    private String descricao;
    private Usuario usuario;
    private String prioridade;

    public Chamado(int codigo, String descricao, Usuario usuario, String prioridade) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.prioridade = prioridade;
    }

    public int getCodigo() { return codigo; }
    public String getDescricao() { return descricao; }
    public Usuario getUsuario() { return usuario; }
    public String getPrioridade() { return prioridade; }

    @Override
    public String toString() {
        return "Chamado #" + codigo +
               " | Usuário: " + usuario.getNome() +
               " | Prioridade: " + prioridade +
               " | Problema: " + descricao;
    }
}

package model;

public class Servico {

    private int id_servico;
    private String nome_servico;

    public Servico(int id_servico, String nome_servico) {
        this.id_servico = id_servico;
        this.nome_servico = nome_servico;
    }

    public int getId_servico() { return id_servico; }
    public void setId_servico(int id_servico) { this.id_servico = id_servico; }

    public String getNome_servico() { return nome_servico; }
    public void setNome_servico(String nome_servico) { this.nome_servico = nome_servico; }
}

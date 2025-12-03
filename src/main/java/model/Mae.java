package model;

import java.time.LocalDate;

public class Mae {

    private int id_mae;
    private String nome;
    private String telefone;
    private String endereco;
    private LocalDate data_aniversario;

    public Mae(int id_mae, String nome, String telefone, String endereco, LocalDate data_aniversario) {
        this.id_mae = id_mae;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.data_aniversario = data_aniversario;
    }

    public int getId_mae() { return id_mae; }
    public void setId_mae(int id_mae) { this.id_mae = id_mae; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public LocalDate getData_aniversario() { return data_aniversario; }
    public void setData_aniversario(LocalDate data_aniversario) { this.data_aniversario = data_aniversario; }
}

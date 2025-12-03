package model;

public class ServicoEncontro {

    private int id_servico_encontro;
    private int id_encontro;
    private int id_servico;
    private int id_mae_responsavel;
    private String descricao;

    public ServicoEncontro(int id_servico_encontro, int id_encontro, int id_servico, int id_mae_responsavel, String descricao) {
        this.id_servico_encontro = id_servico_encontro;
        this.id_encontro = id_encontro;
        this.id_servico = id_servico;
        this.id_mae_responsavel = id_mae_responsavel;
        this.descricao = descricao;
    }

    public int getId_servico_encontro() { return id_servico_encontro; }
    public void setId_servico_encontro(int id_servico_encontro) { this.id_servico_encontro = id_servico_encontro; }

    public int getId_encontro() { return id_encontro; }
    public void setId_encontro(int id_encontro) { this.id_encontro = id_encontro; }

    public int getId_servico() { return id_servico; }
    public void setId_servico(int id_servico) { this.id_servico = id_servico; }

    public int getId_mae_responsavel() { return id_mae_responsavel; }
    public void setId_mae_responsavel(int id_mae_responsavel) { this.id_mae_responsavel = id_mae_responsavel; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}

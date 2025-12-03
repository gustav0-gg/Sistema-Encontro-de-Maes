package dao;

import model.ServicoEncontro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicoEncontroDAO {

    private Connection conn;

    public ServicoEncontroDAO(Connection conn) {
        this.conn = conn;
    }

    // CREATE
    public void inserir(ServicoEncontro se) throws SQLException {
        String sql = "INSERT INTO servicos_encontros (id_encontro, id_servico, id_mae_responsavel, descricao) VALUES (?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, se.getId_encontro());
        stmt.setInt(2, se.getId_servico());
        stmt.setInt(3, se.getId_mae_responsavel());
        stmt.setString(4, se.getDescricao());

        stmt.executeUpdate();
        stmt.close();
    }

    // READ by ID
    public ServicoEncontro buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM servicos_encontros WHERE id_servico_encontro = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();
        ServicoEncontro se = null;

        if (rs.next()) {
            se = new ServicoEncontro(
                rs.getInt("id_servico_encontro"),
                rs.getInt("id_encontro"),
                rs.getInt("id_servico"),
                rs.getInt("id_mae_responsavel"),
                rs.getString("descricao")
            );
        }

        rs.close();
        stmt.close();
        return se;
    }

    // READ ALL
    public List<ServicoEncontro> listarTodos() throws SQLException {
        String sql = "SELECT * FROM servicos_encontros";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        List<ServicoEncontro> lista = new ArrayList<>();

        while (rs.next()) {
            lista.add(new ServicoEncontro(
                rs.getInt("id_servico_encontro"),
                rs.getInt("id_encontro"),
                rs.getInt("id_servico"),
                rs.getInt("id_mae_responsavel"),
                rs.getString("descricao")
            ));
        }

        rs.close();
        stmt.close();
        return lista;
    }

    // UPDATE
    public void atualizar(ServicoEncontro se) throws SQLException {
        String sql = "UPDATE servicos_encontros SET id_encontro = ?, id_servico = ?, id_mae_responsavel = ?, descricao = ? WHERE id_servico_encontro = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, se.getId_encontro());
        stmt.setInt(2, se.getId_servico());
        stmt.setInt(3, se.getId_mae_responsavel());
        stmt.setString(4, se.getDescricao());
        stmt.setInt(5, se.getId_servico_encontro());

        stmt.executeUpdate();
        stmt.close();
    }

    // DELETE
    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM servicos_encontros WHERE id_servico_encontro = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
    }
}

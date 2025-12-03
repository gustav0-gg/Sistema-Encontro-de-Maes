package dao;

import model.Servico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicoDAO {

    private Connection conn;

    public ServicoDAO(Connection conn) {
        this.conn = conn;
    }

    // CREATE
    public void inserir(Servico servico) throws SQLException {
        String sql = "INSERT INTO servicos (nome_servico) VALUES (?)";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, servico.getNome_servico());
        stmt.executeUpdate();
        stmt.close();
    }

    // READ by ID
    public Servico buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM servicos WHERE id_servico = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();
        Servico servico = null;

        if (rs.next()) {
            servico = new Servico(
                rs.getInt("id_servico"),
                rs.getString("nome_servico")
            );
        }

        rs.close();
        stmt.close();
        return servico;
    }

    // READ ALL
    public List<Servico> listarTodos() throws SQLException {
        String sql = "SELECT * FROM servicos";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        List<Servico> lista = new ArrayList<>();

        while (rs.next()) {
            lista.add(new Servico(
                rs.getInt("id_servico"),
                rs.getString("nome_servico")
            ));
        }

        rs.close();
        stmt.close();
        return lista;
    }

    // UPDATE
    public void atualizar(Servico servico) throws SQLException {
        String sql = "UPDATE servicos SET nome_servico = ? WHERE id_servico = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, servico.getNome_servico());
        stmt.setInt(2, servico.getId_servico());
        stmt.executeUpdate();
        stmt.close();
    }

    // DELETE
    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM servicos WHERE id_servico = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
    }
}

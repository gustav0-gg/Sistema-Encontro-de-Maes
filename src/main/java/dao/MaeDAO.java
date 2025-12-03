package dao;

import model.Mae;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MaeDAO {

    private Connection conn;

    public MaeDAO(Connection conn) {
        this.conn = conn;
    }

    // CREATE
    public void inserir(Mae mae) throws SQLException {
        String sql = "INSERT INTO maes (nome, telefone, endereco, data_aniversario) VALUES (?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, mae.getNome());
        stmt.setString(2, mae.getTelefone());
        stmt.setString(3, mae.getEndereco());
        stmt.setDate(4, Date.valueOf(mae.getData_aniversario()));
        stmt.executeUpdate();
        stmt.close();
    }

    // READ (por id)
    public Mae buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM maes WHERE id_mae = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();
        Mae mae = null;

        if (rs.next()) {
            mae = new Mae(
                rs.getInt("id_mae"),
                rs.getString("nome"),
                rs.getString("telefone"),
                rs.getString("endereco"),
                rs.getDate("data_aniversario").toLocalDate()
            );
        }

        rs.close();
        stmt.close();
        return mae;
    }

    // READ ALL
    public List<Mae> listarTodas() throws SQLException {
        String sql = "SELECT * FROM maes";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        List<Mae> lista = new ArrayList<>();

        while (rs.next()) {
            lista.add(new Mae(
                rs.getInt("id_mae"),
                rs.getString("nome"),
                rs.getString("telefone"),
                rs.getString("endereco"),
                rs.getDate("data_aniversario").toLocalDate()
            ));
        }

        rs.close();
        stmt.close();
        return lista;
    }

    // UPDATE
    public void atualizar(Mae mae) throws SQLException {
        String sql = "UPDATE maes SET nome = ?, telefone = ?, endereco = ?, data_aniversario = ? WHERE id_mae = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, mae.getNome());
        stmt.setString(2, mae.getTelefone());
        stmt.setString(3, mae.getEndereco());
        stmt.setDate(4, Date.valueOf(mae.getData_aniversario()));
        stmt.setInt(5, mae.getId_mae());
        stmt.executeUpdate();
        stmt.close();
    }

    // DELETE
    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM maes WHERE id_mae = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
    }
}

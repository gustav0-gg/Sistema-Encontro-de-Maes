package dao;

import model.Encontro;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EncontroDAO {

    private Connection conn;

    public EncontroDAO(Connection conn) {
        this.conn = conn;
    }

    // CREATE
    public void inserir(Encontro encontro) throws SQLException {
        String sql = "INSERT INTO encontros (data_encontro, realizado) VALUES (?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setDate(1, Date.valueOf(encontro.getData_encontro()));
        stmt.setInt(2, encontro.getRealizado());
        stmt.executeUpdate();
        stmt.close();
    }

    // READ by ID
    public Encontro buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM encontros WHERE id_encontro = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();
        Encontro encontro = null;

        if (rs.next()) {
            encontro = new Encontro(
                rs.getInt("id_encontro"),
                rs.getDate("data_encontro").toLocalDate(),
                rs.getInt("realizado")
            );
        }

        rs.close();
        stmt.close();
        return encontro;
    }

    // READ ALL
    public List<Encontro> listarTodos() throws SQLException {
        String sql = "SELECT * FROM encontros";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        List<Encontro> lista = new ArrayList<>();

        while (rs.next()) {
            lista.add(new Encontro(
                rs.getInt("id_encontro"),
                rs.getDate("data_encontro").toLocalDate(),
                rs.getInt("realizado")
            ));
        }

        rs.close();
        stmt.close();
        return lista;
    }

    // UPDATE
    public void atualizar(Encontro encontro) throws SQLException {
        String sql = "UPDATE encontros SET data_encontro = ?, realizado = ? WHERE id_encontro = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setDate(1, Date.valueOf(encontro.getData_encontro()));
        stmt.setInt(2, encontro.getRealizado());
        stmt.setInt(3, encontro.getId_encontro());
        stmt.executeUpdate();
        stmt.close();
    }

    // DELETE
    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM encontros WHERE id_encontro = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
    }
}

package dao;

import factory.ConnectionFactory;
import model.Encontro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EncontroDAO {

    // -------------------------------------------
    // CREATE
    // -------------------------------------------
    public void inserir(Encontro encontro) {
        String sql = "INSERT INTO encontros (data_encontro, realizado) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setDate(1, Date.valueOf(encontro.getData_encontro()));
            stmt.setInt(2, encontro.getRealizado());
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Erro ao inserir encontro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // -------------------------------------------
    // READ by ID
    // -------------------------------------------
    public Encontro buscarPorId(int id) {
        String sql = "SELECT * FROM encontros WHERE id_encontro = ?";
        Encontro encontro = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                encontro = new Encontro(
                    rs.getInt("id_encontro"),
                    rs.getDate("data_encontro").toLocalDate(),
                    rs.getInt("realizado")
                );
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar encontro por ID: " + e.getMessage());
            e.printStackTrace();
        }

        return encontro;
    }

    // -------------------------------------------
    // READ ALL
    // -------------------------------------------
    public List<Encontro> listarTodos() {
        String sql = "SELECT * FROM encontros";
        List<Encontro> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                lista.add(new Encontro(
                    rs.getInt("id_encontro"),
                    rs.getDate("data_encontro").toLocalDate(),
                    rs.getInt("realizado")
                ));
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao listar encontros: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    // -------------------------------------------
    // UPDATE
    // -------------------------------------------
    public void atualizar(Encontro encontro) {
        String sql = "UPDATE encontros SET data_encontro = ?, realizado = ? WHERE id_encontro = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setDate(1, Date.valueOf(encontro.getData_encontro()));
            stmt.setInt(2, encontro.getRealizado());
            stmt.setInt(3, encontro.getId_encontro());
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar encontro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // -------------------------------------------
    // DELETE
    // -------------------------------------------
    public void excluir(int id) {
        String sql = "DELETE FROM encontros WHERE id_encontro = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Erro ao excluir encontro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
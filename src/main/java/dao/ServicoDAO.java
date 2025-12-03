package dao;

import factory.ConnectionFactory;
import model.Servico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicoDAO {

    // -------------------------------------------
    // CREATE
    // -------------------------------------------
    public void inserir(Servico servico) {
        String sql = "INSERT INTO servicos (nome_servico) VALUES (?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, servico.getNome_servico());
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Erro ao inserir serviço: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // -------------------------------------------
    // READ by ID
    // -------------------------------------------
    public Servico buscarPorId(int id) {
        String sql = "SELECT * FROM servicos WHERE id_servico = ?";
        Servico servico = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                servico = new Servico(
                    rs.getInt("id_servico"),
                    rs.getString("nome_servico")
                );
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar serviço por ID: " + e.getMessage());
            e.printStackTrace();
        }

        return servico;
    }

    // -------------------------------------------
    // READ ALL
    // -------------------------------------------
    public List<Servico> listarTodos() {
        String sql = "SELECT * FROM servicos";
        List<Servico> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                lista.add(new Servico(
                    rs.getInt("id_servico"),
                    rs.getString("nome_servico")
                ));
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao listar serviços: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    // -------------------------------------------
    // UPDATE
    // -------------------------------------------
    public void atualizar(Servico servico) {
        String sql = "UPDATE servicos SET nome_servico = ? WHERE id_servico = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, servico.getNome_servico());
            stmt.setInt(2, servico.getId_servico());
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar serviço: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // -------------------------------------------
    // DELETE
    // -------------------------------------------
    public void excluir(int id) {
        String sql = "DELETE FROM servicos WHERE id_servico = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Erro ao excluir serviço: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public int buscarIdPorNome(String nome) {
        String sql = "SELECT id_servico FROM servicos WHERE nome_servico = ?";
        int id = -1;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id_servico");
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar ID do serviço: " + e.getMessage());
            e.printStackTrace();
        }

        return id;
    }
}
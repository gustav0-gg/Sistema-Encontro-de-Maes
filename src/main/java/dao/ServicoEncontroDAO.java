package dao;

import factory.ConnectionFactory;
import model.ServicoEncontro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicoEncontroDAO {

    // -------------------------------------------
    // CREATE
    // -------------------------------------------
    public void inserir(ServicoEncontro se) {
        String sql = "INSERT INTO servicos_encontros (id_encontro, id_servico, id_mae_responsavel, descricao) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, se.getId_encontro());
            stmt.setInt(2, se.getId_servico());
            stmt.setInt(3, se.getId_mae_responsavel());
            stmt.setString(4, se.getDescricao());
            
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Erro ao inserir serviço de encontro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // READ by ID
    // -------------------------------------------
    public ServicoEncontro buscarPorId(int id) {
        String sql = "SELECT * FROM servicos_encontros WHERE id_servico_encontro = ?";
        ServicoEncontro servicoEncontro = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                servicoEncontro = new ServicoEncontro(
                    rs.getInt("id_servico_encontro"),
                    rs.getInt("id_encontro"),
                    rs.getInt("id_servico"),
                    rs.getInt("id_mae_responsavel"),
                    rs.getString("descricao")
                );
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar serviço de encontro por ID: " + e.getMessage());
            e.printStackTrace();
        }

        return servicoEncontro;
    }

    // -------------------------------------------
    // READ ALL
    // -------------------------------------------
    public List<ServicoEncontro> listarTodos() {
        String sql = "SELECT * FROM servicos_encontros";
        List<ServicoEncontro> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                lista.add(new ServicoEncontro(
                    rs.getInt("id_servico_encontro"),
                    rs.getInt("id_encontro"),
                    rs.getInt("id_servico"),
                    rs.getInt("id_mae_responsavel"),
                    rs.getString("descricao")
                ));
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao listar serviços de encontro: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    // -------------------------------------------
    // UPDATE
    // -------------------------------------------
    public void atualizar(ServicoEncontro se) {
        String sql = "UPDATE servicos_encontros SET id_encontro = ?, id_servico = ?, id_mae_responsavel = ?, descricao = ? WHERE id_servico_encontro = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, se.getId_encontro());
            stmt.setInt(2, se.getId_servico());
            stmt.setInt(3, se.getId_mae_responsavel());
            stmt.setString(4, se.getDescricao());
            stmt.setInt(5, se.getId_servico_encontro());
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar serviço de encontro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // -------------------------------------------
    // DELETE
    // -------------------------------------------
    public void excluir(int id) {
        String sql = "DELETE FROM servicos_encontros WHERE id_servico_encontro = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Erro ao excluir serviço de encontro: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // -------------------------------------------
    // READ BY ENCONTRO
    // -------------------------------------------
    public List<ServicoEncontro> buscarPorEncontro(int idEncontro) {
        String sql = "SELECT * FROM servicos_encontros WHERE id_encontro = ?";
        List<ServicoEncontro> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idEncontro);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(new ServicoEncontro(
                    rs.getInt("id_servico_encontro"),
                    rs.getInt("id_encontro"),
                    rs.getInt("id_servico"),
                    rs.getInt("id_mae_responsavel"),
                    rs.getString("descricao")
                ));
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar serviços do encontro: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }
}
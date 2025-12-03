package dao;

import factory.ConnectionFactory;
import model.Mae;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaeDAO {

    // -------------------------------------------
    // CREATE
    // -------------------------------------------
    public void inserir(Mae mae) {
        String sql = "INSERT INTO maes (nome, telefone, endereco, data_aniversario) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, mae.getNome());
            stmt.setString(2, mae.getTelefone());
            stmt.setString(3, mae.getEndereco());
            stmt.setDate(4, Date.valueOf(mae.getData_aniversario()));

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao inserir mãe: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // -------------------------------------------
    // READ by ID
    // -------------------------------------------
    public Mae buscarPorId(int id) {
        String sql = "SELECT * FROM maes WHERE id_mae = ?";
        Mae mae = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                mae = new Mae(
                    rs.getInt("id_mae"),
                    rs.getString("nome"),
                    rs.getString("telefone"),
                    rs.getString("endereco"),
                    rs.getDate("data_aniversario").toLocalDate()
                );
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar mãe por ID: " + e.getMessage());
            e.printStackTrace();
        }

        return mae;
    }

    // -------------------------------------------
    // READ ALL
    // -------------------------------------------
    public List<Mae> listarTodas() {
        String sql = "SELECT * FROM maes";
        List<Mae> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Mae(
                    rs.getInt("id_mae"),
                    rs.getString("nome"),
                    rs.getString("telefone"),
                    rs.getString("endereco"),
                    rs.getDate("data_aniversario").toLocalDate()
                ));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar mães: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    // -------------------------------------------
    // UPDATE
    // -------------------------------------------
    public void atualizar(Mae mae) {
        String sql = "UPDATE maes SET nome = ?, telefone = ?, endereco = ?, data_aniversario = ? WHERE id_mae = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, mae.getNome());
            stmt.setString(2, mae.getTelefone());
            stmt.setString(3, mae.getEndereco());
            stmt.setDate(4, Date.valueOf(mae.getData_aniversario()));
            stmt.setInt(5, mae.getId_mae());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar mãe: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // -------------------------------------------
    // DELETE
    // -------------------------------------------
    public void excluir(int id) {
        String sql = "DELETE FROM maes WHERE id_mae = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao excluir mãe: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // -------------------------------------------
    // ANIVERSARIANTES DO MÊS
    // -------------------------------------------
    public List<Mae> listarAniversariantesDoMes(int mes) {
        List<Mae> lista = new ArrayList<>();
        String sql = "SELECT * FROM maes WHERE MONTH(data_aniversario) = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, mes);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(new Mae(
                    rs.getInt("id_mae"),
                    rs.getString("nome"),
                    rs.getString("telefone"),
                    rs.getString("endereco"),
                    rs.getDate("data_aniversario").toLocalDate()
                ));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar aniversariantes do mês: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }
}

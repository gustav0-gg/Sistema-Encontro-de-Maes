package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:mysql://localhost:3306/encontro_maes?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";     
    private static final String PASSWORD = "fatec";    

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
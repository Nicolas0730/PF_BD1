package co.edu.uniquindio.proyectofinal.bd1.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String URL = "jdbc:mysql://localhost:3306/bd1?serverTimezone=America/Bogota&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "nicolas";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

package co.edu.uniquindio.proyectofinal.bd1.database;

import co.edu.uniquindio.proyectofinal.bd1.model.Departamento;
import co.edu.uniquindio.proyectofinal.bd1.model.Especialidad;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EspecialidadDAO {


    /**
     * Método que recupera una lista de especialidades de la base de datos en la tabla especialidad
     * @return lista de especialidades
     * @throws SQLException
     */
    public static ObservableList<String> recuperarListaEspecialidades() throws SQLException {
        ObservableList<String> nombresEspe = FXCollections.observableArrayList();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Database.getConnection();
            stmt = conn.prepareStatement("SELECT nombre, descripcion FROM especialidad");
            rs = stmt.executeQuery();

            while (rs.next()) {
                nombresEspe.add(rs.getString("nombre")+" - "+ rs.getString("descripcion"));
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return nombresEspe;
    }

    /**
     * Método que recupera una especialidad dado su nombre
     * @param newValue el nombre de la especialidad a buscar
     * @return La especialidad encontrada
     * @throws SQLException
     */
    public static Especialidad recuperarCodigoEspecialidad(String newValue) throws SQLException {
        Especialidad especialidad = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Database.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM especialidad WHERE nombre = ?");
            stmt.setString(1,newValue);
            rs = stmt.executeQuery();
            if (rs.next()) {
                especialidad = new Especialidad(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5));
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        return especialidad;
    }
}

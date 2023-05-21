package co.edu.uniquindio.proyectofinal.bd1.database;

import co.edu.uniquindio.proyectofinal.bd1.model.Ciudad;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CiudadDAO {

    public CiudadDAO(){

    }

    /**
     * Método que busca en la BD una ciudad dado su codigo
     * @param codDANE de la ciudad a buscar
     * @return
     * @throws SQLException
     */
    public Ciudad consultarCiudadID(String codDANE ) throws SQLException {
        Ciudad ciudad=null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Database.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM ciudad WHERE codDANE = ?");
            stmt.setString(1,codDANE);
            rs = stmt.executeQuery();
            if (rs.next()) {
                ciudad = new Ciudad(rs.getInt(1),rs.getString(3), rs.getString(2),rs.getString(4) );
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        return ciudad;
    }

    /**
     * Método que consulta en la BD una ciudad dado su nombre
     * @param nombreCiudad
     * @return ciudad con el nombre indicado
     * @throws SQLException
     */
    public Ciudad consultarCiudadNombre(String nombreCiudad ) throws SQLException {
        Ciudad ciudad = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Database.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM ciudad WHERE nombre = ?");
            stmt.setString(1,nombreCiudad);
            rs = stmt.executeQuery();
            if (rs.next()) {
                ciudad = new Ciudad(rs.getInt(1),rs.getString(3), rs.getString(2),rs.getString(4) );
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return ciudad;
    }

    /**
     * Método que crea un ObservableList con todos los nombres de las ciudades existentes en la BD
     * @return Todas los nombres de las ciudades
     * @throws SQLException
     */
    public ObservableList<String> recuperarListaCiudades() throws SQLException {
        ObservableList<String> nombresCiudades = FXCollections.observableArrayList();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Database.getConnection();
            stmt = conn.prepareStatement("SELECT nombre FROM ciudad");
            rs = stmt.executeQuery();

            while (rs.next()) {
                nombresCiudades.add(rs.getString("nombre"));
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return nombresCiudades;
    }

}

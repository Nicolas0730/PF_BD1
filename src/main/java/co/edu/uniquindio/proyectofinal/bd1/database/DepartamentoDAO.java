package co.edu.uniquindio.proyectofinal.bd1.database;

import co.edu.uniquindio.proyectofinal.bd1.model.Departamento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartamentoDAO {

    public DepartamentoDAO(){

    }

    /**
     * Método que devuelve un Departamento de la BD dado su codigo (que es único)
     * @param codigoDep del departamento a buscar
     * @return departamento con el codigo @param codigoDep
     * @throws SQLException
     */
    public Departamento recuperarDepartamento(int codigoDep) throws SQLException, NoSuchAlgorithmException {
        Departamento departamento = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Database.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM departamento WHERE id = ?");
            stmt.setInt(1,codigoDep);
            rs = stmt.executeQuery();
            if (rs.next()) {
                departamento = new Departamento(codigoDep,rs.getString("nombre"));
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        return departamento;
    }

    /**
     * Método que retorna un ObservableList con todos los nombres de la tabla departamento
     * @return ObservableList con todos los nombres de los dept registados en la bd
     * @throws SQLException
     */
    public ObservableList<String> recuperarNombresDepartamentos() throws SQLException {
        ObservableList<String> nombresDepartamentos = FXCollections.observableArrayList();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Database.getConnection();
            stmt = conn.prepareStatement("SELECT nombre FROM departamento");
            rs = stmt.executeQuery();

            while (rs.next()) {
                nombresDepartamentos.add(rs.getString("nombre"));
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return nombresDepartamentos;
    }
}

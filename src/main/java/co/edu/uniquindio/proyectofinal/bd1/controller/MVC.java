package co.edu.uniquindio.proyectofinal.bd1.controller;

import co.edu.uniquindio.proyectofinal.bd1.Aplicacion;
import co.edu.uniquindio.proyectofinal.bd1.database.CiudadDAO;
import co.edu.uniquindio.proyectofinal.bd1.database.DepartamentoDAO;
import co.edu.uniquindio.proyectofinal.bd1.database.EspecialidadDAO;
import co.edu.uniquindio.proyectofinal.bd1.model.Ciudad;
import co.edu.uniquindio.proyectofinal.bd1.model.Departamento;
import co.edu.uniquindio.proyectofinal.bd1.model.Especialidad;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MVC {

    private ArrayList<Ciudad> ciudad = new ArrayList<>();
    private Aplicacion aplicacion = new Aplicacion();
    private DepartamentoDAO departamentoDAO = new DepartamentoDAO();
    private CiudadDAO ciudadDAO = new CiudadDAO();


    /**
     * Método que llama un método de la clase departamentoDAO y retorna un departamento
     * @param codigoDep código (id) del departamento a buscar
     * @throws SQLException
     * @throws NoSuchAlgorithmException
     */
    public Departamento mostrarDepartamento(int codigoDep) throws SQLException, NoSuchAlgorithmException {
        Departamento dep=departamentoDAO.recuperarDepartamento(codigoDep);
        return dep;
    }

    /**
     * Método que llama un método de la clase departamentoDAO y retorna un ObservableLis<String>
     * @return ObservableList con los nombres de los departamentos que hay en la BD
     * @throws SQLException
     */
    public ObservableList<String> listarDepartamentos() throws SQLException {
        return departamentoDAO.recuperarNombresDepartamentos();
    }

    /**
     * Método que muestra una
     * @param nombreCiudad
     * @throws SQLException
     */
    public Ciudad mostrarCiudad(String nombreCiudad) throws SQLException {

        Ciudad ciudad = ciudadDAO.consultarCiudadNombre(nombreCiudad);

        if (ciudad == null) {
            JOptionPane.showMessageDialog(null, "NO SE ENCONTRÓ LA CIUDAD CON EL NOMBRE " + nombreCiudad);
            return null;
        } else {
//            System.out.println("Nombre ciudad: " + ciudad.getNombre() + "\nCODIGO DANE: " + ciudad.getCodigo() + "\nCODIGO DEP: " + ciudad.getCodigo_Dep());
            return ciudad;
        }

    }

    public ObservableList<String> listarCiudades() throws SQLException {
        return ciudadDAO.recuperarListaCiudades();
    }

    /**
     * Método que llama a EspecialidadDAO a que consulte los nombres de las especialidades en la base de
     * datos y en la tabla especialidad
     * @return
     * @throws SQLException
     */
    public ObservableList<String> listarEspecialidades() throws SQLException {
        return EspecialidadDAO.recuperarListaEspecialidades();
    }

    public Especialidad mostrarCodigo(String newValue) throws SQLException {
        return EspecialidadDAO.recuperarCodigoEspecialidad(newValue);
    }
}

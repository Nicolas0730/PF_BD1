package co.edu.uniquindio.proyectofinal.bd1.controller;

import co.edu.uniquindio.proyectofinal.bd1.Aplicacion;
import co.edu.uniquindio.proyectofinal.bd1.database.DepartamentoDAO;
import co.edu.uniquindio.proyectofinal.bd1.model.Ciudad;
import co.edu.uniquindio.proyectofinal.bd1.model.Departamento;
import javafx.collections.ObservableList;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MVC {

    private ArrayList<Ciudad> ciudad = new ArrayList<>();
    private Aplicacion aplicacion = new Aplicacion();
    private DepartamentoDAO departamentoDAO = new DepartamentoDAO();


    /**
     * Método que llama un método de la clase departamentoDAO y retorna un departamento
     * @param codigoDep código (id) del departamento a buscar
     * @throws SQLException
     * @throws NoSuchAlgorithmException
     */
    public void mostrarDepartamento(int codigoDep) throws SQLException, NoSuchAlgorithmException {
        Departamento dep=departamentoDAO.recuperarDepartamento(codigoDep);
        System.out.println("Codigo departamento: "+dep.getId()+"\nNombre departamento: "+dep.getNombre());
    }

    /**
     * Método que llama un método de la clase departamentoDAO y retorna un ObservableLis<String>
     * @return ObservableList con los nombres de los departamentos que hay en la BD
     * @throws SQLException
     */
    public ObservableList<String> listarDepartamentos() throws SQLException {
        return departamentoDAO.recuperarNombresDepartamentos();
    }
}

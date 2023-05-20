package co.edu.uniquindio.proyectofinal.bd1.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class consultaProcesoController implements Initializable{

    @FXML
    private ComboBox<String> comboBoxAño;

    @FXML
    private ComboBox<String> comboBoxCiudad = new ComboBox<String>();

    @FXML
    private ComboBox<String> comboBoxEspecialidad = new ComboBox<String>();

    @FXML
    private TextField textFieldNumeroConsecutivo;

    @FXML
    private TextField txtFieldDespacho;

    @FXML
    private TextField txtFieldNumeroProceso;

    @FXML
    private TextField txtFieldNumeroRadicacion;
    @FXML
    private Button consultarBtn;

    MVC modelFactory = new MVC();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    cargarCiudades();
    cargarEspecialidad();
    }
    private void cargarCiudades() {
        ObservableList<String> ciudades = FXCollections.observableArrayList();
        ciudades.add("Quindío");
        comboBoxCiudad.getItems().addAll("Armenia","Medellin", "Bogotá", "Cali", "Barranquilla", "Cartagena", "Bucaramanga");
    }

    private void cargarEspecialidad(){
        comboBoxEspecialidad.getItems().addAll("TRIBUNAL ADMINISTRATIVO DEL QUINDÍO","TRIBUNAL SUPERIOR SALA PENAL ARMENIA");
    }

    /**
     * Button que se encarga de realizar búsquedas
     * @param event codigo del proceso a consultar
     * @throws SQLException
     * @throws NoSuchAlgorithmException
     */
    @FXML
    void realizarConsulta(ActionEvent event) throws SQLException, NoSuchAlgorithmException {
        if (!txtFieldNumeroProceso.getText().isEmpty()){
            modelFactory.mostrarDepartamento(Integer.parseInt(txtFieldNumeroProceso.getText()));
        }

        ObservableList<String> nombresDepartamentos = null;
        nombresDepartamentos=modelFactory.listarDepartamentos();

    // Recorriendo e imprimiendo los elementos con un bucle for-each
        for (String nombre : nombresDepartamentos) {
            System.out.println(nombre);
        }
    }

}

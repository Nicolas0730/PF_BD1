package co.edu.uniquindio.proyectofinal.bd1.controller;
import co.edu.uniquindio.proyectofinal.bd1.model.Departamento;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
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
    @FXML
    private Button nuevaConsultaBtn;
    @FXML
    private Label labelDespacho = new Label();

    MVC modelFactory = new MVC();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            cargarCiudades();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        cargarEspecialidad();
        cargarAños();
        labelDespacho.setVisible(false);

//        desactivarCampos();

    }

    public void desactivarCampos(){
        txtFieldDespacho.setDisable(true);
        comboBoxAño.setDisable(true);
        txtFieldNumeroRadicacion.setDisable(true);
        textFieldNumeroConsecutivo.setDisable(true);
    }

    /**
     * Método que limpia todos los datos para volver a empezar a construir el numero de proceso
     * @param event
     */
    @FXML
    void limpiarCampos(MouseEvent event) {
        txtFieldDespacho.clear();
        comboBoxAño.getSelectionModel().select(null);
        comboBoxCiudad.getSelectionModel().select(null);
        comboBoxEspecialidad.getSelectionModel().select(null);
        txtFieldNumeroRadicacion.clear();
        textFieldNumeroConsecutivo.clear();
        txtFieldNumeroProceso.clear();
    }
    private void cargarAños() {
        ObservableList<String> anios = FXCollections.observableArrayList();
        for (int anio = 1950; anio <= 2023; anio++) {
            anios.add(Integer.toString(anio));
        }
        comboBoxAño.setItems(anios);
    }

    /**
     * Método que carga en el comboBox la lista de ciudades contenidas en un ObservableList
     * @throws SQLException
     */
    private void cargarCiudades() throws SQLException {
        ObservableList<String> ciudades = modelFactory.listarCiudades();
            comboBoxCiudad.setItems(ciudades);
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

        if (!comboBoxCiudad.getValue().isEmpty()&&comboBoxCiudad.getValue()!=null){
            modelFactory.mostrarCiudad(comboBoxCiudad.getValue());
            txtFieldDespacho.setDisable(false);
        }
    }

    /**
     * Método que utiliza la información de la ciudad/departamento seleccionada en el Combobox
     */
    @FXML
    void escribirCodNumProceso() {
        if (comboBoxCiudad.getValue()!=null){
            txtFieldDespacho.setDisable(false);
        }
            comboBoxCiudad.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                    try {
                        Departamento dep=modelFactory.mostrarDepartamento(Integer.parseInt(modelFactory.mostrarCiudad(newValue).getCodigo_Dep()));
                        String codDep = Integer.toString(dep.getId());
                                    if(codDep.length()==1){
                            codDep="0"+codDep;
                        }
                        txtFieldNumeroProceso.setText(codDep);
                        txtFieldNumeroProceso.setText(txtFieldNumeroProceso.getText()+""+modelFactory.mostrarCiudad(newValue).getCodigo());
                        txtFieldNumeroProceso.setAlignment(Pos.CENTER_RIGHT);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
    }

    @FXML
    void escribirAño(ActionEvent event) {
        String aux = txtFieldNumeroProceso.getText();
        if (txtFieldNumeroProceso.getText().length()==12){
            txtFieldNumeroProceso.setText(aux + "" +comboBoxAño.getValue());
        }else if (txtFieldNumeroProceso.getText().length()==16){
            String content = txtFieldNumeroProceso.getText().substring(0,12);
            txtFieldNumeroProceso.setText(content+""+comboBoxAño.getValue());
        }
        }

    @FXML
    void escribirConsecutivo(ActionEvent event) {

    }

    /**
     * Método que escribe en el número de proceso el número de despacho.
     * @param event
     */
    @FXML
    void escribirDespacho(ActionEvent event) {

        String aux = txtFieldNumeroProceso.getText();
        if (txtFieldDespacho.getText().length()!=12){
            labelDespacho.setVisible(true);
        }
        if (txtFieldNumeroProceso.getText().length()==9 && aux.length()+txtFieldDespacho.getText().length()==12){
            txtFieldNumeroProceso.setText(aux + "" + txtFieldDespacho.getText());
            labelDespacho.setVisible(false);
        }else if (txtFieldNumeroProceso.getText().length()==12){
            String content = txtFieldNumeroProceso.getText().substring(0,9);
                if (content.length()+txtFieldDespacho.getText().length()==12){
                    txtFieldNumeroProceso.setText(content+""+txtFieldDespacho.getText());
                    labelDespacho.setVisible(false);
                }

        }
    }


    @FXML
    void escribirRadicacion(ActionEvent event) {
//        if (!txtFieldNumeroRadicacion.getText().isEmpty()){
//            textFieldNumeroConsecutivo.setDisable(false);
//        }
    }


}

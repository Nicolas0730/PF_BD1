package co.edu.uniquindio.proyectofinal.bd1.controller;
import co.edu.uniquindio.proyectofinal.bd1.model.Ciudad;
import co.edu.uniquindio.proyectofinal.bd1.model.Departamento;
import co.edu.uniquindio.proyectofinal.bd1.model.Especialidad;
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
    private Label labelDespacho = new Label();
    @FXML
    private Label labelConsecutivo = new Label();
    @FXML
    private Label labelRadicacion = new Label();

    MVC modelFactory = new MVC();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            cargarCiudades();
            cargarEspecialidad();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        cargarAños();
        labelDespacho.setVisible(false);
        labelConsecutivo.setVisible(false);
        labelRadicacion.setVisible(false);

    }

    public void desactivarCampos(){
        txtFieldDespacho.setDisable(true);
        comboBoxAño.setDisable(true);
        txtFieldNumeroRadicacion.setDisable(true);
        textFieldNumeroConsecutivo.setDisable(true);
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

    /**
     * Metodo que carga en el combobox las especialidades
     * @throws SQLException
     */
    private void cargarEspecialidad() throws SQLException {
        ObservableList<String> especialidades = modelFactory.listarEspecialidades();
        comboBoxEspecialidad.setItems(especialidades);
    }

    /**
     * Método que se encarga de poner en el textfield del numero de proceso
     * el codigo del lugar que se haya seleccionado en el ecomboBox
     */
    @FXML
    private void cargarCodigoEspecialidad(){
        comboBoxEspecialidad.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                try {
                    String aux=separarTexto(newValue);
                    Especialidad especialidad = modelFactory.mostrarCodigo(aux);
                    String codDane=Integer.toString(especialidad.getCod_dane());
                    String codCat=Integer.toString(especialidad.getCategoria_id()); //debe ser de 2 cifras

                    if (codCat.length()==1){
                        codCat="0"+codCat;
                    }
                    if (codDane.length()==1){
                        codDane="0"+codDane;
                    }

                    if (txtFieldNumeroProceso.getText().length()==9){
                        String a=txtFieldNumeroProceso.getText().substring(0,5);
                        txtFieldNumeroProceso.setText(a+""+codDane+codCat);
                    }
                    if (txtFieldNumeroProceso.getText().length()==5){
                    txtFieldNumeroProceso.setText(txtFieldNumeroProceso.getText()+""+codDane+codCat);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private String separarTexto(String newValue) {
        String[] palabras = newValue.split(" ");
        StringBuilder resultado = new StringBuilder();

        for (String palabra : palabras) {
            if (palabra.matches("[A-Z]+")) {
                resultado.append(palabra).append(" ");
            }
        }

        return resultado.toString().trim();
    }

    /**
     * Button que se encarga de realizar búsquedas
     * @param event codigo del proceso a consultar
     * @throws SQLException
     * @throws NoSuchAlgorithmException
     */
    @FXML
    void realizarConsulta(ActionEvent event) throws SQLException, NoSuchAlgorithmException {
        JOptionPane.showMessageDialog(null,"El proceso con el código: "+txtFieldNumeroProceso.getText()+" está siendo consultado.");
    }

    /**
     * Método que utiliza la información de la ciudad/departamento seleccionada en el Combobox
     */
    @FXML
    void escribirCodNumProceso() throws SQLException, NoSuchAlgorithmException {

        if (comboBoxCiudad.getValue()!=null){
            txtFieldDespacho.setDisable(false);
        }
            comboBoxCiudad.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                    try {
                        Departamento dep=modelFactory.mostrarDepartamento(Integer.parseInt(modelFactory.mostrarCiudad(newValue).getCodigo()));
                        String codDep = Integer.toString(dep.getId());
                                    if(codDep.length()==1){
                            codDep="0"+codDep;
                        }
                        txtFieldNumeroProceso.setText(codDep);
                        txtFieldNumeroProceso.setText(txtFieldNumeroProceso.getText()+""+modelFactory.mostrarCiudad(newValue).getCodigo_Dep());
                        txtFieldNumeroProceso.setAlignment(Pos.CENTER_RIGHT);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
    }

    /**
     * Metodo que escribe el año seleccionado en el textfieldNumeroProceso
     * @param event
     */
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

    /**
     * Metodo que escribe el consecutivo puesto por el usuario en el campo de consecutivo
     * @param event
     */
    @FXML
    void escribirConsecutivo(ActionEvent event) {
        String aux = txtFieldNumeroProceso.getText();
        if (textFieldNumeroConsecutivo.getText().length()!=23){
            labelConsecutivo.setVisible(true);
        }if (textFieldNumeroConsecutivo.getText().length()==23){
            labelConsecutivo.setVisible(false);
        }
        if (txtFieldNumeroProceso.getText().length()==21 && txtFieldNumeroProceso.getText().length()+textFieldNumeroConsecutivo.getText().length()==23){
            txtFieldNumeroProceso.setText(aux + "" + textFieldNumeroConsecutivo.getText());
            labelConsecutivo.setVisible(false);
        } if (txtFieldNumeroProceso.getText().length()==23){
            String content = txtFieldNumeroProceso.getText().substring(0,21);
            if ((content.length()+textFieldNumeroConsecutivo.getText().length())==23){
                txtFieldNumeroProceso.setText(content+""+textFieldNumeroConsecutivo.getText());
                labelConsecutivo.setVisible(false);
            }
        }
    }


    /**
     * Método que escribe en el número de proceso el número de despacho.
     * el tamaño de este es de 3
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


    /**
     * Método que escribe el número de radicación dentro del número de proceso
     * el tamaño de este es de 5
     * @param event
     */
    @FXML
    void escribirRadicacion(ActionEvent event) {
        String aux = txtFieldNumeroProceso.getText();
        if (txtFieldNumeroRadicacion.getText().length()!=21){
            labelRadicacion.setVisible(true);
        }if (txtFieldNumeroRadicacion.getText().length()==21){
            labelRadicacion.setVisible(false);
        }
        if (txtFieldNumeroProceso.getText().length()==16 && txtFieldNumeroProceso.getText().length()+txtFieldNumeroRadicacion.getText().length()==21){
            txtFieldNumeroProceso.setText(aux + "" + txtFieldNumeroRadicacion.getText());
            labelRadicacion.setVisible(false);
        } if (txtFieldNumeroProceso.getText().length()==21){
            String content = txtFieldNumeroProceso.getText().substring(0,16);
                if ((content.length()+txtFieldNumeroRadicacion.getText().length())==21){
                    txtFieldNumeroProceso.setText(content+""+txtFieldNumeroRadicacion.getText());
                    labelRadicacion.setVisible(false);
                }
        }
    }


}

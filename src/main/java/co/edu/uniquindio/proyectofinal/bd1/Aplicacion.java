package co.edu.uniquindio.proyectofinal.bd1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class Aplicacion  extends Application{

    private Stage primaryStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws IOException {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Constructora CAMU");
        mostrarVentanaInicio();
    }


    private void mostrarVentanaInicio() {
            try {
                primaryStage.close();
                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(Aplicacion.class.getResource("view/inicio.fxml"));
                System.out.println(loader.getLocation());

                AnchorPane vistaIndex = (AnchorPane) loader.load();
                Scene scene = new Scene(vistaIndex);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
    }


    public static void main(String[] args) {
        launch();
    }
}

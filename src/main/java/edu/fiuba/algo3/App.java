package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.vista.*;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        stage.setTitle("AlgoHoot");
        Image icono = new Image("file:"+System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/icono.png");
        stage.getIcons().add(icono);
        stage.setResizable(false);
        VistaMenuPrincipal vistaMenu = new VistaMenuPrincipal(stage, 1280, 720);
        stage.setScene(vistaMenu);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}
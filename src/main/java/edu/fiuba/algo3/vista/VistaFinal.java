package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.AlgoHoot;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Pregunta;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import static java.lang.Math.floor;

public class VistaFinal extends Scene {

    public VistaFinal(double width, double height, String ganador) {
        super(new FlowPane(), width, height);
        FlowPane root = (FlowPane) this.getRoot();
        BackgroundImage imagenFondo = new BackgroundImage(new Image("file:"+System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/background.png"), BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background fondo = new Background(imagenFondo);
        root.setBackground(fondo);
        this.getStylesheets().add("styles.css");


        Label titulo = new Label(ganador);
        titulo.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 24px");
        root.getChildren().add(titulo);
    }

}

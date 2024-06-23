package edu.fiuba.algo3;

import edu.fiuba.algo3.vista.elementos.LogoMenu;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class AppTests extends Application {

    // Velocidad de movimiento
    private double dx = 4;
    private double dy = 4;

    @Override
    public void start(Stage primaryStage) {

        Pane pane = new Pane();
        for (int k = 0; k < 5; k++) {
            for (int i = 0; i < 8; i++) {
                Image imagen = new Image("file:"+System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/logos/ah"+(i+1)+".png");
                pane.getChildren().add(new LogoMenu(imagen, 1280, 720));
            }
        }

        Scene scene = new Scene(pane, 1280, 720);
        BackgroundImage imagenFondo = new BackgroundImage(new Image("file:"+System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/background2.png"), BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background fondo = new Background(imagenFondo);
        pane.setBackground(fondo);
        primaryStage.setScene(scene);
        primaryStage.setTitle("MainMenu");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

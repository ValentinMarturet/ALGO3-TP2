package edu.fiuba.algo3.vista.escenas;

import edu.fiuba.algo3.controladores.CambiadorDeVistas;
import edu.fiuba.algo3.modelo.AlgoHoot.AlgoHoot;
import edu.fiuba.algo3.vista.elementos.VistaTableroJugadores;
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

public class VistaFinPregunta extends Scene {
    private Stage stage;
    private VistaTableroJugadores tablero;

    public VistaFinPregunta(Stage stage, double width, double height, String textoFin, VistaTableroJugadores tablero) {
        super(new FlowPane(), width, height);
        FlowPane root = (FlowPane) this.getRoot();
        double margenAncho = width/32;
        double margenAlto = height/18;
        BackgroundImage imagenFondo = new BackgroundImage(new Image("file:"+System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/background.png"), BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background fondo = new Background(imagenFondo);
        root.setBackground(fondo);
        this.getStylesheets().add("styles.css");

        this.stage = stage;
        this.tablero = tablero;


        FlowPane segundaColumna = new FlowPane();
        segundaColumna.setPrefHeight(height);
        segundaColumna.setPrefWidth(floor(width * 2/3));
        root.getChildren().add(segundaColumna);

        FlowPane panelTextoFin = new FlowPane();
        panelTextoFin.setPrefWidth(floor(width * 2/3));
        panelTextoFin.setPrefHeight(height/3);
        segundaColumna.getChildren().add(panelTextoFin);

        Label labelTextoFin = new Label(textoFin);
        labelTextoFin.setPrefHeight(height*2/5 - 2*margenAlto);
        labelTextoFin.setPrefWidth(width * 2/3 - 2*margenAncho);
        FlowPane.setMargin(labelTextoFin,new Insets(margenAlto, margenAncho, margenAlto, margenAncho));
        establecerEstilo(labelTextoFin);
        cambiarTamanoFuente(labelTextoFin, 32);
        labelTextoFin.setWrapText(true);
        panelTextoFin.getChildren().add(labelTextoFin);

        StackPane panelBoton = new StackPane();
        panelBoton.setPrefWidth(floor(width * 2/3));
        panelBoton.setPrefHeight(floor(height * 2/3));
        segundaColumna.getChildren().add(panelBoton);

        ImageView imagen = new ImageView(new Image("file:"+System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/botonSiguiente.png"));
        Button boton = new Button("", imagen);
        boton.setStyle("-fx-background-color: transparent;");
        panelBoton.getChildren().add(boton);

        boton.setOnAction(event -> {
            AlgoHoot a = AlgoHoot.getInstancia();
            a.comenzarNuevaRondaDePreguntas();
            CambiadorDeVistas.cambiarVistaANuevaPregunta(stage,tablero);
        });

        FlowPane panelTableroJugadores = new FlowPane();
        panelTableroJugadores.setPrefHeight(height - 2*margenAlto);
        panelTableroJugadores.setPrefWidth(floor(width/3 - margenAncho));
        FlowPane.setMargin(panelTableroJugadores,new Insets(margenAlto, 0, margenAlto, margenAncho));
        root.getChildren().add(panelTableroJugadores);
        panelTableroJugadores.getChildren().add(tablero);
    }


    private void establecerEstilo(Node nodo) {
        nodo.setStyle("-fx-background-color: white;" +
                "-fx-border-width: 4px;" +
                "-fx-text-fill: black;" +
                "-fx-font-family: 'Comic Sans MS';" +
                "-fx-text-alignment: center;" +
                "-fx-border-color: black;");
    }

    private void cambiarTamanoFuente(Node nodo, int size) {
        String estiloAnterior = nodo.getStyle();
        nodo.setStyle(estiloAnterior + "-fx-font-size: "+size+";");
    }
}

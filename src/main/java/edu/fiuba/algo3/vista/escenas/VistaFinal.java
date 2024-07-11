package edu.fiuba.algo3.vista.escenas;

import edu.fiuba.algo3.modelo.AlgoHoot.AlgoHoot;
import edu.fiuba.algo3.modelo.AlgoHoot.Jugador;
import edu.fiuba.algo3.vista.elementos.BotonVF;
import edu.fiuba.algo3.vista.elementos.LabelJugadorConPuntaje;
import edu.fiuba.algo3.vista.elementos.VistaTableroJugadores;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import static java.lang.Math.floor;

public class VistaFinal extends Scene {

    public VistaFinal(Stage stage, double width, double height, VistaTableroJugadores tablero) {
        super(new FlowPane(), width, height);
        FlowPane root = (FlowPane) this.getRoot();
        root.setPrefWidth(width);
        root.setPrefHeight(height);
        BackgroundImage imagenFondo = new BackgroundImage(new Image("file:"+System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/background2.png"), BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background fondo = new Background(imagenFondo);
        root.setBackground(fondo);
        this.getStylesheets().add("styles.css");

        FlowPane panelContenido = new FlowPane();
        panelContenido.setPrefHeight(root.getPrefHeight());
        panelContenido.setPrefWidth(floor(root.getPrefWidth()*2/3));
        FlowPane panelGanador = new FlowPane();
        panelGanador.setPrefHeight(floor(root.getPrefHeight()*2/3));
        panelGanador.setPrefWidth(floor(root.getPrefWidth()*2/3));
        FlowPane panelBotones = new FlowPane();
        panelBotones.setPrefHeight(floor(root.getPrefHeight()*1/3));
        panelBotones.setPrefWidth(floor(root.getPrefWidth()*2/3));
        panelContenido.getChildren().addAll(panelGanador,panelBotones);
        FlowPane panelTablero = new FlowPane();
        panelTablero.setPrefHeight(root.getPrefHeight());
        panelTablero.setPrefWidth(floor(root.getPrefWidth()*1/3));
        panelTablero.setAlignment(Pos.CENTER);
        root.getChildren().addAll(panelContenido,panelTablero);
        tablero.ordenarPorPuntajes();
        panelTablero.getChildren().add(tablero);

        panelGanador.setAlignment(Pos.CENTER);
        VBox boxGanador = new VBox();
        boxGanador.setAlignment(Pos.CENTER);
        boxGanador.setSpacing(50);
        Label labelFin = new Label("juego terminado!!");
        labelFin.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-width: 4px;" +
                "-fx-text-fill: black;" +
                "-fx-font-size: 50;" +
                "-fx-padding: 20px;" +
                "-fx-font-family: 'Comic Sans MS';" +
                "-fx-text-alignment: center;" +
                "-fx-border-color: black;"
        );

        FlowPane panelVistaGanador = new FlowPane();
        panelVistaGanador.setMaxWidth(300);
        Label labelGanador = new Label("ganador: ");
        labelGanador.setStyle(
                "-fx-text-fill: black;" +
                "-fx-font-size: 30;" +
                "-fx-font-family: 'Comic Sans MS';" +
                "-fx-text-alignment: center;"
        );
        panelVistaGanador.getChildren().add(labelGanador);

        Jugador ganador = AlgoHoot.getInstancia().obtenerGanador();
        ganador.hacerConNombreYPuntaje( (nombre,puntos) -> {
            LabelJugadorConPuntaje l = new LabelJugadorConPuntaje(nombre,puntos,300,100);
            l.resaltar();
            panelVistaGanador.getChildren().add(l);
        });

        boxGanador.getChildren().addAll(labelFin, panelVistaGanador);
        panelGanador.getChildren().add(boxGanador);


        HBox boxBotones = new HBox();
        boxBotones.setAlignment(Pos.CENTER);
        boxBotones.setSpacing(40);
        panelBotones.setAlignment(Pos.CENTER);
        panelBotones.getChildren().add(boxBotones);
        BotonVF botonCerrar = new BotonVF("cerrar juego", 250);
        BotonVF botonVolverAJugar = new BotonVF("volver a jugar", 250);
        boxBotones.getChildren().addAll(botonVolverAJugar,botonCerrar);

        botonCerrar.setOnAction(e -> {
            stage.close();
        });

        botonVolverAJugar.setOnAction(e -> {
            AlgoHoot.reiniciarInstancia();
            stage.setScene(new VistaMenuPrincipal(stage, 1280, 720));
        });

    }

}

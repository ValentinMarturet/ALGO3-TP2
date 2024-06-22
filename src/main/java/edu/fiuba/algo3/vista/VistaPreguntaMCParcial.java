package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controladores.ControladorResponderMC;
import edu.fiuba.algo3.modelo.Opcion;
import edu.fiuba.algo3.modelo.PreguntaMC;
import edu.fiuba.algo3.modelo.PreguntaMCParcial;
import edu.fiuba.algo3.modelo.PreguntaOC;
import edu.fiuba.algo3.vista.botones.BotonMC;
import edu.fiuba.algo3.vista.botones.BotonPoder;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.floor;

public class VistaPreguntaMCParcial extends Scene {
    private FlowPane root;

    public VistaPreguntaMCParcial(Stage stage, double width, double height, PreguntaMCParcial pregunta, VistaTableroJugadores tablero) {
        super(new FlowPane(), width, height);
        double margenAncho = width/32;
        double margenAlto = height/18;
        this.root = (FlowPane) this.getRoot();
        BackgroundImage imagenFondo = new BackgroundImage(new Image("file:"+System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/background.png"),BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
        Background fondo = new Background(imagenFondo);
        this.root.setBackground(fondo);
        this.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        FlowPane panelTableroJugadores = new FlowPane();
        panelTableroJugadores.setPrefHeight(height - 2*margenAlto);
        panelTableroJugadores.setPrefWidth(floor(width/3 - margenAncho));
        FlowPane.setMargin(panelTableroJugadores,new Insets(margenAlto, 0, margenAlto, margenAncho));
        this.root.getChildren().add(panelTableroJugadores);
        panelTableroJugadores.getChildren().add(tablero);
        if (!tablero.getItems().isEmpty()) {tablero.resaltarSiguienteJugador();}

        FlowPane panelPregunta = new FlowPane();
        panelPregunta.setPrefHeight(height);
        panelPregunta.setPrefWidth(floor(width * 2/3));
        this.root.getChildren().add(panelPregunta);


        Label textoPregunta = new Label(pregunta.getPregunta());
        textoPregunta.setPrefHeight(height*2/5 - 2*margenAlto);
        textoPregunta.setPrefWidth(width * 2/3 - 2*margenAncho);
        FlowPane.setMargin(textoPregunta,new Insets(margenAlto, margenAncho, margenAlto, margenAncho));
        establecerEstilo(textoPregunta);
        cambiarTamanoFuente(textoPregunta, 32);

        textoPregunta.setWrapText(true);
        StackPane contenidoPregunta= new StackPane();
        contenidoPregunta.setPrefWidth(floor(width * 2/3));
        contenidoPregunta.setPrefHeight(height/3);
        StackPane.setMargin(textoPregunta,new Insets(margenAlto, margenAncho, margenAlto, margenAncho));

        HBox contenedorTipo = new HBox();
        contenedorTipo.setPrefWidth(floor(width * 2/3));
        Label tipoDePregunta = new Label("Multiple Choice Puntaje Parcial");
        contenedorTipo.setPrefHeight(tipoDePregunta.getPrefHeight());

        establecerEstilo(tipoDePregunta);
        cambiarTamanoFuente(tipoDePregunta, 25);
        contenedorTipo.getChildren().add(tipoDePregunta);
        contenedorTipo.setAlignment(Pos.TOP_RIGHT);
        tipoDePregunta.setPadding(new Insets(0,6,2,6));

        StackPane.setMargin(contenedorTipo,new Insets(margenAlto/3, margenAncho/2, 0, 0));
        contenidoPregunta.getChildren().addAll(textoPregunta,contenedorTipo);

        panelPregunta.getChildren().add(contenidoPregunta);


        FlowPane panelBotones = new FlowPane();
        panelBotones.setPrefHeight(height - textoPregunta.getPrefHeight());
        panelBotones.setPrefWidth(textoPregunta.getPrefWidth());
        FlowPane.setMargin(panelBotones,new Insets(0, margenAncho, 0, margenAncho));
        panelPregunta.getChildren().add(panelBotones);

        FlowPane panelOpciones = new FlowPane();
        panelOpciones.setPrefHeight(height/2);
        panelOpciones.setPrefWidth(floor((panelBotones.getPrefWidth())*3/4));
        panelOpciones.setAlignment(Pos.CENTER);
        panelBotones.getChildren().add(panelOpciones);

        FlowPane panelBotonesControl = new FlowPane();
        panelBotonesControl.setPrefHeight(height/2);
        panelBotonesControl.setPrefWidth(floor((panelBotones.getPrefWidth())*1/4));
        panelBotones.getChildren().add(panelBotonesControl);

        FlowPane panelBotonResponder = new FlowPane();
        panelBotonResponder.setPrefHeight(height/8);
        panelBotonResponder.setPrefWidth(panelBotonesControl.getPrefWidth());
        panelBotonResponder.setAlignment(Pos.CENTER);
        panelBotonesControl.getChildren().add(panelBotonResponder);

        ImageView imagenResponder = new ImageView(new Image("file:"+System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/botonResponder.png"));
        Button botonResponder = new Button("", imagenResponder);
        botonResponder.setStyle("-fx-background-color: transparent;");
        panelBotonResponder.getChildren().add(botonResponder);

        FlowPane panelBotonesPoderes = new FlowPane();
        panelBotonesPoderes.setPrefHeight(panelBotonesControl.getPrefHeight() - panelBotonResponder.getPrefHeight());
        panelBotonesPoderes.setPrefWidth(panelBotonesControl.getPrefWidth());
        panelBotonesControl.getChildren().add(panelBotonesPoderes);
        VBox poderes = new VBox();
        poderes.setPrefHeight(panelBotonesPoderes.getPrefHeight());
        poderes.setPrefWidth(panelBotonesPoderes.getPrefWidth());
        poderes.setAlignment(Pos.CENTER);
        poderes.setPadding(new Insets(margenAlto,0,0,0));
        poderes.setSpacing(margenAlto/2);
        panelBotonesPoderes.getChildren().add(poderes);


        VBox opciones = new VBox();
        opciones.setPrefHeight(panelOpciones.getPrefHeight());
        opciones.setPrefWidth(panelOpciones.getPrefWidth());
        opciones.setAlignment(Pos.CENTER);
        opciones.setSpacing(25);
        panelOpciones.getChildren().add(opciones);

        List<String> stringOpciones = pregunta.getOpciones().stream().map(Opcion::getOpcion).collect(Collectors.toList());
        Collections.shuffle(stringOpciones);
        stringOpciones.forEach(o -> {
            opciones.getChildren().add(new BotonMC(o));
        });

        ToggleButton botonAnulador = new BotonPoder("anulador");
        ToggleButton botonExclusividad = new BotonPoder("exclusividad");
        poderes.getChildren().addAll(botonAnulador,botonExclusividad);

        ControladorResponderMC controlador = new ControladorResponderMC(stage, opciones.getChildren(), poderes.getChildren(), tablero);
        botonResponder.setOnAction(controlador);
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


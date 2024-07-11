package edu.fiuba.algo3.vista.escenas;

import edu.fiuba.algo3.controladores.ControladorConfigurarPartida;
import edu.fiuba.algo3.vista.elementos.NumberField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

import java.io.File;

import static java.lang.Math.floor;

import javafx.stage.Stage;


public class VistaConfigurarPartida extends Scene {
    private FlowPane root;
    private ListView<String> listaJugadores;
    private TextField fieldNombreJugador;
    private ObservableList<String> jugadores;
    private AudioClip sonidoError;

    public VistaConfigurarPartida(Stage stage, double width, double height) {
        super(new FlowPane(), width, height);
        double margenAncho = width/32;
        double margenAlto = height/18;
        this.root = (FlowPane) this.getRoot();
        BackgroundImage imagenFondo = new BackgroundImage(new Image("file:"+System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/background.png"),BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
        Background fondo = new Background(imagenFondo);
        this.root.setBackground(fondo);
        this.getStylesheets().add("styles.css");
        this.jugadores = FXCollections.observableArrayList();
        File archivoSonido = new File(System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/sonidos/error.wav");
        Media media = new Media(archivoSonido.toURI().toString());
        sonidoError = new AudioClip(media.getSource());

        // Paneles y distribución en la pantalla
        FlowPane primerColumna = new FlowPane();
        FlowPane segundaColumna = new FlowPane();
        primerColumna.setPrefWidth(floor(width/3));
        primerColumna.setMaxWidth(floor(width/3));
        primerColumna.setPrefHeight(height);
        segundaColumna.setPrefWidth(floor(width*2/3));
        segundaColumna.setPrefHeight(height);
        this.root.getChildren().addAll(primerColumna,segundaColumna);

        FlowPane panelAgregarJugador = new FlowPane();
        panelAgregarJugador.setPrefWidth(segundaColumna.getPrefWidth());
        panelAgregarJugador.setPrefHeight(floor(segundaColumna.getPrefHeight()/3));

        FlowPane panelIngresoNombre = new FlowPane();
        panelIngresoNombre.setPrefWidth(floor(panelAgregarJugador.getPrefWidth()*2/3));
        panelIngresoNombre.setPrefHeight(panelAgregarJugador.getPrefHeight());
        VBox boxIngresoNombre = new VBox();
        boxIngresoNombre.setAlignment(Pos.CENTER);
        panelIngresoNombre.getChildren().add(boxIngresoNombre);

        FlowPane panelBotonesIngreso = new FlowPane();
        panelBotonesIngreso.setPrefWidth(floor(panelAgregarJugador.getPrefWidth()*1/4));
        panelBotonesIngreso.setPrefHeight(panelAgregarJugador.getPrefHeight());
        panelBotonesIngreso.setPadding(new Insets(margenAlto,margenAncho,margenAlto,margenAncho));

        HBox boxBotonesIngreso = new HBox();
        boxBotonesIngreso.setSpacing(panelBotonesIngreso.getPrefWidth()/8);
        boxBotonesIngreso.setAlignment(Pos.CENTER);
        panelBotonesIngreso.getChildren().add(boxBotonesIngreso);

        panelAgregarJugador.getChildren().addAll(panelIngresoNombre, panelBotonesIngreso);
        segundaColumna.getChildren().add(panelAgregarJugador);

        FlowPane panelBotonJugar = new FlowPane();
        panelBotonJugar.setPrefHeight(segundaColumna.getPrefHeight() * 1/3);
        panelBotonJugar.setPrefWidth(segundaColumna.getPrefWidth());
        panelBotonJugar.setAlignment(Pos.CENTER);


        FlowPane panelLimites = new FlowPane();
        panelLimites.setPrefHeight(segundaColumna.getPrefHeight() * 1/3);
        panelLimites.setPrefWidth(segundaColumna.getPrefWidth());
        HBox boxLimites = new HBox();
        panelLimites.getChildren().add(boxLimites);

        segundaColumna.getChildren().addAll(panelLimites,panelBotonJugar);

        // Botones y cosas con funcionalidad
        Label textoAgregarJugador = new Label("agregar jugador:");
        textoAgregarJugador.setStyle("-fx-text-fill: black;" +
                        "-fx-font-family: 'Comic Sans MS';" +
                        "-fx-text-alignment: center;" +
                        "-fx-font-size: 32;");
        fieldNombreJugador = new TextField();
        fieldNombreJugador.setPrefWidth(width / 2 - margenAncho);
        fieldNombreJugador.setStyle("-fx-text-fill: black;" +
                        "-fx-font-family: 'Comic Sans MS';" +
                        "-fx-text-alignment: center;" +
                        "-fx-font-size: 32;" +
                        "-fx-background-color: white;" +
                        "-fx-border-width: 4px;" +
                        "-fx-border-color: black;");
        fieldNombreJugador.setOnAction(event -> agregarJugador());

        boxIngresoNombre.getChildren().addAll(textoAgregarJugador,fieldNombreJugador);

        Button botonAgregar = new Button("+");
        botonAgregar.getStyleClass().add("custom-button");
        botonAgregar.setOnAction(event -> agregarJugador());

        Button botonQuitar = new Button("-");
        botonQuitar.getStyleClass().add("custom-button");
        botonQuitar.setOnAction(event -> quitarJugador());

        boxBotonesIngreso.getChildren().addAll(botonAgregar,botonQuitar);


        FlowPane panelListaJugadores = new FlowPane();
        panelListaJugadores.setPrefWidth(primerColumna.getPrefWidth() - 2*margenAncho);
        panelListaJugadores.setPrefHeight(primerColumna.getPrefHeight() - 2*margenAlto);
        FlowPane.setMargin(panelListaJugadores, new Insets(margenAlto,margenAncho,margenAlto,margenAncho));
        this.listaJugadores = new ListView<>(jugadores);
        listaJugadores.setPrefWidth(panelListaJugadores.getPrefWidth());
        listaJugadores.setPrefHeight(panelListaJugadores.getPrefHeight());
        panelListaJugadores.getChildren().add(listaJugadores);
        primerColumna.getChildren().add(panelListaJugadores);
        listaJugadores.setStyle(
            "-fx-text-fill: black;" +
            "-fx-font-family: 'Comic Sans MS';" +
            "-fx-text-alignment: center;" +
            "-fx-font-size: 32;" +
            "-fx-background-color: white;" +
            "-fx-border-width: 4px;" +
            "-fx-border-color: black;"
        );


        ImageView imagenJugar = new ImageView(new Image("file:"+System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/botonJugar.png"));
        Button botonjugar = new Button("", imagenJugar);
        botonjugar.setStyle("-fx-background-color: transparent;");
        panelBotonJugar.getChildren().add(botonjugar);


        FlowPane panelLimitePreguntas = new FlowPane();
        panelLimitePreguntas.setPrefWidth(segundaColumna.getPrefWidth()/2);
        panelLimitePreguntas.setAlignment(Pos.CENTER);
        VBox boxLimitePreguntas = new VBox();
        boxLimitePreguntas.setAlignment(Pos.CENTER);
        Label textoLimitePreguntas = new Label("máximo de preguntas a jugar");
        textoLimitePreguntas.setStyle("-fx-text-fill: black;" +
                "-fx-font-family: 'Comic Sans MS';" +
                "-fx-text-alignment: center;" +
                "-fx-font-size: 32;");
        textoLimitePreguntas.maxWidth(segundaColumna.getPrefWidth()/2);
        textoLimitePreguntas.wrapTextProperty().set(true);
        NumberField limitePreguntas = new NumberField();
        limitePreguntas.setText("25");
        boxLimitePreguntas.getChildren().addAll(textoLimitePreguntas,limitePreguntas);
        panelLimitePreguntas.getChildren().add(boxLimitePreguntas);
        boxLimites.getChildren().add(panelLimitePreguntas);

        FlowPane panelLimitePuntaje = new FlowPane();
        panelLimitePuntaje.setPrefWidth(segundaColumna.getPrefWidth()/2);
        panelLimitePuntaje.setAlignment(Pos.CENTER);
        VBox boxLimitePuntaje = new VBox();
        boxLimitePuntaje.setAlignment(Pos.CENTER);
        Label textoLimitePuntaje = new Label("puntos para ganar");
        textoLimitePuntaje.setStyle("-fx-text-fill: black;" +
                "-fx-font-family: 'Comic Sans MS';" +
                "-fx-text-alignment: center;" +
                "-fx-font-size: 32;");
        textoLimitePuntaje.maxWidth(segundaColumna.getPrefWidth()/2);
        textoLimitePuntaje.wrapTextProperty().set(true);
        NumberField limitePuntaje = new NumberField();
        limitePuntaje.setText("100");
        boxLimitePuntaje.getChildren().addAll(textoLimitePuntaje,limitePuntaje);
        panelLimitePuntaje.getChildren().add(boxLimitePuntaje);
        boxLimites.getChildren().add(panelLimitePuntaje);


        // Controlador

        ControladorConfigurarPartida controlador = new ControladorConfigurarPartida(stage,listaJugadores,limitePuntaje,limitePreguntas);
        botonjugar.setOnAction(controlador);

    }

    private void agregarJugador() {
        String nombre = fieldNombreJugador.getText().trim();
        if (!nombre.isEmpty()) {
            jugadores.add(nombre);
            fieldNombreJugador.clear();
        }
    }

    private void quitarJugador() {
        if (jugadores.isEmpty()) {
            sonidoError.play();
        } else {
            jugadores.remove(listaJugadores.getSelectionModel().getSelectedItem());
            listaJugadores.getSelectionModel().clearSelection();
        }
    }
}

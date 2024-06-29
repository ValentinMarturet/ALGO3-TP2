package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controladores.ControladorResponder;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Pregunta;
import edu.fiuba.algo3.vista.elementos.ConjuntoPoderes;
import edu.fiuba.algo3.vista.elementos.ConjuntoPoderesClasicos;
import edu.fiuba.algo3.vista.elementos.ConjuntoPoderesPenalidad;
import edu.fiuba.algo3.vista.elementos.CustomToggleButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.floor;

public abstract class VistaPregunta extends Scene {

    protected ConjuntoPoderes poderes;
    private final StackPane stackInformacionPregunta;
    private final Button botonResponder;
    protected final FlowPane panelOpciones;
    private final FlowPane panelBotonesPoderes;
    private final VistaTableroJugadores tablero;

    public VistaPregunta(Stage stage, double width, double height, Pregunta pregunta, VistaTableroJugadores tablero) {
        super(new FlowPane(), width, height);
        double margenAncho = width/32;
        double margenAlto = height/18;
        this.tablero = tablero;
        FlowPane root = (FlowPane) this.getRoot();
        BackgroundImage imagenFondo = new BackgroundImage(new Image("file:"+System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/background.png"), BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background fondo = new Background(imagenFondo);
        root.setBackground(fondo);
        this.getStylesheets().add("styles.css");

        FlowPane panelTableroJugadores = new FlowPane();
        panelTableroJugadores.setPrefHeight(height - 2*margenAlto);
        panelTableroJugadores.setPrefWidth(floor(width/3 - margenAncho));
        FlowPane.setMargin(panelTableroJugadores,new Insets(margenAlto, 0, margenAlto, margenAncho));
        root.getChildren().add(panelTableroJugadores);
        panelTableroJugadores.getChildren().add(tablero);
        if (!tablero.getItems().isEmpty()) {tablero.siguienteJugador();}

        FlowPane panelPregunta = new FlowPane();
        panelPregunta.setPrefHeight(height);
        panelPregunta.setPrefWidth(floor(width * 2/3));
        root.getChildren().add(panelPregunta);

        stackInformacionPregunta = new StackPane();
        stackInformacionPregunta.setPrefWidth(floor(width * 2/3));
        stackInformacionPregunta.setPrefHeight(height/3);

        Label textoPregunta = new Label(pregunta.getPregunta());
        textoPregunta.setPrefHeight(height*2/5 - 2*margenAlto);
        textoPregunta.setPrefWidth(width * 2/3 - 2*margenAncho);
        textoPregunta.setWrapText(true);
        establecerEstilo(textoPregunta);
        cambiarTamanoFuente(textoPregunta, 32);
        StackPane.setMargin(textoPregunta,new Insets(margenAlto, margenAncho, margenAlto, margenAncho));

        HBox contenedorTema = new HBox();
        contenedorTema.setPrefWidth(floor(width * 2/3));
        contenedorTema.setAlignment(Pos.TOP_RIGHT);
        Label tipoDePregunta = new Label(pregunta.getTipo());
        establecerEstilo(tipoDePregunta);
        cambiarTamanoFuente(tipoDePregunta, 25);
        contenedorTema.getChildren().add(tipoDePregunta);
        tipoDePregunta.setPadding(new Insets(0,6,2,6));
        StackPane.setMargin(contenedorTema,new Insets(margenAlto/3, margenAncho/2, 0, 0));

        stackInformacionPregunta.getChildren().addAll(textoPregunta, contenedorTema);
        panelPregunta.getChildren().add(stackInformacionPregunta);

        FlowPane panelBotones = new FlowPane();
        panelBotones.setPrefHeight(height - textoPregunta.getPrefHeight());
        panelBotones.setPrefWidth(textoPregunta.getPrefWidth());
        FlowPane.setMargin(panelBotones,new Insets(0, margenAncho, 0, margenAncho));
        panelPregunta.getChildren().add(panelBotones);

        panelOpciones = new FlowPane();
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
        botonResponder = new Button("", imagenResponder);
        botonResponder.setStyle("-fx-background-color: transparent;");
        panelBotonResponder.getChildren().add(botonResponder);

        panelBotonesPoderes = new FlowPane();
        panelBotonesPoderes.setPrefHeight(panelBotonesControl.getPrefHeight() - panelBotonResponder.getPrefHeight());
        panelBotonesPoderes.setPrefWidth(panelBotonesControl.getPrefWidth());
        panelBotonesControl.getChildren().add(panelBotonesPoderes);

    }


    protected void establecerEstilo(Node nodo) {
        nodo.setStyle("-fx-background-color: white;" +
                "-fx-border-width: 4px;" +
                "-fx-text-fill: black;" +
                "-fx-font-family: 'Comic Sans MS';" +
                "-fx-text-alignment: center;" +
                "-fx-border-color: black;");
    }

    protected void cambiarTamanoFuente(Node nodo, int size) {
        String estiloAnterior = nodo.getStyle();
        nodo.setStyle(estiloAnterior + "-fx-font-size: "+size+";");
    }


    protected void establecerPoderesClasicos() {
        poderes = new ConjuntoPoderesClasicos();
        poderes.setPrefHeight(panelBotonesPoderes.getPrefHeight());
        poderes.setPrefWidth(panelBotonesPoderes.getPrefWidth());
        panelBotonesPoderes.getChildren().add(poderes);
        poderes.reestablecer(tablero.obtenerJugadorActual());
    }

    protected void establecerPoderesPenalidad() {
        poderes = new ConjuntoPoderesPenalidad();
        poderes.setPrefHeight(panelBotonesPoderes.getPrefHeight());
        poderes.setPrefWidth(panelBotonesPoderes.getPrefWidth());
        panelBotonesPoderes.getChildren().add(poderes);
        poderes.reestablecer(tablero.obtenerJugadorActual());
    }

    protected void establecerControladorBotonResponder(ControladorResponder controlador) {
        botonResponder.setOnAction(controlador);
    }

    protected void agregarAlPanelOpciones(Node nodo) {
        panelOpciones.getChildren().add(nodo);
    }

    protected void agregarAlStackInformacionPregunta(Node nodo) {
        stackInformacionPregunta.getChildren().add(nodo);
    }

    public void restablecerPoderes() {
        poderes.reestablecer(tablero.obtenerJugadorActual());
    }

    public abstract void restablecerOpciones();
}

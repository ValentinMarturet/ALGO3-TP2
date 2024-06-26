package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controladores.ControladorResponder;
import edu.fiuba.algo3.controladores.ControladorResponderGC;
import edu.fiuba.algo3.modelo.Opcion;
import edu.fiuba.algo3.vista.elementos.BotonGC;
import edu.fiuba.algo3.vista.elementos.BotonPoder;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.floor;

public class VistaPregunta extends Scene {
    protected Label textoPregunta;
    protected Label tipoDePregunta;
    private VBox contenedorPoderes;
    private StackPane stackInformacionPregunta;
    private Button botonResponder;

    public VistaPregunta(Stage stage, double width, double height, VistaTableroJugadores tablero) {
        super(new FlowPane(), width, height);
        double margenAncho = width/32;
        double margenAlto = height/18;
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

        textoPregunta = new Label();
        textoPregunta.setPrefHeight(height*2/5 - 2*margenAlto);
        textoPregunta.setPrefWidth(width * 2/3 - 2*margenAncho);
        textoPregunta.setWrapText(true);
        establecerEstilo(textoPregunta);
        cambiarTamanoFuente(textoPregunta, 32);
        StackPane.setMargin(textoPregunta,new Insets(margenAlto, margenAncho, margenAlto, margenAncho));

        HBox contenedorTema = new HBox();
        contenedorTema.setPrefWidth(floor(width * 2/3));
        contenedorTema.setAlignment(Pos.TOP_RIGHT);
        tipoDePregunta = new Label();
        establecerEstilo(tipoDePregunta);
        cambiarTamanoFuente(tipoDePregunta, 25);
        contenedorTema.getChildren().add(tipoDePregunta);
        tipoDePregunta.setPadding(new Insets(0,6,2,6));

        stackInformacionPregunta.getChildren().addAll(textoPregunta, contenedorTema);
        panelPregunta.getChildren().add(stackInformacionPregunta);

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
        botonResponder = new Button("", imagenResponder);
        botonResponder.setStyle("-fx-background-color: transparent;");
        panelBotonResponder.getChildren().add(botonResponder);

        FlowPane panelBotonesPoderes = new FlowPane();
        panelBotonesPoderes.setPrefHeight(panelBotonesControl.getPrefHeight() - panelBotonResponder.getPrefHeight());
        panelBotonesPoderes.setPrefWidth(panelBotonesControl.getPrefWidth());
        panelBotonesControl.getChildren().add(panelBotonesPoderes);
        contenedorPoderes = new VBox();
        contenedorPoderes.setPrefHeight(panelBotonesPoderes.getPrefHeight());
        contenedorPoderes.setPrefWidth(panelBotonesPoderes.getPrefWidth());
        contenedorPoderes.setAlignment(Pos.CENTER);
        contenedorPoderes.setPadding(new Insets(margenAlto,0,0,0));
        contenedorPoderes.setSpacing(margenAlto/2);
        panelBotonesPoderes.getChildren().add(contenedorPoderes);
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

    protected void agregarAlStackInfoPregunta(Node nodo) {
        stackInformacionPregunta.getChildren().add(nodo);
    }

    protected void agregarPoderesClasicos() {
        ToggleButton botonAnulador = new BotonPoder("anulador");
        ToggleButton botonExclusividad = new BotonPoder("exclusividad");
        contenedorPoderes.getChildren().addAll(botonAnulador,botonExclusividad);
    }

    protected void agregarPoderesPenalidad() {
        ToggleButton botonAnulador = new BotonPoder("anulador");
        ToggleButton botonDuplicador = new BotonPoder("duplicador");
        ToggleButton botonTriplicador = new BotonPoder("triplicador");
        contenedorPoderes.getChildren().addAll(botonAnulador,botonDuplicador,botonTriplicador);
    }

    protected void establecerControladorBotonResponder(ControladorResponder controlador) {
        botonResponder.setOnAction(controlador);
    }
}

package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controladores.ControladorResponderMC;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.vista.elementos.BotonMC;
import edu.fiuba.algo3.vista.elementos.BotonPoder;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.floor;

public class VistaPreguntaMC extends VistaPregunta {
    private List<String> stringOpciones;
    private VBox opciones;

    public VistaPreguntaMC(Stage stage, double width, double height, PreguntaMC pregunta, VistaTableroJugadores tablero) {
        super(stage, width, height, pregunta, tablero);
        inicializarPreguntaMC(stage, pregunta, tablero);
        agregarPoderesClasicos();
    }

    public VistaPreguntaMC(Stage stage, double width, double height, PreguntaMCParcial pregunta, VistaTableroJugadores tablero) {
        super(stage, width, height, pregunta, tablero);
        inicializarPreguntaMC(stage, pregunta, tablero);
        agregarPoderesClasicos();
    }

    public VistaPreguntaMC(Stage stage, double width, double height, PreguntaMCPenalidad pregunta, VistaTableroJugadores tablero) {
        super(stage, width, height, pregunta, tablero);
        inicializarPreguntaMC(stage, pregunta, tablero);
        agregarPoderesPenalidad();
    }

    private void inicializarPreguntaMC(Stage stage, Pregunta pregunta, VistaTableroJugadores tablero) {
        opciones = new VBox();
        opciones.setPrefHeight(panelOpciones.getPrefHeight());
        opciones.setPrefWidth(panelOpciones.getPrefWidth());
        opciones.setAlignment(Pos.CENTER);
        opciones.setSpacing(25);

        stringOpciones = pregunta.getOpciones().stream().map(Opcion::getOpcion).collect(Collectors.toList());
        reestablecerOpciones();

        agregarAlPanelOpciones(opciones);

        ControladorResponderMC controlador = new ControladorResponderMC(stage, opciones.getChildren(), poderes.getChildren(), tablero);
        establecerControladorBotonResponder(controlador);
    }

    @Override
    public void reestablecerOpciones() {
        opciones.getChildren().removeAll(opciones.getChildren());
        Collections.shuffle(stringOpciones);
        stringOpciones.forEach(o -> {
            opciones.getChildren().add(new BotonMC(o));
        });
    }

}
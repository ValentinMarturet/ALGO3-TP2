package edu.fiuba.algo3.vista.escenas;

import edu.fiuba.algo3.controladores.ControladorResponderMC;
import edu.fiuba.algo3.modelo.Preguntas.Pregunta;
import edu.fiuba.algo3.modelo.Preguntas.PreguntaMC;
import edu.fiuba.algo3.modelo.Preguntas.PreguntaMCParcial;
import edu.fiuba.algo3.modelo.Preguntas.PreguntaMCPenalidad;
import edu.fiuba.algo3.modelo.Preguntas.Opcion;
import edu.fiuba.algo3.vista.elementos.BotonMC;
import edu.fiuba.algo3.vista.elementos.VistaTableroJugadores;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class VistaPreguntaMC extends VistaPregunta {
    private List<String> stringOpciones;
    private VBox opciones;

    public VistaPreguntaMC(Stage stage, double width, double height, PreguntaMC pregunta, VistaTableroJugadores tablero) {
        super(stage, width, height, pregunta, tablero);
        establecerPoderesClasicos();
        inicializarPreguntaMC(stage, pregunta, tablero);
    }

    public VistaPreguntaMC(Stage stage, double width, double height, PreguntaMCParcial pregunta, VistaTableroJugadores tablero) {
        super(stage, width, height, pregunta, tablero);
        establecerPoderesClasicos();
        inicializarPreguntaMC(stage, pregunta, tablero);
    }

    public VistaPreguntaMC(Stage stage, double width, double height, PreguntaMCPenalidad pregunta, VistaTableroJugadores tablero) {
        super(stage, width, height, pregunta, tablero);
        establecerPoderesPenalidad();
        inicializarPreguntaMC(stage, pregunta, tablero);
    }

    private void inicializarPreguntaMC(Stage stage, Pregunta pregunta, VistaTableroJugadores tablero) {
        opciones = new VBox();
        opciones.setPrefHeight(panelOpciones.getPrefHeight());
        opciones.setPrefWidth(panelOpciones.getPrefWidth());
        opciones.setAlignment(Pos.CENTER);
        opciones.setSpacing(15);

        stringOpciones = pregunta.getOpciones().stream().map(Opcion::getOpcion).collect(Collectors.toList());
        restablecerOpciones();

        agregarAlPanelOpciones(opciones);

        ControladorResponderMC controlador = new ControladorResponderMC(stage, opciones.getChildren(), poderes.getChildren(), tablero);
        establecerControladorBotonResponder(controlador);
    }

    @Override
    public void restablecerOpciones() {
        opciones.getChildren().removeAll(opciones.getChildren());
        Collections.shuffle(stringOpciones);
        stringOpciones.forEach(o -> {
            opciones.getChildren().add(new BotonMC(o));
        });
    }

}
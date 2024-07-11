package edu.fiuba.algo3.vista.escenas;

import edu.fiuba.algo3.controladores.ControladorResponderVF;
import edu.fiuba.algo3.modelo.PreguntaVF;
import edu.fiuba.algo3.modelo.PreguntaVFPenalidad;
import edu.fiuba.algo3.vista.elementos.BotonVF;
import edu.fiuba.algo3.vista.elementos.VistaTableroJugadores;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import javafx.stage.Stage;

public class VistaPreguntaVF extends VistaPregunta {
    private HBox opciones;
    public VistaPreguntaVF(Stage stage, double width, double height, PreguntaVF pregunta, VistaTableroJugadores tablero) {
        super(stage, width, height, pregunta, tablero);

        opciones = new HBox();
        opciones.setAlignment(Pos.CENTER);
        opciones.setSpacing(width/32);
        ToggleGroup grupoOpciones = new ToggleGroup();
        pregunta.getOpciones().forEach(o -> {
            ToggleButton botonOpcion = new BotonVF(o.getOpcion());
            botonOpcion.setToggleGroup(grupoOpciones);
            opciones.getChildren().add(botonOpcion);
        });
        agregarAlPanelOpciones(opciones);
        
        establecerPoderesClasicos();

        VBox poderes = new VBox();
        ControladorResponderVF controlador = new ControladorResponderVF(stage, grupoOpciones, poderes.getChildren(), tablero);
        establecerControladorBotonResponder(controlador);
    }

    public VistaPreguntaVF(Stage stage, double width, double height, PreguntaVFPenalidad pregunta, VistaTableroJugadores tablero) {
        super(stage, width, height, pregunta, tablero);

        opciones = new HBox();
        opciones.setAlignment(Pos.CENTER);
        opciones.setSpacing(width/32);
        ToggleGroup grupoOpciones = new ToggleGroup();
        pregunta.getOpciones().forEach(o -> {
            ToggleButton botonOpcion = new BotonVF(o.getOpcion());
            botonOpcion.setToggleGroup(grupoOpciones);
            opciones.getChildren().add(botonOpcion);
        });
        agregarAlPanelOpciones(opciones);

        establecerPoderesPenalidad();

        VBox poderes = new VBox();
        ControladorResponderVF controlador = new ControladorResponderVF(stage, grupoOpciones, poderes.getChildren(), tablero);
        establecerControladorBotonResponder(controlador);
    }

    @Override
    public void restablecerOpciones() {
        opciones.getChildren().forEach(n -> {
            ((ToggleButton) n).setSelected(false);
        });
    }
}
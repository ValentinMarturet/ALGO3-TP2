package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controladores.ControladorResponderVF;
import edu.fiuba.algo3.modelo.Anulador;
import edu.fiuba.algo3.modelo.Exclusividad;
import edu.fiuba.algo3.modelo.PreguntaVF;
import edu.fiuba.algo3.modelo.PreguntaVFPenalidad;
import edu.fiuba.algo3.vista.elementos.BotonPoder;
import edu.fiuba.algo3.vista.elementos.BotonVF;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import static java.lang.Math.floor;
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
        
        agregarPoderesClasicos();

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

        agregarPoderesPenalidad();

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
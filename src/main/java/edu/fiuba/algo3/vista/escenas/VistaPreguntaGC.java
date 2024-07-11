package edu.fiuba.algo3.vista.escenas;

import edu.fiuba.algo3.controladores.ControladorResponderGC;
import edu.fiuba.algo3.modelo.Respuestas.Opcion;
import edu.fiuba.algo3.modelo.Preguntas.PreguntaGC;
import edu.fiuba.algo3.vista.elementos.BotonGC;
import edu.fiuba.algo3.vista.elementos.VistaTableroJugadores;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.floor;

public class VistaPreguntaGC extends VistaPregunta {
    private VBox opciones;
    private List<String> opcionesLista;

    public VistaPreguntaGC(Stage stage, double width, double height, PreguntaGC pregunta, VistaTableroJugadores tablero) {
        super(stage, width, height, pregunta, tablero);
        double margenAncho = width/32;
        double margenAlto = height/18;

        HBox grupos = new HBox();
        grupos.setPrefWidth(floor(width * 2/3));

        Pair<String,String> nombresGrupos = pregunta.getNombreDeGrupos();

        Label grupo1 = new Label(nombresGrupos.getKey());
        Label grupo2 = new Label(nombresGrupos.getValue());
        grupos.setAlignment(Pos.BOTTOM_CENTER);
        grupos.setSpacing(50);
        grupo1.setPadding(new Insets(0,6,2,6));
        grupo2.setPadding(new Insets(0,6,2,6));

        cambiarTamanoFuente(grupo1, 25);
        cambiarTamanoFuente(grupo2, 25);
        grupo1.getStyleClass().add("GP-toggle-button");
        grupo1.getStyleClass().add("selected-color1");
        grupo2.getStyleClass().add("GP-toggle-button");
        grupo2.getStyleClass().add("selected-color2");
        grupos.getChildren().addAll(grupo1, grupo2);


        StackPane.setMargin(grupos,new Insets( height*2/5 - 2*margenAlto, margenAncho, margenAlto/4, margenAncho));

        agregarAlStackInformacionPregunta(grupos);

        opciones = new VBox();
        opciones.setPrefHeight(panelOpciones.getPrefHeight());
        opciones.setPrefWidth(panelOpciones.getPrefWidth());
        opciones.setAlignment(Pos.CENTER);
        opciones.setSpacing(10);
        agregarAlPanelOpciones(opciones);

        opcionesLista = pregunta.getOpciones().stream().map(Opcion::getOpcion).collect(Collectors.toList());
        restablecerOpciones();

        establecerPoderesClasicos();

        ControladorResponderGC controlador = new ControladorResponderGC(stage, opciones.getChildren(), poderes.getChildren(), tablero);
        establecerControladorBotonResponder(controlador);
    }


    @Override
    public void restablecerOpciones() {
        opciones.getChildren().removeAll(opciones.getChildren());
        Collections.shuffle(opcionesLista);
        opcionesLista.forEach(opcion -> {
            BotonGC boton = new BotonGC(opcion);
            opciones.getChildren().add(boton);
        });
    }
}
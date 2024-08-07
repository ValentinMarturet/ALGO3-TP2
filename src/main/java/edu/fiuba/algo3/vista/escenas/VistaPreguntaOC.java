package edu.fiuba.algo3.vista.escenas;

import edu.fiuba.algo3.controladores.ControladorResponderOC;
import edu.fiuba.algo3.modelo.Preguntas.Opcion;
import edu.fiuba.algo3.modelo.Preguntas.PreguntaOC;
import edu.fiuba.algo3.vista.elementos.CeldaOrdenable;
import edu.fiuba.algo3.vista.elementos.VistaTableroJugadores;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class VistaPreguntaOC extends VistaPregunta {
    private List<String> listaOpcionesStrings;
    private ObservableList<String> opciones;
    private ListView<String> listaOpciones;

    public VistaPreguntaOC(Stage stage, double width, double height, PreguntaOC pregunta, VistaTableroJugadores tablero) {
        super(stage, width, height, pregunta, tablero);

        // Opciones
        listaOpcionesStrings = pregunta.getOpciones().stream().map(Opcion::getOpcion).collect(Collectors.toList());
        listaOpciones = new ListView<>();
        restablecerOpciones();

        listaOpciones.setCellFactory(param -> new CeldaOrdenable());
        listaOpciones.setPrefWidth(panelOpciones.getPrefWidth()-height/9);
        listaOpciones.setOnMouseExited(e -> listaOpciones.getSelectionModel().clearSelection());
        listaOpciones.setOnMouseDragExited(e -> listaOpciones.getSelectionModel().clearSelection());
        listaOpciones.setMaxHeight(opciones.size()*32*2.05);
        establecerEstilo(listaOpciones);
        cambiarTamanoFuente(listaOpciones,32);

        agregarAlPanelOpciones(listaOpciones);

        establecerPoderesClasicos();

        ControladorResponderOC controlador = new ControladorResponderOC(stage,opciones,poderes.getChildren(),tablero);
        establecerControladorBotonResponder(controlador);
    }

    @Override
    public void restablecerOpciones() {
        Collections.shuffle(listaOpcionesStrings);
        opciones = FXCollections.observableList(listaOpcionesStrings);
        listaOpciones.getItems().removeAll(listaOpciones.getItems());
        listaOpciones.getItems().addAll(opciones);
        listaOpciones.refresh();
    }
}

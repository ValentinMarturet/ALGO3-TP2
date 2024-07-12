package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.Preguntas.Respuesta;
import edu.fiuba.algo3.vista.elementos.VistaTableroJugadores;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ControladorResponderMC extends ControladorResponder {
    private ObservableList<Node> opciones;

    public ControladorResponderMC(Stage stage, ObservableList<Node> opciones, ObservableList<Node> poderes, VistaTableroJugadores tablero) {
        super(stage,tablero,poderes);
        this.opciones = opciones;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        List<String> opcionesSeleccionadas = opciones.stream()
                .map(o -> {
                    if (o instanceof ToggleButton) {
                        return (ToggleButton) o;
                    }
                    return null;
                    })
                .filter(Objects::nonNull)
                .filter(ToggleButton::isSelected)
                .map(ToggleButton::getText)
                .collect(Collectors.toList());

        if (opcionesSeleccionadas.isEmpty()) {
            if (sonidoSinSeleccion.isPlaying()) {sonidoSinSeleccion.stop();}
            sonidoSinSeleccion.play();
            mostrarPopUp("no seleccionaste ninguna opción!");

        } else {
            sonidoResponder.play();

            Respuesta[] respuestas = opcionesSeleccionadas.stream()
                    .map(Respuesta::new)
                    .toArray(Respuesta[]::new);

            responderPregunta(respuestas);
        }
    }
}
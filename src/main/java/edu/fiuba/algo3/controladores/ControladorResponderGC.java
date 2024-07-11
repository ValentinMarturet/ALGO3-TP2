package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.Respuestas.Respuesta;
import edu.fiuba.algo3.vista.elementos.VistaTableroJugadores;
import edu.fiuba.algo3.vista.elementos.BotonGC;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ControladorResponderGC extends ControladorResponder {
    private ObservableList<Node> opciones;

    public ControladorResponderGC(Stage stage, ObservableList<Node> opciones, ObservableList<Node> poderes, VistaTableroJugadores tablero) {
        super(stage,tablero,poderes);
        this.opciones = opciones;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        List<BotonGC> opcionesSeleccionadas = opciones.stream()
                .map(o -> {
                    if (o instanceof BotonGC) {
                        return (BotonGC) o;
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .map(o -> {
                    if (o.getGrupoSeleccionado() == 0) {
                        return null;
                    }
                    return o;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (opcionesSeleccionadas.size() != opciones.size()) {
            if (sonidoSinSeleccion.isPlaying()) {sonidoSinSeleccion.stop();}
            sonidoSinSeleccion.play();
            // falta seleccionar alguna opcion
            System.out.println("falta seleccionar alguna opcion");

        } else {
            sonidoResponder.play();

            Respuesta[] respuestas = opcionesSeleccionadas.stream()
                    .filter(b -> b.getGrupoSeleccionado() == 1)
                    .map(b -> new Respuesta(b.getText()))
                    .toArray(Respuesta[]::new);

            responderPregunta(respuestas);

        }
    }
}
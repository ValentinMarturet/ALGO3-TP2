package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.excepciones.JugadorInexistente;
import edu.fiuba.algo3.vista.VistaTableroJugadores;
import edu.fiuba.algo3.vista.botones.BotonGC;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ControladorResponderGC extends ControladorResponderAbstracto {
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

            // Busco el jugador actual en la lista de AlgoHoot
            Jugador jugador = obtenerJugadorActual();

            if (jugador != null) {
                AlgoHoot a = AlgoHoot.getInstancia();
                opcionesSeleccionadas.forEach(o -> {
                    if (o.getGrupoSeleccionado() == 1) {
                        System.out.println("rosa - " + o.getText());
                    } else {
                        System.out.println("verde - " + o.getText());
                    }});

                List<ModificadorIndividual> mi = obtenerModificadoresIndividuales();

                List<ModificadorGlobal> mg = obtenerModificadoresGlobales();

                Respuesta[] respuestas = opcionesSeleccionadas.stream()
                        .filter(b -> b.getGrupoSeleccionado() == 1)
                        .map(b -> new Respuesta(b.getText()))
                        .toArray(Respuesta[]::new);
                /*
                a.jugarRondaDePreguntas(jugador,
                        mi,
                        mg,
                        respuestas
                );
                */
                tablero.siguienteJugador();

            }else {
                throw new JugadorInexistente("Se quiso responder una pregunta con un jugador que no fue registrado");
            }
        }
    }
}
package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.excepciones.JugadorInexistente;
import edu.fiuba.algo3.vista.CambiadorDeVistas;
import edu.fiuba.algo3.vista.VistaTableroJugadores;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ControladorResponderMC extends ControladorResponderAbstracto {
    private ObservableList<Node> opciones;
    private boolean ultimoTurno;

    public ControladorResponderMC(Stage stage, ObservableList<Node> opciones, ObservableList<Node> poderes, VistaTableroJugadores tablero) {
        super(stage,tablero,poderes);
        this.opciones = opciones;
        this.ultimoTurno = false;
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
            // no se seleccionó ninguna opción
            System.out.println("no seleccionaste ninguna opcion");

        } else {
            AlgoHoot a = AlgoHoot.getInstancia();
            // Busco el jugador actual en la lista de AlgoHoot
            Jugador jugador = obtenerJugadorActual();

            if (jugador != null) {

                sonidoResponder.play();
                opcionesSeleccionadas.forEach(System.out::println);

                List<ModificadorIndividual> mi = obtenerModificadoresIndividuales();

                List<ModificadorGlobal> mg = obtenerModificadoresGlobales();

                Respuesta[] respuestas = opcionesSeleccionadas.stream()
                        .map(Respuesta::new)
                        .toArray(Respuesta[]::new);

                /*
                a.jugarRondaDePreguntas(jugador,
                        mi,
                        mg,
                        respuestas
                );
                */
                if(this.ultimoTurno){
                    Jugador jugadorActual = obtenerJugadorActual();
                    a.jugarRondaDePreguntas(jugadorActual,mi,mg,respuestas);
                    a.terminarRondaDePreguntas();
                    CambiadorDeVistas.cambiarAVistaFin(stage,tablero);
                }else{
                    Jugador jugadorActual = obtenerJugadorActual();
                    a.jugarRondaDePreguntas(jugadorActual,mi,mg,respuestas);
                    tablero.siguienteJugador();
                    this.ultimoTurno = tablero.esElUltimoJugador();
                }


            }else {
                throw new JugadorInexistente("Se quiso responder una pregunta con un jugador que no fue registrado");
            }
        }
    }
}
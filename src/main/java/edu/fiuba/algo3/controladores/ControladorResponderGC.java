package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.excepciones.JugadorInexistente;
import edu.fiuba.algo3.vista.CambiadorDeVistas;
import edu.fiuba.algo3.vista.VistaTableroJugadores;
import edu.fiuba.algo3.vista.elementos.BotonGC;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ControladorResponderGC extends ControladorResponderAbstracto {
    private ObservableList<Node> opciones;
    private boolean ultimoTurno;

    public ControladorResponderGC(Stage stage, ObservableList<Node> opciones, ObservableList<Node> poderes, VistaTableroJugadores tablero) {
        super(stage,tablero,poderes);
        this.opciones = opciones;
        this.ultimoTurno = false;
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
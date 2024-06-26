package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.excepciones.JugadorInexistente;
import edu.fiuba.algo3.vista.CambiadorDeVistas;
import edu.fiuba.algo3.vista.VistaTableroJugadores;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.util.List;

public class ControladorResponderOC extends ControladorResponder {
    private ObservableList<String> opciones;
    private boolean ultimoTurno;

    public ControladorResponderOC(Stage stage, ObservableList<String> opciones, ObservableList<Node> poderes, VistaTableroJugadores tablero) {
        super(stage,tablero,poderes);
        this.opciones = opciones;
        this.ultimoTurno = false;
    }
    @Override
    public void handle(ActionEvent actionEvent) {

        // Busco el jugador actual en la lista de AlgoHoot
        Jugador jugador = obtenerJugadorActual();

        if (jugador != null) {

            AlgoHoot a = AlgoHoot.getInstancia();
            sonidoResponder.play();
            opciones.forEach(System.out::println);
            System.out.println(" ");

            List<ModificadorIndividual> mi = obtenerModificadoresIndividuales();

            List<ModificadorGlobal> mg = obtenerModificadoresGlobales();

            Respuesta[] respuestas = opciones.stream()
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
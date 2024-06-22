package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.excepciones.JugadorInexistente;
import edu.fiuba.algo3.vista.VistaTableroJugadores;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class ControladorResponderOC extends ControladorResponderAbstracto {
    private ObservableList<String> opciones;

    public ControladorResponderOC(Stage stage, ObservableList<String> opciones, ObservableList<Node> poderes, VistaTableroJugadores tablero) {
        super(stage,tablero,poderes);
        this.opciones = opciones;
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
            tablero.siguienteJugador();

        }else {
            throw new JugadorInexistente("Se quiso responder una pregunta con un jugador que no fue registrado");
        }
    }
}
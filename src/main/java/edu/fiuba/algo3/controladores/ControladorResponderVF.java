package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.excepciones.JugadorInexistente;
import edu.fiuba.algo3.vista.VistaTableroJugadores;
import edu.fiuba.algo3.vista.botones.BotonPoder;
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
import java.util.Objects;
import java.util.stream.Collectors;

public class ControladorResponderVF implements EventHandler<ActionEvent> {
    private Stage stage;
    private ToggleGroup opciones;
    private VistaTableroJugadores tablero;
    private ObservableList<Node> poderes;
    private AudioClip sonidoResponder;
    private AudioClip sonidoSinSeleccion;

    public ControladorResponderVF(Stage stage, ToggleGroup opciones, ObservableList<Node> poderes, VistaTableroJugadores tablero) {
        this.stage = stage;
        this.opciones = opciones;
        this.poderes = poderes;
        this.tablero = tablero;
        File archivo = new File(System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/sonidos/responder.wav");
        Media media = new Media(archivo.toURI().toString());
        this.sonidoResponder = new AudioClip(media.getSource());
        this.sonidoResponder.setVolume(0.1);
        File archivo2 = new File(System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/sonidos/sinSeleccion.wav");
        Media media2 = new Media(archivo2.toURI().toString());
        this.sonidoSinSeleccion = new AudioClip(media2.getSource());
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        ToggleButton eleccion = (ToggleButton) opciones.getSelectedToggle();
        if (eleccion == null) {
            if (sonidoSinSeleccion.isPlaying()) {sonidoSinSeleccion.stop();}
            sonidoSinSeleccion.play();
            // no se seleccionó ninguna opción
            System.out.println("no seleccionaste ninguna opcion");

        } else {
            AlgoHoot a = AlgoHoot.getInstancia();
            sonidoResponder.play();

            // Busco el jugador actual en la lista de AlgoHoot
            Jugador jugador = a.obtenerJugadores().stream()
                    .filter(j -> j.equals(tablero.getJugadoractual()))
                    .findFirst()
                    .orElse(null);

            if (jugador != null) {
                System.out.println("seleccionaste: " + eleccion.getText());

                // Obtengo los modificadores que seleccionó el jugador
                List<Object> modificadores = poderes.stream()
                        .map(p -> {
                            if (p instanceof BotonPoder) {
                                return ((BotonPoder) p).obtenerModificador(jugador);
                            }
                            return null;
                        })
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
                // guardo en una lista los individuales
                List<ModificadorIndividual> mi = modificadores.stream()
                        .map(m -> {
                            if (m instanceof ModificadorIndividual) {
                                return (ModificadorIndividual) m;
                            }
                            return null;
                        })
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
                // guardo en una lista los globales
                List<ModificadorGlobal> mg = modificadores.stream()
                        .map(m -> {
                            if (m instanceof ModificadorGlobal) {
                                return (ModificadorGlobal) m;
                            }
                            return null;
                        })
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());

                a.jugarRondaDePreguntas(jugador,
                        mi,
                        mg,
                        new Respuesta(eleccion.getText()));
                
                tablero.siguienteJugador();

            } else {
                throw new JugadorInexistente("Se quiso responder una pregunta con un jugador que no fue registrado");
            }
        }
    }
}

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

public class ControladorResponderVF extends ControladorResponderAbstracto {
    private Stage stage;
    private ToggleGroup opciones;
    private VistaTableroJugadores tablero;
    private ObservableList<Node> poderes;

    public ControladorResponderVF(Stage stage, ToggleGroup opciones, ObservableList<Node> poderes, VistaTableroJugadores tablero) {
        super(stage, tablero, poderes);
        this.opciones = opciones;
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
            Jugador jugador = obtenerJugadorActual();

            if (jugador != null) {
                System.out.println("seleccionaste: " + eleccion.getText());

                List<ModificadorIndividual> mi = obtenerModificadoresIndividuales();

                List<ModificadorGlobal> mg = obtenerModificadoresGlobales();

                a.jugarRondaDePreguntas(jugador,
                        mi,
                        mg,
                        new Respuesta(eleccion.getText())
                );

                tablero.siguienteJugador();

            } else {
                throw new JugadorInexistente("Se quiso responder una pregunta con un jugador que no fue registrado");
            }
        }
    }
}

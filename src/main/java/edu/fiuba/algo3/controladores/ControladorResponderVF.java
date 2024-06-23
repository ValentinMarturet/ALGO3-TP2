package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.excepciones.JugadorInexistente;
import edu.fiuba.algo3.vista.CambiadorDeVistas;
import edu.fiuba.algo3.vista.VistaTableroJugadores;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import java.util.List;

public class ControladorResponderVF extends ControladorResponderAbstracto {
    private final ToggleGroup opciones;
    private boolean ultimoTurno;
    public ControladorResponderVF(Stage stage, ToggleGroup opciones, ObservableList<Node> poderes, VistaTableroJugadores tablero) {
        super(stage, tablero, poderes);
        this.opciones = opciones;
        this.ultimoTurno = false;
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
                mi.forEach(System.out::println);
                List<ModificadorGlobal> mg = obtenerModificadoresGlobales();
                mg.forEach(System.out::println);
                /*
                a.jugarRondaDePreguntas(jugador,
                        mi,
                        mg,
                        new Respuesta(eleccion.getText())
                );
                */
                Respuesta jugadorResponde = new Respuesta(eleccion.getText());
                if(this.ultimoTurno){
                    Jugador jugadorActual = obtenerJugadorActual();
                    a.jugarRondaDePreguntas(jugadorActual,mi,mg,jugadorResponde);
                    a.terminarRondaDePreguntas();
                    CambiadorDeVistas.cambiarAVistaFin(stage,tablero);
                }else{
                    Jugador jugadorActual = obtenerJugadorActual();
                    a.jugarRondaDePreguntas(jugadorActual,mi,mg,jugadorResponde);
                    tablero.siguienteJugador();
                    this.ultimoTurno = tablero.esElUltimoJugador();
                }

            } else {
                throw new JugadorInexistente("Se quiso responder una pregunta con un jugador que no fue registrado");
            }
        }
    }
}

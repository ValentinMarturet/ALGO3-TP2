package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.vista.elementos.LabelJugadorConPuntaje;
import javafx.event.Event;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static java.lang.Math.floor;

public class VistaTableroJugadores extends ListView<LabelJugadorConPuntaje> {
    private final double ancho;
    private final double alto;
    private final List<LabelJugadorConPuntaje> vistasJugadores;
    private ListIterator<LabelJugadorConPuntaje> cursorVista;
    private LabelJugadorConPuntaje vistaActual;
    private final List<Jugador> jugadores;

    public VistaTableroJugadores(List<Jugador> jugadores, double ancho, double alto) {
        super();
        this.setPrefSize(ancho, alto);
        this.setMaxSize(ancho, alto);
        this.ancho = ancho;
        this.alto = alto;
        this.vistasJugadores = new ArrayList<>();
        this.jugadores = jugadores;
        actualizarTabla();
        this.cursorVista = null;
        this.vistaActual = null;

        this.addEventFilter(MouseEvent.MOUSE_PRESSED, Event::consume);
        this.addEventFilter(MouseEvent.MOUSE_CLICKED, Event::consume);
        this.addEventFilter(MouseEvent.MOUSE_ENTERED, Event::consume);
        this.addEventFilter(MouseEvent.MOUSE_EXITED, Event::consume);
        this.addEventFilter(MouseEvent.MOUSE_ENTERED, Event::consume);

        this.setStyle("-fx-background-color: white;" +
                "-fx-border-width: 4px;" +
                "-fx-border-color: black;");
    }


    public void siguienteJugador() {
        if (cursorVista == null || !cursorVista.hasNext()) {
            cursorVista = vistasJugadores.listIterator();
        }
        if (vistaActual != null) {
            vistaActual.desResaltar();
        }
        vistaActual = cursorVista.next();
        vistaActual.resaltar();
        this.scrollTo(vistaActual);
    }

    public Jugador obtenerJugadorActual() {
        return jugadores.stream()
                .filter(j -> j.tieneNombre(vistaActual.getNombre()))
                .findFirst()
                .orElse(null);
    }

    public boolean esElUltimoJugador(){
        return !this.cursorVista.hasNext();
    }

    public void actualizarTabla(){
        this.vistasJugadores.clear();
        jugadores.forEach(this::agregarJugador);
        this.getItems().setAll(vistasJugadores);
    }

    private void agregarJugador(Jugador unJugador) {
        unJugador.hacerConNombreYPuntaje(
                (nombre, puntos) ->
                vistasJugadores.add(new LabelJugadorConPuntaje(nombre,puntos,ancho*4/5,floor(alto/8)))
        );
    }

}

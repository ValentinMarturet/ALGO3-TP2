package edu.fiuba.algo3.vista;

import javafx.event.Event;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static java.lang.Math.floor;

public class VistaTableroJugadores extends ListView {
    private double ancho;
    private double alto;
    private List<VistaJugadorConPuntaje> jugadores;
    private ListIterator<VistaJugadorConPuntaje> cursor;
    private VistaJugadorConPuntaje jugadorActual;

    public VistaTableroJugadores(double ancho, double alto) {
        super();
        this.setPrefSize(ancho, alto);
        this.setMaxSize(ancho, alto);
        this.ancho = ancho;
        this.alto = alto;
        this.jugadores = new ArrayList<>();
        this.getItems().setAll(jugadores);
        this.cursor = null;
        this.jugadorActual = null;
        this.addEventFilter(MouseEvent.MOUSE_PRESSED, Event::consume);
        this.addEventFilter(MouseEvent.MOUSE_CLICKED, Event::consume);
        this.addEventFilter(MouseEvent.MOUSE_ENTERED, Event::consume);
        this.addEventFilter(MouseEvent.MOUSE_EXITED, Event::consume);
        this.addEventFilter(MouseEvent.MOUSE_ENTERED, Event::consume);

        this.setStyle("-fx-background-color: white;" +
                "-fx-border-width: 4px;" +
                "-fx-border-color: black;");
    }

    public VistaTableroJugadores() {
        super();
        this.ancho = 368;
        this.alto = 640;
        this.setPrefSize(ancho, alto);
        this.setMaxSize(ancho, alto);
        this.jugadores = new ArrayList<>();
        this.getItems().setAll(jugadores);
        this.cursor = null;
        this.jugadorActual = null;
        this.addEventFilter(MouseEvent.MOUSE_PRESSED, Event::consume);
        this.addEventFilter(MouseEvent.MOUSE_CLICKED, Event::consume);
        this.addEventFilter(MouseEvent.MOUSE_ENTERED, Event::consume);
        this.addEventFilter(MouseEvent.MOUSE_EXITED, Event::consume);
        this.addEventFilter(MouseEvent.MOUSE_ENTERED, Event::consume);
        this.getSelectionModel().clearSelection();
        this.setStyle("-fx-background-color: white;" +
                "-fx-border-width: 4px;" +
                "-fx-border-color: black;");
    }

    public void agregarJugador(String nombre, int puntaje) {
        VistaJugadorConPuntaje nuevoJugador = new VistaJugadorConPuntaje(nombre,puntaje,ancho*4/5,floor(alto/8));
        jugadores.add(nuevoJugador);
        this.getItems().setAll(jugadores);
    }

    public void resaltarSiguienteJugador() {
        if (cursor == null || !cursor.hasNext()) {
            cursor = jugadores.listIterator();
        }
        if (jugadorActual != null) {
            jugadorActual.desResaltar();
        }
        jugadorActual = cursor.next();
        jugadorActual.resaltar();
        this.scrollTo(jugadorActual);
    }
}

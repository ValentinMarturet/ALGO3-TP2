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
    private double ancho;
    private double alto;
    private List<LabelJugadorConPuntaje> jugadores;
    private ListIterator<LabelJugadorConPuntaje> cursor;
    private LabelJugadorConPuntaje jugadorActual;
    private List<Jugador> jugadoresTotales;
    private ListIterator<Jugador> cursorJugador;
    private Jugador jugadorEntidadActual;

    public VistaTableroJugadores(double ancho, double alto) {
        super();
        this.setPrefSize(ancho, alto);
        this.setMaxSize(ancho, alto);
        this.ancho = ancho;
        this.alto = alto;
        this.jugadores = new ArrayList<>();
        this.jugadoresTotales = new ArrayList<>();
        this.getItems().setAll(jugadores);
        this.cursor = null;
        this.jugadorActual = null;
        this.jugadoresTotales = new ArrayList<>();
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
        this.jugadoresTotales = new ArrayList<>();
        this.getItems().setAll(jugadores);
        this.cursor = null;
        this.jugadorActual = null;
        this.jugadoresTotales = new ArrayList<>();
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

    public void agregarJugador(Jugador unJugador) {
        String nombreDeJugador = unJugador.obtenerNombre();
        int puntosDeJugador = unJugador.obtenerPuntaje();
        LabelJugadorConPuntaje nuevoJugadorEnVista = new LabelJugadorConPuntaje(nombreDeJugador,puntosDeJugador,ancho*4/5,floor(alto/8));
        jugadores.add(nuevoJugadorEnVista);
        jugadoresTotales.add(unJugador);
        this.getItems().setAll(jugadores);
    }

    public void siguienteJugador() {
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

    public String getJugadorActual() {
        return jugadorActual.getNombre();
    }

    public boolean esElUltimoJugador(){
        return !this.cursor.hasNext();
    }

    public void limpiarTabla(){
        this.jugadores.clear();
        this.jugadoresTotales.clear();
    }

    public void actualizarTabla(List<Jugador> jugadores){
        limpiarTabla();
        jugadores.forEach(this::agregarJugador);
    }

    public Jugador obtenerJugadorActual(){
        if(cursorJugador==null || cursorJugador.hasNext()){
            cursorJugador = jugadoresTotales.listIterator();
        }
        jugadorEntidadActual = cursorJugador.next();
        return jugadorEntidadActual;
    }
}

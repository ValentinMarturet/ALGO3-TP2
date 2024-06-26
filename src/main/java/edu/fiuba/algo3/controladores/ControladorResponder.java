package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.ModificadorGlobal;
import edu.fiuba.algo3.modelo.ModificadorIndividual;
import edu.fiuba.algo3.vista.VistaTableroJugadores;
import edu.fiuba.algo3.vista.elementos.BotonPoder;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class ControladorResponder implements EventHandler<ActionEvent> {
    protected AudioClip sonidoResponder;
    protected AudioClip sonidoSinSeleccion;
    protected Stage stage;
    protected VistaTableroJugadores tablero;
    protected List<Object> modificadores;
    private ObservableList<Node> botonesPoderes;

    public ControladorResponder(Stage stage, VistaTableroJugadores tablero, ObservableList<Node> poderes) {
        this.stage = stage;
        this.tablero = tablero;
        this.botonesPoderes = poderes;
        File archivo = new File(System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/sonidos/responder.wav");
        Media media = new Media(archivo.toURI().toString());
        this.sonidoResponder = new AudioClip(media.getSource());
        this.sonidoResponder.setVolume(0.1);
        File archivo2 = new File(System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/sonidos/sinSeleccion.wav");
        Media media2 = new Media(archivo2.toURI().toString());
        this.sonidoSinSeleccion = new AudioClip(media2.getSource());
    }

    @Override
    public abstract void handle(ActionEvent actionEvent);

    protected Jugador obtenerJugadorActual() {
        return  tablero.obtenerJugadorActual();
    }

    protected List<ModificadorIndividual> obtenerModificadoresIndividuales() {
        actualizarModificadores();
        return  modificadores.stream()
                .map(m -> {
                    if (m instanceof ModificadorIndividual) {
                        return (ModificadorIndividual) m;
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    protected List<ModificadorGlobal> obtenerModificadoresGlobales() {
        actualizarModificadores();
        return  modificadores.stream()
                .map(m -> {
                    if (m instanceof ModificadorGlobal) {
                        return (ModificadorGlobal) m;
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private void actualizarModificadores() {
        Jugador j = obtenerJugadorActual();
        modificadores = botonesPoderes.stream()
                .map(p -> {
                    if (p instanceof BotonPoder) {
                        return ((BotonPoder) p).obtenerModificador( j );
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}

package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.vista.VistaTableroJugadores;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ControladorResponderMC implements EventHandler<ActionEvent> {
    private Stage stage;
    private ObservableList<Node> opciones;
    private ObservableList<Node> poderes;
    private AudioClip sonidoResponder;
    private AudioClip sonidoSinSeleccion;

    public ControladorResponderMC(Stage stage, ObservableList<Node> opciones, ObservableList<Node> poderes, VistaTableroJugadores tablero) {
        this.stage = stage;
        this.opciones = opciones;
        this.poderes = poderes;
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
        List<String> opcionesSeleccionadas = opciones.stream()
                .map(o -> {
                    if (o instanceof ToggleButton) {
                        return (ToggleButton) o;
                    }
                    return null;
                    })
                .filter(Objects::nonNull)
                .filter(ToggleButton::isSelected)
                .map(ToggleButton::getText)
                .collect(Collectors.toList());

        if (opcionesSeleccionadas.isEmpty()) {
            if (sonidoSinSeleccion.isPlaying()) {sonidoSinSeleccion.stop();}
            sonidoSinSeleccion.play();
            // no se seleccionó ninguna opción
            System.out.println("no seleccionaste ninguna opcion");

        } else {
            sonidoResponder.play();
            opcionesSeleccionadas.forEach(System.out::println);
        }
    }
}
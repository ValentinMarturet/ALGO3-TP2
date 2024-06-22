package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.vista.VistaTableroJugadores;
import edu.fiuba.algo3.vista.botones.BotonGC;
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

public class ControladorResponderGC implements EventHandler<ActionEvent> {
    private Stage stage;
    private ObservableList<Node> opciones;
    private ObservableList<Node> poderes;
    private AudioClip sonidoResponder;
    private AudioClip sonidoSinSeleccion;

    public ControladorResponderGC(Stage stage, ObservableList<Node> opciones, ObservableList<Node> poderes, VistaTableroJugadores tablero) {
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
        List<BotonGC> opcionesSeleccionadas = opciones.stream()
                .map(o -> {
                    if (o instanceof BotonGC) {
                        return (BotonGC) o;
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .map(o -> {
                    if (o.getGrupoSeleccionado() == 0) {
                        return null;
                    }
                    return o;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (opcionesSeleccionadas.size() != opciones.size()) {
            if (sonidoSinSeleccion.isPlaying()) {sonidoSinSeleccion.stop();}
            sonidoSinSeleccion.play();
            // falta seleccionar alguna opcion
            System.out.println("falta seleccionar alguna opcion");

        } else {
            sonidoResponder.play();
            opcionesSeleccionadas.forEach(o -> {
               if (o.getGrupoSeleccionado() == 1) {
                   System.out.println("rosa - " + o.getText());
               } else {
                   System.out.println("verde - " + o.getText());
                }

            });

        }
    }
}
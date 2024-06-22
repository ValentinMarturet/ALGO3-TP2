package edu.fiuba.algo3.controladores;

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

public class ControladorResponderOC implements EventHandler<ActionEvent> {
    private Stage stage;
    private ObservableList<String> opciones;
    private ObservableList<Node> poderes;
    private AudioClip sonidoResponder;

    public ControladorResponderOC(Stage stage, ObservableList<String> opciones, ObservableList<Node> poderes, VistaTableroJugadores tablero) {
        this.stage = stage;
        this.opciones = opciones;
        this.poderes = poderes;
        File archivo = new File(System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/sonidos/responder.wav");
        Media media = new Media(archivo.toURI().toString());
        this.sonidoResponder = new AudioClip(media.getSource());
        this.sonidoResponder.setVolume(0.1);

    }
    @Override
    public void handle(ActionEvent actionEvent) {
        sonidoResponder.play();
        opciones.forEach(System.out::println);
        System.out.println(" ");
    }
}
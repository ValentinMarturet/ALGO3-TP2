package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.AlgoHoot;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.excepciones.ArchivoInexistente;
import edu.fiuba.algo3.vista.CambiadorDeVistas;
import edu.fiuba.algo3.vista.VistaTableroJugadores;
import edu.fiuba.algo3.vista.elementos.NumberField;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class ControladorConfigurarPartida implements EventHandler<ActionEvent>  {
    private Stage stage;
    private ListView<String> jugadores;
    private NumberField limitePuntaje;
    private NumberField limitePreguntas;
    private AudioClip sonidoError;

    public ControladorConfigurarPartida(Stage stage, ListView<String> jugadores, NumberField limitePuntaje, NumberField limitePreguntas) {
        this.stage = stage;
        this.jugadores = jugadores;
        this.limitePuntaje = limitePuntaje;
        this.limitePreguntas = limitePreguntas;
        File archivoSonido = new File(System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/sonidos/sinSeleccion.wav");
        Media media = new Media(archivoSonido.toURI().toString());
        sonidoError = new AudioClip(media.getSource());

    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (jugadores.getItems().isEmpty() || limitePreguntas.getText().isEmpty() || limitePuntaje.getText().isEmpty()) {
            if (!sonidoError.isPlaying()) sonidoError.play();
            if (jugadores.getItems().isEmpty()) {
                highlight(jugadores);
                temblor(jugadores);
            }
            if (limitePreguntas.getText().isEmpty()) {
                highlight(limitePreguntas);
                temblor(limitePreguntas);
            }
            if (limitePuntaje.getText().isEmpty()) {
                highlight(limitePuntaje);
                temblor(limitePuntaje);
            }
        } else {
            AlgoHoot a = AlgoHoot.getInstancia();
            try {
                a.inicializarGestorDePreguntas();
                jugadores.getItems().forEach(j -> {
                    a.agregarJugador(new Jugador(j));
                });
                VistaTableroJugadores tablero = new VistaTableroJugadores(a.obtenerJugadores(), 368,640);
                a.setMaximoPreguntas(Integer.parseInt(limitePreguntas.getText()));
                a.setPuntajeMaximo(Integer.parseInt(limitePuntaje.getText()));
                CambiadorDeVistas.cambiarVistaANuevaPregunta(stage,tablero);
            } catch (ArchivoInexistente e) {
                Alert archivoInexistente = new Alert(Alert.AlertType.ERROR);
                archivoInexistente.setTitle("Archivo no encontrado");
                archivoInexistente.setHeaderText("No se encontró el archivo de preguntas.");
                archivoInexistente.show();
            }
        }
    }

    private void temblor(Node nodo){
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(nodo);
        translate.setByX(2);
        translate.setAutoReverse(true);
        translate.setDuration(Duration.millis(65));
        translate.setCycleCount(10);
        translate.play();
    }

    private void highlight(Node nodo) {
        // Color de fondo inicial y final
        String originalStyle = nodo.getStyle();
        String highlightStyle =
                "-fx-font-family: 'Comic Sans MS';" +
                "-fx-font-size: 32;" +
                "-fx-border-width: 4px;" +
                "-fx-border-color: red;";
        // Duración del resaltado
        Duration highlightDuration = Duration.seconds(0.25);
        Duration fadeDuration = Duration.seconds(0.45);

        // Timeline para cambiar el color de fondo
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(nodo.styleProperty(), highlightStyle)),
                new KeyFrame(highlightDuration, new KeyValue(nodo.styleProperty(), highlightStyle)),
                new KeyFrame(highlightDuration.add(fadeDuration), new KeyValue(nodo.styleProperty(), originalStyle))
        );

        // Inicia la animación
        if (nodo.getStyle().contains("-fx-border-color: black;")) {
            timeline.playFromStart();
        }
    }
}

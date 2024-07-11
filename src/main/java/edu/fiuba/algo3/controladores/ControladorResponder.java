package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.AlgoHoot.AlgoHoot;
import edu.fiuba.algo3.modelo.AlgoHoot.Jugador;
import edu.fiuba.algo3.modelo.Preguntas.Pregunta;
import edu.fiuba.algo3.modelo.Puntuacion.Modificadores.ModificadorGlobal;
import edu.fiuba.algo3.modelo.Puntuacion.Modificadores.ModificadorIndividual;
import edu.fiuba.algo3.modelo.Preguntas.Respuesta;
import edu.fiuba.algo3.vista.escenas.VistaFinPregunta;
import edu.fiuba.algo3.vista.escenas.VistaPregunta;
import edu.fiuba.algo3.vista.elementos.VistaTableroJugadores;
import edu.fiuba.algo3.vista.elementos.BotonPoderGlobal;
import edu.fiuba.algo3.vista.elementos.BotonPoderIndividual;
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


    protected List<ModificadorIndividual> obtenerModificadoresIndividuales() {
        return botonesPoderes.stream()
                .map(m -> {
                    if (m instanceof BotonPoderIndividual) {
                        // Si está seleccionado devuelve el modificador correspondiente
                        // si no devuelve null
                        return ((BotonPoderIndividual) m).obtenerModificador();
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    protected List<ModificadorGlobal> obtenerModificadoresGlobales() {
        return botonesPoderes.stream()
                .map(m -> {
                    if (m instanceof BotonPoderGlobal) {
                        // Si está seleccionado devuelve el modificador correspondiente
                        // si no devuelve null
                        return ((BotonPoderGlobal) m).obtenerModificador();
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    protected void reestablecerPregunta() {
        VistaPregunta vista = (VistaPregunta) stage.getScene();
        vista.restablecerOpciones();
        vista.restablecerPoderes();
    }

    protected void responderPregunta(Respuesta... respuestas) {
        AlgoHoot a = AlgoHoot.getInstancia();
        List<ModificadorIndividual> mi = obtenerModificadoresIndividuales();
        List<ModificadorGlobal> mg = obtenerModificadoresGlobales();
        if(tablero.esElUltimoJugador()){
            Jugador jugadorActual = tablero.obtenerJugadorActual();
            a.jugarRondaDePreguntas(jugadorActual,mi,mg,respuestas);
            a.terminarRondaDePreguntas();
            cambiarAVistaFin(stage,tablero);
        }else{
            Jugador jugadorActual = tablero.obtenerJugadorActual();
            a.jugarRondaDePreguntas(jugadorActual,mi,mg,respuestas);
            tablero.siguienteJugador();
            reestablecerPregunta();
        }
    }

    private void cambiarAVistaFin(Stage stage, VistaTableroJugadores tablero){
        AlgoHoot a = AlgoHoot.getInstancia();
        Pregunta pregunta = a.obtenerPreguntaActual();
        tablero.actualizarTabla();
        VistaFinPregunta nuevaVista = new VistaFinPregunta(stage,1280,720, pregunta.getTextoRespuesta(),tablero);
        stage.setScene(nuevaVista);
    }
}

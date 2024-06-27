package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.excepciones.JugadorInexistente;
import edu.fiuba.algo3.vista.CambiadorDeVistas;
import edu.fiuba.algo3.vista.VistaTableroJugadores;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.util.List;

public class ControladorResponderOC extends ControladorResponder {
    private ObservableList<String> opciones;

    public ControladorResponderOC(Stage stage, ObservableList<String> opciones, ObservableList<Node> poderes, VistaTableroJugadores tablero) {
        super(stage,tablero,poderes);
        this.opciones = opciones;
    }
    @Override
    public void handle(ActionEvent actionEvent) {

        sonidoResponder.play();

        Respuesta[] respuestas = opciones.stream()
                .map(Respuesta::new)
                .toArray(Respuesta[]::new);

        responderPregunta(respuestas);

    }
}
package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.Respuestas.Respuesta;
import edu.fiuba.algo3.vista.elementos.VistaTableroJugadores;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

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
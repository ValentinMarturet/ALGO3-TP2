package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.excepciones.JugadorInexistente;
import edu.fiuba.algo3.vista.CambiadorDeVistas;
import edu.fiuba.algo3.vista.VistaTableroJugadores;
import edu.fiuba.algo3.vista.elementos.BotonPoder;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import java.util.List;

public class ControladorResponderVF extends ControladorResponder {
    private final ToggleGroup opciones;
    public ControladorResponderVF(Stage stage, ToggleGroup opciones, ObservableList<Node> poderes, VistaTableroJugadores tablero) {
        super(stage, tablero, poderes);
        this.opciones = opciones;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        ToggleButton eleccion = (ToggleButton) opciones.getSelectedToggle();
        if (eleccion == null) {
            if (sonidoSinSeleccion.isPlaying()) {sonidoSinSeleccion.stop();}
            sonidoSinSeleccion.play();
            // no se seleccionó ninguna opción
            System.out.println("no seleccionaste ninguna opcion");

        } else {
            sonidoResponder.play();
            Respuesta respuesta = new Respuesta(eleccion.getText());
            responderPregunta(respuesta);

        }
    }
}

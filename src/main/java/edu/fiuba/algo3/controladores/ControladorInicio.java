package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.vista.VistaPreguntaGC;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControladorInicio implements EventHandler<ActionEvent> {

    private Stage stage;

    public ControladorInicio(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void handle(ActionEvent actionEvent) {

    }
}
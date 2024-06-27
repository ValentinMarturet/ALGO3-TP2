package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FabricaVistasPreguntas {
    public static Scene crearVista(Pregunta pregunta, Stage stage, VistaTableroJugadores tablero) {
        if (pregunta instanceof PreguntaVF) {
            return new VistaPreguntaVF(stage, stage.getScene().getWidth(), stage.getScene().getHeight(), (PreguntaVF) pregunta, tablero);
        } else if (pregunta instanceof PreguntaVFPenalidad) {
            return new VistaPreguntaVF(stage, stage.getScene().getWidth(), stage.getScene().getHeight(), (PreguntaVFPenalidad) pregunta, tablero);
        } else if (pregunta instanceof PreguntaMC) {
            return new VistaPreguntaMC(stage, stage.getScene().getWidth(), stage.getScene().getHeight(), (PreguntaMC) pregunta, tablero);
        } else if (pregunta instanceof PreguntaMCParcial) {
            return new VistaPreguntaMC(stage, stage.getScene().getWidth(), stage.getScene().getHeight(), (PreguntaMCParcial) pregunta, tablero);
        } else if (pregunta instanceof PreguntaMCPenalidad) {
            return new VistaPreguntaMC(stage, stage.getScene().getWidth(), stage.getScene().getHeight(), (PreguntaMCPenalidad) pregunta, tablero);
        } else if (pregunta instanceof PreguntaGC) {
            return new VistaPreguntaGC(stage, stage.getScene().getWidth(), stage.getScene().getHeight(), (PreguntaGC) pregunta, tablero);
        } else {
            return new VistaPreguntaOC(stage, stage.getScene().getWidth(), stage.getScene().getHeight(), (PreguntaOC) pregunta, tablero);
        }
    }
}

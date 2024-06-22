package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FabricaVistasPreguntas {
    public static Scene crearVista(Pregunta pregunta, Stage stage, VistaTableroJugadores tablero) {
        Scene vista;
        if (pregunta instanceof PreguntaVF) {
            return new VistaPreguntaVF(stage, stage.getWidth(), stage.getHeight(), (PreguntaVF) pregunta, tablero);
        } else if (pregunta instanceof PreguntaVFPenalidad) {
            return new VistaPreguntaVFPenalidad(stage, stage.getWidth(), stage.getHeight(), (PreguntaVFPenalidad) pregunta, tablero);
        } else if (pregunta instanceof PreguntaMC) {
            return new VistaPreguntaMC(stage, stage.getWidth(), stage.getHeight(), (PreguntaMC) pregunta, tablero);
        } else if (pregunta instanceof PreguntaMCParcial) {
            return new VistaPreguntaMCParcial(stage, stage.getWidth(), stage.getHeight(), (PreguntaMCParcial) pregunta, tablero);
        } else if (pregunta instanceof PreguntaMCPenalidad) {
            return new VistaPreguntaMCPenalidad(stage, stage.getWidth(), stage.getHeight(), (PreguntaMCPenalidad) pregunta, tablero);
        } else if (pregunta instanceof PreguntaGC) {
            return new VistaPreguntaGC(stage, stage.getWidth(), stage.getHeight(), (PreguntaGC) pregunta, tablero);
        } else {
            return new VistaPreguntaOC(stage, stage.getWidth(), stage.getHeight(), (PreguntaOC) pregunta, tablero);
        }
    }
}

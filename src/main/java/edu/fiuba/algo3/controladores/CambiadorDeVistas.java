package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.AlgoHoot.AlgoHoot;
import edu.fiuba.algo3.modelo.Preguntas.*;
import edu.fiuba.algo3.vista.escenas.*;
import edu.fiuba.algo3.vista.elementos.VistaTableroJugadores;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CambiadorDeVistas {
    public static void cambiarVistaANuevaPregunta(Stage stage, VistaTableroJugadores tablero){
        AlgoHoot a = AlgoHoot.getInstancia();
        if(a.juegoFinalizado()){
            Scene escena = new VistaFinal(stage,1280,720, tablero);
            stage.setScene(escena);
        }else{
            Pregunta pregunta = a.obtenerPreguntaActual();
            Scene escena = crearVista(pregunta,stage,tablero);
            stage.setScene(escena);
        }
    }

    private static Scene crearVista(Pregunta pregunta, Stage stage, VistaTableroJugadores tablero) {
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

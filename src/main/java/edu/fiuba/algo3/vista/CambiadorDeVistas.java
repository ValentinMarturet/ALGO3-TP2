package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.AlgoHoot;
import edu.fiuba.algo3.modelo.Pregunta;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CambiadorDeVistas {
    public static void cambiarVistaANuevaPregunta(Stage stage, VistaTableroJugadores tablero){
        AlgoHoot a = AlgoHoot.getInstancia();
        Pregunta pregunta = a.obtenerPreguntaActual();
        Scene escena = FabricaVistasPreguntas.crearVista(pregunta,stage,tablero);

        stage.setScene(escena);
    }
    public static void cambiarAVistaFin(Stage stage, VistaTableroJugadores tablero){
        AlgoHoot a = AlgoHoot.getInstancia();
        Pregunta pregunta = a.obtenerPreguntaActual();
        tablero.actualizarTabla();
        VistaFinPregunta nuevaVista = new VistaFinPregunta(stage,1280,720, pregunta.getTextoRespuesta(),tablero);
        stage.setScene(nuevaVista);
    }
}

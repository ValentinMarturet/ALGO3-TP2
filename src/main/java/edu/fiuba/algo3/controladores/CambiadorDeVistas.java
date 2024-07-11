package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.AlgoHoot;
import edu.fiuba.algo3.modelo.Pregunta;
import edu.fiuba.algo3.vista.FabricaVistasPreguntas;
import edu.fiuba.algo3.vista.escenas.VistaFinal;
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
            Scene escena = FabricaVistasPreguntas.crearVista(pregunta,stage,tablero);
            stage.setScene(escena);
        }
    }
}

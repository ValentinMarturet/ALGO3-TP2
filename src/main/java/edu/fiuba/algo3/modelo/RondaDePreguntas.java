package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class RondaDePreguntas {
    private Pregunta pregunta;
    private List<PuntajeParcial> puntajes;
    private List<ModificadorGlobal> modificadoresGlobales;

    public RondaDePreguntas(Pregunta pregunta) {
        this.pregunta = pregunta;
        this.puntajes = new ArrayList<PuntajeParcial>();
        this.modificadoresGlobales = new ArrayList<ModificadorGlobal>();
    }

    public void jugar(Jugador j, List<ModificadorIndividual> modsInd, List<ModificadorGlobal> modsGlob, Respuesta... respuestas){

        PuntajeParcial puntos = pregunta.responder(respuestas);

        puntos.establecerJugador(j);

        modsInd.forEach(puntos::agregarModificador);

        modificadoresGlobales.addAll(modsGlob);

        puntajes.add(puntos);
    }

    public void terminar() {

        modificadoresGlobales.forEach(m -> m.aplicar(puntajes));

        puntajes.forEach(PuntajeParcial::actualizarPuntajeDelJugador);
    }

    public Pregunta getPregunta() {
        return pregunta;
    }
}

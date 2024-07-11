package edu.fiuba.algo3.modelo.AlgoHoot;

import edu.fiuba.algo3.modelo.Preguntas.Pregunta;
import edu.fiuba.algo3.modelo.Puntuacion.Modificadores.ModificadorGlobal;
import edu.fiuba.algo3.modelo.Puntuacion.Modificadores.ModificadorIndividual;
import edu.fiuba.algo3.modelo.Puntuacion.Puntajes.PuntajeParcial;
import edu.fiuba.algo3.modelo.Respuestas.Respuesta;

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

        modsInd.forEach(mi -> {
            puntos.agregarModificador(mi);
            j.gastar(mi);
        });

        modificadoresGlobales.addAll(modsGlob);
        modsGlob.forEach(j::gastar);

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

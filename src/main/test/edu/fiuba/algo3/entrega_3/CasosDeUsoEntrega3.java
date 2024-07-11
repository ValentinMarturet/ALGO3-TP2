package edu.fiuba.algo3.entrega_3;

import edu.fiuba.algo3.modelo.AlgoHoot.Jugador;
import edu.fiuba.algo3.modelo.AlgoHoot.RondaDePreguntas;
import edu.fiuba.algo3.modelo.Preguntas.PreguntaVFPenalidad;
import edu.fiuba.algo3.modelo.Puntuacion.Modificadores.*;
import edu.fiuba.algo3.modelo.Respuestas.OpcionCorrecta;
import edu.fiuba.algo3.modelo.Respuestas.OpcionIncorrecta;
import edu.fiuba.algo3.modelo.Respuestas.Respuesta;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CasosDeUsoEntrega3 {
    @Test
    public void testUnTurno(){
        // Arrange
        PreguntaVFPenalidad p = new PreguntaVFPenalidad("cuantos dedos tiene mi mano derecha?","SENTIDO COMUN", "La mano derecha de Pat tiene 5 dedos.",
                new OpcionCorrecta("5"),
                new OpcionIncorrecta("3"));
        Jugador j1 = new Jugador("J1");
        Jugador j2 = new Jugador("J2");
        Jugador j3 = new Jugador("J3");
        List<Jugador> jugadores = new ArrayList<Jugador>();
        jugadores.add(j1);
        jugadores.add(j2);
        jugadores.add(j3);
        RondaDePreguntas rondaDePreguntas = new RondaDePreguntas(p);
        int puntosEsperadosJ1 = 2;
        int puntosEsperadosJ2 = -3;
        int puntosEsperadosJ3 = -1;

        // Act
        ArrayList<ModificadorIndividual> modsInd1 = new ArrayList<>();
        modsInd1.add(new Duplicador());
        ArrayList<ModificadorIndividual> modsInd2 = new ArrayList<>();
        modsInd2.add(new Triplicador());
        ArrayList<ModificadorIndividual> modsInd3 = new ArrayList<>();
        modsInd3.add(new ModificadorIndividualBase());

        ArrayList<ModificadorGlobal> modsGlob1 = new ArrayList<>();
        modsGlob1.add(new ModificadorGlobalBase());
        ArrayList<ModificadorGlobal> modsGlob2 = new ArrayList<>();
        modsGlob2.add(new ModificadorGlobalBase());
        ArrayList<ModificadorGlobal> modsGlob3 = new ArrayList<>();
        modsGlob3.add(new ModificadorGlobalBase());

        rondaDePreguntas.jugar(j1, modsInd1, modsGlob1, new Respuesta("5"));
        rondaDePreguntas.jugar(j2, modsInd2, modsGlob2, new Respuesta("3"));
        rondaDePreguntas.jugar(j3, modsInd3, modsGlob3, new Respuesta("3"));
        rondaDePreguntas.terminar();

        // Assert
        assertEquals( j1.obtenerPuntaje(), puntosEsperadosJ1);
        assertEquals( j2.obtenerPuntaje(), puntosEsperadosJ2);
        assertEquals( j3.obtenerPuntaje(), puntosEsperadosJ3);
    }

    @Test
    public void testGestorDeTurnos() {
        // Arrange

        // Act

        // Assert

    }
}

package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.AlgoHoot.AlgoHoot;
import edu.fiuba.algo3.modelo.AlgoHoot.Jugador;
import edu.fiuba.algo3.modelo.Preguntas.Pregunta;
import edu.fiuba.algo3.modelo.Puntuacion.Modificadores.*;
import edu.fiuba.algo3.modelo.Preguntas.Opcion;
import edu.fiuba.algo3.modelo.Preguntas.Respuesta;
import edu.fiuba.algo3.modelo.excepciones.ArchivoInexistente;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AlgoHootTest {

    @Test
    public void test01AgregarJugador() throws ArchivoInexistente {
        //Arrange
        AlgoHoot algoHoot = AlgoHoot.getInstancia();

        // Act
        Jugador jugador1 = new Jugador("Jugador1");

        algoHoot.agregarJugador(jugador1);

        Jugador jugadorObtenido = (Jugador) ((LinkedList) algoHoot.obtenerJugadores()).getLast();
        // Assert
        assertEquals(jugador1, jugadorObtenido);
    }

    @Test
    public void test02obtenerPreguntaNoEspecifica() throws ArchivoInexistente {
        // Arrange
        AlgoHoot algoHoot = AlgoHoot.getInstancia();
        algoHoot.inicializarGestorDePreguntas();

        // Act
        Pregunta pregunta = algoHoot.obtenerPreguntaActual();
        //System.out.println(pregunta.getPregunta());

        // Assert
        assertNotNull(pregunta);
    }

    @Test
    public void test03jugarUnTurno() throws ArchivoInexistente{
        //Arrange
        AlgoHoot.reiniciarInstancia();
        AlgoHoot algoHoot = AlgoHoot.getInstancia();
        algoHoot.inicializarGestorDePreguntas(false);

        algoHoot.reiniciarListaDeJugadores();
        Jugador j1 = new Jugador("J1");
        Jugador j2 = new Jugador("J2");
        Jugador j3 = new Jugador("J3");

        algoHoot.agregarJugador(j1);
        algoHoot.agregarJugador(j2);
        algoHoot.agregarJugador(j3);

        algoHoot.comenzarNuevaRondaDePreguntas();
        int puntosEsperadosJ1 = 1;
        int puntosEsperadosJ2 = 0;
        int puntosEsperadosJ3 = 0;

        System.out.println(algoHoot.obtenerPreguntaActual().getPregunta());
        List<Opcion> opciones = algoHoot.obtenerPreguntaActual().getOpciones();
        for(Opcion opcion : opciones) {
            System.out.println(opcion.getOpcion());
        }

        // Act
        ArrayList<ModificadorIndividual> modsInd = new ArrayList<>();
        modsInd.add(new ModificadorIndividualBase());


        ArrayList<ModificadorGlobal> modsGlob = new ArrayList<>();
        modsGlob.add(new ModificadorGlobalBase());



        algoHoot.jugarRondaDePreguntas(j1, modsInd, modsGlob, new Respuesta("Microondas"), new Respuesta("Televisor de tubo CRT"), new Respuesta("Heladera"), new Respuesta("Imanes del delivery"));
        algoHoot.jugarRondaDePreguntas(j2, modsInd, modsGlob, new Respuesta("Microondas"), new Respuesta("Televisor de tubo CRT"), new Respuesta("Imanes del delivery"), new Respuesta("Heladera"));
        algoHoot.jugarRondaDePreguntas(j3, modsInd, modsGlob, new Respuesta("Televisor de tubo CRT"), new Respuesta("Microondas"), new Respuesta("Imanes del delivery"), new Respuesta("Heladera"));
        algoHoot.terminarRondaDePreguntas();

        // Assert
        assertEquals(puntosEsperadosJ1, j1.obtenerPuntaje());
        assertEquals(puntosEsperadosJ2, j2.obtenerPuntaje());
        assertEquals(puntosEsperadosJ3, j3.obtenerPuntaje());
    }

    @Test
    public void test04jugarUnTurnoUsandoExclusividadModificaCorrectamenteElPuntaje() throws ArchivoInexistente {
        //Arrange
        AlgoHoot.reiniciarInstancia();
        AlgoHoot algoHoot = AlgoHoot.getInstancia();
        algoHoot.inicializarGestorDePreguntas(false);
        algoHoot.reiniciarListaDeJugadores();
        Jugador j1 = new Jugador("J1");
        algoHoot.agregarJugador(j1);
        algoHoot.comenzarNuevaRondaDePreguntas();
        int puntosEsperadosJ1 = 2;

        //Act
        ArrayList<ModificadorIndividual> modsInd = new ArrayList<>();
        modsInd.add(new ModificadorIndividualBase());

        ArrayList<ModificadorGlobal> modsGlob = new ArrayList<>();
        modsGlob.add(new Exclusividad());

        algoHoot.jugarRondaDePreguntas(j1, modsInd, modsGlob, new Respuesta("Microondas"), new Respuesta("Televisor de tubo CRT"), new Respuesta("Heladera"), new Respuesta("Imanes del delivery"));
        algoHoot.terminarRondaDePreguntas();
        
        //Arrange
        assertEquals(puntosEsperadosJ1, j1.obtenerPuntaje());
    }

}

package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.ArchivoInexistente;

import java.util.LinkedList;
import java.util.List;

public class GestorDeTurnos {
    private LinkedList<RondaDePreguntas> rondasDePreguntas;
    private GestorDePreguntas gestorDePreguntas;
    private TablaDeJugadores tablaDeJugadores;

    public GestorDeTurnos() {
        this.rondasDePreguntas = new LinkedList<>();
        tablaDeJugadores = new TablaDeJugadores();
    }

    public void inicializarGestorDePreguntas() throws ArchivoInexistente {
        gestorDePreguntas = new GestorDePreguntas();
    }

    public void inicializarGestorDePreguntas(boolean mezclado) throws ArchivoInexistente {
        gestorDePreguntas = new GestorDePreguntas(mezclado);
    }

    public void comenzarNuevaRonda() {
        Pregunta p = gestorDePreguntas.obtenerSiguientePregunta();
        RondaDePreguntas nuevoRondaDePreguntas = new RondaDePreguntas(p);
        rondasDePreguntas.add(nuevoRondaDePreguntas);
    }

    public Pregunta obtenerPreguntaRondaActual(){
        if(rondasDePreguntas.isEmpty()){
            comenzarNuevaRonda();
        }
        return rondasDePreguntas.getLast().getPregunta();
    }

    public void jugarRondaActual(Jugador j, List<ModificadorIndividual> mis, List<ModificadorGlobal> mgs, Respuesta... respuestas){
        RondaDePreguntas rondaDePreguntas = rondasDePreguntas.getLast();

        rondaDePreguntas.jugar(j, mis, mgs, respuestas);
    }

    public void terminarRondaActual() {
        RondaDePreguntas rondaDePreguntas = rondasDePreguntas.getLast();
        rondaDePreguntas.terminar();
    }

    public void agregarJugador(Jugador j) {
        tablaDeJugadores.add(j);
    }

    public List<Jugador> obtenerJugadores() {
        return tablaDeJugadores;
    }

    public void reiniciarListaDeJugadores(){
        tablaDeJugadores.clear();
    }

    public Jugador obtenerJugadorConMayorPuntaje(){
        return tablaDeJugadores.obtenerJugadorConMayorPuntaje();
    }


}

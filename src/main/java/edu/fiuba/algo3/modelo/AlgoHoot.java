package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.ArchivoInexistente;

import java.util.LinkedList;
import java.util.List;

public class AlgoHoot {

    private static AlgoHoot instancia;

    private LinkedList<RondaDePreguntas> rondasDePreguntas;
    private GestorDePreguntas gestorDePreguntas;
    private TablaDeJugadores tablaDeJugadores;

    private int maximoPreguntas;
    private int puntajeMaximo;

    private int preguntasJugadas;

    private AlgoHoot(){
        maximoPreguntas = 0;
        this.puntajeMaximo = 0;
        this.preguntasJugadas = 0;
        this.rondasDePreguntas = new LinkedList<>();
        tablaDeJugadores = new TablaDeJugadores();
    }

    public static AlgoHoot getInstancia() {
        if (instancia == null) {
            instancia = new AlgoHoot();
        }
        return instancia;
    }

    public static void reiniciarInstancia() {
        instancia = new AlgoHoot();
    }

    public void setPuntajeMaximo(int puntajeMaximo) {this.puntajeMaximo = puntajeMaximo;}
    public void setMaximoPreguntas(int maximoPreguntas) {this.maximoPreguntas = maximoPreguntas;}



    public void inicializarGestorDePreguntas() throws ArchivoInexistente {
        gestorDePreguntas = new GestorDePreguntas();
    }

    public void inicializarGestorDePreguntas(boolean mezclado) throws ArchivoInexistente {
        gestorDePreguntas = new GestorDePreguntas(mezclado);
    }

    public void comenzarNuevaRondaDePreguntas() {
        Pregunta p = gestorDePreguntas.obtenerSiguientePregunta();
        RondaDePreguntas nuevoRondaDePreguntas = new RondaDePreguntas(p);
        rondasDePreguntas.add(nuevoRondaDePreguntas);
        this.preguntasJugadas++;
    }

    public Pregunta obtenerPreguntaActual(){
        if(rondasDePreguntas.isEmpty()){
            comenzarNuevaRondaDePreguntas();
        }
        return rondasDePreguntas.getLast().getPregunta();
    }

    public void jugarRondaDePreguntas(Jugador j, List<ModificadorIndividual> mis, List<ModificadorGlobal> mgs, Respuesta... respuestas){
        RondaDePreguntas rondaDePreguntas = rondasDePreguntas.getLast();

        rondaDePreguntas.jugar(j, mis, mgs, respuestas);
    }

    public void terminarRondaDePreguntas() {
        RondaDePreguntas rondaDePreguntas = rondasDePreguntas.getLast();
        rondaDePreguntas.terminar();
    }

    public void agregarJugador(Jugador j) {
        tablaDeJugadores.add(j);
    }

    public TablaDeJugadores obtenerJugadores() {
        return tablaDeJugadores;
    }

    public void reiniciarListaDeJugadores(){
        tablaDeJugadores.clear();
    }

    private Jugador obtenerJugadorConMayorPuntaje(){
        return tablaDeJugadores.obtenerJugadorConMayorPuntaje();
    }

    public boolean juegoFinalizado(){
        if(obtenerJugadorConMayorPuntaje().obtenerPuntaje()>=puntajeMaximo || preguntasJugadas>=maximoPreguntas){
            return true;
        } else{
            return false;
        }
    }

    public Jugador obtenerGanador(){
        if(this.juegoFinalizado()){
            return obtenerJugadorConMayorPuntaje();
        }
        return null;
    }

}

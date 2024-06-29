package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.ArchivoInexistente;

import java.util.List;

public class AlgoHoot {

    private static AlgoHoot instancia;

    private GestorDeTurnos gestorDeTurnos;

    private int maximoPreguntas;
    private int puntajeMaximo;

    private int preguntasJugadas;

    private AlgoHoot(){
        gestorDeTurnos = new GestorDeTurnos();
        maximoPreguntas = 0;
        this.puntajeMaximo = 0;
        this.preguntasJugadas = 1;
    }

    public static AlgoHoot getInstancia() {
        if (instancia == null) {
            instancia = new AlgoHoot();
        }
        return instancia;
    }

    public void setPuntajeMaximo(int puntajeMaximo) {this.puntajeMaximo = puntajeMaximo;}
    public void setMaximoPreguntas(int maximoPreguntas) {this.maximoPreguntas = maximoPreguntas;}

    public void inicializarGestorDePreguntas() throws ArchivoInexistente {
        gestorDeTurnos.inicializarGestorDePreguntas();
    }
    public void inicializarGestorDePreguntas(boolean mezclado) throws ArchivoInexistente {
        gestorDeTurnos.inicializarGestorDePreguntas(mezclado);
    }

    public void comenzarNuevaRondaDePreguntas(){
        gestorDeTurnos.comenzarNuevaRonda();
        this.preguntasJugadas++;
    }

    public void jugarRondaDePreguntas(Jugador j, List<ModificadorIndividual> mis, List<ModificadorGlobal> mgs, Respuesta... respuestas){
        gestorDeTurnos.jugarRondaActual(j, mis, mgs, respuestas);
    }

    public void terminarRondaDePreguntas(){
        gestorDeTurnos.terminarRondaActual();
    }

    public Pregunta obtenerPreguntaActual(){
        return gestorDeTurnos.obtenerPreguntaRondaActual();
    }

    public void agregarJugador(Jugador jugador) {
        gestorDeTurnos.agregarJugador(jugador);
    }

    public List<Jugador> obtenerJugadores(){
        return gestorDeTurnos.obtenerJugadores();
    }

    public void reiniciarListaDeJugadores() {
        gestorDeTurnos.reiniciarListaDeJugadores();
    }

    public boolean elJuegoTermino(){
        if(gestorDeTurnos.obtenerJugadorConMayorPuntaje().obtenerPuntaje()>=puntajeMaximo || preguntasJugadas>=maximoPreguntas){
            return true;
        } else{
            return false;
        }
    }

    public Jugador obtenerGanador(){
        return gestorDeTurnos.obtenerJugadorConMayorPuntaje();
    }

}

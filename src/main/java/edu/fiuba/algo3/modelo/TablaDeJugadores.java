package edu.fiuba.algo3.modelo;

import java.util.LinkedList;
import java.util.List;

public class TablaDeJugadores extends LinkedList<Jugador> {



    public Jugador obtenerJugadorConMayorPuntaje(){
        Jugador jugadorconMaximoPuntaje = null;
        int puntajeMaximo = 0;
        for(Jugador jugador : this){
            int puntajeActual = jugador.obtenerPuntaje();
            if(puntajeActual>=puntajeMaximo){
                jugadorconMaximoPuntaje = jugador;
                puntajeMaximo = puntajeActual;
            }
        }
        return jugadorconMaximoPuntaje;
    }

}

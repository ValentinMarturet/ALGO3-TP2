package edu.fiuba.algo3.modelo.AlgoHoot;

import java.util.Comparator;
import java.util.LinkedList;

public class TablaDeJugadores extends LinkedList<Jugador> {

    public Jugador obtenerJugadorConMayorPuntaje(){
        return  this.stream()
                .max(Comparator.comparingInt(Jugador::obtenerPuntaje))
                .orElse(null);
    }

}

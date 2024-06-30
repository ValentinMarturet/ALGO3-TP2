package edu.fiuba.algo3.modelo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class TablaDeJugadores extends LinkedList<Jugador> {

    public Jugador obtenerJugadorConMayorPuntaje(){
        return  this.stream()
                .max(Comparator.comparingInt(Jugador::obtenerPuntaje))
                .orElse(null);
    }

}

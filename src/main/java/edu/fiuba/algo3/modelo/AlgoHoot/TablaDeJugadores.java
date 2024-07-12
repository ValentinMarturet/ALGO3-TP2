package edu.fiuba.algo3.modelo.AlgoHoot;

import java.util.Comparator;
import java.util.LinkedList;

public class TablaDeJugadores extends LinkedList<Jugador> {


    private Jugador obtenerJugadorConMayorPuntaje(){
        return  this.stream()
                .max(Comparator.comparingInt(Jugador::obtenerPuntaje))
                .orElse(null);
    }

    public Jugador obtenerGanador() {
        Jugador primero = obtenerJugadorConMayorPuntaje();

        if (primero.obtenerPuntaje() >= AlgoHoot.getInstancia().obtenerPuntosParaGanar())
            return primero;
        return null;
    }
}

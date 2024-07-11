package edu.fiuba.algo3.modelo.Respuestas;

import edu.fiuba.algo3.modelo.Puntuacion.Puntajes.PuntajeParcial;

import java.util.*;
import java.util.stream.IntStream;

public class Orden {
    private List<Opcion> orden;

    public Orden(Opcion... orden) {
        this.orden = new ArrayList<Opcion>();
        Collections.addAll(this.orden, orden);
    }

    public PuntajeParcial puntuar(Respuesta... respuesta) {
        if (equals( respuesta )) {
            return new PuntajeParcial(1);
        }
        return new PuntajeParcial(0);
    }

    public boolean equals(Respuesta... respuesta) {
        if (orden.size() != respuesta.length) return false;
        return IntStream.range(0, orden.size())
                .allMatch(i -> orden.get(i).equals(respuesta[i]));
    }
}
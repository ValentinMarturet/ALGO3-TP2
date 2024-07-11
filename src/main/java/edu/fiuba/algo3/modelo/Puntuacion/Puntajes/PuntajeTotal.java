package edu.fiuba.algo3.modelo.Puntuacion.Puntajes;

import java.util.ArrayList;
import java.util.List;

public class PuntajeTotal {
    private List<PuntajeParcial> puntosParciales;

    public PuntajeTotal() {
        this.puntosParciales = new ArrayList<PuntajeParcial>();
    }
    public void agregar(PuntajeParcial puntos) {
        puntosParciales.add(puntos);
    }

    public int obtenerPuntaje() {
        return puntosParciales.stream()
                .map(PuntajeParcial::obtenerPuntos)
                .reduce(0, Integer::sum);
    }

}

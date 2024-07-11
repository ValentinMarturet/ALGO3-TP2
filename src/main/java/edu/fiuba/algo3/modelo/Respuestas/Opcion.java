package edu.fiuba.algo3.modelo.Respuestas;

import edu.fiuba.algo3.modelo.Puntuacion.Puntajes.PuntajeParcial;

public class Opcion {
    protected String opcion;

    public Opcion(String opcion) {
        this.opcion = opcion;
    }

    public String getOpcion() {
        return opcion;
    }

    public boolean equals(Respuesta respuesta) {
        return respuesta.equals( opcion );
    }

    public PuntajeParcial puntuar(Respuesta respuesta) {
        if ( equals(respuesta) ) {
            return new PuntajeParcial(1);
        }
        return new PuntajeParcial(0);
    }
}
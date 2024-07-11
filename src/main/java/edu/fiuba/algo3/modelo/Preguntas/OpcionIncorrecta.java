package edu.fiuba.algo3.modelo.Preguntas;

import edu.fiuba.algo3.modelo.Puntuacion.Puntajes.PuntajeParcial;

public class OpcionIncorrecta extends Opcion {

    public OpcionIncorrecta(String opcion) {
        super(opcion);
    }

    public PuntajeParcial puntuar(Respuesta respuesta) {
        if ( equals( respuesta ) ) {
            return new PuntajeParcial(-1);
        }
        return new PuntajeParcial(0);
    }

}

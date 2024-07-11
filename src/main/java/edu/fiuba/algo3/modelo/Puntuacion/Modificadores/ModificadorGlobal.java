package edu.fiuba.algo3.modelo.Puntuacion.Modificadores;

import edu.fiuba.algo3.modelo.Puntuacion.Puntajes.PuntajeParcial;

import java.util.List;

public interface ModificadorGlobal {

    void aplicar(List<PuntajeParcial> puntajes);
}

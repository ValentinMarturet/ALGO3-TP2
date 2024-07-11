package edu.fiuba.algo3.modelo.Puntuacion.Modificadores;

import edu.fiuba.algo3.modelo.AlgoHoot.Jugador;
import edu.fiuba.algo3.modelo.Puntuacion.Puntajes.PuntajeParcial;

import java.util.List;

public class Anulador implements ModificadorGlobal{
    private final Jugador jugador;
    public Anulador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void aplicar(List<PuntajeParcial> puntajes) {
        puntajes.stream()
                .filter(p -> !p.perteneceA(jugador))
                .forEach(p -> p.agregarModificador(new ModificadorNulo()));
    }
}
package edu.fiuba.algo3.modelo.AlgoHoot;

import edu.fiuba.algo3.modelo.Puntuacion.Modificadores.ModificadorGlobal;

@FunctionalInterface
public interface OperacionPoderesClasicos {
    public void operar(ModificadorGlobal anulador, ModificadorGlobal exclusividad);
}

package edu.fiuba.algo3.modelo.AlgoHoot;

import edu.fiuba.algo3.modelo.Puntuacion.Modificadores.ModificadorGlobal;
import edu.fiuba.algo3.modelo.Puntuacion.Modificadores.ModificadorIndividual;

@FunctionalInterface
public interface OperacionPoderesPenalidad {
    public void operar(ModificadorGlobal anulador, ModificadorIndividual duplicador, ModificadorIndividual triplicador);
}

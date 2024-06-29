package edu.fiuba.algo3.modelo;

@FunctionalInterface
public interface OperacionPoderesPenalidad {
    public void operar(ModificadorGlobal anulador, ModificadorIndividual duplicador, ModificadorIndividual triplicador);
}

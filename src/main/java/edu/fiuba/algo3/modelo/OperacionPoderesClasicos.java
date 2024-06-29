package edu.fiuba.algo3.modelo;

@FunctionalInterface
public interface OperacionPoderesClasicos {
    public void operar(ModificadorGlobal anulador, ModificadorGlobal exclusividad);
}

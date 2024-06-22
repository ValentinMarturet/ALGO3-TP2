package edu.fiuba.algo3.modelo.excepciones;

public class TurnoNoIniciado extends RuntimeException{
    public TurnoNoIniciado(String mensaje){
        super(mensaje);
    }
}

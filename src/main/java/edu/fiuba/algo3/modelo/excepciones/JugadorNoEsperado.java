package edu.fiuba.algo3.modelo.excepciones;

public class JugadorNoEsperado extends RuntimeException {
    public JugadorNoEsperado(String mensaje) {
        super(mensaje);
    }
}

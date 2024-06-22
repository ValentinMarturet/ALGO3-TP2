package edu.fiuba.algo3.modelo.excepciones;

public class JugadorInexistente extends RuntimeException {
    public JugadorInexistente(String mensaje) {
        super(mensaje);
    }
}
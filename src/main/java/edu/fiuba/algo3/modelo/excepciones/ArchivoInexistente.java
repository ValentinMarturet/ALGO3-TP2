package edu.fiuba.algo3.modelo.excepciones;

public class ArchivoInexistente extends RuntimeException {
    public ArchivoInexistente(String mensaje){
        super(mensaje);
    }
}

package edu.fiuba.algo3.modelo;

public class Respuesta {
    private String respuesta;

    public Respuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public boolean equals(String opcion) {
        return opcion.equals(respuesta);
    }

}
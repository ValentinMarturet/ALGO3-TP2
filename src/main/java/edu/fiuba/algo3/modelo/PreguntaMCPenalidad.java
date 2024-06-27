package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PreguntaMCPenalidad implements Pregunta{
    private String pregunta;
    private List<Opcion> opciones;
    private String tematica;
    private String textoRespuesta;

    public PreguntaMCPenalidad(String pregunta, String tematica, String textoRespuesta, Opcion... opciones) {
        this.pregunta = pregunta;
        this.opciones = new ArrayList<Opcion>();
        Collections.addAll(this.opciones, opciones);
        this.tematica = tematica;
        this.textoRespuesta = textoRespuesta;
    }

    public PuntajeParcial responder(Respuesta... respuestas) {
        return Arrays.stream(respuestas)
                .flatMap(r -> opciones.stream().map(op -> op.puntuar(r)))
                .reduce(new PuntajeParcial(0), (p, valor) -> {
                    p.sumar(valor);
                    return p;
                });
    }

    @Override
    public String getTipo() {
        return "Multiple Choice Penalidad";
    }

    @Override
    public String getPregunta() {
        return this.pregunta;
    }

    @Override
    public List<Opcion> getOpciones() {
        return this.opciones;
    }

    public String getTematica() {return this.tematica;}
    public String getTextoRespuesta() {return this.textoRespuesta;}
}

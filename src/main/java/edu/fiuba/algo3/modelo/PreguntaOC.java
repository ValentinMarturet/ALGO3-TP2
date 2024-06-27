package edu.fiuba.algo3.modelo;

import java.util.LinkedList;
import java.util.List;

public class PreguntaOC implements Pregunta{
    private String pregunta;
    private Opcion[] opciones;
    private Orden ordenCorrecto;
    private String tematica;
    private String textoRespuesta;

    public PreguntaOC(String pregunta, String tematica, String textoRespuesta, Opcion... opciones) {
        this.pregunta = pregunta;
        this.opciones = opciones;
        this.ordenCorrecto = new Orden(opciones);
        this.tematica = tematica;
        this.textoRespuesta = textoRespuesta;
    }

    @Override
    public String getPregunta() {
        return this.pregunta;
    }

    @Override
    public List<Opcion> getOpciones() {
        return new LinkedList<Opcion>(List.of(this.opciones));
    }

    @Override
    public PuntajeParcial responder(Respuesta... respuestas) {
        return ordenCorrecto.puntuar(respuestas);
    }

    @Override
    public String getTipo() {
        return "Ordered Choice";
    }

    public String getTematica() {return this.tematica;}
    public String getTextoRespuesta() {return this.textoRespuesta;}
}

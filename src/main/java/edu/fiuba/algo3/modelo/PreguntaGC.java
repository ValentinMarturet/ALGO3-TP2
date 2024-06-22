package edu.fiuba.algo3.modelo;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;

public class PreguntaGC implements Pregunta {
    private String pregunta;
    private Grupo unGrupo;
    private Grupo otroGrupo;
    private String tematica;
    private String textoRespuesta;

    public PreguntaGC(String pregunta, String tematica, String textoRespuesta,Grupo unGrupo, Grupo otroGrupo) {
        this.pregunta = pregunta;
        this.unGrupo = unGrupo;
        this.otroGrupo = otroGrupo;
        this.tematica = tematica;
        this.textoRespuesta = textoRespuesta;
    }

    @Override
    public String getPregunta() {
        return this.pregunta;
    }

    @Override
    public List<Opcion> getOpciones() {
        LinkedList<Opcion> opciones = new LinkedList<Opcion>();
        opciones.addAll(unGrupo.getOpciones());
        opciones.addAll(otroGrupo.getOpciones());
        return opciones;
    }


    @Override
    public PuntajeParcial responder(Respuesta... respuestas) {
        PuntajeParcial acumuluador = new PuntajeParcial();
        acumuluador.sumar(unGrupo.puntuar(respuestas));
        acumuluador.sumar(otroGrupo.puntuar(respuestas));
        return acumuluador;
    }

    public String getTematica() {return this.tematica;}
    public String getTextoRespuesta() {return this.textoRespuesta;}
    public Pair<String,String> getNombreDeGrupos() {
        return new Pair<>(unGrupo.getNombre(), otroGrupo.getNombre());
    }
}

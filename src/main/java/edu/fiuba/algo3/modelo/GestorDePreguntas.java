package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.ArchivoInexistente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestorDePreguntas {

    private List<Pregunta> preguntas;
    private Integer contador = 0;

    public GestorDePreguntas() throws ArchivoInexistente {
        Lector lector = new Lector();
        this.preguntas = mezclarPreguntas(lector.cargarPreguntas());
    }

    public Pregunta obtenerSiguientePregunta() {
        Pregunta siguientePregunta = preguntas.get(contador);
        contador++;
        return siguientePregunta;
    }

    private List<Pregunta> mezclarPreguntas(List<Pregunta> preguntas){
        List<Pregunta> otraLista = new ArrayList<>();
        List<Pregunta> noAgregados = new ArrayList<>();
        String ultimaTematica = "";

        for(Pregunta pregunta : preguntas){
            String actualTematica = pregunta.getTematica();
            if(!actualTematica.equals(ultimaTematica)){
                ultimaTematica = actualTematica;
                otraLista.add(pregunta);
            }else{
                noAgregados.add(pregunta);
            }
        }
        return insertarEnLaLista(otraLista,noAgregados);
    }

    private List<Pregunta> insertarEnLaLista(List<Pregunta> listaNormal, List<Pregunta> listaDeNoAgregados){
        List<Pregunta> listaCopia = listaNormal;

        for(Pregunta preguntaNoAgregada : listaDeNoAgregados){
            String tematicaActual = preguntaNoAgregada.getTematica();
            for(int i=1; i<listaCopia.size(); i++){
                Pregunta anteriorPregunta = listaCopia.get(i-1);
                Pregunta actualPregunta = listaCopia.get(i);
                String tematicaDePreguntaAnterior = anteriorPregunta.getTematica();
                String tematicaDePreguntaActual = actualPregunta.getTematica();
                if(!tematicaDePreguntaAnterior.equals(tematicaActual) && !tematicaDePreguntaActual.equals(tematicaActual)){
                    listaCopia.add(i,preguntaNoAgregada);
                    break;
                }
            }
        }
        return listaCopia;
    }
    
}

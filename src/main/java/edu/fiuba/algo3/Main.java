package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Fabricas.Lector;
import edu.fiuba.algo3.modelo.Preguntas.Pregunta;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        Lector nuevaLectura = new Lector();
        ArrayList<Pregunta> preguntas = nuevaLectura.cargarPreguntas();
        for(Pregunta pregunta : preguntas){
            System.out.println(pregunta.getTematica());
        }
    }
}

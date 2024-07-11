package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.AlgoHoot.GestorDePreguntas;
import edu.fiuba.algo3.modelo.Preguntas.Pregunta;
import edu.fiuba.algo3.modelo.excepciones.ArchivoInexistente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GestorDePreguntasTest {
    @Test
    public void test01obtenerPreguntasSinRepetirTematica() throws ArchivoInexistente {
        GestorDePreguntas gp = new GestorDePreguntas();
        Pregunta p1 = gp.obtenerSiguientePregunta();
        Pregunta p2 = gp.obtenerSiguientePregunta();
        Pregunta p3 = gp.obtenerSiguientePregunta();

        System.out.println(p1.getPregunta());
        System.out.println(p2.getPregunta());
        System.out.println(p3.getPregunta());

        assertNotEquals(p1.getTematica(), p2.getTematica());
    }
}

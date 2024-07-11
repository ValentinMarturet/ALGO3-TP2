package edu.fiuba.algo3.entrega_1;

import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.modelo.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CasosDeUsoEntrega1 {

    @Test
    public void testVFRespuestaCorrecta() {
        // Arrange
        PreguntaVF p = new PreguntaVF("1 + 1 = 2?", "MATEMATICA", "Segun los axiomas de la matematica, esta ecuacion es verdadera", new OpcionCorrecta("Si"), new OpcionIncorrecta("No") );
        PuntajeParcial puntosEsperados = new PuntajeParcial(1);

        // Act
        PuntajeParcial puntosObtenidos = p.responder( new Respuesta("Si") );

        // Assert
        assertEquals( puntosObtenidos, puntosEsperados );
    }

    @Test
    public void testVFRespuestaIncorrecta() {
        // Arrange
        PreguntaVF p = new PreguntaVF("1 + 1 = 2?", "MATEMATICA", "Segun los axiomas de la matematica, esta ecuacion es verdadera", new OpcionCorrecta("Si"), new OpcionIncorrecta("No") );
        PuntajeParcial puntosEsperados = new PuntajeParcial(0);

        // Act
        PuntajeParcial puntosObtenidos = p.responder( new Respuesta("No") );

        // Assert
        assertEquals( puntosObtenidos, puntosEsperados );
    }

    @Test
    public void testVFPenalidadRespuestaCorrecta() {
        // Arrange
        PreguntaVFPenalidad p = new PreguntaVFPenalidad("1 + 1 = 2?", "MATEMATICA", "Segun los axiomas de la matematica, esta ecuacion es verdadera", new OpcionCorrecta("Si"), new OpcionIncorrecta("No") );
        PuntajeParcial puntosEsperados = new PuntajeParcial(1);

        // Act
        PuntajeParcial puntosObtenidos = p.responder( new Respuesta("Si") );

        // Assert
        assertEquals( puntosObtenidos, puntosEsperados );
    }
    @Test
    public void testVFPenalidadRespuestaIncorrecta() {
        // Arrange
        PreguntaVFPenalidad p = new PreguntaVFPenalidad("1 + 1 = 2?", "MATEMATICA", "Segun los axiomas de la matematica, esta ecuacion es verdadera", new OpcionCorrecta("Si"), new OpcionIncorrecta("No") );
        PuntajeParcial puntosEsperados = new PuntajeParcial(-1);

        // Act
        PuntajeParcial puntosObtenidos = p.responder( new Respuesta("No") );

        // Assert
        assertEquals( puntosObtenidos, puntosEsperados );
    }

    @Test
    public void testMCRespuestaCorrecta() {
        // Arrange
        PreguntaMC p = new PreguntaMC("Animal alado?", "CIENCIAS", "No se tiene un solo registro de un perro con alas",
                new OpcionCorrecta("Pato"),
                new OpcionCorrecta("Aguila"),
                new OpcionIncorrecta("Perro"));
        PuntajeParcial puntosEsperados = new PuntajeParcial(1);

        // Act
        PuntajeParcial puntosObtenidos = p.responder( new Respuesta("Aguila"), new Respuesta("Pato") );

        // Assert
        assertEquals( puntosObtenidos, puntosEsperados );
    }

    @Test
    public void testMCRespuestaIncompleta() {
        // Arrange
        PreguntaMC p = new PreguntaMC("Animal alado?", "CIENCIAS", "No se tiene un solo registro de un perro con alas",
                new OpcionCorrecta("Pato"),
                new OpcionCorrecta("Aguila"),
                new OpcionIncorrecta("Perro"));
        PuntajeParcial puntosEsperados = new PuntajeParcial(0);

        // Act
        PuntajeParcial puntosObtenidos = p.responder( new Respuesta("Aguila"));

        // Assert
        assertEquals( puntosObtenidos, puntosEsperados );
    }

    @Test
    public void testMCRespuestaIncorrectaSola() {
        // Arrange
        PreguntaMC p = new PreguntaMC("Animal alado?", "CIENCIAS", "No se tiene un solo registro de un perro con alas",
                new OpcionCorrecta("Pato"),
                new OpcionCorrecta("Aguila"),
                new OpcionIncorrecta("Perro"));
        PuntajeParcial puntosEsperados = new PuntajeParcial(0);

        // Act
        PuntajeParcial puntosObtenidos = p.responder( new Respuesta("Perro"));

        // Assert
        assertEquals( puntosObtenidos, puntosEsperados );
    }
    @Test
    public void testMCRespuestaIncorrecta() {
        // Arrange
        PreguntaMC p = new PreguntaMC("Animal alado?","CIENCIAS", "No se tiene un solo registro de un perro con alas",
                new OpcionCorrecta("Pato"),
                new OpcionCorrecta("Aguila"),
                new OpcionIncorrecta("Perro"));
        PuntajeParcial puntosEsperados = new PuntajeParcial(0);

        // Act
        PuntajeParcial puntosObtenidos = p.responder( new Respuesta("Aguila"), new Respuesta("Perro"));

        // Assert
        assertEquals( puntosObtenidos, puntosEsperados );
    }

    @Test
    public void testMCPenalidadRespuestaCorrecta() {
        // Arrange
        PreguntaMCPenalidad p = new PreguntaMCPenalidad("Animal alado?","CIENCIAS", "No se tiene un solo registro de un perro con alas",
                new OpcionCorrecta("Pato"),
                new OpcionCorrecta("Aguila"),
                new OpcionIncorrecta("Perro"));
        PuntajeParcial puntosEsperados = new PuntajeParcial(2);

        // Act
        PuntajeParcial puntosObtenidos = p.responder( new Respuesta("Aguila"), new Respuesta("Pato") );

        // Assert
        assertEquals( puntosObtenidos, puntosEsperados );
    }

    @Test
    public void testMCPenalidadRespuestaIncompleta() {
        // Arrange
        PreguntaMCPenalidad p = new PreguntaMCPenalidad("Animal alado?","CIENCIAS", "No se tiene un solo registro de un perro con alas",
                new OpcionCorrecta("Pato"),
                new OpcionCorrecta("Aguila"),
                new OpcionIncorrecta("Perro"));
        PuntajeParcial puntosEsperados = new PuntajeParcial(1);

        // Act
        PuntajeParcial puntosObtenidos = p.responder( new Respuesta("Aguila"));

        // Assert
        assertEquals( puntosObtenidos, puntosEsperados );
    }

    @Test
    public void testMCPenalidadRespuestaIncorrecta() {
        // Arrange
        PreguntaMCPenalidad p = new PreguntaMCPenalidad("Animal alado?","CIENCIAS", "No se tiene un solo registro de un perro con alas",
                new OpcionCorrecta("Pato"),
                new OpcionCorrecta("Aguila"),
                new OpcionIncorrecta("Perro"));
        PuntajeParcial puntosEsperados = new PuntajeParcial(0);

        // Act
        PuntajeParcial puntosObtenidos = p.responder( new Respuesta("Aguila"), new Respuesta("Perro"));

        // Assert
        assertEquals( puntosObtenidos, puntosEsperados );
    }
    @Test
    public void testMCPenalidad2RespuestasIncorrecta() {
        // Arrange
        PreguntaMCPenalidad p = new PreguntaMCPenalidad("Animal alado?","CIENCIAS", "No se tiene un solo registro de un perro con alas",
                new OpcionCorrecta("Pato"),
                new OpcionIncorrecta("Vaca"),
                new OpcionIncorrecta("Perro"));
        PuntajeParcial puntosEsperados = new PuntajeParcial(-2);

        // Act
        PuntajeParcial puntosObtenidos = p.responder( new Respuesta("Vaca"), new Respuesta("Perro"));

        // Assert
        assertEquals( puntosObtenidos, puntosEsperados );
    }
}



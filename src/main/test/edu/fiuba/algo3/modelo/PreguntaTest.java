package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Preguntas.*;
import edu.fiuba.algo3.modelo.Preguntas.Grupo;
import edu.fiuba.algo3.modelo.Preguntas.Opcion;
import edu.fiuba.algo3.modelo.Preguntas.OpcionCorrecta;
import edu.fiuba.algo3.modelo.Preguntas.OpcionIncorrecta;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class PreguntaTest {

    @Test
    public void test01obtenerTextoPreguntaEspecifica(){
        //Arrange
        PreguntaMC pregunta = new PreguntaMC("Qué significa ADN?", "CIENCIAS", "say no more...",
                new OpcionIncorrecta("Acido Dehidro Nitrogenado"),
                new OpcionCorrecta("Acido Desoxi Ribonucléico"),
                new OpcionIncorrecta("Antes de Navidad"),
                new OpcionIncorrecta("Acid Desoxiribonucleic"));

        String textoEsperado = "Qué significa ADN?";
        //Act
        String textoObtenido = pregunta.getPregunta();

        //Assert
        assertEquals(textoEsperado, textoObtenido);
    }

    @Test
    public void test02obtenerOpcionesPreguntaMC(){
        //Arrange
        PreguntaMC pregunta = new PreguntaMC("Qué significa ADN?", "CIENCIAS", "say no more...",
                new OpcionIncorrecta("Acido Dehidro Nitrogenado"),
                new OpcionCorrecta("Acido Desoxi Ribonucléico"),
                new OpcionIncorrecta("Antes de Navidad"),
                new OpcionIncorrecta("Acid Desoxiribonucleic"));

        List<String> opcionesEsperadas = new LinkedList<String>();
        opcionesEsperadas.add("Acido Dehidro Nitrogenado");
        opcionesEsperadas.add("Acido Desoxi Ribonucléico");
        opcionesEsperadas.add("Antes de Navidad");
        opcionesEsperadas.add("Acid Desoxiribonucleic");
        //Act
        List<String> opcionesObtenidas = pregunta.getOpciones().stream().map(op -> op.getOpcion()).collect(Collectors.toList());

        //Assert
        assertIterableEquals(opcionesEsperadas, opcionesObtenidas);
    }

    @Test
    public void test03obtenerOpcionesPreguntaGC(){
        //Arrange
        PreguntaGC pregunta = new PreguntaGC("Asigne las siguientes leyendas del deporte nacional a disciplina grupales (Fútbol, Básquet, Voley, Rugby,) o individuales (atletismo, tenis, artes marciales, ajedrez, etc):", "DEPORTES", "say no more...",
                new Grupo(new Opcion("Lio Messi"), new Opcion("Manu Ginóbili"), new Opcion("Hugo Conte")),
                new Grupo(new Opcion("Juan Martín del Potro"), new Opcion("Miguel Najdorf"), new Opcion("José Meolans")));

        List<String> opcionesEsperadas = new LinkedList<String>();
        opcionesEsperadas.add("Lio Messi");
        opcionesEsperadas.add("Manu Ginóbili");
        opcionesEsperadas.add("Hugo Conte");
        opcionesEsperadas.add("Juan Martín del Potro");
        opcionesEsperadas.add("Miguel Najdorf");
        opcionesEsperadas.add("José Meolans");
        //Act
        List<String> opcionesObtenidas = pregunta.getOpciones().stream().map(op -> op.getOpcion()).collect(Collectors.toList());

        //Assert
        assertIterableEquals(opcionesEsperadas, opcionesObtenidas);
    }

    @Test
    public void test04obtenerOpcionesPreguntaMCParcial(){
        //Arrange
        PreguntaMCParcial pregunta = new PreguntaMCParcial("¿Qué películas fueron dirigidas por el cineasta estadounidense Francis Ford Coppola?",
                "ARTE",
                "Coppola dirigió El Padrino y El Gran Gatsby en su version original (Sin Di Caprio)",
                new OpcionCorrecta("El Gran Gatsby"),
                new OpcionIncorrecta("Los Simuladores"),
                new OpcionIncorrecta("Scarface"),
                new OpcionIncorrecta("Blade Runner"),
                new OpcionCorrecta("El Padrino"));

        List<String> opcionesEsperadas = new LinkedList<String>();
        opcionesEsperadas.add("El Gran Gatsby");
        opcionesEsperadas.add("Los Simuladores");
        opcionesEsperadas.add("Scarface");
        opcionesEsperadas.add("Blade Runner");
        opcionesEsperadas.add("El Padrino");
        //Act
        List<String> opcionesObtenidas = pregunta.getOpciones().stream().map(op -> op.getOpcion()).collect(Collectors.toList());

        //Assert
        assertIterableEquals(opcionesEsperadas, opcionesObtenidas);
    }

    @Test
    public void test05obtenerOpcionesPreguntaMCPenalidad(){
        //Arrange
        PreguntaMCPenalidad pregunta = new PreguntaMCPenalidad("¿Qué películas fueron dirigidas por el cineasta estadounidense Francis Ford Coppola?",
                "ARTE",
                "Coppola dirigió El Padrino y El Gran Gatsby en su version original (Sin Di Caprio)",
                new OpcionCorrecta("El Gran Gatsby"),
                new OpcionIncorrecta("Los Simuladores"),
                new OpcionIncorrecta("Scarface"),
                new OpcionIncorrecta("Blade Runner"),
                new OpcionCorrecta("El Padrino"));

        List<String> opcionesEsperadas = new LinkedList<String>();
        opcionesEsperadas.add("El Gran Gatsby");
        opcionesEsperadas.add("Los Simuladores");
        opcionesEsperadas.add("Scarface");
        opcionesEsperadas.add("Blade Runner");
        opcionesEsperadas.add("El Padrino");
        //Act
        List<String> opcionesObtenidas = pregunta.getOpciones().stream().map(op -> op.getOpcion()).collect(Collectors.toList());

        //Assert
        assertIterableEquals(opcionesEsperadas, opcionesObtenidas);
    }

    @Test
    public void test06obtenerOpcionesPreguntaVF(){
        //Arrange
        PreguntaVF pregunta = new PreguntaVF("Nueva Orleans en Luisiana, Estados Unidos, fue colonia española",
                "HISTORIA",
                "Desde 1762 hasta 1800 los territorios en torno al río Misisipi fueron españoles.",
                new OpcionCorrecta("Verdadero"),
                new OpcionIncorrecta("Falso"));

        List<String> opcionesEsperadas = new LinkedList<String>();
        opcionesEsperadas.add("Verdadero");
        opcionesEsperadas.add("Falso");
        //Act
        List<String> opcionesObtenidas = pregunta.getOpciones().stream().map(op -> op.getOpcion()).collect(Collectors.toList());

        //Assert
        assertIterableEquals(opcionesEsperadas, opcionesObtenidas);
    }

    @Test
    public void test07obtenerOpcionesPreguntaVFPenalidad(){
        //Arrange
        PreguntaVFPenalidad pregunta = new PreguntaVFPenalidad("Nueva Orleans en Luisiana, Estados Unidos, fue colonia española",
                "HISTORIA",
                "Desde 1762 hasta 1800 los territorios en torno al río Misisipi fueron españoles.",
                new OpcionCorrecta("Verdadero"),
                new OpcionIncorrecta("Falso"));

        List<String> opcionesEsperadas = new LinkedList<String>();
        opcionesEsperadas.add("Verdadero");
        opcionesEsperadas.add("Falso");
        //Act
        List<String> opcionesObtenidas = pregunta.getOpciones().stream().map(op -> op.getOpcion()).collect(Collectors.toList());

        //Assert
        assertIterableEquals(opcionesEsperadas, opcionesObtenidas);
    }

}

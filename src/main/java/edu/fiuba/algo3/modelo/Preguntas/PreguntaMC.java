package edu.fiuba.algo3.modelo.Preguntas;

import edu.fiuba.algo3.modelo.Puntuacion.Puntajes.PuntajeParcial;

import java.util.*;

public class PreguntaMC implements Pregunta{
    private String pregunta;
    private List<Opcion> opciones;
    private String tematica;
    private String textoRespuesta;

    public PreguntaMC(String pregunta, String tematica, String textoRespuesta, Opcion... opciones) {
        this.pregunta = pregunta;
        this.opciones = new ArrayList<Opcion>();
        Collections.addAll(this.opciones, opciones);
        this.tematica = tematica;
        this.textoRespuesta = textoRespuesta;
    }

    public String getTematica() {return this.tematica;}
    public String getTextoRespuesta() {return this.textoRespuesta;}

    @Override
    public String getPregunta() {
        return this.pregunta;
    }

    @Override
    public List<Opcion> getOpciones() {
        return opciones;
    }

    @Override
    public PuntajeParcial responder(Respuesta... respuestas) {

        //Si hay una respuesta incorrecta -> return 0

        Optional<Opcion> opcionIncorrectaSeleccionada = Arrays.stream(respuestas)
                .flatMap(r -> opciones.stream()
                        .filter(op -> op instanceof OpcionIncorrecta && op.equals(r)))
                .findAny();

        if (opcionIncorrectaSeleccionada.isPresent()) {
            return new PuntajeParcial(0);
        }

        //Si hay alguna opcion correcta sin seleccionar -> return 0

        Optional<Opcion> opcionSinSeleccionar = opciones.stream()
                .filter(op -> op instanceof OpcionCorrecta)
                .filter(op -> Arrays.stream(respuestas)
                        .noneMatch(op::equals))
                .findAny();

        if (opcionSinSeleccionar.isPresent()) {
            return new PuntajeParcial(0);
        }

        return new PuntajeParcial(1);
    }

    @Override
    public String getTipo() {
        return "Multiple Choice";
    }

}
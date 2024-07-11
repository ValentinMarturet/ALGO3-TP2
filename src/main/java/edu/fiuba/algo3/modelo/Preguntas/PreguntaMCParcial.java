package edu.fiuba.algo3.modelo.Preguntas;

import edu.fiuba.algo3.modelo.Puntuacion.Puntajes.PuntajeParcial;

import java.util.*;

public class PreguntaMCParcial implements Pregunta {
    private String pregunta;
    private List<Opcion> opciones;
    private String tematica;
    private String textoRespuesta;

    public PreguntaMCParcial(String pregunta, String tematica, String textoRespuesta, Opcion... opciones) {
        this.pregunta = pregunta;
        this.opciones = new ArrayList<Opcion>();
        Collections.addAll(this.opciones, opciones);
        this.tematica = tematica;
        this.textoRespuesta = textoRespuesta;
    }

    public PuntajeParcial responder(Respuesta... respuestas) {

        //Si hay una respuesta incorrecta -> return 0

        Optional<Opcion> opcionIncorrectaSeleccionada = Arrays.stream(respuestas)
                .flatMap(r -> opciones.stream()
                        .filter(op -> op instanceof OpcionIncorrecta && op.equals(r)))
                .findAny();

        if (opcionIncorrectaSeleccionada.isPresent()) {
            return new PuntajeParcial(0);
        }

        return Arrays.stream(respuestas)
                .flatMap(r -> opciones.stream().map(op -> op.puntuar(r)))
                .reduce(new PuntajeParcial(0), (p, valor) -> {
                    p.sumar(valor);
                    return p;
                });

    }

    @Override
    public String getTipo() {
        return "Multiple Choice Parcial";
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

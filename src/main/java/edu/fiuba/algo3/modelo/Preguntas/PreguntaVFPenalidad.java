package edu.fiuba.algo3.modelo.Preguntas;

import edu.fiuba.algo3.modelo.Puntuacion.Puntajes.PuntajeParcial;
import edu.fiuba.algo3.modelo.Respuestas.Opcion;
import edu.fiuba.algo3.modelo.Respuestas.OpcionCorrecta;
import edu.fiuba.algo3.modelo.Respuestas.OpcionIncorrecta;
import edu.fiuba.algo3.modelo.Respuestas.Respuesta;

import java.util.LinkedList;
import java.util.List;

public class PreguntaVFPenalidad implements Pregunta{
    private String pregunta;
    private OpcionCorrecta opcionCorrecta;
    private OpcionIncorrecta opcionIncorrecta;
    private String tematica;
    private String textoRespuesta;

    public PreguntaVFPenalidad(String pregunta, String tematica, String textoRespuesta, OpcionCorrecta opcionCorrecta, OpcionIncorrecta opcionIncorrecta) {
        this.pregunta = pregunta;
        this.opcionCorrecta = opcionCorrecta;
        this.opcionIncorrecta = opcionIncorrecta;
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
        opciones.add(opcionCorrecta);
        opciones.add(opcionIncorrecta);
        return opciones;
    }

    public PuntajeParcial responder(Respuesta... respuesta) {
        PuntajeParcial acumulador = new PuntajeParcial(0);

        acumulador.sumar(opcionCorrecta.puntuar(respuesta[0]));
        acumulador.sumar(opcionIncorrecta.puntuar(respuesta[0]));

        return acumulador;
    }

    @Override
    public String getTipo() {
        return "Verdadero/Falso Penalidad";
    }

    public String getTematica() {return this.tematica;}
    public String getTextoRespuesta() {return this.textoRespuesta;}
}

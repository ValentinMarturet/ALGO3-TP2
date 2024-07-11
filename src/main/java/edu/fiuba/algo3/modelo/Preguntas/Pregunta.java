package edu.fiuba.algo3.modelo.Preguntas;

import edu.fiuba.algo3.modelo.Puntuacion.Puntajes.PuntajeParcial;
import edu.fiuba.algo3.modelo.Respuestas.Opcion;
import edu.fiuba.algo3.modelo.Respuestas.Respuesta;

import java.util.List;

public interface Pregunta {
    public String getPregunta();
    public List<Opcion> getOpciones();
    public PuntajeParcial responder(Respuesta... respuestas);
    public String getTipo();
    public String getTematica();
    public String getTextoRespuesta();
}

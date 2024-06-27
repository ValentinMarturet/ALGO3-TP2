package edu.fiuba.algo3.modelo;

import java.util.List;

public interface Pregunta {
    public String getPregunta();
    public List<Opcion> getOpciones();
    public PuntajeParcial responder(Respuesta... respuestas);
    public String getTipo();
    public String getTematica();
    public String getTextoRespuesta();
}

package edu.fiuba.algo3.modelo.Fabricas;

import edu.fiuba.algo3.modelo.Preguntas.Pregunta;
import org.json.JSONObject;

public interface Fabrica{
    public Pregunta crearPregunta(JSONObject datos);
}
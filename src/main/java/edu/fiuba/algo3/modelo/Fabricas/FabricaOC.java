package edu.fiuba.algo3.modelo.Fabricas;

import edu.fiuba.algo3.modelo.Preguntas.Opcion;
import edu.fiuba.algo3.modelo.Preguntas.Pregunta;
import edu.fiuba.algo3.modelo.Preguntas.PreguntaOC;
import org.json.JSONObject;

import java.util.ArrayList;

public class FabricaOC implements Fabrica {
    @Override
    public Pregunta crearPregunta(JSONObject datos){
        String pregunta =  datos.getString("Pregunta");
        String respuesta = datos.getString("Respuesta");
        String[] listaRepuestas = respuesta.split(",");
        String tematica = datos.getString("Tema");
        String textoRepuesta = datos.getString("Texto respuesta");

        ArrayList<Opcion> opciones = new ArrayList<>();
        for(String resp: listaRepuestas){
            String opcionActual = datos.getString("Opcion "+resp);
            opciones.add(new Opcion(opcionActual));
        }

        Pregunta nuevaPregunta = new PreguntaOC(pregunta,tematica,textoRepuesta,opciones.toArray(new Opcion[0]));

        return nuevaPregunta;
    }
}
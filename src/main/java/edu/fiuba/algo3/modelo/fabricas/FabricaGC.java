package edu.fiuba.algo3.modelo.fabricas;

import edu.fiuba.algo3.modelo.*;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class FabricaGC implements Fabrica{
    @Override
    public Pregunta crearPregunta(JSONObject datos) {
        String pregunta = datos.getString("Pregunta");
        String respuesta = datos.getString("Respuesta");
        String tematica = datos.getString("Tema");
        String textoRepuesta = datos.getString("Texto respuesta");
        String nombreA = datos.getString("Grupo A");
        String nombreB = datos.getString("Grupo B");
        String datosA = "";
        String datosB = "";

        String[] partes = respuesta.split(";");

        for(String parte: partes){
            parte = parte.trim();
            if(parte.startsWith("A:")){
                datosA = parte.substring(2).trim();
            }else {
                datosB = parte.substring(2).trim();
            }
        }

        String[] datosGrupoA = datosA.split(",");
        String[] datosGrupoB = datosB.split(",");

        List<Opcion> opcionesA = new ArrayList<>();
        List<Opcion> opcionesB = new ArrayList<>();

        for(String opcionA: datosGrupoA){
            String opcionActual = datos.getString("Opcion "+opcionA);
            opcionesA.add(new Opcion(opcionActual));
        }
        for(String opcionB: datosGrupoB){
            String opcionActual = datos.getString("Opcion "+opcionB);
            opcionesB.add(new Opcion(opcionActual));
        }


        Grupo grupoA = new Grupo(nombreA, opcionesA.toArray(new Opcion[0]));
        Grupo grupoB = new Grupo(nombreB, opcionesB.toArray(new Opcion[0]));

        return new PreguntaGC(pregunta,tematica,textoRepuesta,grupoA,grupoB);
    }
}

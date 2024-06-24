package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.ArchivoInexistente;
import edu.fiuba.algo3.modelo.fabricas.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Lector {
    public ArrayList<Pregunta> cargarPreguntas() throws ArchivoInexistente {
        try{
            String content = new String(Files.readAllBytes(Paths.get("src/main/resources/preguntas.json")));
            ArrayList<Pregunta> preguntas = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(content);
            Fabrica fabrica = new FabricaOC();

            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String tipo = jsonObject.getString("Tipo");

                if(tipo.equals("Ordered Choice") || tipo.equals("Ordered choice")){
                    fabrica = new FabricaOC();
                }
                if(tipo.equals("Multiple Choice Simple")){
                    fabrica = new FabricaMC();
                }
                if(tipo.equals("Verdadero Falso") || tipo.equals("Verdadero Falso Simple")){
                    fabrica = new FabricaVF();
                }
                if(tipo.equals("Multiple Choice Puntaje Parcial")){
                    fabrica = new FabricaMCParcial();
                }
                if(tipo.equals("Verdadero Falso Penalidad")){
                    fabrica = new FabricaVFPenalidad();
                }
                if(tipo.equals("Multiple Choice Penalidad")){
                    fabrica = new FabricaMCPenalidad();
                }
                if(tipo.equals("Group Choice")){
                    fabrica = new FabricaGC();
                }
                Pregunta nuevaPregunta = fabrica.crearPregunta(jsonObject);
                preguntas.add(nuevaPregunta);
            }

            return preguntas;
        } catch (Exception e){
            throw new ArchivoInexistente("Archivo no encontrado");
        }
    }
}

package edu.fiuba.algo3.modelo.fabricas;

import edu.fiuba.algo3.modelo.Pregunta;
import edu.fiuba.algo3.modelo.PreguntaMCParcial;
import edu.fiuba.algo3.modelo.Opcion;
import edu.fiuba.algo3.modelo.OpcionCorrecta;
import edu.fiuba.algo3.modelo.OpcionIncorrecta;
import org.json.JSONObject;
import java.util.ArrayList;

public class FabricaMCParcial implements Fabrica {
    @Override
    public Pregunta crearPregunta(JSONObject datos) {
        String pregunta =  datos.getString("Pregunta");
        String respuesta = datos.getString("Respuesta");
        String[] listaDeRepuestas = respuesta.split(",");
        String tematica = datos.getString("Tema");
        String textoRepuesta = datos.getString("Texto respuesta");


        ArrayList<Opcion> opcionesTotales = new ArrayList<>();
        ArrayList<String> opcionesYaUsadas = new ArrayList<>();

        /*Añadir opciones correctas*/
        for(String s: listaDeRepuestas){
            String opcionActual = String.valueOf(datos.getString("Opcion "+s));
            opcionesTotales.add(new OpcionCorrecta(opcionActual));
            opcionesYaUsadas.add(s);
        }

        /*Añadir las opciones incorrectas*/
        for(int i=1;i<=5;i++){
            try {
                if (!opcionesYaUsadas.contains(String.valueOf(i))) {
                    String opcionActual = String.valueOf(datos.getString("Opcion " + i));
                    opcionesTotales.add(new OpcionIncorrecta(opcionActual));
                }
            }catch (Exception e){
            }
        }
        Opcion[] opciones = opcionesTotales.toArray(new Opcion[opcionesTotales.size()]);
        Pregunta nuevaPregunta = new PreguntaMCParcial(pregunta,tematica,textoRepuesta,opciones);

        return nuevaPregunta;
    }
}
package edu.fiuba.algo3.modelo.fabricas;

import edu.fiuba.algo3.modelo.Opcion;
import edu.fiuba.algo3.modelo.OpcionCorrecta;
import edu.fiuba.algo3.modelo.OpcionIncorrecta;
import edu.fiuba.algo3.modelo.Pregunta;
import edu.fiuba.algo3.modelo.PreguntaMC;
import org.json.JSONObject;
import java.util.ArrayList;


public class FabricaMC implements Fabrica {
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
            try{
                String opcionActual = datos.getString("Opcion "+s);
                opcionesTotales.add(new OpcionCorrecta(opcionActual));
                opcionesYaUsadas.add(s);
            }catch(Exception e){
                String opcionActual = String.valueOf(datos.getInt("Opcion "+s));
                opcionesTotales.add(new OpcionCorrecta(opcionActual));
                opcionesYaUsadas.add(s);
            }
        }

        /*Añadir las opciones incorrectas*/
        for(int i=1;i<=5;i++){
            try {
                try{
                    if (!opcionesYaUsadas.contains(String.valueOf(i))) {
                        String opcionActual = datos.getString("Opcion " + i);
                        opcionesTotales.add(new OpcionIncorrecta(opcionActual));
                    }
                }catch(Exception e){ /*JSONException*/
                    String opcionActual = String.valueOf(datos.getInt("Opcion " + i));
                    opcionesTotales.add(new OpcionIncorrecta(opcionActual));
                }

            }catch (Exception e){ /*JSONException*/
                System.out.println("No existe la opcion en "+pregunta);
            }
        }
        Opcion[] opciones = opcionesTotales.toArray(new Opcion[opcionesTotales.size()]);
        Pregunta nuevaPregunta = new PreguntaMC(pregunta,tematica,textoRepuesta,opciones);

        return nuevaPregunta;
    }
}
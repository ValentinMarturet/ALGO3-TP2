package edu.fiuba.algo3.modelo.AlgoHoot;

import edu.fiuba.algo3.modelo.Puntuacion.Modificadores.*;
import edu.fiuba.algo3.modelo.Puntuacion.Puntajes.PuntajeParcial;
import edu.fiuba.algo3.modelo.Puntuacion.Puntajes.PuntajeTotal;

public class Jugador {
    private String nombre;
    private PuntajeTotal puntosTotales;
    private ModificadorIndividual duplicador;
    private ModificadorIndividual triplicador;
    private ModificadorGlobal anulador;
    private ModificadorGlobal exclusividad;


    public Jugador(String nombre) {
        this.nombre = nombre;
        this.duplicador = new Duplicador();
        this.triplicador = new Triplicador();
        this.anulador = new Anulador(this);
        this.exclusividad = new Exclusividad();
        this.puntosTotales = new PuntajeTotal();
    }

    public boolean equals(Jugador j) {
        return this.nombre.equals(j.nombre);
    }

    public boolean tieneNombre(String j) {
        return this.nombre.equals(j);
    }

    public void gastar(ModificadorIndividual mod) {
        if (mod != null) {
            if (duplicador.getClass().equals(mod.getClass())) {
                duplicador = new ModificadorIndividualBase();
            } else if (triplicador.getClass().equals(mod.getClass())) {
                triplicador = new ModificadorIndividualBase();
            }
        }
    }

    public void gastar(ModificadorGlobal mod) {
        if (mod != null) {
            if (anulador.getClass().equals(mod.getClass())) {
                anulador = new ModificadorGlobalBase();
            } else if (exclusividad.getClass().equals(mod.getClass())) {
                exclusividad = new ModificadorGlobalBase();
            }
        }
    }

    public void sumarPuntos(PuntajeParcial puntos) {
        this.puntosTotales.agregar(puntos);
    }

    public int obtenerPuntaje() {
        return this.puntosTotales.obtenerPuntaje();
    }

    public void porCadaPoderClasico( OperacionPoderesClasicos operacion ) { operacion.operar(anulador,exclusividad);}

    public void porCadaPoderPenalidad( OperacionPoderesPenalidad operacion ) { operacion.operar(anulador,duplicador,triplicador);}

    public void hacerConNombreYPuntaje( OperacionNombrePuntaje operacion ) { operacion.operar(nombre,obtenerPuntaje()); }
}

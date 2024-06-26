package edu.fiuba.algo3.modelo;

public class Jugador {
    private String nombre;
    private PuntajeTotal puntosTotales;
    private ModificadorIndividual duplicador;
    private ModificadorIndividual triplicador;


    public Jugador(String nombre) {
        this.nombre = nombre;
        this.duplicador = new Duplicador();
        this.triplicador = new Triplicador();
        this.puntosTotales = new PuntajeTotal();
    }

    public boolean equals(Jugador j) {
        return this.nombre.equals(j.nombre);
    }

    public boolean tieneNombre(String j) {
        return this.nombre.equals(j);
    }

    public void gastar(ModificadorIndividual mod) {
        if (duplicador.equals(mod)) {
            duplicador = new ModificadorIndividualBase();
        } else if (triplicador.equals(mod)) {
            triplicador = new ModificadorIndividualBase();
        }
    }

    public void sumarPuntos(PuntajeParcial puntos) {
        this.puntosTotales.agregar(puntos);
    }

    public int obtenerPuntaje() {
        return this.puntosTotales.obtenerPuntaje();
    }

    public String obtenerNombre() {
        return this.nombre;
    }
}

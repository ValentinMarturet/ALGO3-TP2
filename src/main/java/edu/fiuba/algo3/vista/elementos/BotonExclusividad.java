package edu.fiuba.algo3.vista.elementos;

import edu.fiuba.algo3.modelo.AlgoHoot.Jugador;
import edu.fiuba.algo3.modelo.Puntuacion.Modificadores.Anulador;
import edu.fiuba.algo3.modelo.Puntuacion.Modificadores.Exclusividad;
import edu.fiuba.algo3.modelo.Puntuacion.Modificadores.ModificadorGlobal;
import javafx.scene.image.Image;

public class BotonExclusividad extends CustomToggleButton implements BotonPoderGlobal {
    private final ModificadorGlobal modificador;

    public BotonExclusividad(ModificadorGlobal mod) {
        super(new Image("file:"+System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/exclusividadOn.png"),
                new Image("file:"+System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/exclusividadOff.png"));
        this.modificador = mod;
        ModificadorGlobal tipoBoton = new Exclusividad();
        if (mod.getClass() != tipoBoton.getClass()) {this.disableProperty().set(true);}
    }

    public ModificadorGlobal obtenerModificador() {
        return this.isSelected() ? modificador : null;
    }
}
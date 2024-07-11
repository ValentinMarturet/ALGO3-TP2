package edu.fiuba.algo3.vista.elementos;

import edu.fiuba.algo3.modelo.AlgoHoot.Jugador;
import edu.fiuba.algo3.modelo.Puntuacion.Modificadores.Anulador;
import edu.fiuba.algo3.modelo.Puntuacion.Modificadores.ModificadorGlobal;
import javafx.scene.image.Image;

public class BotonAnulador extends CustomToggleButton implements BotonPoderGlobal {
    private final ModificadorGlobal modificador;

    public BotonAnulador(ModificadorGlobal mod) {
        super(new Image("file:"+System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/anuladorOn.png"),
                new Image("file:"+System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/anuladorOff.png"));
        this.modificador = mod;
        ModificadorGlobal tipoBoton = new Anulador(new Jugador(""));
        if (mod.getClass() != tipoBoton.getClass()) {this.disableProperty().set(true);}
    }

    public ModificadorGlobal obtenerModificador() {
        return this.isSelected() ? modificador : null;
    }
}
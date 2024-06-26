package edu.fiuba.algo3.vista.elementos;

import edu.fiuba.algo3.modelo.Exclusividad;
import edu.fiuba.algo3.modelo.ModificadorGlobal;
import javafx.scene.image.Image;

public class BotonExclusividad extends CustomToggleButton implements BotonPoderGlobal {
    private final ModificadorGlobal modificador;

    public BotonExclusividad(ModificadorGlobal mod) {
        super(new Image("file:"+System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/exclusividadOn.png"),
                new Image("file:"+System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/exclusividadOff.png"));
        this.modificador = mod;
        if (mod.getClass() != Exclusividad.class) {this.disableProperty().set(true);}
    }

    public ModificadorGlobal obtenerModificador() {
        return this.isSelected() ? modificador : null;
    }
}
package edu.fiuba.algo3.vista.elementos;

import edu.fiuba.algo3.modelo.Duplicador;
import edu.fiuba.algo3.modelo.ModificadorIndividual;
import javafx.scene.image.Image;

public class BotonDuplicador extends CustomToggleButton implements BotonPoderIndividual {
    private final ModificadorIndividual modificador;

    public BotonDuplicador(ModificadorIndividual mod) {
        super(new Image("file:"+System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/duplicadorOn.png"),
                new Image("file:"+System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/duplicadorOff.png"));
        this.modificador = mod;
        if (mod.getClass() != Duplicador.class) {this.disableProperty().set(true);}
    }

    public ModificadorIndividual obtenerModificador() {
        return this.isSelected() ? modificador : null;
    }
}
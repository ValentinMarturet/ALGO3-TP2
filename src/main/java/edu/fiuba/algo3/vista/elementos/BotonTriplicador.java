package edu.fiuba.algo3.vista.elementos;

import edu.fiuba.algo3.modelo.ModificadorIndividual;
import edu.fiuba.algo3.modelo.Triplicador;
import javafx.scene.image.Image;

public class BotonTriplicador extends CustomToggleButton implements BotonPoderIndividual {
    private final ModificadorIndividual modificador;

    public BotonTriplicador(ModificadorIndividual mod) {
        super(new Image("file:"+System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/triplicadorOn.png"),
                new Image("file:"+System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/triplicadorOff.png"));
        this.modificador = mod;
        if (mod.getClass() != Triplicador.class) {this.disableProperty().set(true);}
    }

    public ModificadorIndividual obtenerModificador() {
        return this.isSelected() ? modificador : null;
    }
}
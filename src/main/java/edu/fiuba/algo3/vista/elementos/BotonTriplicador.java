package edu.fiuba.algo3.vista.elementos;

import edu.fiuba.algo3.modelo.Puntuacion.Modificadores.Duplicador;
import edu.fiuba.algo3.modelo.Puntuacion.Modificadores.ModificadorIndividual;
import edu.fiuba.algo3.modelo.Puntuacion.Modificadores.Triplicador;
import javafx.scene.image.Image;

public class BotonTriplicador extends CustomToggleButton implements BotonPoderIndividual {
    private final ModificadorIndividual modificador;

    public BotonTriplicador(ModificadorIndividual mod) {
        super(new Image("file:"+System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/triplicadorOn.png"),
                new Image("file:"+System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/triplicadorOff.png"));
        this.modificador = mod;
        ModificadorIndividual tipoBoton = new Triplicador();
        if (mod.getClass() != tipoBoton.getClass()) {this.disableProperty().set(true);}
    }

    public ModificadorIndividual obtenerModificador() {
        return this.isSelected() ? modificador : null;
    }
}
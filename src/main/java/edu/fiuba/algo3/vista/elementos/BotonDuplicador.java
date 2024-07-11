package edu.fiuba.algo3.vista.elementos;

import edu.fiuba.algo3.modelo.AlgoHoot.Jugador;
import edu.fiuba.algo3.modelo.Puntuacion.Modificadores.Anulador;
import edu.fiuba.algo3.modelo.Puntuacion.Modificadores.Duplicador;
import edu.fiuba.algo3.modelo.Puntuacion.Modificadores.ModificadorGlobal;
import edu.fiuba.algo3.modelo.Puntuacion.Modificadores.ModificadorIndividual;
import javafx.scene.image.Image;

public class BotonDuplicador extends CustomToggleButton implements BotonPoderIndividual {
    private final ModificadorIndividual modificador;

    public BotonDuplicador(ModificadorIndividual mod) {
        super(new Image("file:"+System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/duplicadorOn.png"),
                new Image("file:"+System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/duplicadorOff.png"));
        this.modificador = mod;
        ModificadorIndividual tipoBoton = new Duplicador();
        if (mod.getClass() != tipoBoton.getClass()) {this.disableProperty().set(true);}
    }

    public ModificadorIndividual obtenerModificador() {
        return this.isSelected() ? modificador : null;
    }
}
package edu.fiuba.algo3.vista.elementos;

import edu.fiuba.algo3.modelo.Jugador;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public abstract class ConjuntoPoderes extends VBox {
    protected List<CustomToggleButton> botonesPoderes;
    public ConjuntoPoderes() {
        botonesPoderes = new ArrayList<>();
        setAlignment(Pos.CENTER);
        setPadding(new Insets(40,0,0,0));
        setSpacing(20);
    }

    public abstract void reestablecer(Jugador jugador);

    protected void actualizarBotones() {
        this.getChildren().removeAll(this.getChildren());
        this.getChildren().addAll(botonesPoderes);
    }
}

package edu.fiuba.algo3.vista.elementos;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import static java.lang.Math.floor;

public class LabelJugadorConPuntaje extends FlowPane {

    private Label nombreLabel;
    private Label puntajeLabel;

    public LabelJugadorConPuntaje(String nombre, int puntaje, double ancho, double alto) {
        super();
        this.setPrefSize(ancho, alto);
        nombreLabel = new Label(nombre);
        nombreLabel.setPrefSize( floor(ancho*3/4), alto);
        nombreLabel.setStyle("-fx-text-fill: black;" +
                "-fx-font-family: 'Comic Sans MS';" +
                "-fx-text-alignment: center;" +
                "-fx-font-size: 32;" +
                "-fx-padding: "+ ancho*1/16+"px");
        this.getChildren().add(nombreLabel);
        puntajeLabel = new Label(String.valueOf(puntaje));
        puntajeLabel.setPrefSize(floor(ancho*1/4), alto);
        puntajeLabel.setStyle("-fx-text-fill: black;" +
                "-fx-font-family: 'Comic Sans MS';" +
                "-fx-text-alignment: center;" +
                "-fx-font-size: 32;");
        puntajeLabel.setAlignment(Pos.CENTER);
        this.getChildren().add(puntajeLabel);
    }

    public void resaltar() {
        this.setStyle(
                "-fx-background-color: deepskyblue;"
        );
    }

    public void desResaltar() {
        this.setStyle(
                "-fx-background-color: white;"
        );
    }

    public String getNombre() {
        return nombreLabel.getText();
    }
}

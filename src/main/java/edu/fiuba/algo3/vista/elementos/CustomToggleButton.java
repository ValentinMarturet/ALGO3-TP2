package edu.fiuba.algo3.vista.elementos;

import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

import java.io.File;

public class CustomToggleButton extends ToggleButton {
    private final ImageView graficoON;
    private final ImageView graficoOFF;
    private final AudioClip sonidoON;
    private final AudioClip sonidoOFF;

    public CustomToggleButton(Image imagenOn, Image imagenOff) {
        super();
        this.graficoON = new ImageView(imagenOn);
        this.graficoOFF = new ImageView(imagenOff);
        File archivoSonidoON = new File(System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/sonidos/poderOn.wav");
        Media mediaON = new Media(archivoSonidoON.toURI().toString());
        sonidoON = new AudioClip(mediaON.getSource());
        sonidoON.setVolume(0.25);
        File archivoSonidoOFF = new File(System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/sonidos/poderOff.wav");
        Media mediaOFF = new Media(archivoSonidoOFF.toURI().toString());
        sonidoOFF = new AudioClip(mediaOFF.getSource());
        sonidoOFF.setVolume(0.25);
        this.setGraphic(graficoOFF);
        this.setStyle("-fx-background-color: transparent;");

        this.setOnMouseClicked(e -> {
            reproducirSonido();
            actualizarGraficos();
        });
    }

    private void reproducirSonido() {
        if (this.isSelected()) {
            sonidoON.play();
        } else {
            sonidoOFF.play();
        }
    }

    public void actualizarGraficos() {
        if (this.isSelected()) {
            this.setGraphic(graficoON);
        } else {
            this.setGraphic(graficoOFF);
        }
    }
}
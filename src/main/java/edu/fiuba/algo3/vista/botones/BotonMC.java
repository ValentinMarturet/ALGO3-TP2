package edu.fiuba.algo3.vista.botones;

import javafx.scene.control.ToggleButton;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

import java.io.File;

public class BotonMC extends ToggleButton {
    private AudioClip sonido;
    public BotonMC(String texto) {
        super(texto);
        File archivoSonido = new File(System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/sonidos/seleccionar.wav");
        Media media = new Media(archivoSonido.toURI().toString());
        this.sonido = new AudioClip(media.getSource());
        this.sonido.setVolume(0.2);
        this.setOnMouseClicked(e -> {
            this.sonido.play();
        });
        this.getStyleClass().add("custom-toggle-button");
        this.setWrapText(true);
    }

}

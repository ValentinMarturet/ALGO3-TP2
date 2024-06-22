package edu.fiuba.algo3.vista.botones;

import edu.fiuba.algo3.modelo.*;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

import java.io.File;

public class BotonPoder extends ToggleButton {
    private ImageView graficoON;
    private ImageView graficoOFF;
    private AudioClip sonidoON;
    private AudioClip sonidoOFF;
    private String tipo;

    public BotonPoder(String tipo) {
        super();
        this.tipo = tipo;
        this.graficoON = new ImageView(new Image("file:"+System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/"+tipo+"On.png"));
        this.graficoOFF = new ImageView(new Image("file:"+System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/"+tipo+"Off.png"));

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
        this.setOnMouseClicked(e -> this.pulsar());
    }

    private void pulsar() {
        if (this.isSelected()) {
            this.setGraphic(graficoON);
            sonidoON.play();
        } else {
            this.setGraphic(graficoOFF);
            sonidoOFF.play();
        }
    }

    public Object obtenerModificador(Jugador j) {
        switch (tipo) {
            case "anulador":
                return isSelected() ? new Anulador(j) : new ModificadorGlobalBase();
            case "exclusividad":
                return isSelected() ? new Exclusividad() : new ModificadorGlobalBase();
            case "duplicador":
                return isSelected() ? new Duplicador() : new ModificadorIndividualBase();
            default:
                return isSelected() ? new Triplicador() : new ModificadorIndividualBase();
        }
    }
}
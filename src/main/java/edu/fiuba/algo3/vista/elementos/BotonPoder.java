package edu.fiuba.algo3.vista.elementos;

import edu.fiuba.algo3.modelo.*;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

import java.io.File;

public class BotonPoder extends ToggleButton {
    private final ImageView graficoON;
    private final ImageView graficoOFF;
    private final AudioClip sonidoON;
    private final AudioClip sonidoOFF;
    private final String tipo;

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

    public Object obtenerModificador(Jugador j) {
        switch (tipo) {
            case "anulador":
                return new Anulador(j);
            case "exclusividad":
                return new Exclusividad();
            case "duplicador":
                return new Duplicador();
            default:
                return new Triplicador();
        }
    }

    public boolean esDelTipo(ModificadorIndividual tipo) {
        return true;
    }

    public boolean esDelTipo(ModificadorGlobal tipo) {
        return true;
    }
}
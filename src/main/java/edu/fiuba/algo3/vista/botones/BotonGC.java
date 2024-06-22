package edu.fiuba.algo3.vista.botones;

import javafx.scene.control.ToggleButton;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

import java.io.File;

public class BotonGC extends ToggleButton {
        private int grupo; // 0-sin seleccionar 1-rosa 2-verde
        private AudioClip sonido;

        public BotonGC(String text) {
            super(text);
            this.getStyleClass().add("GP-toggle-button");
            this.grupo = 0;
            File archivoSonidoSeleccionar = new File(System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/sonidos/seleccionar.wav");
            Media mediaSeleccionar = new Media(archivoSonidoSeleccionar.toURI().toString());
            this.sonido  = new AudioClip(mediaSeleccionar.getSource());
            this.sonido.setVolume(0.2);
            this.setOnMouseClicked(e -> {
                this.sonido.play();
                actualizarGrafico();
            });


        }

        private void actualizarGrafico() {
            if (this.isSelected()) {
                this.getStyleClass().remove("selected-color1");
                this.getStyleClass().add("selected-color2");
                this.grupo = 2;

            } else {
                this.getStyleClass().remove("selected-color2");
                this.getStyleClass().add("selected-color1");
                this.grupo = 1;

            }
        }

         public int getGrupoSeleccionado() {
            return grupo;
         }
    }


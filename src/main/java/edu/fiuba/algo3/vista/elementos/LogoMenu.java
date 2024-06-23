package edu.fiuba.algo3.vista.elementos;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.time.LocalTime;
import java.util.Random;

public class LogoMenu extends ImageView {
    private Image img;
    private double stageWidth;
    private double stageHeight;
    private double dx;
    private double dy;
    public LogoMenu(Image imagen, double stageWidth, double stageHeight) {
        super(imagen);
        this.img = imagen;
        this.stageWidth = stageWidth;
        this.stageHeight = stageHeight;
        Random r = new Random(LocalTime.now().getNano()^2);
        setX(r.nextInt((int) (1280 - img.getWidth())));
        setY(r.nextInt((int) (720 - img.getHeight())));
        dx = 1 + r.nextDouble()*2;
        dy = dx;
        if (r.nextBoolean()) dx = -dx;
        if (r.nextBoolean()) dy = -dy;
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), e -> moverImagen()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void moverImagen() {

        setX(getX() + dx);
        setY(getY() + dy);

        // Detectar colisiones con los bordes y cambiar la dirección
        if (getX() <= 0 || getX() >= stageWidth - img.getWidth()) {
            dx *= -1;
        }
        if (getY() <= 0 || getY() >= stageHeight - img.getHeight()) {
            dy *= -1;
        }
    }
}

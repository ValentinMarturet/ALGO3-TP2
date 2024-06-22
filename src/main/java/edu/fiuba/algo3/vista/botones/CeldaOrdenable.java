package edu.fiuba.algo3.vista.botones;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

import java.io.File;

public class CeldaOrdenable extends ListCell<String> {
    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText(item);
            setGraphic(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
            setAlignment(Pos.CENTER); // Center text alignment
        }
    }

    public CeldaOrdenable() {
        File archivoAgarrar = new File(System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/sonidos/agarrar.wav");
        Media mediaAgarrar = new Media(archivoAgarrar.toURI().toString());
        AudioClip sonidoAgarrar = new AudioClip(mediaAgarrar.getSource());
        File archivoSoltar = new File(System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/sonidos/soltar.wav");
        Media mediaSoltar = new Media(archivoSoltar.toURI().toString());
        AudioClip sonidoSoltar = new AudioClip(mediaSoltar.getSource());
        ListCell<String> thisCell = this;

        setContentDisplay(ContentDisplay.TEXT_ONLY);
        setAlignment(Pos.CENTER);

        setOnDragDetected(event -> {
            if (getItem() == null) {
                return;
            }
            sonidoAgarrar.play();
            Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(getItem());
            dragboard.setContent(content);

            event.consume();
        });

        setOnDragOver(event -> {
            if (event.getGestureSource() != thisCell && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }

            event.consume();
        });

        setOnDragEntered(event -> {
            if (event.getGestureSource() != thisCell && event.getDragboard().hasString()) {
                setOpacity(0.3);
            }
        });

        setOnDragExited(event -> {
            if (event.getGestureSource() != thisCell && event.getDragboard().hasString()) {
                setOpacity(1);
            }
        });

        setOnDragDropped(event -> {
            if (getItem() == null) {
                return;
            }
            sonidoSoltar.play();
            Dragboard db = event.getDragboard();
            boolean success = false;

            if (db.hasString()) {
                ObservableList<String> items = getListView().getItems();
                int draggedIdx = items.indexOf(db.getString());
                int thisIdx = items.indexOf(getItem());

                items.set(draggedIdx, getItem());
                items.set(thisIdx, db.getString());

                success = true;
            }
            event.setDropCompleted(success);

            event.consume();
        });

        setOnDragDone(DragEvent::consume);
    }
}
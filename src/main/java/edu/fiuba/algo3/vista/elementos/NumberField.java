package edu.fiuba.algo3.vista.elementos;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class NumberField extends TextField {
    public NumberField() {
        super();
        TextField campo = this;
        // Agrega un ChangeListener para permitir solo números positivos
        this.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) { // Permite solo dígitos
                    campo.setText(newValue.replaceAll("[^\\d]", ""));
                } else if (newValue.length() > 3) { // Evita ceros innecesarios
                    campo.setText(oldValue);
                }
            }
        });
        this.setMaxWidth(115);
        this.setStyle("-fx-background-color: white;" +
                "-fx-border-width: 4px;" +
                "-fx-border-color: black;" +
                "-fx-font-family: 'Comic Sans MS';" +
                "-fx-font-size: 32;" +
                "-fx-text-alignment: center;");
    }

    public int obtenerNumero() {
        return Integer.parseInt(getText());
    }
}

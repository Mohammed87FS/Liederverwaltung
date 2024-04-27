package com.first.liederverwaltung;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class AddSongDialog extends Dialog<Lied> {
    public AddSongDialog() {
        setTitle("Lied hinzufügen");
        setHeaderText("Geben Sie hier die Daten ein, um ein neues Lied aufzunehmen");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField titleField = createTextField("Titel");
        TextField artistField = createTextField("Interpret");
        TextField albumField = createTextField("Album");
        TextField pathField = createTextField("Pfad");
        TextField yearField = createTextField("Erscheinungsjahr", true);

        grid.add(new Label("Titel:"), 0, 0);
        grid.add(titleField, 1, 0);
        grid.add(new Label("Interpret:"), 0, 1);
        grid.add(artistField, 1, 1);
        grid.add(new Label("Album:"), 0, 2);
        grid.add(albumField, 1, 2);
        grid.add(new Label("Pfad:"), 0, 3);
        grid.add(pathField, 1, 3);
        grid.add(new Label("Erscheinungsjahr:"), 0, 4);
        grid.add(yearField, 1, 4);

        getDialogPane().setContent(grid);
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                if (validateInputs(titleField, artistField, albumField, pathField, yearField)) {
                    String title = titleField.getText();
                    String artist = artistField.getText();
                    String album = albumField.getText();
                    String path = pathField.getText();
                    int year = Integer.parseInt(yearField.getText());
                    return new Lied(title, artist, album, path, year);
                }
            }
            return null;
        });
    }

    private TextField createTextField(String promptText) {
        return createTextField(promptText, false);
    }

    private TextField createTextField(String promptText, boolean isNumeric) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        if (isNumeric) {
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            });
        }
        return textField;
    }

    private boolean validateInputs(TextField... fields) {
        for (TextField field : fields) {
            if (field.getText().trim().isEmpty()) {
                showAlert("Validation Error", "All fields must be filled.");
                field.requestFocus();
                return false;
            }
        }
        return true;
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

package com.first.liederverwaltung;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        ListView<Lied> songListView = new ListView<>();
        SongController controller = new SongController(songListView);

        Button addSongButton = new Button("Add Song");
        Button removeSongButton = new Button("Remove Selected Song");

        addSongButton.setOnAction(e -> controller.showAddSongDialog());
        removeSongButton.setOnAction(e -> controller.removeSelectedSong());

        root.getChildren().addAll(songListView, addSongButton, removeSongButton);

        Scene scene = new Scene(root, 400, 500);
        primaryStage.setTitle("Liederverwaltung");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

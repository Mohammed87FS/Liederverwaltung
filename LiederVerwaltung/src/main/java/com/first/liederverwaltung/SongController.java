package com.first.liederverwaltung;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SongController {
    private ObservableList<Lied> songs = FXCollections.observableArrayList();
    private ListView<Lied> listView;
    private static String FILE_PATH = "songs.txt";

    public SongController(ListView<Lied> listView) {
        this.listView = listView;
        listView.setItems(songs);
        loadSongsFromFile();
    }

    public void addSong(Lied song) {
        songs.add(song);
        saveSongsToFile();
    }

    public void removeSelectedSong() {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            songs.remove(selectedIndex);
            saveSongsToFile();
        }
    }

    public void showAddSongDialog() {
        AddSongDialog dialog = new AddSongDialog();
        dialog.showAndWait().ifPresent(this::addSong);
    }

    private void saveSongsToFile() {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH))) {
            for (Lied song : songs) {
                writer.write(songToFileString(song));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadSongsFromFile() {
        if (Files.exists(Paths.get(FILE_PATH))) {
            try (BufferedReader reader = Files.newBufferedReader(Paths.get(FILE_PATH))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Lied song = fileStringToSong(line);
                    if (song != null) {
                        songs.add(song);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String songToFileString(Lied song) {
        return song.getTitle() + ";" + song.getArtist() + ";" + song.getAlbum() + ";" +
                song.getPath() + ";" + song.getYear();
    }

    private Lied fileStringToSong(String fileString) {
        String[] parts = fileString.split(";");
        if (parts.length == 5) {
            try {
                String title = parts[0];
                String artist = parts[1];
                String album = parts[2];
                String path = parts[3];
                int year = Integer.parseInt(parts[4]);
                return new Lied(title, artist, album, path, year);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
}

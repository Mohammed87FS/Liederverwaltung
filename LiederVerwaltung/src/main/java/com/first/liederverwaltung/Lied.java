package com.first.liederverwaltung;

public class Lied {
    private String title;
    private String artist;
    private String album;
    private String path;
    private int year;

    public Lied(String title, String artist, String album, String path, int year) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.path = path;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String getPath() {
        return path;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return title + " by " + artist + " from the album " + album + " (" + year + ")";
    }
}

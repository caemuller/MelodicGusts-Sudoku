package com.trabf.melodicgusts.Models.entities;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;

public class Music {
    private final File directory;
    private final File[] files;
    private final ArrayList<File> songs;
    private MediaPlayer mediaPlayer;

    public Music() {
        this.directory = new File("src/main/resources/Music");
        this.files = directory.listFiles();
        this.songs = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                songs.add(file);
            }
        }
    }

    public ArrayList<File> getSongs() {
        return songs;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void createMediaPlayer(Media media) {
        this.mediaPlayer = new MediaPlayer(media);
    }

}



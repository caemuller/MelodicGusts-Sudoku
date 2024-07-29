package com.trabf.melodicgusts.Models.entities;

import javafx.scene.media.Media;

public class Piece {
    private int idPiece;
    private Media media;

    public Piece() {
        this.idPiece = 0;
    }

    public int getIdPiece() {
        return idPiece;
    }

    public void setIdPiece(int id) {
        this.idPiece = id;
    }

    public Media getMedia() {return media;}

    public void createMedia(String path) {
        this.media = new Media(path);
    }
}
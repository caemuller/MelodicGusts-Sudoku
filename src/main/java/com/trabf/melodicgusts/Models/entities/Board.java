package com.trabf.melodicgusts.Models.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private int rows;
    private int columns;
    private Piece[][] pieces;
    private List<Integer> pairPieces = new ArrayList<>();
    private Music music;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.pieces = new Piece[rows][columns];
        this.music = new Music();
        createPieces();
    }

    public Piece[][] getPieces() {
        return pieces;
    }

    public void createPieces() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                pieces[i][j] = new Piece();
                randomPieces(i, j);
            }
        }
    }

    public void randomPieces(int i, int j) {
        Random rand = new Random();
        int cont=0;
        int flag=0;

        while (flag == 0) {
            int random = rand.nextInt(8);
            pairPieces.add(random);

            // conta quantas vezes o random esta na lista para formar os pares, só pode ter 2 numeros iguais na lista
            for (int k = 0; k < pairPieces.size(); k++) {
                if (pairPieces.get(k) == random) { // se esse numero ja esta na lista
                    cont++;
                }
            }

            if (cont > 2) { // quer dizer que tem mais de um par
                pairPieces.remove(pairPieces.size() - 1); //remove o ultimo numero que colocou na lista
            } else {
                flag = 1;
                pieces[i][j].setIdPiece(random);
                String path = music.getSongs().get(random).toURI().toString();
                pieces[i][j].createMedia(path);
            }
            cont = 0;
        }
    }

    public Music getMusic() {
        return music;
    }
}

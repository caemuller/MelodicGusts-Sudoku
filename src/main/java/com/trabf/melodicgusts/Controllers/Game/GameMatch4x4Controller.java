package com.trabf.melodicgusts.Controllers.Game;

import com.trabf.melodicgusts.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class GameMatch4x4Controller extends GameMatch implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.buttons = new Button[gridPane.getRowCount()][gridPane.getColumnCount()];
        createButtons();
        allButtons();
        confirm_btn.setOnAction(event -> comparisonPiece());  //faz a comparacao
    }

    @Override
    public void createButtons() {
        for (int i = 0; i < gridPane.getRowCount(); i++) {
            for (int j = 0; j < gridPane.getColumnCount(); j++) {
                buttons[i][j] = new Button();
                gridPane.add(buttons[i][j], j, i);
                buttons[i][j].setMinWidth(110.4);
                buttons[i][j].setMinHeight(86.4);
                buttons[i][j].setStyle("-fx-background-color: #bfb8b8");
                buttons[i][j].setText("?");
            }
        }
    }

    @Override
    public void stopMusic() {
     Model.getInstance().getBoard4x4().getMusic().getMediaPlayer().stop();
    }

    // executa musica da determinada peca
    @Override
    public void onMusic(int row, int col) {
        Media media = Model.getInstance().getBoard4x4().getPieces()[row][col].getMedia();
        Model.getInstance().getBoard4x4().getMusic().createMediaPlayer(media);
        m00_media.setMediaPlayer(Model.getInstance().getBoard4x4().getMusic().getMediaPlayer());

        Model.getInstance().getBoard4x4().getMusic().getMediaPlayer().play();
    }

    @Override
    protected int captureId(int index) {
        Button button = active_buttons.get(index);
        int row = rowButton(button);
        int col = colButton(button);
        // retorna piece Id para comparacao
        return Model.getInstance().getBoard4x4().getPieces()[row][col].getIdPiece();
    }
}
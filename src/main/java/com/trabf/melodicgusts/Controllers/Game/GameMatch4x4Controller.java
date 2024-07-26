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

public class GameMatch4x4Controller implements Initializable {
    public Button[][] buttons;
    public GridPane gridPane;
    public Button confirm_btn;
    public List<Button> active_buttons = new ArrayList<>();
    public Label accept_lbl;
    public Label error_lbl;
    public MediaView m00_media;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.buttons = new Button[gridPane.getRowCount()][gridPane.getColumnCount()];
        createButtons();
        allButtons();
        confirm_btn.setOnAction(event -> comparisonPiece());  //faz a comparacao
    }

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

    public void allButtons() {
        for (int i = 0; i < gridPane.getRowCount(); i++) {
            for (int j = 0; j < gridPane.getColumnCount(); j++) {
                int finalI = i;
                int finalJ = j;
                buttons[i][j].setOnAction(event -> onButton(buttons[finalI][finalJ], finalI, finalJ));
            }
        }
    }

    public void onButton(Button button, int row, int col) {
        accept_lbl.setText("");
        error_lbl.setText("");
        button.setDisable(true); //desabilita botao que foi clicado para nao poder clicar mais de uma vez
        addButton(button);
        openPiece(row, col);
    }

    //verifica se existe mais de um botao ativo e para sua musica pq nao pode ter mais de uma musica tocando ao mesmo tempo
    public void openPiece(int row, int col) {
        if(active_buttons.size() == 2) {
            stopMusic();
        }
        onMusic(row, col);
    }

    // executa musica da determinada peca
    public void onMusic(int row, int col) {
        Media media = Model.getInstance().getBoard4x4().getPieces()[row][col].getMedia();
        Model.getInstance().getBoard4x4().getMusic().createMediaPlayer(media);
        m00_media.setMediaPlayer(Model.getInstance().getBoard4x4().getMusic().getMediaPlayer());

        Model.getInstance().getBoard4x4().getMusic().getMediaPlayer().play();
    }

    public void addButton(Button button) {
        //adiciona o botao na lista de botoes ativos
        active_buttons.add(button);
        // verifica se existe mais de um par de botoes na lista, só pode ter 2 boteos na lista na comparacao
        if(active_buttons.size() == 3) { // se tiver 3 botoes no buffer
            removeButton(button);
        }
    }

    public void removeButton(Button button) {
        //habilita o botao que foi removido para poder ser novamente clicado
        active_buttons.get(1).setDisable(false);
        //remove o segundo botao da lista
        active_buttons.remove(1);
        // move o botao novo que estava no index 2 para a posicao do antigo index 1
        active_buttons.add(1, button);
        // remove o botao que tinha adicionado na lista na funcao adicionarButton() para nao ficar repetido na lista
        active_buttons.remove(2);
    }


    public void comparisonPiece() {
        if(active_buttons.size() == 1) {
            stopMusic();
            error_lbl.setText("Error: Não é possivel fazer a comparação com 1 peça");
            error_lbl.setStyle("-fx-text-fill: red; -fx-font-size: 1.3em; -fx-font-weight: bold");
            accept_lbl.setText("");
        } else if (active_buttons.isEmpty()) {
            error_lbl.setText("Error: Não é possivel fazer a comparação com nenhuma peça");
            error_lbl.setStyle("-fx-text-fill: red; -fx-font-size: 1.3em; -fx-font-weight: bold");
            accept_lbl.setText("");
        } else {
            stopMusic();

            int piece1_id = captureId(0);
            int piece2_id = captureId(1);

            if (piece1_id == piece2_id) {
                onAcert();
            } else {
                onError();
            }
            // limpa a lista de botes ativos para poder escolher outras peças
            active_buttons.clear();
        }
    }

    private int captureId(int index) {
        Button button = active_buttons.get(index);
        int row = rowButton(button);
        int col = colButton(button);
        // retorna piece Id para comparacao
        return Model.getInstance().getBoard4x4().getPieces()[row][col].getIdPiece();
    }

    public void onAcert() {
        // desabilita os botoes pq ja foram acertados
        active_buttons.get(0).setDisable(true);
        active_buttons.get(1).setDisable(true);
        active_buttons.get(0).setText("X");
        active_buttons.get(1).setText("X");
        accept_lbl.setText("Acertou !!!");
        accept_lbl.setStyle("-fx-text-fill: green; -fx-font-size: 1.3em; -fx-font-weight: bold");
    }

    public void onError() {
        // habilita os botoes para poderem ser clicados
        active_buttons.get(0).setDisable(false);
        active_buttons.get(1).setDisable(false);
        accept_lbl.setText("Errou !!!");
        accept_lbl.setStyle("-fx-text-fill: red; -fx-font-size: 1.3em; -fx-font-weight: bold");
    }

    public void stopMusic() {
        Model.getInstance().getBoard4x4().getMusic().getMediaPlayer().stop();
    }

    // pegar a posicao do button
    private int rowButton(Button button) {
        int row;
        Iterator<Object> list = button.getProperties().values().iterator();
        row = (int) list.next();
        row = (int) list.next();
        return row;
    }

    private int colButton(Button button) {
        int col;
        Iterator<Object> list = button.getProperties().values().iterator();
        col = (int) list.next();
        return col;
    }
}
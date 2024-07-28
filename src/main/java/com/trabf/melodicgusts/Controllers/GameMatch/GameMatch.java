package com.trabf.melodicgusts.Controllers.GameMatch;
import com.trabf.melodicgusts.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.media.MediaView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public abstract class GameMatch implements Initializable {
    public Button[][] buttons;
    public GridPane gridPane;
    public Button confirm_btn;
    public List<Button> active_buttons;
    public Label accept_lbl;
    public Label error_lbl;
    public Label score_lbl;
    public Label success_lbl;
    public MediaView m00_media;
    protected int score;
    protected int success_count;
    protected int PAIRS;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        constructor();
        createButtons();
        allButtons();
        scoreInitialize();
        // reset labels
        score_lbl.setText("" + score);
        success_lbl.setText(0 + "/" + PAIRS);
        confirm_btn.setOnAction(event -> comparisonPiece());  //faz a comparacao
    }

    public void constructor() {
        this.PAIRS = (gridPane.getRowCount() * gridPane.getColumnCount()) / 2;
        this.buttons = new Button[gridPane.getRowCount()][gridPane.getColumnCount()];
        this.active_buttons = new ArrayList<>();
        this.success_count = 0;
    }

    protected void scoreInitialize() {
    }

    protected void createButtons() {
        for (int i = 0; i < gridPane.getRowCount(); i++) {
            for (int j = 0; j < gridPane.getColumnCount(); j++) {
                buttons[i][j] = new Button();
                gridPane.add(buttons[i][j], j, i);
                buttons[i][j].setStyle("-fx-background-color: #bfb8b8");
                buttons[i][j].setText("?");
            }
        }
    }

    protected void allButtons() {
        for (int i = 0; i < gridPane.getRowCount(); i++) {
            for (int j = 0; j < gridPane.getColumnCount(); j++) {
                int finalI = i;
                int finalJ = j;
                // coloca um evento para cada botao do gridPane quando este botao for clicado
                buttons[i][j].setOnAction(event -> onButton(buttons[finalI][finalJ], finalI, finalJ));
            }
        }
    }

    // clicou em um botao do gridPane
    protected void onButton(Button button, int row, int col) {
        resetVariables();
        if (score == 0) {
            // mostra alerta que perdeu
            showFinalAlert("PERDEU");
            return;
        }
        button.setDisable(true); //desabilita botao que foi clicado para nao poder clicar mais de uma vez
        addButton(button);
        openPiece(row, col);
        // score do usuario
        score -= 1;
        score_lbl.setText("" + score);
        if(!error_lbl.getText().isEmpty()) {
            error_lbl.setText("");
        }
    }

    private void resetVariables() {
        // resetar variaveis, quando jogo iniciar na primeira partida ou posteriores
        if (Model.getInstance().isStartGame()) {
            // set startGame = false - pq iniciou o jogo
            Model.getInstance().setStartGame(false);
            // reset variaveis
            scoreInitialize();
            success_count = 0;
            // clear - para conseguir fazer as comparacoes da maneira certa
            active_buttons.clear();
        }
    }

    //verifica se existe mais de um botao ativo e para sua musica pq nao pode ter mais de uma musica tocando ao mesmo tempo
    protected void openPiece(int row, int col) {
        if(active_buttons.size() == 2) {
            stopMusic();
        }
        onMusic(row, col);
    }

    // executa musica da determinada peca
    protected void onMusic(int row, int col) {
    }

    protected void addButton(Button button) {
        //adiciona o botao na lista de botoes ativos
        active_buttons.add(button);
        // verifica se existe mais de um par de botoes na lista, só pode ter 2 boteos na lista na comparacao
        if(active_buttons.size() == 3) { // se tiver 3 botoes no buffer
            removeButton(button);
        }
    }

    protected void removeButton(Button button) {
        //habilita o botao que foi removido para poder ser novamente clicado
        active_buttons.get(1).setDisable(false);
        //remove o segundo botao da lista
        active_buttons.remove(1);
        // move o botao novo que estava no index 2 para a posicao do antigo index 1
        active_buttons.add(1, button);
        // remove o botao que tinha adicionado na lista na funcao adicionarButton() para nao ficar repetido na lista
        active_buttons.remove(2);
    }

    protected void comparisonPiece() {
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

    protected int captureId(int index) {
        return 0;
    }

    protected void onAcert() {
        score+=2;

        // acertou, atualiza os acertos da partida
        success_count++;
        success_lbl.setText(success_count + "/" + PAIRS);
        // desabilita os botoes pq ja foram acertados
        active_buttons.get(0).setDisable(true);
        active_buttons.get(1).setDisable(true);
        active_buttons.get(0).setText("X");
        active_buttons.get(1).setText("X");
        accept_lbl.setText("Acertou !!!");
        accept_lbl.setStyle("-fx-text-fill: green; -fx-font-size: 1.3em; -fx-font-weight: bold");

        // se ganhou o jogo
        if (success_count == PAIRS) {
            winner();
        }
    }

    protected void onError() {
        score-=1;
        // habilita os botoes para poderem ser clicados
        active_buttons.get(0).setDisable(false);
        active_buttons.get(1).setDisable(false);
        accept_lbl.setText("Errou !!!");
        accept_lbl.setStyle("-fx-text-fill: red; -fx-font-size: 1.3em; -fx-font-weight: bold");
    }

    private void showFinalAlert(String victoryOrDefeat) {
        // show alert
        Model.getInstance().getViewFactory().showFinalAlert(victoryOrDefeat);
    }

    private void winner() {
        // coloca o score da partida no score do user se for maior do que o que ele possui
        Model.getInstance().getUser().scoreProperty().set(score);
        // mostra alerta que venceu
        showFinalAlert("GANHOU");
        // salvar no arquivo
        Model.getInstance().getUser().saveScore();
    }

    protected void stopMusic() {
    }

    // pegar a posicao do button
    protected int rowButton(Button button) {
        int row;
        Iterator<Object> list = button.getProperties().values().iterator();
        row = (int) list.next();
        row = (int) list.next();
        return row;
    }

    protected int colButton(Button button) {
        int col;
        Iterator<Object> list = button.getProperties().values().iterator();
        col = (int) list.next();
        return col;
    }
}

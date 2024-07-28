package com.trabf.melodicgusts.Views;

import com.trabf.melodicgusts.Controllers.User.UserController;
import com.trabf.melodicgusts.Models.Model;
import com.trabf.melodicgusts.Models.entities.Board;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Optional;

public class ViewFactory {
    private final ObjectProperty<UserMenuOptions> userMenuOptions;
    private AnchorPane scoreView;
    private AnchorPane newGameBoardView;
    private AnchorPane newGameModalityView;
    private AnchorPane newGameCharacterView;
    private AnchorPane gameMatch4x4View;
    private AnchorPane gameMatch6x6View;

    public ViewFactory() {
        this.userMenuOptions = new SimpleObjectProperty<>();
    }

    public ObjectProperty<UserMenuOptions> getUserMenuOptions() {
        return userMenuOptions;
    }


    public AnchorPane getScoreView() {
        if (scoreView == null) {
            try {
                scoreView = new FXMLLoader(getClass().getResource("/Fxml/User/Score.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return scoreView;
    }

    public AnchorPane getNewGameBoardView() {
        if (newGameBoardView == null) {
            try {
                newGameBoardView = new FXMLLoader(getClass().getResource("/Fxml/NewGame/NewGameBoard.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return newGameBoardView;
    }

    public AnchorPane getNewGameModalityView() {
        if (newGameModalityView == null) {
            try {
                newGameModalityView = new FXMLLoader(getClass().getResource("/Fxml/NewGame/NewGameModality.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return newGameModalityView;
    }

    public AnchorPane getNewGameCharacterView() {
        if (newGameCharacterView == null) {
            try {
                newGameCharacterView = new FXMLLoader(getClass().getResource("/Fxml/NewGame/NewGameCharacter.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return newGameCharacterView;
    }

    public AnchorPane getGameMatch4x4View() {
        if (gameMatch4x4View == null) {
            try {
                gameMatch4x4View = new FXMLLoader(getClass().getResource("/Fxml/GameMatch/GameMatch4x4.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return gameMatch4x4View;
    }

    public AnchorPane getGameMatch6x6View() {
        if (gameMatch6x6View == null) {
            try {
                gameMatch6x6View = new FXMLLoader(getClass().getResource("/Fxml/GameMatch/GameMatch6x6.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return gameMatch6x6View;
    }

    // Alert Method
    public void showAlert(UserMenuOptions item) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Se você trocar de janela perderá seu jogo");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            resetGames();
            getUserMenuOptions().set(item);
        }
    }

    public void showAlertWinner() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Você GANHOU, deseja jogar novamente ?");
        Optional<ButtonType> result = alert.showAndWait();

        // mantendo no mesmo jogo ou saindo para outra pagina, deve resetar o jogo
        resetGames();

        if(result.isPresent() && result.get() == ButtonType.CANCEL) {
            Model.getInstance().getViewFactory().getUserMenuOptions().set(UserMenuOptions.SCORE);
        }
    }

    // Reset Method
    public void resetGames() {
        // se sendo apresentado o alerta em uma pagina de game 4x4
        if(Model.getInstance().getViewFactory().getUserMenuOptions().get() == UserMenuOptions.GAME_MATCH4X4){
            resetGameMatch(gameMatch4x4View, Model.getInstance().getBoard4x4(), 50);
        } else { // se o alerta nao esta em uma pagina ge game 4x4 ent é de um game 6x6
            resetGameMatch(gameMatch6x6View, Model.getInstance().getBoard6x6(), 100);
        }
    }

    public void resetGameMatch(AnchorPane gameMatchView, Board board, int score) {
        if (gameMatchView != null) {
            // starGame = true para reset labels
            Model.getInstance().setStartGame(true);
            // stopMusic - para nao continuar tocando a musica em uma tela sem ser o jogo
            if(board.getMusic().getMediaPlayer() != null){
                board.getMusic().getMediaPlayer().stop();
            }
            // random pieces
            board.getPairPieces().clear();
            for (int i = 0; i < board.getRows(); i++) {
                for (int j = 0; j < board.getColumns(); j++) {
                    board.randomPieces(i, j);
                }
            }
            // reset Disable
            GridPane grid = (GridPane) gameMatchView.getChildren().get(0);
            // i comeca por 1, pq o i=0 é o MediaView
            for (int i = 1; i < (grid.getRowCount() * grid.getColumnCount()) + 1; i++) {
                // permite clicar em todos botoes
                grid.getChildren().get(i).setDisable(false);
                // setText "?" em todos botoes
                Button button = (Button) grid.getChildren().get(i);
                button.setText("?");
            }
            // reset labels
            Label accept_lbl = (Label) gameMatchView.getChildren().get(2);
            accept_lbl.setText("");
            Label error_lbl = (Label) gameMatchView.getChildren().get(3);
            error_lbl.setText("");

            AnchorPane anchorPane = (AnchorPane) gameMatchView.getChildren().get(4);
            Label score_lbl = (Label) anchorPane.getChildren().get(0);
            score_lbl.setText("" + score);
            Label success_lbl = (Label) anchorPane.getChildren().get(1);
            success_lbl.setText(0 + "/" + (board.getRows() * board.getColumns()) / 2);
        }
    }

    // Show window
    public void showInitialWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Home.fxml"));
        createStage(loader);
    }

    public void showMenuWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/User/Menu.fxml"));
        UserController userController = new UserController();
        loader.setController(userController);
        createStage(loader);
    }

    // Utility Methods
    public void createStage(FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("MelodicGusts");
        stage.setResizable(false);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/icon.png"))));
        stage.show();
    }

    public void closeStage(Stage stage) {
        stage.close();
    }
}




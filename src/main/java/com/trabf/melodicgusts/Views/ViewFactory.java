package com.trabf.melodicgusts.Views;

import com.trabf.melodicgusts.Controllers.User.UserController;
import com.trabf.melodicgusts.Models.Model;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Optional;

public class ViewFactory {
    private final ObjectProperty<UserMenuOptions> userMenuOptions;
    private AnchorPane optionsView;
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


    public AnchorPane getOptionsView() {
        if (optionsView == null) {
            try {
                optionsView = new FXMLLoader(getClass().getResource("/Fxml/User/Options.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return optionsView;
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
            resetGameMatch4x4();
            getUserMenuOptions().set(item);
        }
    }

    // Reset Method
    public void resetGameMatch4x4() {
        if (gameMatch4x4View != null) {
            // random pieces
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    Model.getInstance().getBoard4x4().randomPieces(i, j);
                }
            }
            // reset Disable
            GridPane grid = (GridPane) gameMatch4x4View.getChildren().get(1);
            // i comeca por 1, pq o i=0 é o MediaView
            for (int i = 1; i < (grid.getRowCount() * grid.getColumnCount()); i++) {
                // permite clicar em todos botoes
                grid.getChildren().get(i).setDisable(false);
                // setText "?" em todos botoes
                Button button = (Button) grid.getChildren().get(i);
                button.setText("?");
            }
            // reset labels in GameMatch4x4
            Label accept_lbl = (Label) gameMatch4x4View.getChildren().get(3);
            accept_lbl.setText("");
            Label error_lbl = (Label) gameMatch4x4View.getChildren().get(4);
            error_lbl.setText("");
            Label score_lbl = (Label) gameMatch4x4View.getChildren().get(5);
            score_lbl.setText("50");
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
        stage.show();
    }

    public void closeStage(Stage stage) {
        stage.close();
    }
}




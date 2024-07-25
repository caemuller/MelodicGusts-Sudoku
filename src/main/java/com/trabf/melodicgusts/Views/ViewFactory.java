package com.trabf.melodicgusts.Views;

import com.trabf.melodicgusts.Controllers.User.UserController;
import com.trabf.melodicgusts.Models.Model;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class ViewFactory {
    private final ObjectProperty<UserMenuOptions> userMenuOptions;
    private AnchorPane optionsView;
    private AnchorPane newGameBoardView;
    private AnchorPane newGameModalityView;
    private AnchorPane newGameCharacterView;
    private AnchorPane newGameMatch4x4View;
    private AnchorPane newGameMatch6x6View;


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

    public AnchorPane getNewGameMatch4x4View() {
        if (Model.getInstance().isOnBoard4x4()) {
            if (newGameMatch4x4View == null) {
                try {
                    newGameMatch4x4View = new FXMLLoader(getClass().getResource("/Fxml/NewGame/NewGameMatch.fxml")).load();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return newGameMatch4x4View;
        }
        else {
            if (newGameMatch6x6View == null) {
                try {
                    newGameMatch6x6View = new FXMLLoader(getClass().getResource("/Fxml/NewGame/NewGameMatch6x6.fxml")).load();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return newGameMatch6x6View;
        }
    }

    // SHOW WINDOW

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




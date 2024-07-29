package com.trabf.melodicgusts.Controllers.User;

import com.trabf.melodicgusts.Models.Model;
import com.trabf.melodicgusts.Views.UserMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

// classe que controla os botoes da parte grafica
public class UserMenuController implements Initializable {
    public Button score_btn;
    public Button newgame_btn;
    public Button exit_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners() {
        score_btn.setOnAction(event -> onScore());
        newgame_btn.setOnAction(event -> onNewGame());
        exit_btn.setOnAction(event -> onExit());
    }

    private void onScore() {
        if (Model.getInstance().getViewFactory().getUserMenuOptions().get() == UserMenuOptions.GAME_MATCH4X4) {
            // mostra alerta
            Model.getInstance().getViewFactory().showAlert(UserMenuOptions.SCORE);
        }
        else if(Model.getInstance().getViewFactory().getUserMenuOptions().get() == UserMenuOptions.GAME_MATCH6X6){
            // mostra alerta
            Model.getInstance().getViewFactory().showAlert(UserMenuOptions.SCORE);
        }
        else {
            Model.getInstance().getViewFactory().getUserMenuOptions().set(UserMenuOptions.SCORE);
        }
    }

    private void onNewGame() {
        if (Model.getInstance().getViewFactory().getUserMenuOptions().get() == UserMenuOptions.GAME_MATCH4X4) {
            // mostra alerta
            Model.getInstance().getViewFactory().showAlert(UserMenuOptions.NEWGAME_CHARACTER);
        }
        else if(Model.getInstance().getViewFactory().getUserMenuOptions().get() == UserMenuOptions.GAME_MATCH6X6){
            // mostra alerta
            Model.getInstance().getViewFactory().showAlert(UserMenuOptions.NEWGAME_CHARACTER);
        }
        else {
            Model.getInstance().getViewFactory().getUserMenuOptions().set(UserMenuOptions.NEWGAME_CHARACTER);
        }
    }

    private void onExit() {
        Stage stage = (Stage) exit_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }
}

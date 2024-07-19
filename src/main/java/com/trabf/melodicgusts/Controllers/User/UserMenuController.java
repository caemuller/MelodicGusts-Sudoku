package com.trabf.melodicgusts.Controllers.User;

import com.trabf.melodicgusts.Models.Model;
import com.trabf.melodicgusts.Views.UserMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UserMenuController implements Initializable {

    public Button options_btn;
    public Button scores_btn;
    public Button newgame_btn;
    public Button about_btn;
    public Button exit_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    public void addListeners() {
        options_btn.setOnAction(event -> onOptions());
        newgame_btn.setOnAction(event -> onNewGame());
        exit_btn.setOnAction(event -> onExit());
    }

    public void onOptions() {
        Model.getInstance().getViewFactory().getUserMenuOptions().set(UserMenuOptions.OPTIONS);
    }

    public void onNewGame() {
        Model.getInstance().getViewFactory().getUserMenuOptions().set(UserMenuOptions.NEWGAME_BOARD);
    }

    public void onExit() {
        Stage stage = (Stage) exit_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }
}

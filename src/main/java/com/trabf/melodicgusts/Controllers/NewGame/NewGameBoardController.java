package com.trabf.melodicgusts.Controllers.NewGame;

import com.trabf.melodicgusts.Models.Model;
import com.trabf.melodicgusts.Views.UserMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class NewGameBoardController implements Initializable {
    public Button board4x4_btn;
    public Button board6x6_btn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners() {
        board4x4_btn.setOnAction(event -> onBoard());
        board6x6_btn.setOnAction(event -> onBoard());
    }

    public void onBoard() {
        Model.getInstance().getViewFactory().getUserMenuOptions().set(UserMenuOptions.NEWGAME_MODALITY);
    }
}

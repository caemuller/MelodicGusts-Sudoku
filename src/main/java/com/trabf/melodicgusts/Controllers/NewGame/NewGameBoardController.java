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
        board4x4_btn.setOnAction(event -> onBoard4x4());
        board6x6_btn.setOnAction(event -> onBoard6x6());
    }

    private void onBoard6x6() {
        Model.getInstance().setOnBoard6x6(true);
        Model.getInstance().getViewFactory().getUserMenuOptions().set(UserMenuOptions.NEWGAME_MODALITY);

    }

    public void onBoard4x4() {
        Model.getInstance().setOnBoard4x4(true);
        Model.getInstance().getViewFactory().getUserMenuOptions().set(UserMenuOptions.NEWGAME_MODALITY);
    }
}

package com.trabf.melodicgusts.Controllers.NewGame;

import com.trabf.melodicgusts.Models.Model;
import com.trabf.melodicgusts.Views.UserMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class NewGameModalityController implements Initializable {
    public Button pairs_btn;
    public Button continuation_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    public void addListeners() {
        pairs_btn.setOnAction(e -> onModality());
        continuation_btn.setOnAction(e -> onModality());
    }

    private void onModality() {
        Model.getInstance().getViewFactory().getUserMenuOptions().set(UserMenuOptions.NEWGAME_CHARACTER);
    }
}

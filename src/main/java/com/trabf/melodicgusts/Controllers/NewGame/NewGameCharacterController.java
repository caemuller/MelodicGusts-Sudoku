package com.trabf.melodicgusts.Controllers.NewGame;

import com.trabf.melodicgusts.Models.Model;
import com.trabf.melodicgusts.Views.UserMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class NewGameCharacterController implements Initializable {
    public Button ready_btn;
    public TextField name_fld;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners() {
        ready_btn.setOnAction(event -> onReady());
    }

    private void onReady() {
        //define o nome do usuario
        String name = name_fld.getText();
        Model.getInstance().getUser().nameProperty().set(name);
        Model.getInstance().getViewFactory().getUserMenuOptions().set(UserMenuOptions.NEWGAME_BOARD);
        name_fld.clear();
    }
}

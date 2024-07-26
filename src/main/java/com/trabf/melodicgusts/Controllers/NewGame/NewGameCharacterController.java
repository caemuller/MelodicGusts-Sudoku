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
    public Button confirm_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners() {
        confirm_btn.setOnAction(e -> nameUser());
        ready_btn.setOnAction(event -> onReady());
    }

    private void onReady() {
        name_fld.clear();
        Model.getInstance().getViewFactory().getUserMenuOptions().set(UserMenuOptions.NEWGAME_MODALITY);
    }

    //define o nome do usuario
    private void nameUser() {
        String name = name_fld.getText();
        Model.getInstance().getUser().setName(name);;
    }
}

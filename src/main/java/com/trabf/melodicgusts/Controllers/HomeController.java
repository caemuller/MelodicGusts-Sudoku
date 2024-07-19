package com.trabf.melodicgusts.Controllers;

import com.trabf.melodicgusts.Models.Model;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    public Button space_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        space_btn.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.SPACE) {
                Model.getInstance().getViewFactory().showMenuWindow();
                // Fecha a Tela Inicial
                Stage stage = (Stage) space_btn.getScene().getWindow();
                Model.getInstance().getViewFactory().closeStage(stage);
            }
        });
    }
}


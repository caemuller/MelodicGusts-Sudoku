package com.trabf.melodicgusts.Controllers.User;

import com.trabf.melodicgusts.Models.Model;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class OptionsController implements Initializable {
    public Label user_lbl;
    public Label score_lbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindData();
    }

    private void bindData() {
        user_lbl.textProperty().bind(Bindings.concat("Melhor pontuação de ")
                .concat(Model.getInstance().getUser().nameProperty()));
        score_lbl.textProperty().bind(Model.getInstance().getUser().scoreProperty().asString());
    }
}

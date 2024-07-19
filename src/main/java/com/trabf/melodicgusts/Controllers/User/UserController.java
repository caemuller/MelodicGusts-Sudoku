package com.trabf.melodicgusts.Controllers.User;

import com.trabf.melodicgusts.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    public BorderPane user_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getUserMenuOptions().addListener((observableVal, odlVal, newVal)-> {
           switch (newVal) {
               case NEWGAME_BOARD -> user_parent.setCenter(Model.getInstance().getViewFactory().getNewGameBoardView());
               case NEWGAME_MODALITY -> user_parent.setCenter(Model.getInstance().getViewFactory().getNewGameModalityView());
               case NEWGAME_CHARACTER -> user_parent.setCenter(Model.getInstance().getViewFactory().getNewGameCharacterView());
               case NEWGAME_MATCH -> user_parent.setCenter(Model.getInstance().getViewFactory().getNewGameMatchView());
               default -> user_parent.setCenter(Model.getInstance().getViewFactory().getOptionsView());
           }
        });
    }
}

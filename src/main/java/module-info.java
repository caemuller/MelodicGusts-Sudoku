
module com.trabf.melodicgusts {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens com.trabf.melodicgusts to javafx.fxml;
    exports com.trabf.melodicgusts;
    exports com.trabf.melodicgusts.Controllers;
    exports com.trabf.melodicgusts.Controllers.User;
    exports com.trabf.melodicgusts.Controllers.NewGame;
    exports com.trabf.melodicgusts.Controllers.Game;
    exports com.trabf.melodicgusts.Models;
    exports com.trabf.melodicgusts.Models.entities;
    exports com.trabf.melodicgusts.Views;
}

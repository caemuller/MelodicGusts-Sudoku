/*
 * O model representa os dados do aplicativo e a lógica de negócios,
 * enquanto o View controla a camada de apresentação (interfaces do usuário) e o
 * Controller gerencia o fluxo de dados entre o Model e o View
 */
package com.trabf.melodicgusts;

import com.trabf.melodicgusts.Models.Model;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        Model.getInstance().getViewFactory().showInitialWindow();
    }
}
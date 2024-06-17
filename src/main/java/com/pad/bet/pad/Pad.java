package com.pad.bet.pad;

import com.pad.bet.pad.login.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Pad extends Application {
    @Override
    public void start(Stage primaryStage) {
        LoginController loginController = new LoginController();
        loginController.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package org.com.components.buttons;


import java.sql.Connection;

import org.com.screens.landing.SplashScreen;
import org.com.state.user.UserState;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class LogOutButton {
    public static Button logOutButton(Connection connection, Stage stage, UserState userState, GridPane pane){
        Button button = new Button("Logout");
        button.setOnAction(e -> {
            userState.setName("", "");
            userState.setLoggedInState();
            new SplashScreen(connection).createPane(stage);
        });

        return button;
    }
}
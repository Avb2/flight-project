package org.com.components.buttons;


import java.sql.Connection;

import org.com.screens.landing.SplashScreen;
import org.com.state.user.UserState;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class LogOutButton {
    public static Node logOutButton(Connection connection, Stage stage, UserState userState, GridPane pane){
        Node button = new StyledButton1("Logout", e -> {
            userState.setName("", "");
            userState.setLoggedInState();
            stage.setScene(new SplashScreen(connection).createScreen(stage));
            stage.show();
        }).createComponent();

        return button;
    }
}
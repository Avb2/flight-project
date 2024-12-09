package org.com.components.buttons;

import java.sql.Connection;

import org.com.screens.home.AdminHomeScreen;
import org.com.screens.home.HomeScreen;
import org.com.screens.landing.SplashScreen;
import org.com.state.user.UserState;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainMenuButton {
    public static Button mainMenuButton(Connection connection, Stage stage, GridPane pane){
        Button returnMain = new Button("Main Menu");
        returnMain.setOnAction(e -> new SplashScreen(connection).createPane(stage));
        return returnMain;
    }

    public static Button mainMenuButton(Connection connection, Stage stage, UserState userState, GridPane pane){
        Button returnMain = new Button("Main Menu");
        returnMain.setOnAction(e -> {
            if (userState.getLoggedInState() && userState.getPermissions().matches("user") ){
                new HomeScreen(connection, userState).createScreen(stage);
            } else if (userState.getLoggedInState() && userState.getPermissions().matches("admin") ){
                stage.setScene(new AdminHomeScreen().createScreen(stage));
            } else {
                stage.setScene(new SplashScreen(connection).createScreen(stage));
            }
        });
        return returnMain;
    }
}
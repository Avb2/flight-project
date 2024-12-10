package org.com.components.buttons;

import java.sql.Connection;

import org.com.screens.home.HomeScreen;
import org.com.screens.landing.SplashScreen;
import org.com.state.user.UserState;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainMenuButton {
    public static Node mainMenuButton(Connection connection, Stage stage, GridPane pane){
        Node returnMain = new StyledButton1("Main Menu", e -> {
            Scene scene =  new SplashScreen(connection).createScreen(stage);
            stage.setScene(scene);
            stage.show();
         }).createComponent();
    
        return returnMain;
    }

    public static Node mainMenuButton(Connection connection, Stage stage, UserState userState, GridPane pane){
        Node returnMain = new StyledButton1("Main Menu", e -> {
            if (userState.getLoggedInState()){
                stage.setScene(new HomeScreen(connection, userState).createScreen(stage));
                stage.show();
                
            } else {
                stage.setScene(new SplashScreen(connection).createScreen(stage));
                stage.show();
            }
        }).createComponent();
      
        return returnMain;
    }
}
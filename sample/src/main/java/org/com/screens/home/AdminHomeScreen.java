package org.com.screens.home;



import javafx.stage.Stage;

import org.com.bases.Screen;
import org.com.components.navBars.AdminNavBar;

import javafx.scene.layout.GridPane;

import org.com.state.user.UserState;

import java.sql.Connection;


public class AdminHomeScreen extends Screen {
    private Connection connection;
    private final UserState userState;

    public AdminHomeScreen(Connection connection, UserState userState) {
        this.connection = connection;
        this.userState = userState;
    }


    
    @Override
    public GridPane createPane(Stage stage){
        // Create main pane
        GridPane pane = new GridPane();
        pane.add(new AdminNavBar(stage, userState, this.connection, pane).createComponent(), 0, 0);   

        return pane;
    }
}
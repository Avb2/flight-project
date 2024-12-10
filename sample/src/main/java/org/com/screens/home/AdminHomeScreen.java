package org.com.screens.home;



import java.sql.Connection;

import org.com.bases.Screen;
import org.com.components.navBars.AdminNavBar;
import org.com.state.user.UserState;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


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
        pane.getStyleClass().add("background-primary");
        pane.add(new AdminNavBar(stage, userState, this.connection, pane).createComponent(), 0, 0);   

        return pane;
    }
}
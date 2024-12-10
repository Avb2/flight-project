package org.com.functionality.navigation;

import java.sql.Connection;

import org.com.bases.Navigate;
import org.com.screens.modify.EditFlightScreen;
import org.com.state.user.UserState;

import javafx.stage.Stage;

public class PushEditFlight extends Navigate{
    @Override 
    public void push(Connection connection, UserState userState, Stage stage){
        stage.setScene(new EditFlightScreen(connection, userState).createScreen(stage));
        
    }
}
package org.com.functionality.navigation;

import org.com.bases.Navigate;
import javafx.stage.Stage;
import org.com.screens.modify.EditFlightScreen;
import org.com.state.user.UserState;

import java.sql.Connection;

public class PushEditFlight extends Navigate{
    @Override 
    public void push(Connection connection, UserState userState, Stage stage){
        stage.setScene(new EditFlightScreen(connection, userState).createScreen(stage));
    }
}
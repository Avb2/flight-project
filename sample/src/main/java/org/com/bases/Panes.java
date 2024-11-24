package org.com.bases;

import java.sql.Connection;

import org.com.state.user.UserState;

import javafx.scene.layout.GridPane;


public abstract class Panes {
    public abstract void createPane(GridPane mainPane, UserState userState, Connection connection);
    
}

package org.com.bases;

import java.sql.Connection;

import org.com.state.user.UserState;

import javafx.stage.Stage;


public abstract class Navigate {
    public abstract void push(Connection connection, UserState state, Stage stage);
}
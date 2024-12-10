package org.com.functionality.auth;


import java.sql.Connection;
import java.sql.SQLException;

import org.com.database.UserDatabase;
import org.com.screens.home.AdminHomeScreen;
import org.com.screens.home.HomeScreen;
import org.com.state.user.UserState;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Login {
    public static void  login(GridPane usernameFieldPane, GridPane passwordFieldPane, Connection connection, GridPane pane, Stage stage){
        UserDatabase conn = new UserDatabase(connection);
            // Extract textfields from gridpanes
            TextField usernameField = (TextField) (usernameFieldPane.getChildren().get(1));
            TextField passwordField = (TextField) (passwordFieldPane.getChildren().get(1));
 
            // Extract text from textfields
            String username = usernameField.getText();
            String password = passwordField.getText();

            try {
                // Validate user by username and password
                Boolean validUser = conn.validateUsername(username, password);

                // Login user if auth was successful
                if (validUser == true) {
                    // Create user state
                    UserState userState = new UserState();
                    // Update user state and status within User State
                    userState.login(conn, username);
                    // If the user is an admin
                    if (Login.validateAdmin(connection, username)){
                        System.out.println("admin");
                        stage.setScene(new AdminHomeScreen(connection, userState).createScreen(stage));
                        stage.show();
                    } else {
                        System.out.println("User");
                        Scene scene = new HomeScreen(connection, userState).createScreen(stage);
                        // Push to main logged in screen
                        stage.setScene(scene);
                        stage.show();
                    }


                    
                } else {
                    System.out.println("Failed to validate user");
                }
            } catch (SQLException error) {
                error.printStackTrace();
            }

    }

    //  Returns true if admin account/ false if user account
    public static boolean validateUserType(String username){
        return username.matches("^admin_.*");

    }


    public static boolean validateAdmin(Connection connection, String username) throws SQLException{
        if (Login.validateUserType(username)){
            UserDatabase userDb = new UserDatabase(connection);
            return userDb.validateType(username);
        }
        return false;

    }
}
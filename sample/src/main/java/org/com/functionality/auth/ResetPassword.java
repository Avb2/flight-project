package org.com.functionality.auth;


import java.sql.Connection;
import java.sql.SQLException;

import org.com.components.buttons.StyledButton1;
import org.com.components.inputFields.InputField;
import org.com.database.UserDatabase;
import org.com.screens.landing.LoginScreen;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;



public class ResetPassword {
    public static void answerSecurityQuestion(String username , String answer, GridPane answerPane, GridPane pane, Connection connection, Stage stage){
        // Extract the textfield from the grid pane
        TextField answerTextField = (TextField) (answerPane.getChildren().get(1));
            // Check if the inputted answer matches the security answer
            if (answerTextField.getText().equals(answer)){
                // Clear existing content from the pane
                pane.getChildren().clear();
                
                // New password/confirm fields
                GridPane newPasswordPane = InputField.inputField("New password");
                pane.add(newPasswordPane, 0, 0);

                GridPane confirmPasswordPane = InputField.inputField("Confirm password");
                pane.add(confirmPasswordPane, 0 , 1);

                // Submit security answer button
                pane.add(new StyledButton1("Enter", e -> {
                    TextField newPasswordTextField = (TextField) (newPasswordPane.getChildren().get(1));
                    TextField confirmPasswordTextField = (TextField) (confirmPasswordPane.getChildren().get(1));
                    String newPassword = newPasswordTextField.getText();
                    String confirmPassword = confirmPasswordTextField.getText();
                    
                    if (newPassword.matches(confirmPassword)){
                        try{
                            new UserDatabase(connection).changePassword(username, confirmPassword);
                            stage.setScene(new LoginScreen(connection).createScreen(stage));
                        } catch (SQLException err){
                            err.printStackTrace();
                        }
                    } else {
                        pane.add(new Label("Passwords did not match"), 0, 3);
                    }
                    }).createComponent(), 0, 2);

            }
        }
}
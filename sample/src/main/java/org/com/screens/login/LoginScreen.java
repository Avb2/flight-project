package org.com.screens.login;


import java.sql.Connection;

import org.com.bases.Screen;
import org.com.components.buttons.MainMenuButton;
import org.com.components.buttons.StyledButton1;
import org.com.components.inputFields.InputField;
import org.com.constants.Sizes;
import org.com.functionality.auth.Login;
import org.com.screens.security.ResetPasswordScreen;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;



public class LoginScreen extends Screen {
    private Connection connection;
    
    public LoginScreen(Connection connection){
        this.connection = connection;
    }




    @Override
    public GridPane createPane(Stage stage){
         // Grid
         GridPane pane = new GridPane();
         pane.getStyleClass().add("background-primary");
         pane.setAlignment(Pos.CENTER);
         pane.setVgap(Sizes.mediumGap);
 
         // Return to Main Menu Button
         Button mainMenuButton = MainMenuButton.mainMenuButton(this.connection, stage, pane);
         mainMenuButton.getStyleClass().add("button-1");
         pane.add(mainMenuButton, 0, 0);
 
         // Login title label
         Label loginTitle = new Label("LOGIN");
         loginTitle.getStyleClass().add("title");
         pane.add(loginTitle, 0, 3);
 
         // Add subpane to main pane
         GridPane subPane = new GridPane();
         subPane.getStyleClass().add("background-primary");
         subPane.setVgap(Sizes.smallGap);
         pane.add(subPane, 0, 4);
 
         // Username Label
         GridPane usernameFieldPane = InputField.inputField("Username");
         subPane.add(usernameFieldPane, 0, 0, 2, 1);
 
         // Password Label
         GridPane passwordFieldPane = InputField.inputField("Password");
         subPane.add(passwordFieldPane, 0, 1, 2, 1);
 
         // Login Button
         subPane.add(new StyledButton1("Enter", e -> {
             Login.login(usernameFieldPane, passwordFieldPane, this.connection, pane,  stage);
         }).createComponent(), 0, 2);
 
         // Reset Password Button
         Node resetButton = new StyledButton1("Reset Password" , e -> {
            Scene scene = new ResetPasswordScreen(this.connection).createScreen(stage);
            stage.setScene(scene);
            stage.show();
        }).createComponent();
         subPane.add(resetButton, 1, 2);
         
         return pane;
    }

}

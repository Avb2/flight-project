package org.com.screens.login;
//upload ui packages
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
//import org.com.components.buttons.styled.StyledButton1;
import javafx.scene.control.TextField;
import java.util.HashMap;
import javafx.scene.Node;

// import util

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.com.bases.Screen;
//import org.com.components.buttons.EnterBtn;
//import org.com.components.InputField;
//import org.com.components.buttons.MainMenuBtn;
import org.com.components.inputFields.InputField;
import org.com.constants.Sizes;
//import org.com.state.UserState;
//import org.com.db.UserDatabase;
//import org.com.functionality.login.Login;
import java.sql.Connection;

// Where users will login to the application with username and password
public class LoginScreen extends Screen {
    private Connection connection;
    public LoginScreen(Connection connection) {
        this.connection = connection;
    }

    @Override
    public GridPane createPane(Stage stage) {
       //set the grid
        GridPane pane = new GridPane();
        pane.getStyleClass().add("background-primary");
        pane.setAlignment(Pos.CENTER);
        pane.setVgap(Sizes.mediumGap);

        // Return to the Main Menu Button

        //add when main menu is created
        //Button mainMenuButton=MainMenuBtn.mainMenuButton(this.connection,stage,pane);
        //pane.add(mainMenuButton,0,0);

        // set login title lable
        Label loginTitle=new Label("LOGIN");
        loginTitle.getStyleClass().add("title");
        pane.add(loginTitle, 0, 3);

        //add subpane
        // Add subpane to main pane
        GridPane subPane = new GridPane();
        subPane.getStyleClass().add("background-primary");
        subPane.setVgap(Sizes.smallGap);
        pane.add(subPane, 0, 4);

        //username
        InputField inputField = new InputField();
        GridPane usernameFieldPane=InputField.inputField("Username");
        subPane.add(usernameFieldPane, 0, 4);

        // Password Label
        GridPane passwordFieldPane = InputField.inputField("Password");
        subPane.add(passwordFieldPane, 0, 1, 2, 1);

        // Login Button
        /*subPane.add(EnterBtn.EnterButton(e -> {
            Login.login(usernameFieldPane, passwordFieldPane, this.connection, pane,  stage);
        }), 0, 2);

        // Reset Password Button
        Node resetButton = new StyledButton1("Reset Password" , e -> new ResetPasswordScreen(this.connection).createPane(pane, stage)).createComponent();
        subPane.add(resetButton, 1, 2);*/

        return pane;
    }
}

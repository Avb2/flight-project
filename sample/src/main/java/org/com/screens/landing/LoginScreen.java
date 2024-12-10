package org.com.screens.landing;


import java.sql.Connection;

import org.com.bases.Screen;
import org.com.components.buttons.MainMenuButton;
import org.com.components.buttons.StyledButton1;
import org.com.components.inputFields.InputField;
import org.com.constants.Sizes;
import org.com.functionality.auth.Login;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class LoginScreen extends Screen {
    private Connection connection;

    public LoginScreen(Connection connection) {
        this.connection = connection;
    }

    @Override
    public GridPane createPane(Stage stage) {
        // Main GridPane
        GridPane pane = new GridPane();
        pane.getStyleClass().add("background-primary");
        pane.setAlignment(Pos.TOP_CENTER);
        pane.setVgap(Sizes.mediumGap);

        // Main Menu Button
        Node mainMenuButton = MainMenuButton.mainMenuButton(this.connection, stage, pane);
        mainMenuButton.getStyleClass().add("button-1");
        pane.add(mainMenuButton, 0, 0);

        // Login Title Label
        Label loginTitle = new Label("Login");
        loginTitle.getStyleClass().add("title");
        pane.add(loginTitle, 0, 1);

        // SubPane for inputs
        GridPane subPane = new GridPane();
        subPane.getStyleClass().add("flight-card-pane");
        subPane.setVgap(Sizes.smallGap);
        subPane.setPadding(new Insets(15));
        pane.add(subPane, 0, 2);

        // Username Input Field
        GridPane usernameFieldPane = InputField.inputField("Username");
        usernameFieldPane.getStyleClass().add("text-field-1");
        subPane.add(usernameFieldPane, 0, 0, 2, 1);

        // Password Input Field
        GridPane passwordFieldPane = InputField.inputField("Password");
        passwordFieldPane.getStyleClass().add("text-field-1");
        subPane.add(passwordFieldPane, 0, 1, 2, 1);

        // Login Button
        Node loginButton = new StyledButton1("Enter", e -> {
            Login.login(usernameFieldPane, passwordFieldPane, this.connection, pane, stage);
        }).createComponent();
        loginButton.getStyleClass().add("button-1");
        subPane.add(loginButton, 0, 2);

        // Reset Password Button
        Node resetButton = new StyledButton1("Reset Password", e -> {
            Scene scene = new ResetPasswordScreen(this.connection).createScreen(stage);
            stage.setScene(scene);
            stage.show();
        }).createComponent();
        resetButton.getStyleClass().add("button-1");
        subPane.add(resetButton, 1, 2);

        return pane;
    }
}
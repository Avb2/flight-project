package org.com.screens.landing;



// Users will reset their password in this screen by first entering their username
import java.sql.Connection;
import java.util.Map;

import org.com.animations.Animate;
import org.com.bases.Screen;
import org.com.components.buttons.MainMenuButton;
import org.com.components.buttons.StyledButton1;
import org.com.components.inputFields.InputField;
import org.com.components.panes.SecurityQuestionPane;
import org.com.constants.Sizes;
import org.com.database.UserDatabase;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ResetPasswordScreen extends Screen {
    private Connection connection;

    public ResetPasswordScreen(Connection connection) {
        this.connection = connection;
    }

    @Override
    public GridPane createPane(Stage stage) {
        // Main pane
        GridPane pane = new GridPane();
        pane.getStyleClass().add("background-primary");
        pane.setAlignment(Pos.TOP_CENTER);
        pane.setVgap(Sizes.mediumGap);

        // Main menu button
        Node mainMenuBtn = MainMenuButton.mainMenuButton(this.connection, stage, pane);
        mainMenuBtn.getStyleClass().add("button-1");
        pane.add(mainMenuBtn, 0, 0);

        // Reset password label
        Label resetLabel = new Label("Reset Password");
        resetLabel.getStyleClass().add("title");
        pane.add(resetLabel, 0, 1);

        // SubPane for inputs
        GridPane subPane = new GridPane();
        subPane.getStyleClass().add("flight-card-pane");
        subPane.setVgap(Sizes.smallGap);
        subPane.setHgap(Sizes.mediumGap);
        subPane.setPadding(new Insets(15));
        pane.add(subPane, 0, 2);

        // Username input field
        GridPane usernameField = InputField.inputField("Username");
        usernameField.getStyleClass().add("text-field-1");
        subPane.add(usernameField, 0, 0);

        // Enter button
        Node enterButton = new StyledButton1("Enter", e -> {
            try {
                TextField usernameTextField = (TextField) usernameField.getChildren().get(1);
                String username = usernameTextField.getText();
                Map<String, String> securityInfo = new UserDatabase(this.connection).retrieveSecurityInfo(username);

                pane.getChildren().remove(subPane);
                pane.add(new SecurityQuestionPane(
                    securityInfo.get("question"), 
                    securityInfo.get("answer"), 
                    username, 
                    this.connection, 
                    stage
                ).createComponent(), 0, 2);

            } catch (Exception err) {
                // Error label
                Label errorLabel = new Label("No matching username");
                errorLabel.getStyleClass().add("subtitle");
                pane.add(errorLabel, 0, 3);
                new Animate(errorLabel).fadeOut(3);
            }
        }).createComponent();
        enterButton.getStyleClass().add("button-1");
        subPane.add(enterButton, 0, 1);

        return pane;
    }
}

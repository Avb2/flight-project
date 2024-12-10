package org.com.screens.registration;

import java.sql.Connection;

import org.com.bases.Screen;
import org.com.components.buttons.MainMenuButton;
import org.com.components.buttons.StyledButton1;
import org.com.components.inputFields.InputField;
import org.com.constants.Sizes;
import org.com.functionality.auth.Register;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RegisterScreen extends Screen {
    private final Connection connection;

    public RegisterScreen(Connection connection) {
        this.connection = connection;
    }

    @Override
    public GridPane createPane(Stage stage) {
        // Main pane
        GridPane pane = new GridPane();
        pane.getStyleClass().add("background-primary");
        pane.setAlignment(Pos.TOP_CENTER);
        pane.setVgap(Sizes.mediumGap);
        pane.setPadding(new Insets(20));

        // Main menu button
        Node mainMenuButton = MainMenuButton.mainMenuButton(this.connection, stage, pane);
        mainMenuButton.getStyleClass().add("button-1");
        pane.add(mainMenuButton, 0, 0);

        // Register label
        Label registerLabel = new Label("Register");
        registerLabel.getStyleClass().add("title");
        registerLabel.setAlignment(Pos.CENTER);
        pane.add(registerLabel, 0, 1);

        // Create a subpane for the input fields
        GridPane subpane = new GridPane();
        subpane.getStyleClass().add("flight-card-pane");
        subpane.setVgap(Sizes.smallGap);
        subpane.setHgap(Sizes.mediumGap);
        subpane.setPadding(new Insets(15));

        // Input fields
        addInputField(subpane, "First Name", 0);
        addInputField(subpane, "Last Name", 1);
        addInputField(subpane, "Address", 2);
        addInputField(subpane, "Zipcode", 3);
        addInputField(subpane, "State", 4);
        addInputField(subpane, "Username", 5);
        addInputField(subpane, "Password", 6);
        addInputField(subpane, "Email", 7);
        addInputField(subpane, "SSN", 8);
        addInputField(subpane, "Security Question", 9);
        addInputField(subpane, "Security Answer", 10);

        // Wrap the subpane in a ScrollPane
        ScrollPane scrollPane = new ScrollPane(subpane);
        scrollPane.setFitToHeight(true); // Make sure it fits the height
        scrollPane.setFitToWidth(true);  // Make sure it fits the width
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // Always show vertical scrollbar
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Do not show horizontal scrollbar
        pane.add(scrollPane, 0, 2);

        // Enter button
        Node enterButton = new StyledButton1("Enter", e -> {
            Register.createAccount(
                this.connection,
                pane,
                getInputField(subpane, 0),
                getInputField(subpane, 1),
                getInputField(subpane, 2),
                getInputField(subpane, 3),
                getInputField(subpane, 4),
                getInputField(subpane, 5),
                getInputField(subpane, 6),
                getInputField(subpane, 7),
                getInputField(subpane, 8),
                getInputField(subpane, 9),
                getInputField(subpane, 10),
                stage
            );
        }).createComponent();
        enterButton.getStyleClass().add("button-1");
        pane.add(enterButton, 0, 3);

        return pane;
    }

    private void addInputField(GridPane subpane, String labelText, int row) {
        GridPane inputField = InputField.inputField(labelText);
        inputField.getStyleClass().add("text-field-1");
        subpane.add(inputField, 0, row);
    }

    private GridPane getInputField(GridPane subpane, int row) {
        return (GridPane) subpane.getChildren().get(row); // Return the entire GridPane
    }
}

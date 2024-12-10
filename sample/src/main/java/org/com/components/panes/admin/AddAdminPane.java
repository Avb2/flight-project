package org.com.components.panes.admin;


import java.sql.Connection;

import org.com.animations.Animate;
import org.com.bases.Panes;
import org.com.components.buttons.StyledButton1;
import org.com.components.inputFields.InputField;
import org.com.components.navBars.AdminNavBar;
import org.com.functionality.auth.Register;
import org.com.models.RegistrationAdmin;
import org.com.state.user.UserState;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class AddAdminPane extends Panes {
    private Stage stage; 

    public AddAdminPane(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void createPane(GridPane mainPane, UserState userState, Connection connection) {
        mainPane.getChildren().clear();
        
        GridPane pane = new GridPane();
        pane.getStyleClass().add("background-primary");
        mainPane.add(pane, 0, 0);

        GridPane navBar = new AdminNavBar(this.stage, userState, connection, mainPane).createComponent();
        pane.add(navBar, 0, 0);
        // Add admins label
        Label titleLabel = new Label("Add Admin");
        titleLabel.getStyleClass().add("subtitle");
        pane.add(titleLabel, 0, 1);

        

        // Add admins
        GridPane firstNameField = InputField.inputField("First Name");
        pane.add(firstNameField, 0, 2);

        // Add field to delete admins
        GridPane lastNameField = InputField.inputField("Last Name");
        pane.add(lastNameField, 0, 3);

        // Add field to delete admins
        GridPane emailField = InputField.inputField("email");
        pane.add(emailField, 0, 4);

        // Add field to delete admins
        GridPane ssnField = InputField.inputField("SSN");
        pane.add(ssnField, 0, 5);

        // Add Enter button with styling and action
        pane.add(new StyledButton1("Enter", e -> {
            RegistrationAdmin registrationModel = new RegistrationAdmin(
                firstNameField,
                lastNameField,
                emailField,
                ssnField
            );

            String[] valid = Register.validateAdminAcct(registrationModel);

            if (valid.length == 4) {
                Register.createAdmin(connection, registrationModel);
                System.out.println("Created admin");

                Label successLabel = new Label("Successfully created");
                pane.add(successLabel, 0, 6);
                new Animate(successLabel).fadeOut(1);
            } else {
                Label failureLabel = new Label("Failed to create");
                pane.add(failureLabel, 0, 6);
                new Animate(failureLabel).fadeOut(1);
                System.out.println("Failed to create admin");
            }
        }).createComponent(), 0, 7);
    }
}

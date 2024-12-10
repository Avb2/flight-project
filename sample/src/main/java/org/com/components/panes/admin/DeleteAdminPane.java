package org.com.components.panes.admin;


import java.sql.Connection;

import org.com.animations.Animate;
import org.com.bases.Panes;
import org.com.components.buttons.StyledButton1;
import org.com.components.inputFields.InputField;
import org.com.components.navBars.AdminNavBar;
import org.com.database.UserDatabase;
import org.com.state.user.UserState;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class DeleteAdminPane extends Panes {
    private Stage stage;

    public DeleteAdminPane(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void createPane(GridPane mainPane, UserState userState, Connection connection) {
        mainPane.getChildren().clear();
        
        GridPane pane = new GridPane();
        pane.getStyleClass().add("background-primary");
        mainPane.add(pane, 0, 0);

        GridPane adminNavBar = new AdminNavBar(this.stage, userState, connection, mainPane).createComponent();
        pane.add(adminNavBar, 0, 0);

        Label titleLabel = new Label("Delete User");
        titleLabel.getStyleClass().add("subtitle");
        pane.add(titleLabel, 0, 1);

        GridPane usernameField = InputField.inputField("Username");
        pane.add(usernameField, 0, 2);

        // Add delete button with action and styling
        pane.add(new StyledButton1("Enter", 
            e -> {
                TextField field = (TextField) usernameField.getChildren().get(1);
                new UserDatabase(connection).deleteUserByUsername(field.getText());

                Label deleteLabel = new Label("Successfully deleted");
                pane.add(deleteLabel, 0, 4);
                new Animate(deleteLabel).fadeOut(3);
            }
        ).createComponent(), 0, 3);
    }
}

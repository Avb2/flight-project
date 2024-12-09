package org.com.components.panes;

import java.sql.Connection;

import org.com.bases.Panes;
import org.com.components.buttons.StyledButton1;
import org.com.components.inputFields.InputField;
import org.com.components.navBars.AdminNavBar;
import org.com.state.user.UserState;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class DeleteAdminPane extends Panes{
    private Stage stage;

    public DeleteAdminPane(Stage stage){
        this.stage = stage;
    }


    @Override
    public void createPane(GridPane mainPane, UserState userState, Connection connection){
        mainPane.getChildren().clear();
        
        GridPane pane = new GridPane();
        mainPane.add(pane, 0, 0);


        GridPane adminNavBar = new AdminNavBar(this.stage, userState, connection, mainPane).createComponent();
        pane.add(adminNavBar, 0, 0);

        GridPane usernameField = InputField.inputField("Username");
        pane.add(usernameField, 0, 1);

        // Fading error label

        // 
        pane.add(new StyledButton1("Enter", 
            e -> {
                System.out.println("hi");
            }
        ).createComponent(), 0, 3);


    }
}
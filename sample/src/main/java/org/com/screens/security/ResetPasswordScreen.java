package org.com.screens.security;



// Users will reset their password in this screen by first entering their username
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.com.bases.Screen;
import org.com.components.buttons.MainMenuButton;
import org.com.components.buttons.StyledButton1;
import org.com.components.inputFields.InputField;
import org.com.components.panes.SecurityQuestionPane;
import org.com.constants.Sizes;
import org.com.database.UserDatabase;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class ResetPasswordScreen extends Screen {
    private Connection connection;

    public ResetPasswordScreen(Connection connection){
        this.connection = connection;
    }

    @Override
    public GridPane createPane(Stage stage){
        // Main pane
        GridPane pane = new GridPane();
        pane.getStyleClass().add("background-primary");
        pane.setVgap(Sizes.largeGap);
        pane.setAlignment(Pos.CENTER);


        // Main menu Btn
        pane.add(MainMenuButton.mainMenuButton(this.connection, stage, pane), 0, 0);

        
        // Reset password label
        Label resetLabel = new Label("Reset Password");
        resetLabel.getStyleClass().add("title");
        pane.add(resetLabel, 0, 2);


        GridPane subPane = new GridPane();
        subPane.getStyleClass().add("background-primary");
        subPane.setVgap(Sizes.mediumGap);
        subPane.setHgap(Sizes.mediumGap);
        pane.add(subPane, 0, 3);


        // Username form
        GridPane usernameField = InputField.inputField("Username");
        subPane.add(usernameField, 0, 0);
        
    
        // Enter Button
       subPane.add(new StyledButton1("Enter",
            e -> {
                try {
                TextField usernameTextField = (TextField) (usernameField.getChildren().get(1));
                String username = usernameTextField.getText();
                Map<String, String> securityInfo =  new UserDatabase(this.connection).retrieveSecurityInfo(username);
                pane.getChildren().remove(subPane);
                pane.add(new SecurityQuestionPane(securityInfo.get("question"), securityInfo.get("answer"), username,this.connection, stage).createComponent(), 0, 3);
                } catch (SQLException err){
                err.printStackTrace();
               }
            }
            ).createComponent(), 1, 1);




            return pane;
    }

}

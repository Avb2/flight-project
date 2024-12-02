package org.com.screens.security;


import org.com.bases.Screen;
//import org.com.components.buttons.EnterBtn;
//import org.com.components.InputField;
//import org.com.components.buttons.MainMenuBtn;
import org.com.constants.Sizes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
//import org.com.db.UserDatabase;

import java.sql.SQLException;
import java.util.Map;
//import org.com.components.panes.SecurityQuestionPane;

import java.sql.Connection;


// Users will reset their password in this screen by first entering their username
public class ResetPasswordScreen extends Screen
{

    @Override
    public GridPane createPane(Stage stage) {
        GridPane pane=new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setVgap(Sizes.largeGap);
        // now add main menu btn
       // pane.add(MainMenuBtn.mainMenuButton(this.connection, stage,pane),0,0);

        // add label for reset password
        Label resetLabel=new Label("Reset Password");
        pane.add(resetLabel, 0, 2);

        GridPane subPane= new GridPane();
        subPane.setVgap(Sizes.mediumGap);
        subPane.setHgap(Sizes.mediumGap);
        pane.add(subPane,0,3);

        //add username form
       // GridPane usernameField=InputField.inputField("Username");
        //subPane.add(usernameField,0,0);

        //Enter button
      /*  subPane.add(EnterBtn.EnterButton(
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
        ), 1, 1);

                */


        return pane;

    }
}

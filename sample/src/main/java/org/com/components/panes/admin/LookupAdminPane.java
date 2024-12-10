package org.com.components.panes.admin;



import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.com.bases.Panes;
import org.com.components.buttons.StyledButton1;
import org.com.components.inputFields.InputField;
import org.com.components.navBars.AdminNavBar;
import org.com.components.panes.UserInfoPane;
import org.com.database.UserDatabase;
import org.com.state.user.UserState;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class LookupAdminPane extends Panes{
    private Stage stage;

    public LookupAdminPane(Stage stage){
        this.stage = stage;
    }


    @Override
    public void createPane(GridPane mainPane, UserState userState, Connection connection){
        mainPane.getChildren().clear();
        
        GridPane pane = new GridPane();
        pane.getStyleClass().add("background-primary");
        mainPane.add(pane, 0, 0);

        GridPane adminNavBar = new AdminNavBar(this.stage, userState, connection, mainPane).createComponent();
        pane.add(adminNavBar, 0, 0);

        Label titleLabel = new Label("Lookup User");
        titleLabel.getStyleClass().add("subtitle");
        pane.add(titleLabel, 0, 1);

        GridPane lookupField = InputField.inputField("Username or SSN");
        pane.add(lookupField, 0, 2);
        // Fading error label
        // 
        pane.add(new StyledButton1("Enter", 
            e -> {
                try{
                    TextField textField = (TextField) (lookupField.getChildren().get(1));

                    UserDatabase userDb = new UserDatabase(connection);
                    Map<String, Object> userInfo = userDb.retrieveAllInfoBySSN(textField.getText())[0];
    
                    new UserInfoPane(this.stage, userInfo).createPane(mainPane, userState, connection);
                } catch (SQLException err){
                    err.printStackTrace();
                }
               
            }
        ).createComponent(), 0, 3);


    }
}
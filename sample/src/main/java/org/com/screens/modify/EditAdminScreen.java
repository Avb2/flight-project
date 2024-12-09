package org.com.screens.modify;

import java.sql.Connection;

import org.com.bases.Screen;
import org.com.components.navBars.AdminNavBar;
import org.com.components.panes.AddAdminPane;
import org.com.components.panes.DeleteAdminPane;
import org.com.components.panes.LookupAdminPane;
import org.com.state.user.UserState;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EditAdminScreen extends Screen{
    private Connection connection;
    private UserState userState;

    public EditAdminScreen(Connection connection, UserState userState){
        this.connection = connection;
        this.userState = userState;
    }
    
    

    @Override
    public GridPane createPane(Stage stage){
        GridPane pane = new GridPane();

        // Admin nav bar
        GridPane adminNavBar = new AdminNavBar(stage, userState, connection, pane).createComponent();
        pane.add(adminNavBar, 0, 0);

        // Add admins button
        Button manageAdminBtn = new Button("Add Admin");
        manageAdminBtn.setOnAction(e -> new AddAdminPane(stage).createPane(pane, userState, connection));
        pane.add(manageAdminBtn, 0, 1);

        // Delete admins button
        Button deleteAdminBtn = new Button("Delete Admin");
        deleteAdminBtn.setOnAction(e -> new DeleteAdminPane(stage).createPane(pane, userState, connection));
        pane.add(deleteAdminBtn, 0, 2);

        // Lookup admins Button
        Button lookupAdminBtn = new Button("Lookup Admin");
        lookupAdminBtn.setOnAction(e -> new LookupAdminPane(stage).createPane(pane, userState, connection));
        pane.add(lookupAdminBtn, 0, 3);

        return pane;
    }
}
    

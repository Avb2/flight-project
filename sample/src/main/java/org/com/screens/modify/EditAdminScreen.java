package org.com.screens.modify;

import java.sql.Connection;

import org.com.bases.Screen;
import org.com.components.buttons.StyledButton1;
import org.com.components.navBars.AdminNavBar;
import org.com.components.panes.AddAdminPane;
import org.com.components.panes.DeleteAdminPane;
import org.com.components.panes.LookupAdminPane;
import org.com.state.user.UserState;

import javafx.scene.Node;
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
        Node manageAdminBtn = new StyledButton1("Add Admin", e -> new AddAdminPane(stage).createPane(pane, userState, connection)).createComponent();
        pane.add(manageAdminBtn, 0, 1);

        // Delete admins button
        Node deleteAdminBtn = new StyledButton1("Delete Admin", e -> new DeleteAdminPane(stage).createPane(pane, userState, connection)).createComponent();
        pane.add(deleteAdminBtn, 0, 2);

        // Lookup admins Button
        Node lookupAdminBtn = new StyledButton1("Lookup Admin", e -> new LookupAdminPane(stage).createPane(pane, userState, connection)).createComponent();
        pane.add(lookupAdminBtn, 0, 3);

        return pane;
    }
}
    

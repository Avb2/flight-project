package org.com.screens.modify;

import java.sql.Connection;

import org.com.bases.Screen;
import org.com.components.buttons.StyledButton1;
import org.com.components.navBars.AdminNavBar;
import org.com.components.panes.admin.AddAdminPane;
import org.com.components.panes.admin.DeleteAdminPane;
import org.com.components.panes.admin.LookupAdminPane;
import org.com.state.user.UserState;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
public class EditAdminScreen extends Screen {
    private Connection connection;
    private UserState userState;

    public EditAdminScreen(Connection connection, UserState userState) {
        this.connection = connection;
        this.userState = userState;
    }

    @Override
    public GridPane createPane(Stage stage) {
        GridPane pane = new GridPane();
        pane.setVgap(10);  
        pane.setHgap(10);  
        pane.setAlignment(Pos.TOP_CENTER);

        // Admin nav bar
        GridPane adminNavBar = new AdminNavBar(stage, userState, connection, pane).createComponent();
        pane.add(adminNavBar, 0, 0);

        // Add Admin button
        Node manageAdminBtn = new StyledButton1("Add Admin", e -> new AddAdminPane(stage).createPane(pane, userState, connection)).createComponent();
        manageAdminBtn.getStyleClass().add("button-1");  
        pane.add(manageAdminBtn, 0, 1);

        // Delete Admin button
        Node deleteAdminBtn = new StyledButton1("Delete Admin", e -> new DeleteAdminPane(stage).createPane(pane, userState, connection)).createComponent();
        deleteAdminBtn.getStyleClass().add("button-1"); 
        pane.add(deleteAdminBtn, 0, 2);

        // Lookup Admin button
        Node lookupAdminBtn = new StyledButton1("Lookup Admin", e -> new LookupAdminPane(stage).createPane(pane, userState, connection)).createComponent();
        lookupAdminBtn.getStyleClass().add("button-1"); 
        pane.add(lookupAdminBtn, 0, 3);

        return pane;
    }
}

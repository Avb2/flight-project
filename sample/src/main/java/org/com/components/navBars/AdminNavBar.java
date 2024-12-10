package org.com.components.navBars;


import java.sql.Connection;

import org.com.bases.Component;
import org.com.components.buttons.LogOutButton;
import org.com.components.buttons.MainMenuButton;
import org.com.components.buttons.ManageAdminsBtn;
import org.com.components.buttons.StyledButton1;
import org.com.functionality.navigation.PushEditFlight;
import org.com.state.user.UserState;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AdminNavBar extends Component{
    private final Stage stage;
    private UserState userState;
    private Connection connection;
    private GridPane mainPane;

    public AdminNavBar(Stage stage, UserState userState, Connection connection, GridPane mainPane){
        this.stage = stage;
        this.userState = userState;
        this.connection = connection;
        this.mainPane = mainPane;
    }

    @Override
    public GridPane createComponent(){
        GridPane pane = new GridPane();
        pane.getStyleClass().add("navbar-primary");
        pane.setAlignment(Pos.TOP_CENTER);
        pane.setHgap(40);

        

        pane.add(MainMenuButton.mainMenuButton(this.connection, this.stage, this.userState, this.mainPane), 0, 0);

        pane.add(new StyledButton1("Flights", e -> {new PushEditFlight().push(this.connection, userState, stage);}).createComponent(), 1, 0);
        
        pane.add(new ManageAdminsBtn(this.connection, this.userState, this.stage).createComponent(), 2, 0);
        
        pane.add(LogOutButton.logOutButton(this.connection, this.stage, this.userState, this.mainPane), 3, 0);

        return pane;
    }
}
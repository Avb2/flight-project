package org.com.components.navBars;


import java.sql.Connection;

import org.com.bases.Component;
import org.com.components.buttons.LogOutButton;
import org.com.components.buttons.MainMenuButton;
import org.com.components.buttons.StyledButton1;
import org.com.screens.flights.ManageFlights;
import org.com.state.user.UserState;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;




public class NavBar extends Component{
    private Connection connection;
    private final Stage stage;
    private UserState userState;
    private GridPane mainPane;

    public NavBar(Connection connection, Stage stage, UserState userState, GridPane mainPane){
        this.connection = connection;
        this.stage = stage;
        this.userState = userState;
        this.mainPane = mainPane;
    }

    @Override
    public Node createComponent(){
        GridPane pane = new GridPane();
        pane.getStyleClass().add("navbar-primary");
        pane.setAlignment(Pos.TOP_CENTER);
        pane.setHgap(40);

        

        pane.add( MainMenuButton.mainMenuButton(connection, stage, userState, pane), 0, 0);
        
        pane.add(new StyledButton1("Manage Flights", e -> {
            Scene scene = new ManageFlights(this.connection, this.userState).createScreen(this.stage);
            stage.setScene(scene);
            stage.show();
        
        }).createComponent(), 1, 0);
        
        pane.add(LogOutButton.logOutButton(this.connection, this.stage, this.userState, this.mainPane), 2, 0);

        return pane;




    }
}
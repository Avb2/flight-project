package org.com.components.navBars;


import org.com.constants.Sizes;
import org.com.state.user.UserState;
import org.com.bases.Component;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javafx.scene.Node; 

import java.sql.Connection;

import org.com.components.buttons.LogOutButton;
import org.com.components.buttons.StyledButton1;




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

        

        pane.add(new StyledButton1("Main Menu", e -> {}).createComponent(), 0, 0);
        
        pane.add(new ManageFlightsButton(this.connection, this.stage, this.userState, this.mainPane).createComponent(), 1, 0);
        
        pane.add(LogOutButton.logOutButton(this.connection, this.stage, this.userState, this.mainPane), 2, 0);

        return pane;




    }
}
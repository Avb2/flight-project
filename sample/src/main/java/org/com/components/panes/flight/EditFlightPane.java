package org.com.components.panes.flight;


import java.sql.Connection;

import org.com.bases.Panes;
import org.com.components.buttons.StyledButton1;
import org.com.components.inputFields.InputField;
import org.com.components.navBars.AdminNavBar;
import org.com.functionality.flights.ModifyFlightsInterface;
import org.com.state.user.UserState;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;




public class EditFlightPane extends Panes{
    private Stage stage;
    public ModifyFlightsInterface modifyFlightInterface;

    public EditFlightPane(Stage stage, ModifyFlightsInterface modifyFlightInterface){
      
        this.stage = stage;
        this.modifyFlightInterface = modifyFlightInterface;
    }


    @Override
    public void createPane(GridPane mainPane, UserState userState, Connection connection){
        mainPane.getChildren().clear();
        
        GridPane pane = new GridPane();
        pane.getStyleClass().add("background-primary");
        mainPane.add(pane, 0, 0);

        GridPane navbar = new AdminNavBar(stage, userState, connection, mainPane).createComponent();
        navbar.getStyleClass().add("navbar-primary");
        pane.add(navbar, 0, 0);




        Label label = new Label("Delete Flight");
        label.getStyleClass().add("subtitle");
        pane.add(label, 0, 1);

        // Flight Number field     
        GridPane flightNumberField = InputField.inputField("Flight Number");
        pane.add(flightNumberField, 0, 2);
        
      
        Node enterBtn = new StyledButton1("Enter", e -> {
            TextField textField = (TextField) (flightNumberField.getChildren().get(1));
            this.modifyFlightInterface.onClick(pane, flightNumberField);
        }).createComponent();
        pane.add(enterBtn, 0, 4);
    
    }



    
}

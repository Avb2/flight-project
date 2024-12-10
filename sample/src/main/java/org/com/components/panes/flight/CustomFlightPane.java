package org.com.components.panes.flight;


import java.sql.Connection;

import org.com.bases.Panes;
import org.com.components.buttons.StyledButton1;
import org.com.components.inputFields.InputField;
import org.com.components.navBars.AdminNavBar;
import org.com.functionality.flights.CreateFlightsInterface;
import org.com.models.Flight;
import org.com.state.user.UserState;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class CustomFlightPane extends Panes{
    String title;
    private Stage stage;
    CreateFlightsInterface onAction;

    public CustomFlightPane(String title,  Stage stage, CreateFlightsInterface onAction){
        this.title = title;
        this.stage = stage;
        this.onAction = onAction;
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

    

        Label titleLabel = new Label(title);
        titleLabel.getStyleClass().add("subtitle");
        pane.add(titleLabel, 0, 1);

       

        // destination     
        GridPane destinationField = InputField.inputField("Destination");
        pane.add(destinationField, 0, 2);
        //        departure location         
        GridPane departureField = InputField.inputField("Departure Location");
        pane.add(departureField, 0, 3);
        // capacity 
        GridPane capacityField = InputField.inputField("Capacity");
        pane.add(capacityField, 0, 4);
        //          takeoff       
        GridPane takeoffField = InputField.inputField("Takeoff Time");  
        pane.add(takeoffField, 0, 5);  
        //          landing
        GridPane landingField = InputField.inputField("Landing time");
        pane.add(landingField, 0, 6);          
        //    date   
        GridPane dateField = InputField.inputField("Flight Date"); 
        pane.add(dateField, 0, 7);
        //  status  
        GridPane statusField = InputField.inputField("Current Status");
        pane.add(statusField, 0, 8);

        
        Node enterBtn = new StyledButton1("Enter", e -> {
            // Get strings
            TextField destinationFieldText = (TextField) (destinationField.getChildren().get(1));
            TextField departureFieldText = (TextField) (departureField.getChildren().get(1));
            TextField capacityFieldText = (TextField) (capacityField.getChildren().get(1));
            TextField takeoffFieldText = (TextField) (takeoffField.getChildren().get(1));
            TextField landingFieldText = (TextField) (landingField.getChildren().get(1));
            TextField dateFieldFieldText = (TextField) (dateField.getChildren().get(1));
            TextField statusFieldText = (TextField) (statusField.getChildren().get(1));

            // Flight model
            Flight flight = new Flight(
                destinationFieldText.getText(),
                departureFieldText.getText(),
                capacityFieldText.getText(),
                takeoffFieldText.getText(),
                landingFieldText.getText(),
                dateFieldFieldText.getText(),
                statusFieldText.getText()
            );


            this.onAction.onClick(pane, flight);
        }).createComponent();
        pane.add(enterBtn, 0, 9);

    }


   
}

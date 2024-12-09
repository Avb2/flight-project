package org.com.components.panes;


import java.sql.Connection;

import org.com.bases.Component;
import org.com.components.buttons.StyledButton1;
import org.com.components.inputFields.InputField;
import org.com.functionality.flights.CreateFlightsInterface;
import org.com.functionality.navigation.PushEditFlight;
import org.com.models.Flight;
import org.com.state.user.UserState;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class CustomFlightPane extends Component{
    private Connection connection;
    private UserState userState;
    private Stage stage;
    CreateFlightsInterface onAction;

    public CustomFlightPane(Connection connection, UserState userState, Stage stage, CreateFlightsInterface onAction){
        this.connection = connection;
        this.userState = userState;
        this.stage = stage;
        this.onAction = onAction;
    }


    @Override
    public Node createComponent(){
        GridPane pane = new GridPane();

        Node backBtn = new StyledButton1("Back", e -> {
            new PushEditFlight().push(this.connection, this.userState, this.stage);
        }).createComponent();
        pane.add(backBtn, 0, 0);

        // destination     
        GridPane destinationField = InputField.inputField("Destination");
        pane.add(destinationField, 0, 1);
        //        departure location         
        GridPane departureField = InputField.inputField("Departure Location");
        pane.add(departureField, 0, 2);
        // capacity 
        GridPane capacityField = InputField.inputField("Capacity");
        pane.add(capacityField, 0, 3);
        //          takeoff       
        GridPane takeoffField = InputField.inputField("Takeoff Time");  
        pane.add(takeoffField, 0, 4);  
        //          landing
        GridPane landingField = InputField.inputField("Landing time");
        pane.add(landingField, 0, 5);          
        //    date   
        GridPane dateField = InputField.inputField("Flight Date"); 
        pane.add(dateField, 0, 6);
        //  status  
        GridPane statusField = InputField.inputField("Current Status");
        pane.add(statusField, 0, 7);

        

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

    
    return pane;
    }
}

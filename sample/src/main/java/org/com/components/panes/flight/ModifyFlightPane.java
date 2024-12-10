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

public class ModifyFlightPane extends Panes{
    String title;
    private Stage stage;
    CreateFlightsInterface onAction;

    public ModifyFlightPane(String title, Stage stage, CreateFlightsInterface onAction){
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

// Admin NavBar at row 
GridPane navbar = new AdminNavBar(stage, userState, connection, mainPane).createComponent();
navbar.getStyleClass().add("navbar-primary");
pane.add(navbar, 0, 0);  

// Title with subtitle styling
Label titleLabel = new Label(title);
titleLabel.getStyleClass().add("subtitle");
pane.add(titleLabel, 0, 1);  

// Input fields
GridPane numberField = InputField.inputField("Flight Number");
pane.add(numberField, 0, 3);

GridPane destinationField = InputField.inputField("Destination");
pane.add(destinationField, 0, 4);

GridPane departureField = InputField.inputField("Departure Location");
pane.add(departureField, 0, 5);

GridPane capacityField = InputField.inputField("Capacity");
pane.add(capacityField, 0, 6);

GridPane takeoffField = InputField.inputField("Takeoff Time");
pane.add(takeoffField, 0, 7);

GridPane landingField = InputField.inputField("Landing Time");
pane.add(landingField, 0, 8);

GridPane dateField = InputField.inputField("Flight Date");
pane.add(dateField, 0, 9);

GridPane statusField = InputField.inputField("Current Status");
pane.add(statusField, 0, 10);

// Enter button
Node enterBtn = new StyledButton1("Enter", e -> {
    // Get text from input fields
    TextField numberFieldText = (TextField) (numberField.getChildren().get(1));
    TextField destinationFieldText = (TextField) (destinationField.getChildren().get(1));
    TextField departureFieldText = (TextField) (departureField.getChildren().get(1));
    TextField capacityFieldText = (TextField) (capacityField.getChildren().get(1));
    TextField takeoffFieldText = (TextField) (takeoffField.getChildren().get(1));
    TextField landingFieldText = (TextField) (landingField.getChildren().get(1));
    TextField dateFieldFieldText = (TextField) (dateField.getChildren().get(1));
    TextField statusFieldText = (TextField) (statusField.getChildren().get(1));

    // Flight model
    Flight flight = new Flight(
        Integer.valueOf(numberFieldText.getText()),
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
pane.add(enterBtn, 0, 11);  
}

}
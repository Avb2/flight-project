package org.com.screens.modify;


import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.com.animations.Animate;
import org.com.bases.Screen;
import org.com.components.buttons.StyledButton1;
import org.com.components.navBars.AdminNavBar;
import org.com.components.panes.CustomFlightPane;
import org.com.components.panes.EditFlightPane;
import org.com.components.panes.ModifyFlightPane;
import org.com.database.FlightDatabase;
import org.com.functionality.flights.CreateFlightsInterface;
import org.com.functionality.flights.ModifyFlightsInterface;
import org.com.state.user.UserState;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EditFlightScreen extends Screen{
    private UserState userState;
    private Connection connection;

    public EditFlightScreen(Connection connection, UserState userState){
        this.connection = connection;
        this.userState = userState;
    }

    @Override 
    public GridPane createPane(Stage stage){
        GridPane pane = new GridPane();
        pane.add(new AdminNavBar(stage, this.userState, this.connection, pane).createComponent(), 0, 0);
        pane.setAlignment(Pos.TOP_CENTER);

        // Load pane for action
        Node addBtn = new StyledButton1("Add", e -> {
            pane.getChildren().clear();

            CreateFlightsInterface createFlightsInterface = (gPane, flight) -> {

                try {
                      // Add data to db 
                    new FlightDatabase(this.connection).addFlight(flight);

                    // Added label
                    Label addedLabel = new Label("Succesffully added");
                    gPane.add(addedLabel, 0 , 12);
                    new Animate(addedLabel).fadeOut(3);
                } catch (SQLException se) {
                    se.printStackTrace();
                }
              

            };

            pane.add(new CustomFlightPane("Add Flights", this.connection, this.userState, stage, createFlightsInterface).createComponent(), 0, 1);
        }).createComponent();
        pane.add(addBtn, 0, 1);


        // Load pane for edit 
        Node editBtn = new StyledButton1("Edit", e -> {
            pane.getChildren().clear();
            CreateFlightsInterface createFlightsInterface = (gPane, flight) -> {
                System.out.println(flight.getDepartureLocation());
                try { 
                    Timestamp takeoffTimestamp = flight.getTakeoffTime() == ""
                        ? null 
                        : Timestamp.valueOf(flight.getTakeoffTime());
                    Timestamp landingTimestamp = flight.getLandingTime() == ""
                        ? null 
                        : Timestamp.valueOf(flight.getLandingTime());
                    Date flightDate = flight.getFlightDate() == "" 
                        ? null 
                        : Date.valueOf(flight.getFlightDate());

                    flight.cleanFields();

                    System.out.println(flight.getId());
                    System.out.println(flight.getDestination());
                    System.out.println(flight.getDepartureLocation());
                    System.out.println(flight.getCapacity());
                    System.out.println(takeoffTimestamp);
                    System.out.println(landingTimestamp);
                    System.out.println(flight.getFlightDate());
                    System.out.println(flight.getStatus());
        
                    // Add data to db
                    new FlightDatabase(this.connection).updateFlight(
                        flight.getId(),
                        flight.getDestination(),
                        flight.getDepartureLocation(),
                        "".equals(flight.getCapacity()) ? Integer.valueOf(flight.getCapacity()) : null,
                        takeoffTimestamp,
                        landingTimestamp,
                        flightDate,
                        flight.getStatus()
                    );
        
                    // Success label
                    Label addedLabel = new Label("Successfully modified");
                    gPane.add(addedLabel, 0, 13);
                    new Animate(addedLabel).fadeOut(3);
                } catch (SQLException se) {
                    se.printStackTrace();
        
                    // Failure label
                    Label errorLabel = new Label("Failed to modify flight");
                    gPane.add(errorLabel, 0, 13);
                    new Animate(errorLabel).fadeOut(3);
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
        
                    // Handle invalid capacity input
                    Label errorLabel = new Label("Invalid input for capacity");
                    gPane.add(errorLabel, 0, 13);
                    new Animate(errorLabel).fadeOut(3);
                }
            };
        
            pane.add(new ModifyFlightPane("Modify Flights", this.connection, this.userState, stage, createFlightsInterface).createComponent(), 0, 1);
        }).createComponent();
        pane.add(editBtn, 0, 2);

        // Load pane for delete actions
        Node deleteBtn = new StyledButton1("Delete", e -> {
            pane.getChildren().clear();
            ModifyFlightsInterface deleteFlightInterface = (gPane, tField) -> {
                TextField textField = (TextField) (tField.getChildren().get(1));
                String text = textField.getText();
                FlightDatabase flightDb = new FlightDatabase(this.connection);

                try {

                    flightDb.deleteFlight(text);
                       // Success label
                    String successText = "Successfully deleted Flight ".concat(text);
                    Label successLabel = new Label(successText);
                    gPane.add(successLabel, 0, 3);

                    //
                   new Animate(successLabel).fadeOut(3);
                
                } catch (SQLException error) {
                    String failText = "Failed ";
                    Label failLabel = new Label(failText);
                    gPane.add(failLabel, 0, 3);

                    //
                    new Animate(failLabel).fadeOut(3);

                   
                }
            };
            pane.add(new EditFlightPane(this.connection, userState, stage, deleteFlightInterface).createComponent(), 0, 1);
        }).createComponent();
        pane.add(deleteBtn, 0, 3);


        return pane;
    }
}

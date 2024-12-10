package org.com.screens.modify;


import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.com.animations.Animate;
import org.com.bases.Screen;
import org.com.components.buttons.StyledButton1;
import org.com.components.navBars.AdminNavBar;
import org.com.components.panes.flight.CustomFlightPane;
import org.com.components.panes.flight.EditFlightPane;
import org.com.components.panes.flight.ModifyFlightPane;
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
public class EditFlightScreen extends Screen {
    private final UserState userState;
    private final Connection connection;

    public EditFlightScreen(Connection connection, UserState userState) {
        this.connection = connection;
        this.userState = userState;
    }

    @Override
    public GridPane createPane(Stage stage) {
        GridPane pane = new GridPane();
        pane.setVgap(10);  
        pane.setHgap(10); 
        pane.setAlignment(Pos.TOP_CENTER);

        // Add NavBar
        GridPane navBar = new AdminNavBar(stage, this.userState, this.connection, pane).createComponent();
        pane.add(navBar, 0, 0);

        // Add buttons with proper spacing and styles
        Node addBtn = new StyledButton1("Add Flight", e -> {
            pane.getChildren().clear();

            CreateFlightsInterface createFlightsInterface = (gPane, flight) -> {
                try {
                    new FlightDatabase(this.connection).addFlight(flight);
                    
                    Label addedLabel = new Label("Successfully added");
                    addedLabel.getStyleClass().add("subtitle");  
                    gPane.add(addedLabel, 0, 12);
                    new Animate(addedLabel).fadeOut(3);
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            };
        new CustomFlightPane("Add Flights", stage, createFlightsInterface).createPane(pane, userState, connection);
        }).createComponent();
        addBtn.getStyleClass().add("button-1");

        pane.add(addBtn, 0, 1);

        Node editBtn = new StyledButton1("Edit Flight", e -> {
            pane.getChildren().clear();

            CreateFlightsInterface createFlightsInterface = (gPane, flight) -> {
                try {
                    Timestamp takeoffTimestamp = flight.getTakeoffTime().isEmpty() ? null : Timestamp.valueOf(flight.getTakeoffTime());
                    Timestamp landingTimestamp = flight.getLandingTime().isEmpty() ? null : Timestamp.valueOf(flight.getLandingTime());
                    Date flightDate = flight.getFlightDate().isEmpty() ? null : Date.valueOf(flight.getFlightDate());

                    flight.cleanFields();

                    new FlightDatabase(this.connection).updateFlight(
                        flight.getId(),
                        flight.getDestination(),
                        flight.getDepartureLocation(),
                        flight.getCapacity().isEmpty() ? null : Integer.valueOf(flight.getCapacity()),
                        takeoffTimestamp,
                        landingTimestamp,
                        flightDate,
                        flight.getStatus()
                    );

                    Label addedLabel = new Label("Successfully modified");
                    addedLabel.getStyleClass().add("subtitle");  
                    gPane.add(addedLabel, 0, 13);
                    new Animate(addedLabel).fadeOut(3);
                } catch (SQLException se) {
                    se.printStackTrace();
                    Label errorLabel = new Label("Failed to modify flight");
                    errorLabel.getStyleClass().add("subtitle");  
                    gPane.add(errorLabel, 0, 13);
                    new Animate(errorLabel).fadeOut(3);
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    Label errorLabel = new Label("Invalid input for capacity");
                    errorLabel.getStyleClass().add("subtitle");  
                    gPane.add(errorLabel, 0, 13);
                    new Animate(errorLabel).fadeOut(3);
                }
            };

            new ModifyFlightPane("Modify Flights", stage, createFlightsInterface).createPane(pane, userState, connection);
        }).createComponent();
        editBtn.getStyleClass().add("button-1");  
        pane.add(editBtn, 0, 2);

        Node deleteBtn = new StyledButton1("Delete Flight", e -> {
            pane.getChildren().clear();
            ModifyFlightsInterface deleteFlightInterface = (gPane, tField) -> {
                TextField textField = (TextField) (tField.getChildren().get(1));
                String text = textField.getText();
                FlightDatabase flightDb = new FlightDatabase(this.connection);

                try {
                    flightDb.deleteFlight(text);
                    String successText = "Successfully deleted Flight ".concat(text);
                    Label successLabel = new Label(successText);
                    successLabel.getStyleClass().add("subtitle");  
                    gPane.add(successLabel, 0, 5);
                    new Animate(successLabel).fadeOut(3);
                } catch (SQLException error) {
                    String failText = "Failed to delete flight";
                    Label failLabel = new Label(failText);
                    failLabel.getStyleClass().add("subtitle"); 
                    gPane.add(failLabel, 0, 5);
                    new Animate(failLabel).fadeOut(3);
                }
            };
            new EditFlightPane(stage, deleteFlightInterface).createPane(pane, userState, connection);
        }).createComponent();
        deleteBtn.getStyleClass().add("button-1");  
        pane.add(deleteBtn, 0, 3);

        return pane;
    }
}

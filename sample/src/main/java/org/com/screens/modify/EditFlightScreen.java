package org.com.screens.modify;


import java.sql.Connection;
import java.sql.SQLException;

import org.com.animations.Animate;
import org.com.bases.Screen;
import org.com.components.navBars.AdminNavBar;
import org.com.components.panes.CustomFlightPane;
import org.com.components.panes.EditFlightPane;
import org.com.database.FlightDatabase;
import org.com.functionality.flights.CreateFlightsInterface;
import org.com.functionality.flights.ModifyFlightsInterface;
import org.com.state.user.UserState;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
        Button addBtn = new Button("Add");
        addBtn.setOnAction(e -> {
            pane.getChildren().clear();

            CreateFlightsInterface createFlightsInterface = (gPane, flight) -> {

                try {
                      // Add data to db 
                    new FlightDatabase(this.connection).addFlight(flight);

                    // Added label
                    Label addedLabel = new Label("Succesffully added");
                    gPane.add(addedLabel, 0 , 8);
                    new Animate(addedLabel).fadeOut(3);
                } catch (SQLException se) {
                    se.printStackTrace();
                }
              

            };

            pane.add(new CustomFlightPane(this.connection, this.userState, stage, createFlightsInterface).createComponent(), 0, 1);
        });
        pane.add(addBtn, 0, 1);


        // Load pane for edit 
        Button editBtn = new Button("Edit");
        editBtn.setOnAction(e -> {
            pane.getChildren().clear();
            ModifyFlightsInterface editFlightInterface = (gPane, tField) -> {
                System.out.println("Modify");
            };
            pane.add(new EditFlightPane(this.connection, this.userState, stage, editFlightInterface).createComponent(), 0, 1);
        });
        pane.add(editBtn, 0, 2);


        // Load pane for delete actions
        Button deleteBtn = new Button("Delete");
        deleteBtn.setOnAction(e -> {
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
        });
        pane.add(deleteBtn, 0, 3);


        return pane;
    }
}

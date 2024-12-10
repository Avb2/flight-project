package org.com.screens.flights;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;

import org.com.bases.Screen;
import org.com.components.buttons.StyledButton1;
import org.com.components.cards.FlightCard;
import org.com.components.navBars.AdminNavBar;
import org.com.components.navBars.FilterBar;
import org.com.components.navBars.NavBar;
import org.com.constants.Sizes;
import org.com.database.BookingDatabase;
import org.com.database.FlightDatabase;
import org.com.database.parser.ResultSetParser;
import org.com.functionality.TimestampHandling;
import org.com.state.user.UserState;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;



public class ManageFlights extends Screen{
    private Connection connection;
    private UserState userState;
    

    public ManageFlights(Connection connection, UserState userState){
        this.connection = connection;
        this.userState = userState;
    }

    @Override
public GridPane createPane(Stage stage) {
    GridPane pane = new GridPane();
    pane.getStyleClass().add("background-primary");
    pane.setPadding(new Insets(10, 10, 10, 10));
    pane.setAlignment(Pos.TOP_CENTER);
    pane.setVgap(Sizes.largeGap);

    // Navbar (Admin or User)
    GridPane navbar = userState.getPermissions().matches("admin") 
        ? (GridPane) new AdminNavBar(stage, this.userState, this.connection, pane).createComponent() 
        : (GridPane) new NavBar(this.connection, stage, this.userState, pane).createComponent();
    pane.add(navbar, 0, 0);

    



    // Title Label
    Label titleLabel = new Label("Manage Flights");
    titleLabel.getStyleClass().add("title");
    pane.add(titleLabel, 0, 2);

    // Scroll Pane for displaying flight results
    ScrollPane scrollPane = new ScrollPane();
    scrollPane.getStyleClass().add("text-field-1"); 
    pane.add(scrollPane, 0, 3);
    
    GridPane subPane = new GridPane();
    scrollPane.setContent(subPane);

    // Keys for flight data
    String[] keys = new String[]{"number", "departureLocation", "destination", "status", "takeoff", "landing", "date"};

    try {
        Map<String, String>[] flightData = new ResultSetParser(new FlightDatabase(this.connection).retrieveFlights()).parseToStringDict(keys);
        
        for (int i = 0; i < flightData.length; i++) {
            if (flightData[i] != null) {
                GridPane tempPane = new GridPane();

                Node flightCard = new FlightCard(
                    flightData[i].get(keys[0]), 
                    flightData[i].get(keys[1]), 
                    flightData[i].get(keys[2]), 
                    flightData[i].get(keys[3]),
                    TimestampHandling.formatTimestamp(flightData[i].get(keys[4])),
                    TimestampHandling.formatTimestamp(flightData[i].get(keys[5])),
                    flightData[i].get(keys[6])
                ).createComponent();
                tempPane.add(flightCard, 0, 0, 1, 2);

                final int index = i;
                int flightNumber = Integer.parseInt(flightData[index].get(keys[0]));

                // Add Button
                Node addBtn = new StyledButton1("+", e -> {
                    try {
                        FlightDatabase flightDb = new FlightDatabase(this.connection);
                        BookingDatabase bookingDb = new BookingDatabase(this.connection);

                        ResultSet selectedFlight = flightDb.retrieveFlight(flightNumber);
                        ResultSetParser selectedFlightRP = new ResultSetParser(selectedFlight);
                        Map<String, Object>[] selectedFlightDict = selectedFlightRP.parse(
                            new String[]{"takeoff", "landing"}, 
                            new Class<?>[]{Timestamp.class, Timestamp.class}
                        );
                        Map<String, Object> selectedFlightData = selectedFlightDict[0];
                        Timestamp selectedTakeoff = (Timestamp) selectedFlightData.get("takeoff");
                        Timestamp selectedLanding = (Timestamp) selectedFlightData.get("landing");

                        ResultSet bookedTimes = bookingDb.retrieveTimes(flightNumber);
                        ResultSetParser bookedTimesRP = new ResultSetParser(bookedTimes);
                        Map<String, Object>[] bookedTimesDict = bookedTimesRP.parse(
                            new String[]{"takeoff", "landing"}, 
                            new Class<?>[]{Timestamp.class, Timestamp.class}
                        );

                        boolean conflict = false;
                        if (bookedTimesDict != null) {
                            for (Map<String, Object> times : bookedTimesDict) {
                                Timestamp existingTakeoff = (Timestamp) times.get("takeoff");
                                Timestamp existingLanding = (Timestamp) times.get("landing");

                                if ((existingTakeoff.before(selectedLanding) && selectedTakeoff.after(existingTakeoff)) ||
                                    (existingTakeoff.before(selectedTakeoff) && selectedLanding.after(existingTakeoff)) ||
                                    (selectedTakeoff.before(existingTakeoff) && selectedLanding.after(existingLanding))) {
                                    conflict = true;
                                }
                            }
                        }

                        if (!conflict) {
                            bookingDb.createBooking(this.userState.getUid(), flightNumber);
                            System.out.println("Added flight");
                        } else {
                            System.out.println("Time Conflict!");
                        }
                    } catch (SQLException se) {
                        se.printStackTrace();
                    }
                }).createComponent();
                addBtn.getStyleClass().add("button-2-small");
                tempPane.add(addBtn, 1, 0);

                GridPane searchBar = new FilterBar(stage, userState, connection, scrollPane).createComponent();
                pane.add(searchBar, 0, 1); 

                // Delete Button
                Button deleteBtn = new Button("-");
                deleteBtn.getStyleClass().add("button-2-small");
                deleteBtn.setOnAction(e -> {
                    try {
                        new BookingDatabase(this.connection).removeBooking(this.userState.getUid(), flightNumber);
                    } catch (SQLException se) {
                        se.printStackTrace();
                    }
                });
                tempPane.add(deleteBtn, 1, 1);

                subPane.add(tempPane, 0, i);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return pane;
}




    }
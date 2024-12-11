package org.com.components.navBars;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Map;

import org.com.bases.Component;
import org.com.components.buttons.StyledButton1;
import org.com.components.cards.FlightCard;
import org.com.database.BookingDatabase;
import org.com.database.FlightDatabase;
import org.com.database.parser.ResultSetParser;
import org.com.functionality.TimestampHandling;
import org.com.state.user.UserState;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FilterBar extends Component {
    private final Stage stage;
    private UserState userState;
    private Connection connection;
    private ScrollPane mainPane;

    // Constructor to initialize the required fields
    public FilterBar(Stage stage, UserState userState, Connection connection, ScrollPane mainPane) {
        this.stage = stage;
        this.userState = userState;
        this.connection = connection;
        this.mainPane = mainPane;
    }

    @Override
    public GridPane createComponent() {
            // Keys for flight data
    String[] keys = new String[]{"number", "departureLocation", "destination", "status", "takeoff", "landing", "date"};

        GridPane pane = new GridPane();
        pane.getStyleClass().add("filter-bar");
        pane.setAlignment(Pos.CENTER);
        pane.setVgap(10);
        pane.setHgap(10);

        // Input fields for filtering
        TextField destinationField = new TextField();
        destinationField.setPromptText("Enter destination");

        TextField departureLocationField = new TextField();
        departureLocationField.setPromptText("Enter departure location");

        DatePicker datePicker = new DatePicker();
        ComboBox<String> timeFilterComboBox = new ComboBox<>();
        timeFilterComboBox.getItems().addAll("Before", "After");

        TextField capacityField = new TextField();
        capacityField.setPromptText("Enter capacity");

        // Add the filters to the grid
        pane.add(new Label("Destination:"), 0, 0);
        pane.add(destinationField, 1, 0);

        pane.add(new Label("Departure Location:"), 0, 1);
        pane.add(departureLocationField, 1, 1);

        pane.add(new Label("Flight Date:"), 0, 2);
        pane.add(datePicker, 1, 2);

        pane.add(new Label("Time Filter (Takeoff/Landing):"), 0, 3);
        pane.add(timeFilterComboBox, 1, 3);

        pane.add(new Label("Capacity:"), 0, 4);
        pane.add(capacityField, 1, 4);

        // Filter button
        Button filterButton = new Button("Filter Flights");
        filterButton.setOnAction(e -> {
            try {
                int capacity = 0;
                if (!capacityField.getText().matches("")) {
                    capacity = Integer.parseInt(capacityField.getText());
                }
                ResultSet resultSet = this.applyFilters(
                    destinationField.getText(),
                    departureLocationField.getText(),
                    datePicker.getValue(),
                    timeFilterComboBox.getValue(),
                    capacity
                );

            


                ResultSetParser rsp = new ResultSetParser(resultSet);

                if (resultSet != null){
                    updateFlightDisplay(rsp.parseToStringDict(keys), mainPane);
                }

                
            } catch (NumberFormatException ex) {
                System.out.println("Invalid");
            } catch (SQLException ex) {
                // Handle SQL exception
                ex.printStackTrace();
            }
        });
        
        pane.add(filterButton, 0, 5, 2, 1);


        Button clearButton = new Button("Clear");
        clearButton.setOnAction(
            e -> {
                try {
        Map<String, String>[] flightData = new ResultSetParser(new FlightDatabase(this.connection).retrieveFlights()).parseToStringDict(keys);
    
                        updateFlightDisplay(flightData, mainPane);
                    
                } catch (SQLException exc) {
                }
               

            }
        );
        pane.add(clearButton, 1, 5, 2, 1);
        return pane;
    }

    private ResultSet applyFilters(String destination, String departureLocation, LocalDate flightDate, String timeFilter, int capacity) throws SQLException {
        FlightDatabase flightDb = new FlightDatabase(connection);

        Date sqlDate = null;
        if (flightDate != null) {
            sqlDate = Date.valueOf(flightDate);
        }
    
        // Apply filters
        if (!destination.isEmpty()) {
            return flightDb.findFlightsByDestination(destination);
        }
        if (!departureLocation.isEmpty()) {
            return flightDb.findFlightsByDepartureLocation(departureLocation);
        }
        if (sqlDate != null) {
            return flightDb.findFlightsByDate(sqlDate, "Before".equals(timeFilter));
        }
        if (capacity > 0) {
            return flightDb.findFlightsByCapacity(capacity, true);
        }
        return null; 
    }private void updateFlightDisplay(Map<String, String>[] flightData, ScrollPane subPane) {
    VBox content = new VBox();
    content.setSpacing(10); // Set spacing between each flight card

    // Loop through the flight data and create tempPanes
    for (int i = 0; i < flightData.length; i++) {
        if (flightData[i] != null) {
            GridPane tempPane = new GridPane();
            
            // Create the flight card
            Node flightCard = new FlightCard(
                flightData[i].get("number"), 
                flightData[i].get("departureLocation"), 
                flightData[i].get("destination"), 
                flightData[i].get("status"),
                TimestampHandling.formatTimestamp(flightData[i].get("takeoff")),
                TimestampHandling.formatTimestamp(flightData[i].get("landing")),
                flightData[i].get("date")
            ).createComponent();

            // Add the flight card to the tempPane
            tempPane.add(flightCard, 0, 0, 1, 2);

            final int index = i;
            int flightNumber = Integer.parseInt(flightData[index].get("number"));
            
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
                        System.out.println("Time Conflict");
                    }
                } catch (SQLException se) {
                    se.printStackTrace();
                }            }).createComponent();
            addBtn.getStyleClass().add("button-2-small");
            tempPane.add(addBtn, 1, 0);

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

            content.getChildren().add(tempPane);
        }
    }
    subPane.setContent(content);
}
}
package org.com.components.cards;

import org.com.bases.Component;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;



public class FlightCard extends Component {
    private String number;
    private String departureLocation;
    private String destination;
    private String status;
    private String takeoff;
    private String landing;
    private String date;

    public FlightCard(String number, String departureLocation, String destination, String status, String takeoff, String landing, String date) {
        this.number = number;
        this.departureLocation = departureLocation;
        this.destination = destination;
        this.status = status;
        this.takeoff = takeoff;
        this.landing = landing;
        this.date = date;
    }

    @Override
public Node createComponent() {
    GridPane pane = new GridPane();
    pane.getStyleClass().add("flight-card-pane");
    pane.setHgap(10);
    pane.setVgap(10);
    pane.setMinWidth(400);
    pane.setPadding(new Insets(15));
    pane.setAlignment(Pos.CENTER);

    // Flight Number Label
    Label flightNumLabel = new Label("Flight: " + this.number);
    flightNumLabel.getStyleClass().add("flight-number-label");
    pane.add(flightNumLabel, 0, 0, 2, 1);

    // Status Label
    Label flightStatus = new Label("Status: " + this.status.toUpperCase());
    flightStatus.getStyleClass().add("flight-status-label");
    GridPane.setHalignment(flightStatus, HPos.RIGHT);
    pane.add(flightStatus, 2, 0);

    // Departure Location Label
    Label departureLocationLabel = new Label("From: " + this.departureLocation);
    departureLocationLabel.getStyleClass().add("departure-location-label");
    pane.add(departureLocationLabel, 0, 1);

    // Arrow Label
    Label toArrowLabel = new Label("➔");
    toArrowLabel.getStyleClass().add("arrow-label");
    GridPane.setHalignment(toArrowLabel, HPos.CENTER);
    pane.add(toArrowLabel, 1, 1);

    // Destination Label
    Label destinationLabel = new Label("To: " + this.destination);
    destinationLabel.getStyleClass().add("destination-label");
    pane.add(destinationLabel, 2, 1);

    // Takeoff and Landing Time Labels
    Label takeoffLabel = new Label("Takeoff: " + this.takeoff);
    takeoffLabel.getStyleClass().add("time-label");
    pane.add(takeoffLabel, 0, 2);

    Label landingLabel = new Label("Landing: " + this.landing);
    landingLabel.getStyleClass().add("time-label");
    GridPane.setHalignment(landingLabel, HPos.RIGHT);
    pane.add(landingLabel, 2, 2);

    // Flight Date Label
    Label dateLabel = new Label("Date: " + this.date);
    dateLabel.getStyleClass().add("date-label");
    GridPane.setColumnSpan(dateLabel, 3);
    GridPane.setHalignment(dateLabel, HPos.CENTER);
    pane.add(dateLabel, 0, 3, 3, 1);

    return pane;
}

}

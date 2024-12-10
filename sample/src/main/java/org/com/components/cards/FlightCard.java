package org.com.components.cards;

import org.com.bases.Component;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;



public class FlightCard extends Component {
    private String number;
    private String departureLocation;
    private String destination;
    private String status;

    public FlightCard(String number, String departureLocation, String destination, String status) {
        this.number = number;
        this.departureLocation = departureLocation;
        this.destination = destination;
        this.status = status;
    }

    @Override
public Node createComponent() {
    GridPane pane = new GridPane();
    pane.getStyleClass().add("flight-card-pane");

    pane.setHgap(10);
    pane.setVgap(10);
    pane.setMinWidth(400);
    pane.setAlignment(Pos.CENTER);


    // Flight Number label
    Label flightNumLabel = new Label("Flight: " + this.number);
    flightNumLabel.getStyleClass().add("flight-number-label");
    pane.add(flightNumLabel, 0, 0, 3, 1);

    // Status label
    Label flightStatus = new Label("Status: " + this.status);
    flightStatus.getStyleClass().add("flight-status-label");
    GridPane.setHalignment(flightStatus, HPos.RIGHT);
    pane.add(flightStatus, 3, 0);

    // Departure Location label
    Label departureLocationLabel = new Label("From: " + this.departureLocation);
    departureLocationLabel.getStyleClass().add("departure-location-label");
    pane.add(departureLocationLabel, 0, 1);

    // Arrow label
    Label toArrowLabel = new Label("➔");
    toArrowLabel.getStyleClass().add("arrow-label");
    GridPane.setHalignment(toArrowLabel, HPos.CENTER);
    pane.add(toArrowLabel, 1, 1);

    // Destination label
    Label destinationLabel = new Label("To: " + this.destination);
    destinationLabel.getStyleClass().add("destination-label");
    GridPane.setHalignment(destinationLabel, HPos.RIGHT);
    pane.add(destinationLabel, 2, 1);

    // Time label
    Label timeLabel = new Label("Time: TBD");
    timeLabel.getStyleClass().add("time-label");
    GridPane.setHalignment(timeLabel, HPos.CENTER);
    pane.add(timeLabel, 1, 2);

    return pane;
}

}

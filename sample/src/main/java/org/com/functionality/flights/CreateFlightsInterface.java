package org.com.functionality.flights;

import org.com.models.Flight;

import javafx.scene.layout.GridPane;


@FunctionalInterface
public interface CreateFlightsInterface {
    void onClick(GridPane pane, Flight flight);
    
}
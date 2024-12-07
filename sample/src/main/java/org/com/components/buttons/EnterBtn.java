package org.com.components.buttons;

// This class will make it so an enter button can be used throughout the program

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

// Creates the class EnterBtn

public class EnterBtn {

    // Creates the EnterButton method with an event handler that defines the action being performed <>

    public static Button EnterButton(EventHandler<ActionEvent> event) {

        // Creates a Button object with the label "Enter"

        Button button = new Button("Enter");

        // A style is configured in the CSS file and the getStyleMethod.add calls it, (button-1) style class

        button.getStyleClass().add("button-1");

        // Sets the button preferred width and height to help with consistency

        button.setPrefSize(120, 30);

        // Attaches an event handler to the button's action event
        // The event handler, which defines the action, is passed as an argument to this method.

        // Side note, the logic for what happens when the button is clicked is not defined within these classes,
        // rather this class is designed to create the button and set the action to be executed without defining the
        // actual action.

        button.setOnAction(event);

        // Returns the configured button to be used in the application GUI

        return button;
    }
}

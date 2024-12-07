package org.com.components.buttons;

// This class will serve to create an edit flight button that can be used across multiple screens in the application

import org.com.bases.Component;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

// Class created for a button to Edit a user's flight
// The component class refers to an element in the user interface

public class EditFlightsBtn extends Component{

    // Creating A runnable to store the action
    // by storing the action the button will not directly depend on the execution when clicked
    // Keeping button logic separate from action logic so the button class can be reused.

    private Runnable onAction;

    // Creates the button with the specified action

    public EditFlightsBtn(Runnable onAction) {

        // assigns the provided action (the runnable)

        this.onAction = onAction;
    }

    // The node will create the visual component of the "Edit Flights button".
    // Node is the base class for all objects part of a scene

    @Override
    public Node createComponent(){

        // Creates a button with the text "Edit Flights"

        Button button = new Button("Edit Flights");

        // e -> is an event handler for the button's action event
        // When it is clicked it will run the onAction runnable

        button.setOnAction(e-> this.onAction.run());

        // Returns the created button as a Node, which can be added to the scene

        return button;
    }
}

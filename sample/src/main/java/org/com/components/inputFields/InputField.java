package org.com.components.inputFields;

import org.com.constants.Sizes;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


public class InputField {
    public static GridPane inputField(String label){
        GridPane pane = new GridPane();
        pane.setHgap(Sizes.mediumGap);

        // Username Label
        Label usernamLabel = new Label(label);
        usernamLabel.getStyleClass().add("text-field-label-1");
        pane.add(usernamLabel, 0, 0);

        // Username Field
        TextField usernameField = new TextField();
        usernameField.getStyleClass().add("text-field-1");
        usernameField.setPrefSize(210, 32);
        pane.add(usernameField, 1, 0);




        return pane;
    }
}
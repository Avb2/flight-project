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
        Label headerLabel = new Label(label);
        headerLabel.getStyleClass().add("text-field-label-1");
        headerLabel.setPrefSize(150, 32);
        pane.add(headerLabel, 0, 0);

        // Username Field
        TextField field = new TextField();
        field.getStyleClass().add("text-field-1");
        field.setPrefSize(210, 32);
        pane.add(field, 1, 0);




        return pane;
    }
}
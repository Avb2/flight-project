package org.com.components.buttons.styled;

import org.com.bases.Component;
import javafx.scene.control.Button;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class StyledButton1 extends Component{
    private String label;
    private EventHandler<ActionEvent> event;

    public StyledButton1(String label, EventHandler<ActionEvent> event){
        this.label = label;
        this.event = event;
    }

    @Override
    public Node createComponent(){
        Button button = new Button(this.label);
        button.setOnAction(this.event);
        button.setPrefSize(120,30);
        button.getStyleClass().add("button-1");

        return button;
    }
}

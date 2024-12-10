package org.com.components.panes;


import java.sql.Connection;

import org.com.bases.Component;
import org.com.components.buttons.StyledButton1;
import org.com.components.inputFields.InputField;
import org.com.functionality.flights.ModifyFlightsInterface;
import org.com.functionality.navigation.PushEditFlight;
import org.com.state.user.UserState;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;




public class EditFlightPane extends Component{
    private Connection connection;
    private UserState userState;
    private Stage stage;
    public ModifyFlightsInterface modifyFlightInterface;

    public EditFlightPane(Connection connection, UserState userState, Stage stage, ModifyFlightsInterface modifyFlightInterface){
        this.connection = connection;
        this.userState = userState;
        this.stage = stage;
        this.modifyFlightInterface = modifyFlightInterface;
    }


    @Override
    public Node createComponent(){
        GridPane pane = new GridPane();
        pane.getStyleClass().add("background-primary");

        Label label = new Label("Delete Flight");
        label.getStyleClass().add("subtitle");
        pane.add(label, 0, 0);

        Node backBtn = new StyledButton1("Back", e -> {
            new PushEditFlight().push(this.connection, this.userState, this.stage);
        }).createComponent();
        pane.add(backBtn, 0, 1);

        // Flight Number field     
        GridPane flightNumberField = InputField.inputField("Flight Number");
        pane.add(flightNumberField, 0, 2);
        
      
        Node enterBtn = new StyledButton1("Enter", e -> {
            TextField textField = (TextField) (flightNumberField.getChildren().get(1));
            this.modifyFlightInterface.onClick(pane, flightNumberField);
        }).createComponent();
        pane.add(enterBtn, 0, 4);
    
        return pane;
    }

    public Node createComponent(GridPane newPane){
        GridPane pane = new GridPane();

        Node backBtn = new StyledButton1("Back", e -> {
            new PushEditFlight().push(this.connection, this.userState, this.stage);
        }).createComponent();
        pane.add(backBtn, 0, 0);

        // Flight Number field     
        GridPane flightNumberField = InputField.inputField("Flight Number");
        pane.add(flightNumberField, 0, 1);
        


        Node enterBtn = new StyledButton1("Enter",
            e -> this.modifyFlightInterface.onClick(pane, flightNumberField)
        ).createComponent();
        pane.add(enterBtn, 0, 4);
    
        return pane;
    }


    
}

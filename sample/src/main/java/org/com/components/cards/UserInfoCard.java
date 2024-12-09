package org.com.components.cards;


import org.com.bases.Component;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;



public class UserInfoCard extends Component{
    private String label;
    private String info;

    public UserInfoCard(String label, String info){
        this.label = label;
        this.info = info;
    }

    @Override
    public Node createComponent(){
        GridPane pane = new GridPane();
        pane.setBorder(new Border(new BorderStroke(
            Color.BLACK,               
            BorderStrokeStyle.SOLID,    
            new CornerRadii(5),      
            new BorderWidths(2)))); 

        pane.setPadding(new Insets(10, 10, 10, 10));

        Label titleLabel = new Label(label);
        pane.add(titleLabel, 0, 0);

        Label infoLabel = new Label(info);
        pane.add(infoLabel, 0, 1);

        return pane;
    }
}
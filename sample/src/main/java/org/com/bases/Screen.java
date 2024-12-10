package org.com.bases;

import org.com.constants.Sizes;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public abstract class Screen {

    public abstract GridPane createPane(Stage stage);


    public Scene createScreen(Stage stage){
        Scene scene = new Scene(this.createPane(stage), Sizes.primaryHeight, Sizes.primaryWidth);
        scene.getStylesheets().add(getClass().getResource("/org/com/style.css").toExternalForm());

        return scene;
    }
   
}


package org.com.bases;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import org.com.constants.Sizes;


public abstract class Screen {

    public abstract GridPane createPane(Stage stage);


    public Scene createScreen(Stage stage){
        Scene scene = new Scene(this.createPane(stage), Sizes.primaryHeight, Sizes.primaryWidth);
        scene.getStylesheets().add(getClass().getResource("/org/com/style.css").toExternalForm());

        return scene;
    }


   
}


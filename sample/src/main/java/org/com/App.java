package org.com;


import java.sql.Connection;

import org.com.screens.landing.SplashScreen;
import org.com.state.database.Pool;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        // Create connection to db
        Pool pool = new Pool();
        Connection connection = pool.returnConnection();
        if (connection != null){

            Scene scene = new SplashScreen(connection).createScreen(stage);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("Error connecting to Azure DB");
        }
    }


    public static void main(String[] args) {
        launch();
    }

}
package org.com;


import java.sql.Connection;

import org.com.state.database.Pool;

import javafx.application.Application;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        // Db pool
        Pool pool = new Pool();
        // Pass this connection around app for database connectivity
        Connection connection = pool.returnConnection();

        
        if (connection != null){
            // Push splash Screen will go here
            stage.show();
        } else {
            System.out.println("Error with database connection");
        }
    }

    public static void main(String[] args) {
        launch();
    }

}
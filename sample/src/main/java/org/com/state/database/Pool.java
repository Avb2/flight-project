package org.com.state.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import org.com.env.Environment;





public class Pool {
    private final String url;
    private final String user; 
    private final String password; 
    private Connection connection;


    public Pool(){
        Environment dotenv = new Environment();
        Map<String, String> dotenvDetails = dotenv.getDbDetails();

        this.url = dotenvDetails.get("url");
        this.user = dotenvDetails.get("user");
        this.password = dotenvDetails.get("password");
    }

    // Create a connection to the database;
    private void connect() {
        try {
            // Connect to the database
            Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
            // Set the instance var connection
            this.setConnection(conn);
            System.out.println("Connected to the Azure database successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Getter
    public Connection returnConnection(){
        this.connect();
        return this.connection;
    }

    // Setter
    private void setConnection(Connection connection){
        this.connection = connection;
    }
}
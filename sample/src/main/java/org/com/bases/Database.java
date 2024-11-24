package org.com.bases;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;



public abstract class Database {
    private final Connection connection;

    public Database(Connection connection){
        this.connection = connection;
    }

    // Query the database and return info
    protected ResultSet query(String query){
        ResultSet result = null;
        
        try{
            // Initialize statement
            Statement statement =  this.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            result = statement.executeQuery(query);

        } catch (SQLException e){
            System.err.println(e);
        }
        return result;
    }

    // Query the db
    protected ResultSet query(String query, String[] args){
        // Initialize statement
        PreparedStatement statement = null;

        // Create statement
        try {
            statement =  this.connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }  

        for (int index = 0; index < args.length; index++){
            try {
                statement.setString(index + 1, args[index]);
            } catch (SQLException e) {
            }
        }

        // Initialize result set
        ResultSet result = null;
        
        // Retrieve result from the db
        try {
            result = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // Query the db
    protected ResultSet query(String query, Object[] args, Object[] types){
        // Initialize statement
        PreparedStatement statement = null;

        // Create statement
        try {
            statement =  this.connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }  

        for (int i = 0; i < args.length; i++){
            try {
                if (args[i] != null){
                    if (types[i] instanceof Integer) {
                        statement.setInt(i + 1, (Integer) (args[i]));
                    } else if (types[i] instanceof String) {
                        statement.setString(i + 1, (String) (args[i]));
                    } else if (types[i] instanceof Double) {
                        statement.setDouble(i + 1, (Double) (args[i]));
                    } else if (types[i] instanceof Date) {
                        statement.setDate(i + 1, (Date)(args[i]));
                    } else if (types[i] instanceof Time) {
                        statement.setTime(i + 1, (Time)(args[i]));
                    }
                }
                
            
            } catch (SQLException e) {
            }
        }

        // Initialize result set
        ResultSet result = null;
        
        // Retrieve result from the db
        try {
            result = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


    // Update db
    protected void updateQuery(String query, Object[] args, Object[] types){
    System.out.println(args.length);
    // Initialize statement
    PreparedStatement statement = null;

    // Create statement
    try {
        statement =  this.connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    } catch (SQLException e) {
        e.printStackTrace();
    }  

    for (int i = 0; i < args.length; i++){
        try {
            if (types[i] instanceof Integer) {
                statement.setInt(i + 1, (Integer) (args[i]));
            } else if (types[i] instanceof String) {
                statement.setString(i + 1, (String) (args[i]));
            } else if (types[i] instanceof Double) {
                statement.setDouble(i + 1, (Double) (args[i]));
            
            } else if (types[i] instanceof Date) {
                statement.setDate(i + 1, (Date)(args[i]));
            } else if (types[i] instanceof Time) {
                statement.setTime(i + 1, (Time)(args[i]));
            } else if (types[i] instanceof Timestamp) {
                statement.setTimestamp (i + 1, (Timestamp)(args[i]));
            }
        } catch (SQLException e) {
        }
    }

    // Update result from the db
    try {
        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }

}


}


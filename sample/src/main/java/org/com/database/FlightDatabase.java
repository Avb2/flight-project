package org.com.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.com.bases.Database;
import org.com.models.Flight;


public class FlightDatabase extends Database {
    public FlightDatabase(Connection connection){
        super(connection);
    }


    // By times
    public ResultSet findFlightsByTakeoffTime(Timestamp time, boolean isBefore) throws SQLException {
        String query = isBefore ? 
            "SELECT * FROM Flights WHERE takeoff < ?" : 
            "SELECT * FROM Flights WHERE takeoff > ?";
        return super.query(query, new Object[] {time}, new Object[] {Timestamp.class});
    }
    
    public ResultSet findFlightsByLandingTime(Timestamp time, boolean isBefore) throws SQLException {
        String query = isBefore ? 
            "SELECT * FROM Flights WHERE landing < ?" : 
            "SELECT * FROM Flights WHERE landing > ?";
        return super.query(query, new Object[] {time}, new Object[] {Timestamp.class});
    }



   // Capacity
    public ResultSet findFlightsByCapacity(int capacity, boolean isGreaterThan) throws SQLException {
        String query = isGreaterThan ? 
            "SELECT * FROM Flights WHERE currentCapacity > ?" : 
            "SELECT * FROM Flights WHERE currentCapacity < ?";
        return super.query(query, new Object[] {capacity}, new Object[] {Integer.class});
    }



    // Destination
    public ResultSet findFlightsByDestination(String destination) throws SQLException {
        String query = "SELECT * FROM Flights WHERE destination LIKE ?";
        return super.query(query, new Object[] {"%" + destination + "%"}, new Object[] {String.class});
    }

    

    // departure location
    public ResultSet findFlightsByDepartureLocation(String departureLocation) throws SQLException {
        String query = "SELECT * FROM Flights WHERE departureLocation LIKE ?";
        return super.query(query, new Object[] {"%" + departureLocation + "%"}, new Object[] {String.class});
    }


    public ResultSet findFlightsByDate(Date date, boolean isBefore) throws SQLException {
        String query = isBefore ? 
            "SELECT * FROM Flights WHERE date < ?" : 
            "SELECT * FROM Flights WHERE date > ?";
        return super.query(query, new Object[] {date}, new Object[] {Date.class});
    }
    
    






    public ResultSet retrieveFlights() throws SQLException{
        return super.query("SELECT * FROM Flights");
    }

    public void deleteFlight(String flightNumber) throws SQLException{
        super.updateQuery(
            "DELETE FROM Flights WHERE number =?",
            new Object[] {Integer.parseInt(flightNumber)},
            new Object[] {Integer.class}
        );
    }


    public void addFlight(Flight flight) throws SQLException{
        

            for (Object i: new Object[] {flight.getDestination(), flight.getDepartureLocation(), Integer.parseInt(flight.getCapacity()), 0, Timestamp.valueOf(flight.getTakeoffTime()), Timestamp.valueOf(flight.getLandingTime()), Date.valueOf(flight.getFlightDate()), flight.getStatus()}){
                System.out.println(i);
            }

            super.updateQuery(
            "INSERT INTO Flights (destination, departurelocation, capacity, currentcapacity, takeoff, landing, date, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
            new Object[] {flight.getDestination(), flight.getDepartureLocation(), Integer.parseInt(flight.getCapacity()), 0, Timestamp.valueOf(flight.getTakeoffTime()), Timestamp.valueOf(flight.getLandingTime()),  Date.valueOf(flight.getFlightDate()), flight.getStatus()},
            new Object[] {String.class, String.class, Integer.class, Integer.class, Timestamp.class, Timestamp.class, Date.class, String.class}
        );
    }


    public ResultSet retrieveFlight(int fid) throws SQLException{
        return super.query("SELECT * FROM Flights WHERE number = ?", new Object[] {fid}, new Object[] {Integer.class});
    }


    public void updateFlight(
    int fid, 
    String destination, 
    String departureLocation, 
    Integer capacity, 
    Timestamp takeoff, 
    Timestamp landing, 
    Date date, 
    String status
) throws SQLException {
    super.updateQuery(
        "UPDATE Flights SET destination = COALESCE(?, destination), departureLocation = COALESCE(?, departureLocation), capacity = COALESCE(?, capacity), takeoff = COALESCE(?, takeoff), landing = COALESCE(?, landing), date = COALESCE(?, date), status = COALESCE(?, status) WHERE number = ?;",
        new Object[]{destination, departureLocation, capacity, takeoff, landing, date, status, fid},
        new Object[]{String.class, String.class, Integer.class, Timestamp.class, Timestamp.class, Date.class, String.class, Integer.class}
    );
}

}
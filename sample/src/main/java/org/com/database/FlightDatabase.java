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

}
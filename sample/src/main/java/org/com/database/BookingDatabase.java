package org.com.database;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.com.bases.Database;


public class BookingDatabase extends Database{
    public BookingDatabase(Connection connection){
        super(connection);
    }

    public void createBooking(int uid, int fid) throws SQLException{
        super.updateQuery("INSERT INTO Bookings(uid, fid) VALUES (?, ?) ON CONFLICT (uid, fid) DO NOTHING", new Object[] {uid, fid}, new Object[] {Integer.class, Integer.class});
    }

    public ResultSet retrieveBookingByUser(int uid) throws SQLException{
        return super.query("SELECT * FROM Flights INNER JOIN Bookings ON Flights.number = Bookings.fid INNER JOIN users ON Users.id = Bookings.uid WHERE Users.id = ?", new Object[] {uid}, new Object[] {Integer.class});
   }

   public ResultSet retrieveTimes(int uid) throws SQLException{
    return super.query("SELECT takeoff, landing FROM FLIGHTS INNER JOIN Bookings ON Flights.number = Bookings.fid INNER JOIN users on Users.id = Bookings.uid WHERE Users.id = ?", new Object[] {uid}, new Object[] {Integer.class});
   }


   public void removeBooking(int uid, int fid) throws SQLException{
    super.updateQuery("DELETE FROM Bookings WHERE uid=? AND fid=?", new Object[] {uid, fid}, new Object[] {Integer.class, Integer.class});
   }
}
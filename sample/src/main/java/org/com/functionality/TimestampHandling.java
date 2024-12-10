package org.com.functionality;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class TimestampHandling {
public static String formatTimestamp(String timestamp) {
    try {
        Timestamp ts = Timestamp.valueOf(timestamp); 
        LocalDateTime dateTime = ts.toLocalDateTime(); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return dateTime.format(formatter); 
    } catch (Exception e) {
        return "Invalid Time";
    }
}

}
package org.com.database.parser;


import java.lang.reflect.Array;
import java.security.Timestamp;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.HashMap;
import java.util.Map;



public class ResultSetParser {
    private final ResultSet rs;

    public ResultSetParser(ResultSet rs){
        this.rs = rs;
    }


    // Counts the number of rows in the result set
    public int countResult(ResultSet result) throws SQLException{
      int count = 0;
      while (result.next()){
        count++;
      }
      // Reset the cursor to before the first row
      result.beforeFirst();

      return count;
    }


    public int countResult() throws SQLException{
      int count = 0;
      while (this.rs.next()){
        count++;
      }

      this.rs.beforeFirst();

      return count;
    }


    // Parse the Result set to Map<String, String> array
    public Map<String, String>[] parseToStringDict(String[] keys) throws SQLException{
        // Create an array of maps the length of results from the result set
        Map<String, String>[] dictList = new HashMap[this.countResult(this.rs)];

        // Iterate through the result set and add it to the map array 
        int count = 0;
            while (this.rs.next()) {
                // Create a map
                HashMap<String, String> dict = new HashMap<>();
                for (String key: keys){
                    dict.put(key, String.valueOf(this.rs.getObject(key)));
                }
                dictList[count] = dict;
                count++;
            }
        return dictList;
    }

    // Parse the Result set to Map<String, String> array, takes a result set and array of key strings 
    public Map<String, String>[] parseToStringDict(ResultSet result, String[] keys) throws SQLException{
      Map<String, String>[] dictList = new HashMap[this.countResult(result)];

      // Iterate through the result set and add it to the map array 
        int count = 0;
          while (result.next()) {
              HashMap<String, String> dict = new HashMap<>();
              for (String key: keys){
                  dict.put(key, String.valueOf(result.getObject(key)));
              }
              dictList[count] = dict;
              count++;
          }
      return dictList;
  }


    // Parse the result set to Map<String, Object> array for any data type. Must specify datatype
    public Map<String, Object>[] parse(String[] keys, Class<?>[] types) throws SQLException {
      // If lengths do not match return empty array of maps
      if (keys.length != types.length) {
          return new HashMap[0]; 
      }
  
      // Get size of result set
      int resultCount = this.countResult(this.rs); 
      // If the result count is empty, return an empty array of maps
      if (resultCount <= 0){
        return new HashMap[0];
      }

      // Create an array of size of number of rows in the result set
      Map<String, Object>[] objList = new HashMap[resultCount];
      
      // Iterate through the result set and add the values to the map array
      int count = 0;
      while (this.rs.next()) {
          Map<String, Object> dict = new HashMap<>();
          for (int i = 0; i < keys.length; i++) {
              if (types[i] == Integer.class) {
                  dict.put(keys[i], this.rs.getInt(keys[i]));
              } else if (types[i] == String.class) {
                  dict.put(keys[i], this.rs.getString(keys[i]));
              } else if (types[i] == Double.class) {
                  dict.put(keys[i], this.rs.getDouble(keys[i]));
              } else if (types[i] == Array.class) {
                  dict.put(keys[i], this.rs.getArray(keys[i]));
              } else if (types[i] == Date.class) {
                  dict.put(keys[i], this.rs.getDate(keys[i]));
              } else if (types[i] == Time.class) {
                  dict.put(keys[i], this.rs.getTime(keys[i]));
              } else if (types[i] == Timestamp.class) {
                  dict.put(keys[i], this.rs.getTimestamp(keys[i]));
              }
          }
          objList[count] = dict; 
          count++;
      }
      
      return objList; 
  }

}   




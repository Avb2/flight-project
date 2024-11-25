package org.com.env;

import java.util.HashMap;
import java.util.Map;

import io.github.cdimascio.dotenv.Dotenv;


public class Environment {
    private final Dotenv dotenv; 

    public Environment(){
        this.dotenv = Dotenv.configure().load();
    }


    // Gets the database details from the .env file
    public Map<String, String> getDbDetails(){
        Map<String, String> map = new HashMap<>(); 

        map.put("url", this.dotenv.get("DB_URL"));
        map.put("user", this.dotenv.get("DB_USER"));
        map.put("password", this.dotenv.get("DB_PASSWORD"));



        return map;
    }
}

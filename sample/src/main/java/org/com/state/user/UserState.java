package org.com.state.user;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.com.database.UserDatabase;


public class UserState {
    private String firstName;
    private String lastName;
    private boolean loggedIn;
    private int uid;
    private String username;
    private String permissions;



    // Getters
    public Map<String, String> getName(){
        Map<String, String> name = new HashMap<>();

        name.put("first name", this.firstName);
        name.put("last name", this.lastName);
        name.put("full name", this.firstName + " " + this.lastName);

        return name;
    }

    public boolean getLoggedInState(){
        return this.loggedIn;
    }

    public int getUid(){
        return this.uid;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPermissions(){
        return this.permissions;
    }


    // Private setters
    private void setLoggedInStatePriv(){
        this.loggedIn = !this.loggedIn;
    }

    private void setFirstNamePriv(String name){
        this.firstName = name;
    }

    private void setLastNamePriv(String name){
        this.lastName = name;
    }

    private void setUIDpriv(int uid){
        this.uid = uid;
    }

    private void setUsernamePriv(String username){
        this.username = username;
    }

    private void setPermissions(String perms){
        this.permissions = perms;
    }
    

    // Public setters
    public void setName(String firstName, String lastName){
        if (this.loggedIn == true){
            setFirstNamePriv(firstName);
            setLastNamePriv(lastName);
        }
    }

    public void setLoggedInState(){
        setLoggedInStatePriv();
    }

    

    // Methods
    public void login(UserDatabase userdb, String username) throws SQLException{
        // Get all of the user information from the database with the corresponding username
        Map<String, Object>[] userInfo = userdb.retrieveInfoByUsername(username);

        // Set logged in state
        this.setLoggedInState();

        // Set name in UserState
        this.setName((String) (userInfo[0].get("firstname")), (String) (userInfo[0].get("lastname")));

        // Set UID
        this.setUIDpriv((Integer) (userInfo[0].get("id")));

        // Set username
        this.setUsernamePriv(username);

        // set permissions
        this.setPermissions((String) (userInfo[0]).get("type"));

       
    }

    public void logout(){
        // Clear name
        setName("", "");
        // Clear logged in state
        setLoggedInState();
        // Clear UID
        setUIDpriv(0);
        //Clear username
        setUsernamePriv("");

    }


}

package org.com.models;



import org.com.bases.Models;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;




// Convert to child of Registration Base class
public class RegistrationAdmin extends Models{
    public final String firstName;
    private final String lastName;
    private final String email;
    private final String ssn;

    public RegistrationAdmin(
         GridPane firstName,
         GridPane lastName,
         GridPane email,
         GridPane ssn
    ){
        this.firstName = this.toString(firstName);
        this.lastName = this.toString(lastName);
        this.email = this.toString(email);
        this.ssn = this.toString(ssn);
    }
    

    // Extracts grid panes to their string content
    private String toString(GridPane pane){
        TextField textField = (TextField) (pane.getChildren().get(1));
        
        String string = textField.getText();

        return string;
    }

    

    // Types for the instance attributes
    @Override
    public Object[] types(){
        return new Object[] {String.class, String.class, String.class, String.class};
    }

    public Object[] types(boolean register){
        if (register == true){
            return new Object[] {String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class};
        } else {
            return this.types();
        }
        
    }

    // Array containing attributes
    @Override
    public Object[] toArray(){
        return new Object[] {this.firstName, this.lastName, this.email, this.ssn};
    }

    public Object[] toArray(boolean register){
        if (register){
            String username = "admin_"+ this.ssn.substring(4);
            return new Object[] {this.firstName, this.lastName, username, this.ssn, this.email, this.ssn, "What is your SSN?", this.ssn, "admin"};
        } else {
            return this.toArray();
        }
       
    
    }


    public String getSSN() {
        return this.ssn;

    }

    public String getPassword(){
        return this.ssn;
    }

}
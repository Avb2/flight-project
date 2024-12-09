package org.com.functionality.auth;

import java.sql.Connection;
import java.sql.SQLException;

import org.com.database.UserDatabase;
import org.com.models.RegistrationAdmin;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


@FunctionalInterface
interface Validator {
    boolean validate(String input);
}


public class Register {
    public static void createAccount(Connection connection, GridPane pane,  GridPane firstNameField, GridPane lastNameField, GridPane addressField, GridPane zipcodeField, GridPane stateField, GridPane usernameField, GridPane passwordField, GridPane emailField, GridPane ssnField, GridPane securityQuestion, GridPane securityAnswer, Stage stage){
        // Create account
        String[] valid = Register.validateRegistrationForm(new GridPane[] {firstNameField, lastNameField, addressField, zipcodeField, stateField, usernameField, passwordField, emailField, ssnField, securityQuestion, securityAnswer});

        
        // If account creation success
        if (valid.length > 0){
            try{
                UserDatabase userDb = new UserDatabase(connection); 
                valid[11] = "user";
                // Register the user
                userDb.registerUser(valid);
                // Login the user and push to homepage
                Login.login(
                    usernameField, passwordField, connection, pane, stage);
            } catch (SQLException err){
                err.printStackTrace();
            }
           
        }
    }

    public static void createAdmin(Connection connection, RegistrationAdmin registrationModel){
        try {
            UserDatabase userDb = new UserDatabase(connection); 
            userDb.registerUser(registrationModel);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


    public static String[] validateAdminAcct(RegistrationAdmin registrationModel){
        Validator[] validators = {
            Register::validateName, 
            Register::validateName,
            Register::validateEmail, 
            Register::validateSSN
        };

        Object[] registrationArray = registrationModel.toArray();

        String[] info = new String[registrationArray.length];

        for (int i = 0; i < registrationArray.length; i++){
            String val = (String) registrationArray[i];
            // Check if fields are empty
            if (val.isEmpty() || !validators[i].validate(val)){
                System.out.println("FAIL TO VALIDATE "+ " "+ val);
                return new String[]{};
            } 
            info[i] = val;
            
        }
        return info;


    }


    public static String[] validateRegistrationForm(GridPane[] fields){
        Validator[] validators = {
            Register::validateName, 
            Register::validateName,
            Register::validateAddress,
            Register::validateZipcode,
            Register::validateState,
            Register::validateUsername,
            Register::validatePassword,
            Register::validateEmail, 
            Register::validateSSN,
            Register::validateQuestion,
            Register::validateQuestion
        };

        String[] info = new String[fields.length + 1];

        for (int i = 0; i < fields.length; i++){
            System.out.println(fields[i].getChildren().get(0));
            TextField textField = (TextField) (fields[i].getChildren().get(1));
            // Check if fields are empty
            if (textField.getText().isEmpty() || !validators[i].validate(textField.getText())){
                System.out.println("FAIL TO VALIDATE "+ " "+ textField.getText());
                return new String[]{};
            } 
            info[i] = textField.getText();
            
        }
        return info;
    }


    // If name is greater than 2 digits and only contains letters
    public static boolean validateName(String name){
        
        if (name.length() < 3){
            return false;
        } else if (name.matches(".*\\d.*")){
            return false;
        }
        return true;
    }
    

    // Begins with numbers and contains letters ex: 203 Pryor Street
    public static boolean validateAddress(String address){
        if (address.matches("\\d{1,8}\\s.*")){
            return true;
        }
        return false;
    }


    // Only digits and 5 chars
    public static boolean validateZipcode(String zipcode){
        return zipcode.matches("\\d{5}");
    }


    // 2 letters for state, caps does not matter
    public static boolean validateState(String state){
        return state.matches("(?i)[a-z]{2}");
    }

    // Username contains atleast 1-10 letter and can contain numbers 1-8
    public static boolean validateUsername(String username){
        if (username.matches("(?i)[a-z]{1,10}[0-9]{1,8}")){
            return true;
        }
        return false;
        // make sure no conflicting in db
    }

    // Password contains letters/special chars and numbers
    public static boolean validatePassword(String password){
        if (password.matches("(?i)[a-z1-9!@#$%^&*_+-]{4,15}")){
            return true;
        } 
        return false;
    }
    
    // Email contains atleast 3-10 letters (Not caps sensitive), and ends with .org or .com
    public static boolean validateEmail(String email){
        if (email.matches("(?i).*@[a-z]{3,10}\\.(com|org)$")){
            return true;
        }
        return false;
    }

   
    public static boolean validateSSN(String ssn){
        if (ssn.matches("^\\d{9}$")){
            // Validate no other same ssn
            return true;
        }

        return false;
    }
    

    public static boolean validateQuestion(String string){
        return  !string.isEmpty();
    }
    

    public static boolean validateType(String type){
        return type.matches("(user|admin)");
    }
    
}
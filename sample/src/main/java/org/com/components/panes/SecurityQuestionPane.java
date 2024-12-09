package org.com.components.panes;

import java.sql.Connection;

import org.com.bases.Component;
import org.com.components.buttons.StyledButton1;
import org.com.components.inputFields.InputField;
import org.com.functionality.auth.ResetPassword;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;



public class SecurityQuestionPane extends Component{
    private final String question;
    private final String answer;
    private final String username;
    private Connection connection;
    private final Stage stage;
    

    public SecurityQuestionPane(String question, String answer, String username, Connection connection, Stage stage){
        this.question = question;
        this.answer = answer;
        this.username = username;
        this.connection = connection;
        this.stage = stage;
    }
    @Override 
    public Node createComponent() {
        // 
        GridPane pane = new GridPane();

        
        pane.add(new Label(this.question), 0, 0);


        // Security Question answer field & label
        pane.add(new Label(), 0, 0);
        GridPane answerPane = InputField.inputField("Answer");
        pane.add(answerPane, 0, 1);


        // Enter Button
        pane.add(new StyledButton1("Enter" , e -> {
            ResetPassword.answerSecurityQuestion(this.username, this.answer, answerPane, pane, this.connection, this.stage);
        }).createComponent(), 0, 2);
        return pane;
    }
}

package org.com.components.panes;

import java.sql.Connection;

import org.com.bases.Component;
import org.com.components.buttons.StyledButton1;
import org.com.components.inputFields.InputField;
import org.com.constants.Sizes;
import org.com.functionality.auth.ResetPassword;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SecurityQuestionPane extends Component {
    private final String question;
    private final String answer;
    private final String username;
    private Connection connection;
    private final Stage stage;

    public SecurityQuestionPane(String question, String answer, String username, Connection connection, Stage stage) {
        this.question = question;
        this.answer = answer;
        this.username = username;
        this.connection = connection;
        this.stage = stage;
    }

    @Override
    public Node createComponent() {
        // Main pane
        GridPane pane = new GridPane();
        pane.getStyleClass().add("flight-card-pane"); // Styled as a card
        pane.setVgap(Sizes.smallGap);
        pane.setHgap(Sizes.mediumGap);
        pane.setPadding(new Insets(15));
        pane.setAlignment(Pos.CENTER);

        // Security question label
        Label questionLabel = new Label(this.question);
        questionLabel.getStyleClass().add("text-field-label-1");
        pane.add(questionLabel, 0, 0);

        // Security question answer input field
        GridPane answerPane = InputField.inputField("Answer");
        answerPane.getStyleClass().add("text-field-1");
        pane.add(answerPane, 0, 1, 2, 1);

        // Enter button
        Node enterButton = new StyledButton1("Enter", e -> {
            ResetPassword.answerSecurityQuestion(
                this.username, 
                this.answer, 
                answerPane, 
                pane, 
                this.connection, 
                this.stage
            );
        }).createComponent();
        enterButton.getStyleClass().add("button-1");
        pane.add(enterButton, 0, 2);

        return pane;
    }
}

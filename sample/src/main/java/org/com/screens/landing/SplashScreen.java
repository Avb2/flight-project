package org.com.screens.landing;


import java.sql.Connection;

import org.com.animations.Animate;
import org.com.bases.Screen;
import org.com.components.buttons.StyledButton1;
import org.com.constants.Sizes;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
 

public class SplashScreen extends Screen{
    private Connection connection;

    public SplashScreen(Connection connection){
        this.connection = connection;
    }

    @Override
    public GridPane createPane(Stage stage){
        // Main splash screen
        GridPane pane = new GridPane();
        pane.getStyleClass().add("background-primary");
        pane.setVgap(Sizes.largeGap);
        pane.setAlignment(Pos.CENTER);

        Label label = new Label("QualityAirlines.com");
        label.getStyleClass().add("title");
        pane.add(label, 0, 0);


        Animate animationLabel = new Animate(label);
        animationLabel.rotate(1, 360);
        animationLabel.fadeIn(2);


        GridPane subPane = new GridPane();
        subPane.setAlignment(Pos.CENTER);
        subPane.setVgap(Sizes.smallGap);
        pane.add(subPane, 0, 2);

        Node loginBtn = new StyledButton1("Login", e -> {}).createComponent();
        // Node loginBtn = new StyledButton1("Login", e -> new LoginScreen(this.connection).createPane(pane, stage)).createComponent();
        subPane.add(loginBtn, 0, 0);


        // Node registerBtn = new StyledButton1("Register", e -> new RegisterScreen(this.connection).createPane(pane, stage)).createComponent();
        Node registerBtn = new StyledButton1("Register", e -> {}).createComponent();
        subPane.add(registerBtn, 0, 1);

        return pane;
    }
}


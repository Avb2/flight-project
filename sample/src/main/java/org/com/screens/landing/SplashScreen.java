package org.com.screens.landing;


import java.sql.Connection;

import org.com.animations.Animate;
import org.com.bases.Screen;
import org.com.components.buttons.StyledButton1;
import org.com.constants.Sizes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
 

public class SplashScreen extends Screen {
    private Connection connection;

    public SplashScreen(Connection connection) {
        this.connection = connection;
    }

    @Override
    public GridPane createPane(Stage stage) {
        // Main pane
        GridPane pane = new GridPane();
        pane.getStyleClass().add("background-primary");
        pane.setVgap(Sizes.largeGap);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(50));

        // Title Label
        Label label = new Label("QualityAirlines.com");
        label.getStyleClass().add("title");
        label.setAlignment(Pos.CENTER);
        pane.add(label, 0, 0);
        // Animation to the title
        Animate animationLabel = new Animate(label);
        animationLabel.rotate(1, 360); 
        animationLabel.fadeIn(2);


          // Airplane
          Label flightSymbol = new Label("\u2708"); 
          flightSymbol.getStyleClass().add("subtitle");
          flightSymbol.setStyle("-fx-font-size: 50px; -fx-text-fill: #5984e0;"); 
          Animate planeAnimation = new Animate(flightSymbol);
         planeAnimation.move(2, 100, 0, 1000);
          pane.add(flightSymbol, 0, 1);
  
          // Footer text
          Label footerText = new Label("Your journey begins here");
          footerText.getStyleClass().add("subtitle");
          pane.add(footerText, 0, 2);
          new Animate(footerText).fadeIn(3);

        // Subpane for buttons
        GridPane subPane = new GridPane();
        subPane.setAlignment(Pos.CENTER);
        subPane.setVgap(Sizes.mediumGap);
        pane.add(subPane, 0, 3);

        // Login Button
        Node loginBtn = new StyledButton1("Login", e -> {
            Scene scene = new LoginScreen(this.connection).createScreen(stage);
            stage.setScene(scene);
            stage.show();
        }).createComponent();
        loginBtn.getStyleClass().add("button-1");
        subPane.add(loginBtn, 0, 0);

        // Register Button
        Node registerBtn = new StyledButton1("Register", e -> {
            Scene scene = new RegisterScreen(this.connection).createScreen(stage);
            stage.setScene(scene);
            stage.show();
        }).createComponent();
        registerBtn.getStyleClass().add("button-1");
        subPane.add(registerBtn, 0, 1);

      

        return pane;
    }
}

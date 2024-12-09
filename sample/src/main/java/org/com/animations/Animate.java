package org.com.animations;


import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.scene.Node;
import javafx.util.Duration;


public class Animate {
    private Node node; 

    public Animate(Node node){
        this.node = node;
    }

    public void fadeIn(int seconds){
        FadeTransition animation = new FadeTransition(Duration.millis(seconds * 1000));
                    animation.setNode(this.node);
                    animation.setFromValue(0); 
                    animation.setToValue(1.0); 
                    animation.setAutoReverse(false);
                    animation.setCycleCount(1);

                    // Fading aniamtion
                    animation.play();
    }

    public void fadeOut(int seconds){
        FadeTransition animation = new FadeTransition(Duration.millis(seconds * 1000));
                    animation.setNode(this.node);
                    animation.setFromValue(1.0); 
                    animation.setToValue(0); 
                    animation.setAutoReverse(false);
                    animation.setCycleCount(1);

                    // Fading aniamtion
                    animation.play();
    }

    public void rotate(int seconds, int angle){
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(seconds * 1000), this.node);
        rotateTransition.setByAngle(angle);
        
        rotateTransition.setCycleCount(1);
        rotateTransition.setAutoReverse(true);
        rotateTransition.play();
    }
}
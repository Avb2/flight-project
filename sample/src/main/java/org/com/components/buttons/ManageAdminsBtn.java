package org.com.components.buttons;

import java.sql.Connection;

import org.com.bases.Component;
import org.com.screens.modify.EditAdminScreen;
import org.com.state.user.UserState;

import javafx.scene.Node;
import javafx.stage.Stage;


public class ManageAdminsBtn extends Component{
    private Connection connection;
    private UserState userState;
    private Stage stage; 

    public ManageAdminsBtn(Connection connection, UserState userState, Stage stage){
        this.connection = connection;
        this.userState = userState;
        this.stage = stage;
    }


    @Override 
    public Node createComponent(){
        Node button = new StyledButton1("Admin", e -> stage.setScene(new EditAdminScreen(this.connection, this.userState).createScreen(this.stage))).createComponent();

        return button;
        }
}
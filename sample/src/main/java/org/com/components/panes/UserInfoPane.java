package org.com.components.panes;


import java.sql.Connection;
import java.util.Map;

import org.com.bases.Panes;
import org.com.components.cards.UserInfoCard;
import org.com.components.navBars.AdminNavBar;
import org.com.state.user.UserState;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;




public class UserInfoPane extends Panes{
    private Stage stage;
    private Map<String, Object> userInfo;

    public UserInfoPane(Stage stage, Map<String, Object> userInfo){
        this.stage = stage;
        this.userInfo = userInfo;
    }


    @Override
    public void createPane(GridPane mainPane, UserState userState, Connection connection){
        mainPane.getChildren().clear();
        
        GridPane pane = new GridPane();
        mainPane.add(pane, 0, 0);

        // Nav bar
        GridPane adminNavBar = new AdminNavBar(this.stage, userState, connection, mainPane).createComponent();
        pane.add(adminNavBar, 0, 0);


        // Take the user back to the lookup screen
        Button backButton = new Button("<-");
        backButton.setOnAction(e -> new LookupAdminPane(this.stage).createPane(mainPane, userState, connection));
        pane.add(backButton, 0, 1);

    

        ScrollPane scrollPane = new ScrollPane();
        pane.add(scrollPane, 0, 2);
        
        GridPane subPane = new GridPane();
        

        Object[] arr = this.userInfo.keySet().toArray();
        for (int i = 0; i < arr.length; i++) {
            String key = (String) (arr[i]);
            Object val = this.userInfo.get(key);

            if (val == null){
                val = "N/A";
            } else if (val instanceof Integer){
                subPane.add(new UserInfoCard(key, val.toString()).createComponent(), 0 , i);
            } else {
                subPane.add(new UserInfoCard(key, (String) (val)).createComponent(), 0 , i);
            }
        }


        scrollPane.setContent(subPane);


    }
}
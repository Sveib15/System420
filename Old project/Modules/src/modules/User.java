/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Adne
 */
public class User {
    private String firstname;
    private String lastname;
    private String email;
    private String rights;
    
    public User()
    {

    }
    
    public String getFirstname()
    {
        return firstname;
    }
    
    public void setFirstName(String firstname)
    {
        this.firstname = firstname;
    }
    
    public String getLastname()
    {
        return lastname;
    }
    
    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String getRights()
    {
        return rights;
    }
    
    public void setRights(String rights)
    {
        this.rights = rights;
    }   
    
    public void showProfile()
    {
        Stage stage = new Stage();
        GridPane gp = new GridPane();
        gp.setStyle("-fx-background-color:#333333;");
        gp.setVgap(10);
        gp.setHgap(10);
        gp.setAlignment(Pos.CENTER);
        
        Label name = new Label();
        name.setText(firstname + " " + lastname + " [" + rights + "]");
        name.setStyle("-fx-text-fill:white; -fx-font-size:14px; -fx-font-weight:bold;");
        
        Label emaillbl = new Label(email);
        emaillbl.setStyle("-fx-text-fill:white; -fx-font-size:14px; -fx-font-weight:bold;");
        
        Button btn = new Button("Change password");
        btn.setStyle("-fx-background-color:black; -fx-text-fill:white; -fx-font-weight:bold;");
        
        gp.add(name, 0, 0);
        gp.add(emaillbl, 0, 1);
        gp.add(btn, 0, 2);
        
        Scene scene = new Scene(gp, 200, 150);
        stage.setScene(scene);
        stage.show();
        
    }
}

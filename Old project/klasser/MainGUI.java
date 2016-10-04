/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Adne
 */
public class MainGUI 
{
    private Stage window;
    private ArrayList<User> users;
    private ArrayList<Delivery> deliveries;
    private ArrayList<Modul> moduls;
    private UserDatabaseController udc;
    private DeliveryDatabaseController ddc;
    private ModulDatabaseController mdc;
    
    public MainGUI()
    {
        window = new Stage();
        udc = new UserDatabaseController();
        ddc = new DeliveryDatabaseController();
        mdc = new ModulDatabaseController();
        users = udc.getUsers();
        deliveries = ddc.getDeliveries();
        moduls = mdc.getModuls();
        setMainScene();
    }
    
    public void setMainScene()
    {
        BorderPane bp = new BorderPane();
        HBox hbox = new HBox();
        VBox usersBox = new VBox();
        usersBox.setAlignment(Pos.TOP_CENTER);
        
        ArrayList<Label> usersLbl = new ArrayList<Label>();
        Label nameLbl = new Label("Navn");
        nameLbl.setPrefWidth(200);
        usersBox.getChildren().add(nameLbl);
        
        for(User u: users)
        {
            Label usrLbl = new Label(u.getEmail());
            usrLbl.setPrefWidth(200);
            usersBox.getChildren().add(usrLbl);
            usersLbl.add(usrLbl);
        }
        
        hbox.getChildren().add(usersBox);
        
        for(Modul m: moduls)
        {
            VBox vbox = new VBox();
            vbox.setAlignment(Pos.TOP_CENTER);
            
            Label modulName = new Label(m.getName());
            modulName.setPrefWidth(200);
            vbox.getChildren().add(modulName);
            
            for(Label l: usersLbl)
            { 
                for(Delivery d: deliveries)
                {
                    if(d.getEmail().equals(l.getText()) && d.getModulID().equals(m.getName()))
                    {
                        Label deliveredDateLbl = new Label(d.getDeliveredDate());
                        deliveredDateLbl.setPrefWidth(200);
                        vbox.getChildren().add(deliveredDateLbl);
                    }
                    
                    else
                    {
                        Label notDeliveredLbl = new Label("");
                        notDeliveredLbl.setPrefWidth(200);
                        vbox.getChildren().add(notDeliveredLbl);
                    }
                }
            }
            
            hbox.getChildren().add(vbox);
        }
        
        bp.setCenter(hbox);
        
        Scene scene = new Scene(bp, 600, 600);
        scene.getStylesheets().add(this.getClass().getResource("mainGUIstyle.css").toExternalForm());
        window.setScene(scene);
    }
    
    public void show()
    {
        window.show();
    }
}

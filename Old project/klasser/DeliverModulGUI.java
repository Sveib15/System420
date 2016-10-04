/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import classes.Delivery;
import classes.Modul;
import classes.User;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.ejb.EJB;

/**
 *
 * @author Adne
 */
public class DeliverModulGUI 
{
    private File file;
    private Stage window;
    private FileChooser fc;
    private String filepath;
    
    public DeliverModulGUI()
    {
        fc = new FileChooser();
        window = new Stage();
    }
    
    public void deliver(Modul m, User u)
    {
        DeliveryDatabaseController ddc = new DeliveryDatabaseController();
        
        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        Date date = new Date();
        
        GridPane gp = new GridPane();
        gp.setAlignment(Pos.TOP_CENTER);
        TextArea commentArea = new TextArea();
        TextField fileName = new TextField();
        fileName.setPrefWidth(300);
        
        Button deliverBtn = new Button("Lever");
        deliverBtn.setOnAction(e->
        {
            String currentDate = df.format(date);
            String comment = commentArea.getText();
            String email = u.getEmail();
            String modulID = m.getName();
            
            Delivery d = new Delivery(modulID, currentDate, comment, email, file);
            
            ddc.addDelivery(d);
        });
        
        Button uploadBtn = new Button("Last opp fil");
        uploadBtn.setOnAction(e->
        {
            getFile();
            fileName.setText(filepath);
        });
        
        HBox upload = new HBox();
        upload.setPadding(new Insets(10, 10, 10, 10));
        upload.setAlignment(Pos.CENTER_LEFT);
        upload.getChildren().addAll(uploadBtn, fileName);
        
        gp.add(upload, 0, 0);
        gp.add(commentArea, 0, 1);
        gp.add(deliverBtn, 0, 2);
        
        Scene scene = new Scene(gp, 420, 255);
        window.setScene(scene);
        window.show();
    }
    
    public void getFile()
    {
        file = fc.showOpenDialog(window);
        filepath = file.getPath();
    }
}

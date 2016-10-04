/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author Adne
 */
public class ModulInfoGUI 
{
    public void showModulInfo(Modul m, int rights)
    {
        ModulDatabaseController mdc = new ModulDatabaseController();
        
        Stage window = new Stage();
        BorderPane bp = new BorderPane();
        
        TextArea ta = new TextArea();
        ta.setWrapText(true);
        ta.setEditable(false);
        
        Label nameLbl = new Label(m.getName());
        nameLbl.setStyle("-fx-font-size:18px;");
        HBox nameBox = new HBox();
        nameBox.setAlignment(Pos.CENTER);
        nameBox.getChildren().add(nameLbl);
        nameBox.setPadding(new Insets(10, 10, 10, 10));
        
        ta.appendText(m.getDescription() + "\n\n");
        ta.appendText("Læremål:");
        for(String str: m.getRequirements())
        {
            ta.appendText("\n   • " + str);
        }
        ta.appendText("\n\nRessurser:");
        for(String str: m.getResources())
        {
            ta.appendText("\n   • " + str);
        }
        
        HBox btnBox = new HBox();
        btnBox.setPadding(new Insets(10, 10, 10, 10));
        Button deleteBtn = new Button("Slett");
        deleteBtn.setOnAction(e-> 
        {
            mdc.deleteModul(m);
            window.hide();
        });
        
        Button deliverBtn = new Button("Lever");
        
        if(rights == 1)
        {
            btnBox.getChildren().add(deleteBtn);
        }
        else if(rights == 3)
        {
            btnBox.getChildren().add(deliverBtn);
        }
        
        bp.setTop(nameBox);
        bp.setCenter(ta);
        bp.setBottom(btnBox);
        Scene scene = new Scene(bp, 400, 400);
        window.setScene(scene);
        window.show();
    }
}

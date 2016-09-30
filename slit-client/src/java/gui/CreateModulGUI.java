/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Beans.ModulDatabaseControllerBeanRemote;
import classes.Modul;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.ejb.EJB;

/**
 *
 * @author Adne
 */
public class CreateModulGUI 
{
    private Stage window;
    
    @EJB
    private static ModulDatabaseControllerBeanRemote mdcb;
    
    public CreateModulGUI()
    {
        window = new Stage();
        
        ArrayList<String> requirements = new ArrayList<String>();
        ArrayList<String> resources = new ArrayList<String>();
        
        GridPane gridPane = new GridPane();
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.TOP_CENTER);
        
        TextField nameField = new TextField();
        nameField.setPromptText("Navn");
        
        TextArea descriptionArea = new TextArea();
        descriptionArea.setPromptText("Beskrivelse");
        descriptionArea.setWrapText(true);
        
        TextField requirementField = new TextField();
        requirementField.setPromptText("Læremål");
        
        TextField resourceField = new TextField();
        resourceField.setPromptText("Ressurs");
        
        ComboBox rightsBox = new ComboBox();
        rightsBox.getItems().addAll("Foreleser", "Hjelpelærer");
        
        Label rightsBoxLbl = new Label("Kan vurderes av:");
        
        Button createBtn = new Button("Opprett");
        createBtn.setOnAction(e-> 
        {
            int rights = 0;

            if(rightsBox.getValue().equals("Foreleser"))
            {
                rights = 1;
            }
            else if(rightsBox.getValue().equals("Hjelpelærer"))
            {
                rights = 2;
            }

            Modul modul = new Modul(nameField.getText(), descriptionArea.getText(), rights);

            for(String str: requirements)
            {
                modul.addRequirement(str);
            }
            for(String str: resources)
            {
                modul.addResources(str);
            }

            mdcb.addModul(modul);
            window.hide();
        });
        
        Button addRequirementBtn  = new Button("Legg til/fjern læremål");
        addRequirementBtn .setOnAction(e->
        {
            Stage delReqStage = new Stage();
            BorderPane bp = new BorderPane();
            ScrollPane scroll = new ScrollPane();
            bp.setCenter(scroll);
            
            VBox v = new VBox();
            v.setAlignment(Pos.CENTER_LEFT);
            scroll.setContent(v);
            ArrayList<TextField> reqs = new ArrayList<TextField>();
            
            HBox hbox = new HBox();
            TextField reqText = new TextField();
            reqText.setPrefWidth(370);
            Button addBtn = new Button("+");
            addBtn.setPrefWidth(30);
            addBtn.setOnAction(es->
            {
                HBox h = new HBox();
                TextField txt = new TextField(reqText.getText());
                Button del = new Button("-");
                del.setPrefWidth(30);
                del.setOnAction(ese->
                {
                    v.getChildren().remove(h);
                    reqs.remove(txt);
                });
                txt.setPrefWidth(410);
                h.getChildren().addAll(del, txt);
                h.setAlignment(Pos.CENTER_LEFT);
                v.getChildren().add(h);
                reqText.clear();
                reqText.requestFocus();
                scroll.setVvalue(1.0);
                reqs.add(txt);
            });
            
            for(String str: requirements)
            {
                TextField txt = new TextField(str);
                HBox h = new HBox();
                Button del = new Button("-");
                del.setPrefWidth(30);
                del.setOnAction(eses->
                {
                    v.getChildren().remove(h);
                    reqs.remove(txt);
                });
                txt.setPrefWidth(410);
                h.getChildren().addAll(del, txt);
                h.setAlignment(Pos.CENTER_LEFT);
                v.getChildren().add(h);
                scroll.setVvalue(1.0);
                reqs.add(txt);
            }
            
            Button save = new Button("Lagre");
            save.setOnAction(ese2->
            {
                requirements.clear();
                for(TextField tf: reqs)
                {
                    requirements.add(tf.getText());
                }
                delReqStage.hide();
            });
            
            hbox.getChildren().addAll(reqText, addBtn, save);
            bp.setBottom(hbox);
            
            Scene s = new Scene(bp, 460, 330);
            delReqStage.setScene(s);
            delReqStage.setTitle("Læremål");
            delReqStage.show();
        });
        
        Button addResourceBtn = new Button("Legg til/fjern ressurs");
        addResourceBtn.setOnAction(ese->
        {
            Stage delReqStage = new Stage();
            BorderPane bp = new BorderPane();
            ScrollPane scroll = new ScrollPane();
            bp.setCenter(scroll);
            
            VBox v = new VBox();
            v.setAlignment(Pos.CENTER_LEFT);
            scroll.setContent(v);
            ArrayList<TextField> reqs = new ArrayList<TextField>();
            
            HBox hbox = new HBox();
            TextField reqText = new TextField();
            reqText.setPrefWidth(370);
            Button addBtn = new Button("+");
            addBtn.setPrefWidth(30);
            addBtn.setOnAction(es->
            {
                HBox h = new HBox();
                TextField txt = new TextField(reqText.getText());
                Button del = new Button("-");
                del.setPrefWidth(30);
                del.setOnAction(eses->
                {
                    v.getChildren().remove(h);
                    reqs.remove(txt);
                });
                txt.setPrefWidth(410);
                h.getChildren().addAll(del, txt);
                h.setAlignment(Pos.CENTER_LEFT);
                v.getChildren().add(h);
                reqText.clear();
                reqText.requestFocus();
                scroll.setVvalue(1.0);
                reqs.add(txt);
            });
            
            for(String str: resources)
            {
                TextField txt = new TextField(str);
                HBox h = new HBox();
                Button del = new Button("-");
                del.setPrefWidth(30);
                del.setOnAction(eses->
                {
                    v.getChildren().remove(h);
                    reqs.remove(txt);
                });
                txt.setPrefWidth(410);
                h.getChildren().addAll(del, txt);
                h.setAlignment(Pos.CENTER_LEFT);
                v.getChildren().add(h);
                scroll.setVvalue(1.0);
                reqs.add(txt);
            }
            
            Button save = new Button("Lagre");
            save.setOnAction(ese2->
            {
                resources.clear();
                for(TextField tf: reqs)
                {
                    resources.add(tf.getText());
                }
                delReqStage.hide();
            });
            hbox.getChildren().addAll(reqText, addBtn, save);
            bp.setBottom(hbox);
            
            Scene s = new Scene(bp, 460, 330);
            delReqStage.setScene(s);
            delReqStage.setTitle("Ressurser");
            delReqStage.show();
        });
        
        HBox hbox = new HBox();
        hbox.getChildren().addAll(rightsBoxLbl, rightsBox);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setSpacing(3);
        
        HBox hbox2 = new HBox();
        hbox2.getChildren().addAll(addRequirementBtn, addResourceBtn);
        hbox2.setAlignment(Pos.CENTER_LEFT);
        hbox2.setSpacing(3);
        
        gridPane.add(nameField, 0, 0);
        gridPane.add(descriptionArea, 0, 1);
        gridPane.add(hbox2, 0, 2);
        gridPane.add(hbox, 0, 3);
        gridPane.add(createBtn, 0, 4);
        
        Scene scene = new Scene(gridPane, 400, 320);
        window.setScene(scene);
        window.setTitle("Opprett modul");
        gridPane.requestFocus();
    }
    
    public void show()
    {
        window.show();
    }
    
    public void hide()
    {
        window.hide();
    }
}
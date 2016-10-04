/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slitclient;

import Connection.testBeanRemote;
import classes.MainGUI;
import javafx.application.Application;
import javafx.stage.Stage;
import javax.ejb.EJB;

/**
 *
 * @author Adne
 */
public class Main extends Application
{ 

    @EJB
    private static testBeanRemote testBean;
    
    
    
    @Override
    public void start(Stage primaryStage)
    {
        MainGUI maingui = new MainGUI();
        maingui.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //launch(args);
        System.out.println(testBean.testMethod());
        
    }
}

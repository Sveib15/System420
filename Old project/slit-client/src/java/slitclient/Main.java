/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slitclient;

import Beans.DeliveryDatabaseControllerBeanRemote;
import Beans.ModulDatabaseControllerBeanRemote;
import Beans.UserDatabaseControllerBeanRemote;
import classes.Delivery;
import classes.Modul;
import classes.User;
import gui.CreateModulGUI;
import java.util.ArrayList;
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
    private static DeliveryDatabaseControllerBeanRemote ddcb;
    @EJB
    private static UserDatabaseControllerBeanRemote udcb;
    @EJB
    private static ModulDatabaseControllerBeanRemote mdcb;
    
    
    private ArrayList<Modul> moduls;
    private ArrayList<User> users;
    private ArrayList<Delivery> deliveries;
    
    public void updateModuls()
    {
        moduls.clear();
        ArrayList<Modul> temp = mdcb.getModuls();
        
        for(Modul m: temp)
        {
            Modul s;
            s = m;
            moduls.add(s);
        }
    }
    
    public void updateUsers()
    {
        users.clear();
        ArrayList<User> temp = udcb.getUsers();
        
        for(User u: temp)
        {
            User s;
            s = u;
            users.add(s);
        }
    }
    
    public void updateDeliveries()
    {
        deliveries.clear();
        ArrayList<Delivery> temp = ddcb.getDeliveries();
        
        for(Delivery d: temp)
        {
            Delivery s;
            s = d;
            deliveries.add(s);
        }
    }
    
    @Override
    public void start(Stage primaryStage)
    {   
        moduls = new ArrayList<Modul>();
        users = new ArrayList<User>();
        deliveries = new ArrayList<Delivery>();
        updateModuls();
        updateUsers();
        updateDeliveries();
        
        for(Modul m: moduls)
        {
            System.out.println(m.getName());
        }
        
        for(User u: users)
        {
            System.out.println(u.getFirstname());
        }
        
        for(Delivery d: deliveries)
        {
            System.out.println(d.getEmail());
        }
        
        CreateModulGUI cmg = new CreateModulGUI();
        cmg.show();
    }
    
    public static void main(String[] args) 
    {
        // TODO code application logic here
        launch(args);
    }
}

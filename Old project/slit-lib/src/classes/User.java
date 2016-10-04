/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Adne
 */
public class User {
    private String firstname;
    private String lastname;
    private String email;
    private int rights;
    private ArrayList<Delivery> deliveries;
    
    public User(String firstname, String lastname, String email, int rights)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.rights = rights;
        deliveries = new ArrayList<Delivery>();
    }
    
    public ArrayList<Delivery> getDeliveries()
    {
        return deliveries;
    }
    
    public void addDelivery(Delivery delivery)
    {
        deliveries.add(delivery);
    }
    
    public String getFirstname()
    {
        return firstname;
    }
    
    public String getLastname()
    {
        return lastname;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public int getRights()
    {
        return rights;
    }
}

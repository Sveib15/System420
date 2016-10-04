/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Beans.DeliveryDatabaseControllerBeanRemote;
import classes.Delivery;
import DatabaseConnections.DeliveryDatabaseController;
import java.util.ArrayList;
import javax.ejb.Stateless;

/**
 *
 * @author Adne
 */
@Stateless
public class DeliveryDatabaseControllerBean implements DeliveryDatabaseControllerBeanRemote 
{

    @Override
    public ArrayList<Delivery> getDeliveries() 
    {
        DeliveryDatabaseController ddc = new DeliveryDatabaseController();
        ArrayList<Delivery> deliveries = ddc.getDeliveries();
        return deliveries;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
}

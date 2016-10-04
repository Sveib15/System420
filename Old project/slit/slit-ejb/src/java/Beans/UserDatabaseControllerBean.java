/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Beans.UserDatabaseControllerBeanRemote;
import classes.User;
import DatabaseConnections.UserDatabaseController;
import java.util.ArrayList;
import javax.ejb.Stateless;

/**
 *
 * @author Adne
 */
@Stateless
public class UserDatabaseControllerBean implements UserDatabaseControllerBeanRemote 
{
    @Override
    public ArrayList<User> getUsers() 
    {
        UserDatabaseController udc = new UserDatabaseController();
        ArrayList<User> users = udc.getUsers();
        return users;
    }
}

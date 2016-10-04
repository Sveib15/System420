/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import classes.Delivery;
import classes.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Adne
 */
public class UserDatabaseController {
    private Connection connection;
    
    public UserDatabaseController()
    {
        connection = connectToDatabase();
    }
    
    private Connection connectToDatabase()
    {
        Connection conn = null;  
        try 
        {  
            // mysql database driver
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);  
            
            // database url, username and password
            String url = "jdbc:mysql://localhost:3306/modul?zeroDateTimeBehavior=convertToNull&autoReconnect=true&useSSL=false";
            String username = "root";  
            String password = "root";  
            
            // creates connection
            conn = DriverManager.getConnection(url, username, password);
        } 
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        
        return conn;
    }
    
    public ArrayList<User> getUsers()
    {
        ArrayList<User> users = new ArrayList<User>();
        try
        {
            Statement stat = connection.createStatement();
            ResultSet rs = stat.executeQuery("select*from user");
            
            while(rs.next())
            {
                String firstname = (String) rs.getObject("firstname");
                String lastname = (String) rs.getObject("lastname");
                String email = (String) rs.getObject("email");
                int rights = (int) rs.getObject("rights");
                
                User user = new User(firstname, lastname, email, rights);
                
                DeliveryDatabaseController dbc = new DeliveryDatabaseController();
                for(Delivery d: dbc.getDeliveries())
                {
                    if(d.getEmail().equals(email))
                    {
                       user.addDelivery(d);
                    }
                }
                
                users.add(user);
            }
        }
        catch(SQLException e){}
        
        return users;
    }
}

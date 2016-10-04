/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import classes.Delivery;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Adne
 */
public class DeliveryDatabaseController {
    
    private Connection connection;
    
    public DeliveryDatabaseController()
    {
        this.connection = connectToDatabase();
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
    
    public ArrayList<Delivery> getDeliveries()
    {
        ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
        
        try
        {
            Statement stat = connection.createStatement();
            ResultSet rs = stat.executeQuery("select*from delivery");
            
            while(rs.next())
            {
                String modulID = (String) rs.getObject("modulID");
                String email = (String) rs.getObject("email");
                String deliveredDate = (String) rs.getObject("deliveredDate");
                String comment = (String) rs.getObject("comment");
                File file = null;
                
                try
                {
                    file = File.createTempFile("something-", ".binary", new File("."));
                }
                catch(IOException e){}
                
                Statement stat2 = connection.createStatement();
                ResultSet rs2 = stat2.executeQuery("select file from files where email='" + email + "' and modulID='" + modulID + "';");
                
                while(rs2.next())
                {
                    try
                    {
                        Blob blob = rs2.getBlob("file");
                        InputStream in = blob.getBinaryStream();
                        OutputStream out = new FileOutputStream(file);
                        byte[] buff = blob.getBytes(1,(int)blob.length());
                        out.write(buff);
                        out.close();
                    }
                    catch(FileNotFoundException e){}
                    catch(IOException e){}
                }
                
                Delivery delivery = new Delivery(modulID, deliveredDate, comment, email, file);
                deliveries.add(delivery);
            }
        }
        catch(SQLException e){}
        
        return deliveries;
    }
    
    public void addDelivery(Delivery d)
    {
        try
        {
            Statement stat = connection.createStatement();
            stat.executeUpdate("insert into delivery(modulid, email, delivereddate, comment) values('" + d.getModulID() + "', '"+d.getEmail() + "', '" + d.getDeliveredDate() + "', '" + d.getComment() + "');");
            
            PreparedStatement ps = connection.prepareStatement("insert into files (name, email, modulid, file) values(?, ?, ?, ?);");
            
            ps.setString(1, d.getFile().getPath());
            ps.setString(2, d.getEmail());
            ps.setString(3, d.getModulID());
            
            try
            {
                InputStream is = new FileInputStream(d.getFile());
                ps.setBlob(4, is);
            }
            catch(FileNotFoundException e)
            {
                System.out.println("woops");
            }
            
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}

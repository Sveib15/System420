/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;
import classes.Modul;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Adne
 */
public class ModulDatabaseController
{
    private Connection connection;
    
    public ModulDatabaseController()
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
    
    public ArrayList<Modul> getModuls()
    {
        ArrayList<Modul> moduls = new ArrayList<Modul>();
        
        try
        {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select*from modul");
            
            while(rs.next())
            {
                String name = (String) rs.getObject("modulID");
                String description = (String) rs.getObject("description");
                int rights = (int) rs.getObject("rights");
                
                Modul m = new Modul(name, description, rights);   
                
                Statement stm2 = connection.createStatement();
                ResultSet rs2 = stm2.executeQuery("select description from requirement where modulID='"+m.getName()+"';");
                
                while(rs2.next())
                {
                    m.addRequirement((String) rs2.getObject("description"));
                }
                
                rs2 = stm2.executeQuery("select description from resource where modulID='"+m.getName()+"';");
                
                while(rs2.next())
                {
                    m.addResources((String) rs2.getObject("description"));
                }
                
                moduls.add(m);
            }
        }
        catch(SQLException e){}
        
        return moduls;
    }
    
    public void addModul(Modul modul)
    {
        try
        {
            Statement stm = connection.createStatement();
            stm.executeUpdate("insert into modul(modulid, description, rights) values('"+modul.getName()+"', '"+modul.getDescription()+"',"+modul.getRights()+");");
            
            ResultSet rs = stm.executeQuery("select reqID from requirement");
            int reqID = 1;
            while(rs.next())
            {
                if((int) rs.getObject("reqID") == reqID)
                {
                    reqID = (int) rs.getObject("reqID") + 1;
                }
            }
            
            for(String str: modul.getRequirements())
            {
                stm.executeUpdate("insert into requirement(reqID, description, modulID) values("+reqID+", '"+str+"', '"+modul.getName()+"');");
                reqID++;
            }
            
            rs = stm.executeQuery("select resID from resource");
            int resID = 1;
            while(rs.next())
            {
                if((int) rs.getObject("resID") == resID)
                {
                    resID = (int) rs.getObject("resID") + 1;
                }
            }
            
            for(String str: modul.getResources())
            {
                stm.executeUpdate("insert into resource(resID, description, modulID) values("+resID+", '"+str+"', '"+modul.getName()+"');");
                resID++;
            }
        }
        catch(SQLException e){}
    }
    
    public void deleteModul(Modul modul)
    {
        for(Modul m: getModuls())
        {
            if(m.getName().equals(modul.getName()))
            {
                try
                {
                    Statement stat = connection.createStatement();
                    stat.executeUpdate("delete from requirement where modulID='"+modul.getName()+"';");
                    stat.executeUpdate("delete from resource where modulID='"+modul.getName()+"';");
                    stat.executeUpdate("delete from modul where modulID='"+modul.getName()+"';");
                }
                catch(SQLException e){}
                break;
            }
        }
    }
}

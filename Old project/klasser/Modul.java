/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;
import java.util.ArrayList;

/**
 *
 * @author Adne
 */
public class Modul {
    private String name;
    private String description;
    private ArrayList<String> requirements;
    private ArrayList<String> resources;
    private int rights;
    
    public Modul(String name, String description, int rights)
    {
        requirements = new ArrayList<String>();
        resources = new ArrayList<String>();
        
        this.name = name;
        this.description = description;
        this.rights = rights;
    }
    
    public void addRequirement(String req)
    {
        requirements.add(req);
    }
    
    public void addResources(String res)
    {
        resources.add(res);
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public ArrayList<String> getRequirements()
    {
        return requirements;
    }
    
    public ArrayList<String> getResources()
    {
        return resources;
    }
    
    public int getRights()
    {
        return rights;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules;

import java.util.ArrayList;

/**
 *
 * @author Adne
 */
public class Modul 
{
    private String name;
    private String description;
    private ArrayList<String> requirements;
    
    public Modul()
    {
        this.name = "not asigned";
        this.description = "not asigned";
        this.requirements = new ArrayList<String>();
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void addRequirement(String rqm)
    {
        requirements.add(rqm);
    }
    
    public ArrayList<String> getRequirements()
    {
        return requirements;
    }
}

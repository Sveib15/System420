/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules;

import java.util.HashMap;

/**
 *
 * @author Adne
 */
public class Student
{
    private String firstname;
    private String lastname;
    private HashMap<String, Integer> modules;

    public Student(String firstname, String lastname)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.modules = new HashMap<String, Integer>();
    }
    
    public String getFirstname()
    {
        return firstname;
    }
    
    public String getLastname()
    {
        return lastname;
    }          
    
    public void addModule(String name, int status)
    {
        modules.put(name, status);
    }
    
    public HashMap<String, Integer> getModules()
    {
        return modules;
    }
}

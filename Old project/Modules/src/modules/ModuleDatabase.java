/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 *
 * @author Adne
 */
public class ModuleDatabase 
{
    private File file;
    
    public ModuleDatabase(String filepath)
    {
        file = new File(filepath);
    }
    
    public void addModule(Modul modul)
    {
        ArrayList<String> modulRequirements = modul.getRequirements();
        String newLine = modul.getName() + "%" + modul.getDescription() + "%"; 
        for(String s: modulRequirements)
        {
            newLine += s + "%";
        }
        
    	try
        {
            BufferedWriter output = new BufferedWriter(new FileWriter(file.getPath(), true));
            output.append(newLine);
            output.newLine();
            output.close();
 
    	}
        catch(IOException e)
        {
            e.printStackTrace();
    	}
    }
    
    public ArrayList<Modul> getModules()
    {
        ArrayList<Modul> modules = new ArrayList<Modul>();
        int seperatorCount = 0;
        String name = "";
        String description = "";
        String requirements = "";
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) 
        {
            String line;
            
            while ((line = br.readLine()) != null)
            {
                String word = "";
                Modul m = new Modul();
                
                for(int i = 0; i < line.length(); i++)
                {
                    char c = line.charAt(i);
                                      
                    if(c == '%')
                    {
                        if(seperatorCount == 0)
                        {
                            name = word;
                        }
                        else if(seperatorCount == 1)
                        {
                            description = word;
                            m.setName(name);
                            m.setDescription(description);
                        }
                        else
                        {
                            m.addRequirement(word);
                        }
                        
                        seperatorCount ++;
                        word = "";
                    }
                    else
                    {
                        word += c;
                    }
                }
                
                modules.add(m);
                
                name = "";
                description = "";
                requirements = "";
                seperatorCount = 0;
            }
        }
        catch(IOException io)
        {
            
        }
        
        return modules;
    }
    
}

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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adne
 */
public class StudentDatabase 
{
    private File file;
    
    public StudentDatabase(String filepath)
    {
        file = new File(filepath);
    }
    
    public void passModule(int moduleNumber, int moduleAmount, String studentFirstname, String studentLastname)
    {
        String nameSearch = studentLastname + "%" + studentFirstname;
        
        ArrayList<String> lines = new ArrayList<String>();
        
        try(BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line;
            
            while((line = br.readLine()) != null)
            {

                lines.add(line);
                
            }
        }
        catch(IOException io)
        {
                
        }
        
        try
        {
            BufferedWriter output = new BufferedWriter(new FileWriter(file.getPath(), false));
            output.write("");
            
            output = new BufferedWriter(new FileWriter(file.getPath(), true));
            for(String s: lines)
            {             
                if(s.substring(0, nameSearch.length()).equals(nameSearch))
                {
                    String modules = s.substring(s.length()-moduleAmount, s.length());
                    String newModules = "";

                    for(int i = 0; i < moduleAmount; i++)
                    {
                        char c = modules.charAt(i);
                        if(i == moduleNumber-1)
                        {
                            newModules += '1';
                        }
                        else
                        {
                            newModules += c;
                        }
                    }

                    output.append(nameSearch + "%" + newModules);
                    output.newLine();
                }
                     
                else
                {
                    output.append(s);
                    output.newLine();
                }
            }
            
            output.close();
 
    	}
        catch(IOException e)
        {
            e.printStackTrace();
    	}
    }
    
    public void moduleAdded()
    {
        ArrayList<String> lines = new ArrayList<String>();
        
        try(BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line;
            
            while((line = br.readLine()) != null)
            {

                lines.add(line);
                
            }
        }
        catch(IOException io)
        {
                
        }
        
        try
        {
            BufferedWriter output = new BufferedWriter(new FileWriter(file.getPath(), false));
            output.write("");
            
            output = new BufferedWriter(new FileWriter(file.getPath(), false));
            for(String s: lines)
            {
                output.append(s + "0");
                output.newLine();
            }
            
            output.close();
 
    	}
        catch(IOException e)
        {
            e.printStackTrace();
    	}
    }
    
    public ArrayList<Student> getStudents(ArrayList<Modul> modules)
    {
        ArrayList<Student> students = new ArrayList<Student>();
        int seperatorCount = 0;
        String firstname = "";
        String lastname = "";
        
        Student stud = new Student("", "");
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) 
        {
            String line;
            
            while ((line = br.readLine()) != null)
            {
                String word = "";
                String modulesStatus = "";
                for(int i = 0; i < line.length(); i++)
                {
                    char c = line.charAt(i);
                                      
                    if(c == '%')
                    {
                        if(seperatorCount == 0)
                        {
                            lastname = word;                        
                        }
                        else if(seperatorCount == 1)
                        {
                            firstname = word;
                            stud = new Student(firstname, lastname);
                        }

                        seperatorCount ++;
                        word = "";
                    }
                    else
                    {
                        word += c;
                    }
                }
                
                modulesStatus = word;
                int moduleCount = 0;
                
                for(int i = 0; i < modulesStatus.length(); i++)
                {
                    char ch = modulesStatus.charAt(i);
                    int status = 0;
                    if(ch == '1')
                    {
                        status = 1;
                    }
                    stud.addModule(modules.get(moduleCount).getName(), status);
                    moduleCount++;
                }
                
                students.add(stud);
                firstname = "";
                lastname = "";
                seperatorCount = 0;
                moduleCount = 0;
            }
        }
        catch(IOException io)
        {
            
        }
        
        return students;
    }
    
}

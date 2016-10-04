/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.File;

/**
 *
 * @author Adne
 */
public class Delivery {
    private String modulID;
    private String deliveredDate;
    private String comment;
    private String email;
    private File file;
    
    public Delivery(String modulID, String deliveredDate, String comment, String email, File file)
    {
        this.modulID = modulID;
        this.deliveredDate = deliveredDate;
        this.comment = comment;
        this.email = email;
        this.file = file;
    }
    
    public File getFile()
    {
        return file;
    }
    
    public void setFile(File file)
    {
        this.file = file;
    }
    
    public String getModulID()
    {
        return modulID;
    }
    
    public String getDeliveredDate()
    {
        return deliveredDate;
    }
    
    public String getComment()
    {
        return comment;
    }
    
    public String getEmail()
    {
        return email;
    }
}

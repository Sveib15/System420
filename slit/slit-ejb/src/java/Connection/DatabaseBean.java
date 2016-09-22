/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import classes.Modul;
import classes.ModulDatabaseController;
import java.util.ArrayList;
import javax.ejb.Stateful;

/**
 *
 * @author Adne
 */
@Stateful
public class DatabaseBean implements DatabaseBeanRemote 
{
    private ModulDatabaseController mdc;
    
    public DatabaseBean()
    {
        mdc = new ModulDatabaseController();
    }
    
    @Override
    public void businessMethod() 
    {
        
    }
    
    @Override
    public ArrayList<Modul> getModuls() 
    {
        return mdc.getModuls();
    }
    
    @Override
    public void test()
    {
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Beans.ModulDatabaseControllerBeanRemote;
import classes.Modul;
import DatabaseConnections.ModulDatabaseController;
import java.util.ArrayList;
import javax.ejb.Stateless;

/**
 *
 * @author Adne
 */
@Stateless
public class ModulDatabaseControllerBean implements ModulDatabaseControllerBeanRemote 
{
    @Override
    public ArrayList<Modul> getModuls() 
    {
        ModulDatabaseController mdc = new ModulDatabaseController();
        ArrayList<Modul> moduls = mdc.getModuls();
        return moduls;
    }

    @Override
    public void addModul(Modul m) 
    {
        ModulDatabaseController mdc = new ModulDatabaseController();
        Modul s = m;
        mdc.addModul(s);
    }
}

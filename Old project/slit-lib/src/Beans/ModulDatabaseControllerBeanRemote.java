/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import classes.Modul;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author Adne
 */
@Remote
public interface ModulDatabaseControllerBeanRemote 
{

    ArrayList<Modul> getModuls();

    void addModul(Modul m);
}

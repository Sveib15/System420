/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import classes.Modul;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author Adne
 */
@Remote
public interface DatabaseBeanRemote 
{
    void businessMethod();

    ArrayList<Modul> getModuls();
    
    void test();
}

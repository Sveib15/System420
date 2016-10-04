/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import javax.ejb.Remote;

/**
 *
 * @author Adne
 */
@Remote
public interface testBeanRemote 
{
    String testMethod();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import javax.ejb.Stateless;

/**
 *
 * @author Adne
 */
@Stateless
public class testBean implements testBeanRemote 
{
    @Override
    public String testMethod() 
    {
        String msg = "Testing";
        return msg;
    }
}

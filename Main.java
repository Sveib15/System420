/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slit2client;

import DataModel.UserDataModel;
import ServerLib.UserSessionBeanRemote;
import javax.ejb.EJB;

/**
 *
 * @author Ådne
 */
public class Main 
{
    @EJB
    private static UserSessionBeanRemote userSessionBean;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        UserDataModel userDataModel = userSessionBean.getUserTest();
        System.out.println(userDataModel.getFirstname() + " " + userDataModel.getLastname());
    }
    
}

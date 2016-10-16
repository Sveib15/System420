/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import DataModel.UserDataModel;
import Database.User;
import ServerLib.UserSessionBeanRemote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ådne
 */
@Stateless
public class UserSessionBean implements UserSessionBeanRemote 
{
    @PersistenceContext(unitName="slit2-ejbPU")
    private EntityManager em;
    
    @Override
    public UserDataModel getUserTest()
    {
        User user = em.find(User.class, 1);
        return convertToDataModel(user);
    }
    
    public UserDataModel convertToDataModel(User user)
    {
        UserDataModel userDataModel = new UserDataModel();
        
        userDataModel.setUserId(user.getUserId());
        userDataModel.setFirstname(user.getFirstname());
        userDataModel.setLastname(user.getLastname());
        userDataModel.setEmail(user.getEmail());
        userDataModel.setRights(user.getRights());
        
        return userDataModel;
    }
}

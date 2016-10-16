/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerLib;

import DataModel.UserDataModel;
import javax.ejb.Remote;

/**
 *
 * @author Ådne
 */
@Remote
public interface UserSessionBeanRemote 
{
    UserDataModel getUserTest();
}

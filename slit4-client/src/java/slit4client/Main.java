/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slit4client;

import DataModel.ModuleDataModel;
import DataModel.RequirementDataModel;
import DataModel.ResourceDataModel;
import Server.ModuleSessionBeanRemote;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Adne
 */
public class Main 
{
    @EJB
    private static ModuleSessionBeanRemote moduleSessionBean;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
       ModuleDataModel moduleDataModel = moduleSessionBean.getModuleTest();
       System.out.println(moduleDataModel.getName() + ": " + moduleDataModel.getDescription());
       
       /**
       ModuleDataModel mdm = new ModuleDataModel();
       
       ArrayList<ResourceDataModel> resourceList = new ArrayList<ResourceDataModel>();
       ArrayList<RequirementDataModel> requirementList = new ArrayList<RequirementDataModel>();
       
       ResourceDataModel rdm = new ResourceDataModel();
       rdm.setDescription("333333333");
       rdm.setResourceId(5);
       RequirementDataModel rdm2 = new RequirementDataModel();
       rdm2.setDescription("333333333");
       rdm2.setRequirementId(5);
       
       resourceList.add(rdm);
       requirementList.add(rdm2);
       
       mdm.setRequirementList(requirementList);
       mdm.setResourceList(resourceList);
       mdm.setModuleId(3);
       mdm.setName("3333333333");
       mdm.setDescription("333333333333");
       mdm.setRights(3);
       
       moduleSessionBean.addModule(mdm);
       **/
       
       List<ModuleDataModel> dataModels = moduleSessionBean.getAllModules();
       dataModels = new ArrayList<ModuleDataModel>();
       
       for(ModuleDataModel mdm: dataModels)
       {
           System.out.println(mdm.getName());
       }
    }
}

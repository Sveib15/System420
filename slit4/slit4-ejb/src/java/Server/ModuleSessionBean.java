/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import DataModel.ModuleDataModel;
import DataModel.RequirementDataModel;
import DataModel.ResourceDataModel;
import Database.Module;
import Database.Requirement;
import Database.Resource;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Adne
 */
@Stateless
public class ModuleSessionBean implements ModuleSessionBeanRemote 
{
    @PersistenceContext(unitName="slit4-ejbPU")
    private EntityManager em;
    
    @Override
    public String test() 
    {
        Module module = em.find(Module.class, 1);
        return module.getName();
    } 

    @Override
    public ModuleDataModel getModuleTest() 
    {   
        Module module = em.find(Module.class, 1);
        ModuleDataModel moduleDataModel = module2DataModel(module);
        return moduleDataModel;
    }
    
    private ModuleDataModel module2DataModel(Module module)
    {
        ModuleDataModel moduleDataModel = new ModuleDataModel();
        ArrayList<ResourceDataModel> resourceDataModelList = new ArrayList<ResourceDataModel>();
        ArrayList<RequirementDataModel> requirementDataModelList = new ArrayList<RequirementDataModel>();
        
        for(Resource r: module.getResourceList())
        {
            ResourceDataModel resourceDataModel = new ResourceDataModel();
            
            resourceDataModel.setDescription(r.getDescription());
            resourceDataModel.setResourceId(r.getResourceId());
            
            resourceDataModelList.add(resourceDataModel);
        }
        
        for(Requirement r: module.getRequirementList())
        {
            RequirementDataModel requirementDataModel = new RequirementDataModel();
            
            requirementDataModel.setDescription(r.getDescription());
            requirementDataModel.setRequirementId(r.getRequirementId());
            
            requirementDataModelList.add(requirementDataModel);
        }
        
        moduleDataModel.setName(module.getName());
        moduleDataModel.setDescription(module.getDescription());
        moduleDataModel.setModuleId(module.getModuleId());
        moduleDataModel.setRights(module.getRights());
        moduleDataModel.setRequirementList(requirementDataModelList);
        moduleDataModel.setResourceList(resourceDataModelList);
        
        return moduleDataModel;
    }
    
    @Override
    public void addModule(ModuleDataModel moduleDataModel) 
    {
        Module module = new Module();
        
        module.setDescription(moduleDataModel.getDescription());
        module.setModuleId(moduleDataModel.getModuleId());
        module.setName(moduleDataModel.getName());
        module.setRights(moduleDataModel.getRights());
        
        em.persist(module);
        
        for(ResourceDataModel rdm: moduleDataModel.getResourceList())
        {
            Resource resource = new Resource();
            
            resource.setDescription(rdm.getDescription());
            resource.setResourceId(rdm.getResourceId());
            resource.setModuleId(module);
            
            em.persist(resource);
        }
        
        for(RequirementDataModel rdm: moduleDataModel.getRequirementList())
        {
            Requirement requirement = new Requirement();
            
            requirement.setDescription(rdm.getDescription());
            requirement.setRequirementId(rdm.getRequirementId());
            requirement.setModuleId(module);
            
            em.persist(requirement);
        }
        
        
    }

    @Override
    public List<ModuleDataModel> getAllModules() 
    {
        List<ModuleDataModel> dataModels = new ArrayList<ModuleDataModel>();
        
        try
        {
            Query query = em.createNamedQuery("Module.findAll", Module.class);
            List<Module> modules = query.getResultList();
            
            for(Module m: modules)
            {
                ModuleDataModel moduleDataModel = module2DataModel(m);
                dataModels.add(moduleDataModel);
                
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return dataModels;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataModel;

import java.util.ArrayList;

/**
 *
 * @author Adne
 */
public class ModuleDataModel implements java.io.Serializable
{
    private Integer moduleId;
    private String description;
    private Integer rights;
    private String name;
    private ArrayList<ResourceDataModel> resourceList;
    private ArrayList<RequirementDataModel> requirementList;

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRights() {
        return rights;
    }

    public void setRights(Integer rights) {
        this.rights = rights;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ResourceDataModel> getResourceList() {
        return resourceList;
    }

    public void setResourceList(ArrayList<ResourceDataModel> resourceList) {
        this.resourceList = resourceList;
    }

    public ArrayList<RequirementDataModel> getRequirementList() {
        return requirementList;
    }

    public void setRequirementList(ArrayList<RequirementDataModel> requirementList) {
        this.requirementList = requirementList;
    }
    
    
    
}

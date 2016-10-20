/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Adne
 */
@Entity
@Table(name = "semester_plan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SemesterPlan.findAll", query = "SELECT s FROM SemesterPlan s"),
    @NamedQuery(name = "SemesterPlan.findBySemesterPlanId", query = "SELECT s FROM SemesterPlan s WHERE s.semesterPlanId = :semesterPlanId")})
public class SemesterPlan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "semester_plan_id")
    private Integer semesterPlanId;
    @OneToMany(mappedBy = "semesterPlanId")
    private List<ModulePlan> modulePlanList;
    @OneToMany(mappedBy = "semesterPlanId")
    private List<User> userList;

    public SemesterPlan() {
    }

    public SemesterPlan(Integer semesterPlanId) {
        this.semesterPlanId = semesterPlanId;
    }

    public Integer getSemesterPlanId() {
        return semesterPlanId;
    }

    public void setSemesterPlanId(Integer semesterPlanId) {
        this.semesterPlanId = semesterPlanId;
    }

    @XmlTransient
    public List<ModulePlan> getModulePlanList() {
        return modulePlanList;
    }

    public void setModulePlanList(List<ModulePlan> modulePlanList) {
        this.modulePlanList = modulePlanList;
    }

    @XmlTransient
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (semesterPlanId != null ? semesterPlanId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SemesterPlan)) {
            return false;
        }
        SemesterPlan other = (SemesterPlan) object;
        if ((this.semesterPlanId == null && other.semesterPlanId != null) || (this.semesterPlanId != null && !this.semesterPlanId.equals(other.semesterPlanId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.SemesterPlan[ semesterPlanId=" + semesterPlanId + " ]";
    }
    
}

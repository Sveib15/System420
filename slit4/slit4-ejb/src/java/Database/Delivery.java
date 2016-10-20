/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Adne
 */
@Entity
@Table(name = "delivery")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Delivery.findAll", query = "SELECT d FROM Delivery d"),
    @NamedQuery(name = "Delivery.findByDeliveryId", query = "SELECT d FROM Delivery d WHERE d.deliveryId = :deliveryId"),
    @NamedQuery(name = "Delivery.findByStatus", query = "SELECT d FROM Delivery d WHERE d.status = :status"),
    @NamedQuery(name = "Delivery.findByComment", query = "SELECT d FROM Delivery d WHERE d.comment = :comment"),
    @NamedQuery(name = "Delivery.findByDateDelivered", query = "SELECT d FROM Delivery d WHERE d.dateDelivered = :dateDelivered"),
    @NamedQuery(name = "Delivery.findByDateApproved", query = "SELECT d FROM Delivery d WHERE d.dateApproved = :dateApproved"),
    @NamedQuery(name = "Delivery.findByQueNumber", query = "SELECT d FROM Delivery d WHERE d.queNumber = :queNumber")})
public class Delivery implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "delivery_id")
    private Integer deliveryId;
    @Column(name = "status")
    private Boolean status;
    @Size(max = 500)
    @Column(name = "comment")
    private String comment;
    @Column(name = "date_delivered")
    @Temporal(TemporalType.DATE)
    private Date dateDelivered;
    @Column(name = "date_approved")
    @Temporal(TemporalType.DATE)
    private Date dateApproved;
    @Column(name = "que_number")
    private Integer queNumber;
    @JoinColumn(name = "module_id", referencedColumnName = "module_id")
    @ManyToOne
    private Module moduleId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne
    private User userId;
    @OneToMany(mappedBy = "deliveryId")
    private List<File> fileList;

    public Delivery() {
    }

    public Delivery(Integer deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Integer getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Integer deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDateDelivered() {
        return dateDelivered;
    }

    public void setDateDelivered(Date dateDelivered) {
        this.dateDelivered = dateDelivered;
    }

    public Date getDateApproved() {
        return dateApproved;
    }

    public void setDateApproved(Date dateApproved) {
        this.dateApproved = dateApproved;
    }

    public Integer getQueNumber() {
        return queNumber;
    }

    public void setQueNumber(Integer queNumber) {
        this.queNumber = queNumber;
    }

    public Module getModuleId() {
        return moduleId;
    }

    public void setModuleId(Module moduleId) {
        this.moduleId = moduleId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @XmlTransient
    public List<File> getFileList() {
        return fileList;
    }

    public void setFileList(List<File> fileList) {
        this.fileList = fileList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deliveryId != null ? deliveryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Delivery)) {
            return false;
        }
        Delivery other = (Delivery) object;
        if ((this.deliveryId == null && other.deliveryId != null) || (this.deliveryId != null && !this.deliveryId.equals(other.deliveryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Delivery[ deliveryId=" + deliveryId + " ]";
    }
    
}

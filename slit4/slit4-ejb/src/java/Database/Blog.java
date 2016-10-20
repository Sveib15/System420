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
@Table(name = "blog")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Blog.findAll", query = "SELECT b FROM Blog b"),
    @NamedQuery(name = "Blog.findByBlogId", query = "SELECT b FROM Blog b WHERE b.blogId = :blogId")})
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "blog_id")
    private Integer blogId;
    @OneToMany(mappedBy = "blogId")
    private List<BlogPost> blogPostList;
    @OneToMany(mappedBy = "blogId")
    private List<User> userList;

    public Blog() {
    }

    public Blog(Integer blogId) {
        this.blogId = blogId;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    @XmlTransient
    public List<BlogPost> getBlogPostList() {
        return blogPostList;
    }

    public void setBlogPostList(List<BlogPost> blogPostList) {
        this.blogPostList = blogPostList;
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
        hash += (blogId != null ? blogId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Blog)) {
            return false;
        }
        Blog other = (Blog) object;
        if ((this.blogId == null && other.blogId != null) || (this.blogId != null && !this.blogId.equals(other.blogId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Blog[ blogId=" + blogId + " ]";
    }
    
}

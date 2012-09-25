package models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.db.ebean.Model;

@Entity
@Table(name="menu")
public class Menu extends Model {
    private Long id;
    private Usergroup usergroup;
    private Date createdAt;
    private Boolean deal;
    private Date closedAt;
    private boolean isAvailable;
    private Set<MenuUser> menuUsers = new HashSet<MenuUser>(0);
    private Set<Foodorder> foodorders = new HashSet<Foodorder>(0);

    @Id  @GeneratedValue(strategy = GenerationType.AUTO)    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="usergroup_id", nullable=false)
    public Usergroup getUsergroup() {
        return this.usergroup;
    }
    
    public void setUsergroup(Usergroup usergroup) {
        this.usergroup = usergroup;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at", length=19)
    public Date getCreatedAt() {
        return this.createdAt;
    }
    
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    @Column(name="deal")
    public Boolean getDeal() {
        return this.deal;
    }
    
    public void setDeal(Boolean deal) {
        this.deal = deal;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="closed_at", length=19)
    public Date getClosedAt() {
        return this.closedAt;
    }
    
    public void setClosedAt(Date closedAt) {
        this.closedAt = closedAt;
    }
    
    @Column(name="is_available", nullable=false)
    public boolean isIsAvailable() {
        return this.isAvailable;
    }
    
    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="menu")
    public Set<MenuUser> getMenuUsers() {
        return this.menuUsers;
    }
    
    public void setMenuUsers(Set<MenuUser> menuUsers) {
        this.menuUsers = menuUsers;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="menu")
    public Set<Foodorder> getFoodorders() {
        return this.foodorders;
    }
    
    public void setFoodorders(Set<Foodorder> foodorders) {
        this.foodorders = foodorders;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Menu pojo = (Menu) o;

        if (usergroup != null ? !usergroup.equals(pojo.usergroup) : pojo.usergroup != null) return false;
        if (createdAt != null ? !createdAt.equals(pojo.createdAt) : pojo.createdAt != null) return false;
        if (deal != null ? !deal.equals(pojo.deal) : pojo.deal != null) return false;
        if (closedAt != null ? !closedAt.equals(pojo.closedAt) : pojo.closedAt != null) return false;
        if (isAvailable != pojo.isAvailable) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (usergroup != null ? usergroup.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (deal != null ? deal.hashCode() : 0);
        result = 31 * result + (closedAt != null ? closedAt.hashCode() : 0);
        result = 31 * result + (isAvailable ? 1 : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("usergroup").append("='").append(getUsergroup()).append("', ");
        sb.append("createdAt").append("='").append(getCreatedAt()).append("', ");
        sb.append("deal").append("='").append(getDeal()).append("', ");
        sb.append("closedAt").append("='").append(getClosedAt()).append("', ");
        sb.append("isAvailable").append("='").append(isIsAvailable()).append("', ");
        
        
        sb.append("]");
      
        return sb.toString();
    }

}

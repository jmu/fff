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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.db.ebean.Model;

@Entity
@Table(name="restaurant")
public class Restaurant extends Model {
    private Long id;
    private String name;
    private String phoneNumber;
    private String address;
    private String manager;
    private Float ranking;
    private Long rankingVote;
    private Date openAt;
    private Date deliveryAt;
    private Boolean isAvailable;
    private Set<Food> foods = new HashSet<Food>(0);

    @Id @GeneratedValue(strategy = GenerationType.AUTO)    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="name", nullable=false, length=100)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="phone_number", length=50)
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    @Column(name="address")
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    @Column(name="manager", length=50)
    public String getManager() {
        return this.manager;
    }
    
    public void setManager(String manager) {
        this.manager = manager;
    }
    
    @Column(name="ranking", precision=12, scale=0)
    public Float getRanking() {
        return this.ranking;
    }
    
    public void setRanking(Float ranking) {
        this.ranking = ranking;
    }
    
    @Column(name="ranking_vote")
    public Long getRankingVote() {
        return this.rankingVote;
    }
    
    public void setRankingVote(Long rankingVote) {
        this.rankingVote = rankingVote;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="open_at")
    public Date getOpenAt() {
        return this.openAt;
    }
    
    public void setOpenAt(Date openAt) {
        this.openAt = openAt;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="delivery_at", nullable=false)
    public Date getDeliveryAt() {
        return this.deliveryAt;
    }
    
    public void setDeliveryAt(Date deliveryAt) {
        this.deliveryAt = deliveryAt;
    }
    
    @Column(name="is_available")
    public Boolean getIsAvailable() {
        return this.isAvailable;
    }
    
    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="restaurant")
    public Set<Food> getFoods() {
        return this.foods;
    }
    
    public void setFoods(Set<Food> foods) {
        this.foods = foods;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Restaurant pojo = (Restaurant) o;

        if (name != null ? !name.equals(pojo.name) : pojo.name != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(pojo.phoneNumber) : pojo.phoneNumber != null) return false;
        if (address != null ? !address.equals(pojo.address) : pojo.address != null) return false;
        if (manager != null ? !manager.equals(pojo.manager) : pojo.manager != null) return false;
        if (ranking != null ? !ranking.equals(pojo.ranking) : pojo.ranking != null) return false;
        if (rankingVote != null ? !rankingVote.equals(pojo.rankingVote) : pojo.rankingVote != null) return false;
        if (openAt != null ? !openAt.equals(pojo.openAt) : pojo.openAt != null) return false;
        if (deliveryAt != null ? !deliveryAt.equals(pojo.deliveryAt) : pojo.deliveryAt != null) return false;
        if (isAvailable != null ? !isAvailable.equals(pojo.isAvailable) : pojo.isAvailable != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (name != null ? name.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (manager != null ? manager.hashCode() : 0);
        result = 31 * result + (ranking != null ? ranking.hashCode() : 0);
        result = 31 * result + (rankingVote != null ? rankingVote.hashCode() : 0);
        result = 31 * result + (openAt != null ? openAt.hashCode() : 0);
        result = 31 * result + (deliveryAt != null ? deliveryAt.hashCode() : 0);
        result = 31 * result + (isAvailable != null ? isAvailable.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("name").append("='").append(getName()).append("', ");
        sb.append("phoneNumber").append("='").append(getPhoneNumber()).append("', ");
        sb.append("address").append("='").append(getAddress()).append("', ");
        sb.append("manager").append("='").append(getManager()).append("', ");
        sb.append("ranking").append("='").append(getRanking()).append("', ");
        sb.append("rankingVote").append("='").append(getRankingVote()).append("', ");
        sb.append("openAt").append("='").append(getOpenAt()).append("', ");
        sb.append("deliveryAt").append("='").append(getDeliveryAt()).append("', ");
        sb.append("isAvailable").append("='").append(getIsAvailable()).append("', ");
        
        sb.append("]");
      
        return sb.toString();
    }

}

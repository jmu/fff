package models;

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

import play.db.ebean.Model;

@Entity
@Table(name="food")
public class Food extends Model {
    private Long id;
    private Foodtype foodtype;
    private Restaurant restaurant;
    private String name;
    private double price;
    private Boolean isAvailable;
    private float ranking;
    private Long rankingVote;
    private Set<Foodorder> foodorders = new HashSet<Foodorder>(0);
    private Set<Comment> comments = new HashSet<Comment>(0);
    private Set<Schedule> schedules = new HashSet<Schedule>(0);

    @Id  @GeneratedValue(strategy = GenerationType.AUTO)    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="foodtype_id")
    public Foodtype getFoodtype() {
        return this.foodtype;
    }
    
    public void setFoodtype(Foodtype foodtype) {
        this.foodtype = foodtype;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="restaurant_id", nullable=false)
    public Restaurant getRestaurant() {
        return this.restaurant;
    }
    
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    
    @Column(name="name", nullable=false, length=100)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="price", nullable=false)
    public double getPrice() {
        return this.price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    @Column(name="is_available")
    public Boolean getIsAvailable() {
        return this.isAvailable;
    }
    
    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    
    @Column(name="ranking", nullable=false, precision=12, scale=0)
    public float getRanking() {
        return this.ranking;
    }
    
    public void setRanking(float ranking) {
        this.ranking = ranking;
    }
    
    @Column(name="ranking_vote")
    public Long getRankingVote() {
        return this.rankingVote;
    }
    
    public void setRankingVote(Long rankingVote) {
        this.rankingVote = rankingVote;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="food")
    public Set<Foodorder> getFoodorders() {
        return this.foodorders;
    }
    
    public void setFoodorders(Set<Foodorder> foodorders) {
        this.foodorders = foodorders;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="food")
    public Set<Comment> getComments() {
        return this.comments;
    }
    
    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="food")
    public Set<Schedule> getSchedules() {
        return this.schedules;
    }
    
    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Food pojo = (Food) o;

        if (foodtype != null ? !foodtype.equals(pojo.foodtype) : pojo.foodtype != null) return false;
        if (restaurant != null ? !restaurant.equals(pojo.restaurant) : pojo.restaurant != null) return false;
        if (name != null ? !name.equals(pojo.name) : pojo.name != null) return false;
        if (price!=pojo.price) return false;
        if (isAvailable != null ? !isAvailable.equals(pojo.isAvailable) : pojo.isAvailable != null) return false;
        if (ranking!=pojo.ranking) return false;
        if (rankingVote != null ? !rankingVote.equals(pojo.rankingVote) : pojo.rankingVote != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (foodtype != null ? foodtype.hashCode() : 0);
        result = 31 * result + (restaurant != null ? restaurant.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (isAvailable != null ? isAvailable.hashCode() : 0);
        result = 31 * result + (rankingVote != null ? rankingVote.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("foodtype").append("='").append(getFoodtype()).append("', ");
        sb.append("restaurant").append("='").append(getRestaurant()).append("', ");
        sb.append("name").append("='").append(getName()).append("', ");
        sb.append("price").append("='").append(getPrice()).append("', ");
        sb.append("isAvailable").append("='").append(getIsAvailable()).append("', ");
        sb.append("ranking").append("='").append(getRanking()).append("', ");
        sb.append("rankingVote").append("='").append(getRankingVote()).append("', ");
        
        
        
        sb.append("]");
      
        return sb.toString();
    }

}

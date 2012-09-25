package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.db.ebean.Model;

@Entity
@Table(name="foodorder")
public class Foodorder extends Model {
    private Long id;
    private User user;
    private Menu menu;
    private Ordertype ordertype;
    private Food food;
    private Long quantity;
    private Double discount;
    private Date orderAt;
    private Boolean deal;
    private String comments;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="menu_id", nullable=false)
    public Menu getMenu() {
        return this.menu;
    }
    
    public void setMenu(Menu menu) {
        this.menu = menu;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ordertype_id")
    public Ordertype getOrdertype() {
        return this.ordertype;
    }
    
    public void setOrdertype(Ordertype ordertype) {
        this.ordertype = ordertype;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="food_id", nullable=false)
    public Food getFood() {
        return this.food;
    }
    
    public void setFood(Food food) {
        this.food = food;
    }
    
    @Column(name="quantity", nullable=false)
    public Long getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
    
    @Column(name="discount", precision=22, scale=0)
    public Double getDiscount() {
        return this.discount;
    }
    
    public void setDiscount(Double discount) {
        this.discount = discount;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="order_at", length=19)
    public Date getOrderAt() {
        return this.orderAt;
    }
    
    public void setOrderAt(Date orderAt) {
        this.orderAt = orderAt;
    }
    
    @Column(name="deal")
    public Boolean getDeal() {
        return this.deal;
    }
    
    public void setDeal(Boolean deal) {
        this.deal = deal;
    }
    
    @Column(name="comments")
    public String getComments() {
        return this.comments;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Foodorder pojo = (Foodorder) o;

        if (user != null ? !user.equals(pojo.user) : pojo.user != null) return false;
        if (menu != null ? !menu.equals(pojo.menu) : pojo.menu != null) return false;
        if (ordertype != null ? !ordertype.equals(pojo.ordertype) : pojo.ordertype != null) return false;
        if (food != null ? !food.equals(pojo.food) : pojo.food != null) return false;
        if (quantity != null ? !quantity.equals(pojo.quantity) : pojo.quantity != null) return false;
        if (discount != null ? !discount.equals(pojo.discount) : pojo.discount != null) return false;
        if (orderAt != null ? !orderAt.equals(pojo.orderAt) : pojo.orderAt != null) return false;
        if (deal != null ? !deal.equals(pojo.deal) : pojo.deal != null) return false;
        if (comments != null ? !comments.equals(pojo.comments) : pojo.comments != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (user != null ? user.hashCode() : 0);
        result = 31 * result + (menu != null ? menu.hashCode() : 0);
        result = 31 * result + (ordertype != null ? ordertype.hashCode() : 0);
        result = 31 * result + (food != null ? food.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        result = 31 * result + (orderAt != null ? orderAt.hashCode() : 0);
        result = 31 * result + (deal != null ? deal.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("user").append("='").append(getUser()).append("', ");
        sb.append("menu").append("='").append(getMenu()).append("', ");
        sb.append("ordertype").append("='").append(getOrdertype()).append("', ");
        sb.append("food").append("='").append(getFood()).append("', ");
        sb.append("quantity").append("='").append(getQuantity()).append("', ");
        sb.append("discount").append("='").append(getDiscount()).append("', ");
        sb.append("orderAt").append("='").append(getOrderAt()).append("', ");
        sb.append("deal").append("='").append(getDeal()).append("', ");
        sb.append("comments").append("='").append(getComments()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}

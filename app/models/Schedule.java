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
@Table(name="schedule")
public class Schedule extends Model {
    private Long id;
    private User user;
    private Ordertype ordertype;
    private Food food;
    private Date startAt;
    private Date endAt;
    private Long calendarType;
    private Long cycleDay;
    private Long cycleTimes;
    private boolean isAvailable;

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
    @JoinColumn(name="ordertype_id", nullable=false)
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
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="start_at", length=19)
    public Date getStartAt() {
        return this.startAt;
    }
    
    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="end_at", length=19)
    public Date getEndAt() {
        return this.endAt;
    }
    
    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }
    
    @Column(name="calendar_type")
    public Long getCalendarType() {
        return this.calendarType;
    }
    
    public void setCalendarType(Long calendarType) {
        this.calendarType = calendarType;
    }
    
    @Column(name="cycle_day")
    public Long getCycleDay() {
        return this.cycleDay;
    }
    
    public void setCycleDay(Long cycleDay) {
        this.cycleDay = cycleDay;
    }
    
    @Column(name="cycle_times")
    public Long getCycleTimes() {
        return this.cycleTimes;
    }
    
    public void setCycleTimes(Long cycleTimes) {
        this.cycleTimes = cycleTimes;
    }
    
    @Column(name="is_available", nullable=false)
    public boolean isIsAvailable() {
        return this.isAvailable;
    }
    
    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Schedule pojo = (Schedule) o;

        if (user != null ? !user.equals(pojo.user) : pojo.user != null) return false;
        if (ordertype != null ? !ordertype.equals(pojo.ordertype) : pojo.ordertype != null) return false;
        if (food != null ? !food.equals(pojo.food) : pojo.food != null) return false;
        if (startAt != null ? !startAt.equals(pojo.startAt) : pojo.startAt != null) return false;
        if (endAt != null ? !endAt.equals(pojo.endAt) : pojo.endAt != null) return false;
        if (calendarType != null ? !calendarType.equals(pojo.calendarType) : pojo.calendarType != null) return false;
        if (cycleDay != null ? !cycleDay.equals(pojo.cycleDay) : pojo.cycleDay != null) return false;
        if (cycleTimes != null ? !cycleTimes.equals(pojo.cycleTimes) : pojo.cycleTimes != null) return false;
        if (isAvailable != pojo.isAvailable) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (user != null ? user.hashCode() : 0);
        result = 31 * result + (ordertype != null ? ordertype.hashCode() : 0);
        result = 31 * result + (food != null ? food.hashCode() : 0);
        result = 31 * result + (startAt != null ? startAt.hashCode() : 0);
        result = 31 * result + (endAt != null ? endAt.hashCode() : 0);
        result = 31 * result + (calendarType != null ? calendarType.hashCode() : 0);
        result = 31 * result + (cycleDay != null ? cycleDay.hashCode() : 0);
        result = 31 * result + (cycleTimes != null ? cycleTimes.hashCode() : 0);
        result = 31 * result + (isAvailable ? 1 : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("user").append("='").append(getUser()).append("', ");
        sb.append("ordertype").append("='").append(getOrdertype()).append("', ");
        sb.append("food").append("='").append(getFood()).append("', ");
        sb.append("startAt").append("='").append(getStartAt()).append("', ");
        sb.append("endAt").append("='").append(getEndAt()).append("', ");
        sb.append("calendarType").append("='").append(getCalendarType()).append("', ");
        sb.append("cycleDay").append("='").append(getCycleDay()).append("', ");
        sb.append("cycleTimes").append("='").append(getCycleTimes()).append("', ");
        sb.append("isAvailable").append("='").append(isIsAvailable()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}

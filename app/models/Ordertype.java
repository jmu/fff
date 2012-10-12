package models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
@Table(name = "ordertype")
public class Ordertype extends Model {
	private static final long serialVersionUID = 1L;
	public static final String AUTO_ORDER_TYPE = "AUTO_ORDER_TYPE";
	public static final String MANUAL_ORDER_TYPE = "MANUAL_ORDER_TYPE";
	
    @Id
	public Long id;
    @Required
	public String name;
    @OneToMany
	public Set<Foodorder> foodorders = new HashSet<Foodorder>(0);
    @OneToMany
	public Set<Schedule> schedules = new HashSet<Schedule>(0);
}

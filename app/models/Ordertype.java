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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.ebean.Model;

@Entity
@Table(name = "ordertype")
public class Ordertype extends Model {
	private Long id;
	private String name;
	private Set<Foodorder> foodorders = new HashSet<Foodorder>(0);
	private Set<Schedule> schedules = new HashSet<Schedule>(0);

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ordertype")
	public Set<Foodorder> getFoodorders() {
		return this.foodorders;
	}

	public void setFoodorders(Set<Foodorder> foodorders) {
		this.foodorders = foodorders;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ordertype")
	public Set<Schedule> getSchedules() {
		return this.schedules;
	}

	public void setSchedules(Set<Schedule> schedules) {
		this.schedules = schedules;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Ordertype pojo = (Ordertype) o;

		if (name != null ? !name.equals(pojo.name) : pojo.name != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result = 0;
		result = (name != null ? name.hashCode() : 0);

		return result;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer(getClass().getSimpleName());

		sb.append(" [");
		sb.append("id").append("='").append(getId()).append("', ");
		sb.append("name").append("='").append(getName()).append("', ");

		sb.append("]");

		return sb.toString();
	}

}

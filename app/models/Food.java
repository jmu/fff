package models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

import com.avaje.ebean.Page;

@Entity
@Table(name = "food")
public class Food extends Model {
	private static final long serialVersionUID = -1006004402235071334L;
	@Id
	public Long id;
	@ManyToOne
	public Foodtype foodtype;
	@ManyToOne
	public Restaurant restaurant;
	@Required
	@MaxLength(100)
	public String name;
	@Required
	public double price;
	public Boolean isAvailable;
	public float ranking;
	public Long rankingVote;

	@OneToMany
	public Set<Foodorder> foodorders = new HashSet<Foodorder>(0);
	@OneToMany
	public Set<Comment> comments = new HashSet<Comment>(0);
	@OneToMany
	public Set<Schedule> schedules = new HashSet<Schedule>(0);

	public static Finder<Long, Food> find = new Finder<Long, Food>(Long.class,
			Food.class);

	public static Page<Food> page(int page, int pageSize, String sortBy,
			String order, String filter) {
		return find.where().ilike("name", "%" + filter + "%")
				.orderBy(sortBy + " " + order).fetch("restaurant")
				.findPagingList(pageSize).getPage(page);
	}
}

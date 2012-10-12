package models;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import play.data.format.Formats;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

import com.avaje.ebean.Page;

@Entity
@Table(name = "restaurant")
public class Restaurant extends Model {
	private static final long serialVersionUID = 6721335676020048715L;

	@Id
	public Long id;
	@Required
	public String name;
	@MaxLength(20)
	public String phoneNumber;
	@MaxLength(50)
	public String address;
	public String manager;
	public Float ranking;
	public Long rankingVote;

	@Formats.DateTime(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date openAt;
	@Formats.DateTime(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date deliveryAt;
	public Boolean isAvailable;

	@OneToMany
	@JsonIgnore
	public Set<Food> foods = new HashSet<Food>(0);

	public static Finder<Long, Restaurant> find = new Finder<Long, Restaurant>(
			Long.class, Restaurant.class);

	/**
	 * Return a page of Restaurant
	 * 
	 * @param page
	 *            Page to display
	 * @param pageSize
	 *            Number of Restaurants per page
	 * @param sortBy
	 *            Restaurant property used for sorting
	 * @param order
	 *            Sort order (either or asc or desc)
	 * @param filter
	 *            Filter applied on the name column
	 */
	public static Page<Restaurant> page(int page, int pageSize, String sortBy,
			String order, String filter) {
		return find.where().ilike("name", "%" + filter + "%")
				.orderBy(sortBy + " " + order)//.fetch("company")
				.findPagingList(pageSize).getPage(page);
	}
	
    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Restaurant c: find.where().eq("isAvailable", true).orderBy("name").findList()) {
            options.put(c.id.toString(), c.name);
        }
        return options;
    }
}

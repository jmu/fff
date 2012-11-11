package models;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

import com.avaje.ebean.Page;

@Entity
@Table(name="foodtype")
public class Foodtype extends Model {
	private static final long serialVersionUID = 9120429003744689257L;
	@Id
    public Long id;
    @Required
    @MaxLength(100)
    public String name;
    //user can change the price
    public Boolean freePrice = false;

    @OneToMany(cascade=CascadeType.PERSIST)
	@JsonIgnore
    public Set<Food> foods = new HashSet<Food>(0);

    public String validate() {
        if (this.freePrice == null) {
            this.freePrice = false;
        }
        return null;
    }

	public static Finder<Long, Foodtype> find = new Finder<Long, Foodtype>(
			Long.class, Foodtype.class);

	/**
	 * Return a page of Foodtype
	 * 
	 * @param page
	 *            Page to display
	 * @param pageSize
	 *            Number of Foodtypes per page
	 * @param sortBy
	 *            Foodtype property used for sorting
	 * @param order
	 *            Sort order (either or asc or desc)
	 * @param filter
	 *            Filter applied on the name column
	 */
	public static Page<Foodtype> page(int page, int pageSize, String sortBy,
			String order, String filter) {
		return find.where().ilike("name", "%" + filter + "%")
				.orderBy(sortBy + " " + order)
				.findPagingList(pageSize).getPage(page);
	}
	
    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Foodtype c: find.where().orderBy("name").findList()) {
            options.put(c.id.toString(), c.name);
        }
        return options;
    }

}

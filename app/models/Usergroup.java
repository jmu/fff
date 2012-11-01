package models;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

import com.avaje.ebean.Page;

@Entity
@Table(name = "usergroup")
public class Usergroup extends Model {
    @Id
	public Long id;
    @Required
    @MaxLength(100)
	public String name;
	public Boolean imOn;
	public Boolean openReg;
	public Long luckyRule;
	public Boolean mailReminderOn;
	public Boolean menuAutoClose;
	public Boolean bonusOn;
	public Double bonusPayRatio;
	public Double bonusOrderRatio;
	public Double bonusManageRatio;
	public Double bonusCarryRatio;
    public Boolean isAvailable;
    @MaxLength(1000)
	public String description;

    @OneToMany
	public Set<User> users = new HashSet<User>(0);
    @OneToMany
	public Set<Payment> payments = new HashSet<Payment>(0);
    @OneToMany
	public Set<Menu> menus = new HashSet<Menu>(0);
    @ManyToMany
    public Set<Restaurant> restaurants = new TreeSet<Restaurant>();

    public String validate() {
        if (isAvailable == null) {
            isAvailable = false;
        }
        return null;
    }

	public static Finder<Long, Usergroup> find = new Finder<Long, Usergroup>(
			Long.class, Usergroup.class);

	/**
	 * Return a page of Usergroup
	 * 
	 * @param page
	 *            Page to display
	 * @param pageSize
	 *            Number of Usergroups per page
	 * @param sortBy
	 *            Usergroup property used for sorting
	 * @param order
	 *            Sort order (either or asc or desc)
	 * @param filter
	 *            Filter applied on the name column
	 */
	public static Page<Usergroup> page(int page, int pageSize, String sortBy,
			String order, String filter) {
		return find.where().ilike("name", "%" + filter + "%")
				.orderBy(sortBy + " " + order)
				.findPagingList(pageSize).getPage(page);
	}
	
    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Usergroup c: find.where().eq("isAvailable", true).orderBy("name").findList()) {
            options.put(c.id.toString(), c.name);
        }
        return options;
    }
}

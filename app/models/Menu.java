package models;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

import com.avaje.ebean.Page;

@Entity
@Table(name="menu")
public class Menu extends Model {
    @Id
    public Long id;
    @MaxLength(100)
    @Required
    public String name;
    @ManyToOne
    public Usergroup usergroup;
    public Date createdAt;
    public Boolean deal;
    public Date closedAt;
    public boolean isAvailable;

    @OneToMany
	@JsonIgnore
    public Set<MenuUser> menuUsers = new HashSet<MenuUser>(0);
    @OneToMany
	@JsonIgnore
    public Set<Foodorder> foodorders = new HashSet<Foodorder>(0);

	public static Finder<Long, Menu> find = new Finder<Long, Menu>(
			Long.class, Menu.class);

    public String validate() {
        if(deal == null) {
            deal = false;
        }
        return null;

    }

	/**
	 * Return a page of Menu
	 * 
	 * @param page
	 *            Page to display
	 * @param pageSize
	 *            Number of Menus per page
	 * @param sortBy
	 *            Menu property used for sorting
	 * @param order
	 *            Sort order (either or asc or desc)
	 * @param filter
	 *            Filter applied on the name column
	 */
	public static Page<Menu> page(int page, int pageSize, String sortBy,
			String order, String filter) {
		return find.where().ilike("name", "%" + filter + "%")
				.orderBy(sortBy + " " + order)
				.findPagingList(pageSize).getPage(page);
	}
	
	public void generateMenuCode(Ordertype ot,long id){
		String prefix = "MTM";
		if(ot != null && Ordertype.AUTO_ORDER_TYPE.equals(ot.name)){
			prefix = "ATM";
		}
		this.name = prefix +System.currentTimeMillis();
	}
	
    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Menu c: find.where().orderBy("name").findList()) {
            options.put(c.id.toString(), c.name);
        }
        return options;
    }
}

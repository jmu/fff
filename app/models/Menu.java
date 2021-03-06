package models;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import buz.DateUtils;

import com.avaje.ebean.Page;

@Entity
@Table(name="menu")
public class Menu extends Model {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7687217458550521027L;
	@Id
    public Long id;
    @MaxLength(100)
    @Required
    public String name;
    @ManyToOne
    public Usergroup usergroup;
    //the date of book for
    public Date dateFor;
    public Date createdAt;
    //public Date updatedAt;
    public Boolean deal;
    public Date closedAt;
    public boolean isAvailable;

  //  @OneToMany
	//@JsonIgnore
//    public Set<MenuUser> menuUsers = new TreeSet<MenuUser>();
    @OneToMany
	@JsonIgnore
    public Set<Foodorder> foodorders = new TreeSet<Foodorder>();

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
	
	public static Page<Menu> afterDay(int page, int pageSize, String sortBy,
			String order, Date date) {
		return find.fetch("foodorders")
                .fetch("foodorders.food")
                .fetch("foodorders.user")
                .where()
                .gt("dateFor",date)
                //.orderBy(sortBy + " " + order)
				.orderBy("foodorders.orderAt desc")
				.findPagingList(pageSize).getPage(page);
	}

	public static Page<Menu> today(int page, int pageSize, String sortBy,
			String order) {
		return afterDay(0,999,"name", "desc",DateUtils.today());
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

        for(Menu c: find.where().ne("deal",true).orderBy("name").findList()) {
            options.put(c.id.toString(), c.name);
        }
        return options;
    }

    public static Map<String,String> todayOptions() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();

        for(Menu c: find.where().eq("isAvailable", true)
                .gt("dateFor",DateUtils.today()).ne("deal",true).findList()) {
            options.put(c.id.toString(), c.name);
        }
        return options;
    }
    
}

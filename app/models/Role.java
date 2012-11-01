package models;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

import com.avaje.ebean.Page;

@Entity
@Table(name = "role")
public class Role extends Model {
	private static final long serialVersionUID = -1258546785030761395L;
    public static final String ROLE_USER = "FFF_USER";
    public static final String ROLE_MANAGER = "FFF_MANAGER";
    public static final String ROLE_ADMIN= "FFF_ADMIN";

	@Id
	public Long id;
	@Required
	@Column(length = 50)
	public String name;
	public String description;

	@OneToMany(cascade = CascadeType.MERGE)
	@JsonIgnore
	public Set<User> users = new HashSet<User>(0);

	/**
	 * Create a new instance and set the name.
	 * 
	 * @param name
	 *            name of the role.
	 */
	public Role(final String name) {
		this.name = name;
	}

	public static Finder<Long, Role> find = new Finder<Long, Role>(
			Long.class, Role.class);

	/**
	 * Return a page of Role
	 * 
	 * @param page
	 *            Page to display
	 * @param pageSize
	 *            Number of Roles per page
	 * @param sortBy
	 *            Role property used for sorting
	 * @param order
	 *            Sort order (either or asc or desc)
	 * @param filter
	 *            Filter applied on the name column
	 */
	public static Page<Role> page(int page, int pageSize, String sortBy,
			String order, String filter) {
		return find.where().ilike("name", "%" + filter + "%")
				.orderBy(sortBy + " " + order)
				.findPagingList(pageSize).getPage(page);
	}
    public static Role findByName(String name) {
        return find.where().eq("name",name).findUnique();
    }
	
    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Role c: find.where().orderBy("name").findList()) {
            options.put(c.id.toString(), c.name);
        }
        return options;
    }
}

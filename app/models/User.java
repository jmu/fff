package models;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.avaje.ebean.Page;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
@Table(name = "user")
public class User extends Model {

	private static final long serialVersionUID = -5584181079093878215L;
	@Id
	public Long id;
    @Required
    @MinLength(4)
	public String userName;
	public String fullName;
    @Required
    @MinLength(4)
	public String password;
	@Email
	public String email;
	public String phoneNumber;
	public Double money;
	public Long bonus;
	public Boolean accountExpired;
	public Boolean accountLocked;
	public Boolean accountEnabled;
	public Boolean credentialsExpired;
	public Date regAt;
	public Date lastLogin;
	public String ip;
	public Boolean mailReminderOn;
	
	@ManyToOne
	public Usergroup usergroup;
	@ManyToOne
	public Role role;
	
	@OneToMany(cascade = CascadeType.ALL)
	public Set<MenuUser> menuUsers = new HashSet<MenuUser>(0);
	@OneToMany(cascade = CascadeType.ALL)
	public Set<Foodorder> foodorders = new HashSet<Foodorder>(0);
	@OneToMany(cascade = CascadeType.ALL)
	public Set<Schedule> schedules = new HashSet<Schedule>(0);
	@OneToMany(cascade = CascadeType.ALL)
	public Set<Payment> payments = new HashSet<Payment>(0);
	@OneToMany(cascade = CascadeType.ALL)
	public Set<Comment> comments = new HashSet<Comment>(0);


	public static Model.Finder<String, User> find = new Model.Finder<String, User>(
			String.class, User.class);
	
	public static Model.Finder<Long, User> findById = new Model.Finder<Long, User>(
			Long.class, User.class);

	/**
	 * Retrieve all users.
	 */
	public static List<User> findAll() {
		return find.all();
	}

	/**
	 * Retrieve a User from email.
	 */
	public static User findByEmail(String email) {
		return find.where().eq("email", email).findUnique();
	}
    /**
     * Authenticate a User.
     */
    public static User authenticate(String email, String password) {
        return find.where()
            .eq("email", email)
            .eq("password", password)
            .findUnique();
    }
    
	public static Page<User> page(int page, int pageSize, String sortBy,
			String order, String filter) {
		return findById.where().ilike("userName", "%" + filter + "%")
				.orderBy(sortBy + " " + order)//.fetch("company")
				.findPagingList(pageSize).getPage(page);
	}
    
    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(User c: findById.where().eq("isAvailable", true).orderBy("userName").findList()) {
            options.put(c.id.toString(), c.userName);
        }
        return options;
    }
}

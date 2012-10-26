package models;

import java.util.ArrayList;
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
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

import play.Logger;
import play.api.libs.Codecs;
import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import secure.PasswordCreater;
import buz.SimplePage;
import buz.SimplePagingList;
import buz.SqlResultBuilder;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Page;
import com.avaje.ebean.RawSql;
import com.avaje.ebean.RawSqlBuilder;
import com.avaje.ebean.SqlQuery;
import com.avaje.ebean.SqlRow;
import com.avaje.ebean.annotation.Formula;

@Entity
@Table(name = "user")
// @SqlSelect(query =
// "select u.id c0, u.user_name c1, u.full_name c2, u.password c3," +
// "u.email c4, u.phone_number c5, " +
// "(select ifnull(p.amount,0)-ifnull(sum(f.price*f.quantity-f.discount),0)"+
// " from foodorder as f where f.deal and f.user_id = u.id) as c6, " +
// "u.bonus c7, u.account_expired c8," +
// " u.account_locked c9, u.account_enabled c10, u.credentials_expired c11, " +
// "u.reg_at c12, u.last_login c13, u.ip c14, u.mail_reminder_on c15, " +
// "u.usergroup_id c16, u.role_id c17 " +
// " from user as u left join  payment as p on p.user_id = u.id ")
public class User extends Model {

	private static final long serialVersionUID = -5584181079093878215L;
	@Id
	public Long id;
	@Required
	@MinLength(4)
	public String userName;
	public String fullName;
	@Required
	@MinLength(6)
	public String password;
	@Required
	@Email
	public String email;
	public String phoneNumber;
	
//	@Transient
	@Formula(select=
			"(select ifnull(p.amount,0)-ifnull(sum(f.price*f.quantity-f.discount),0) as money"
				+ " from foodorder as f where f.deal and f.user_id = ${ta}.id)",
				join="left join payment as p on p.user_id = ${ta}.id")
	public Double money;
	public Long bonus;
	public Boolean accountExpired = false;
	public Boolean accountLocked = false;
	public Boolean accountEnabled = true;
	public Boolean credentialsExpired = false;
	public Date regAt;
	public Date lastLogin;
	public String ip;
	public Boolean mailReminderOn = false;

	@ManyToOne
	public Usergroup usergroup;
	@ManyToOne
	public Role role;

	@OneToMany(cascade = CascadeType.MERGE)
	@JsonIgnore
	public Set<MenuUser> menuUsers = new HashSet<MenuUser>(0);
	@OneToMany(cascade = CascadeType.MERGE)
	@JsonIgnore
	public Set<Foodorder> foodorders = new HashSet<Foodorder>(0);
	@OneToMany(cascade = CascadeType.MERGE)
	@JsonIgnore
	public Set<Schedule> schedules = new HashSet<Schedule>(0);
	@OneToMany(cascade = CascadeType.MERGE)
	@JsonIgnore
	public Set<Payment> payments = new HashSet<Payment>(0);
	@OneToMany(cascade = CascadeType.MERGE)
	@JsonIgnore
	public Set<Comment> comments = new HashSet<Comment>(0);

	public static Model.Finder<String, User> find = new Model.Finder<String, User>(
			String.class, User.class);

	public static Model.Finder<Long, User> findById = new Model.Finder<Long, User>(
			Long.class, User.class);

	public String validate() {
		if(password != null){
			password = PasswordCreater.create(password);
		}
		return null;
	}
	
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
		return find.where().eq("email", email).eq("password", PasswordCreater.create(password))
				.findUnique();
	}
/*
	public static Page<User> page(int page, int pageSize, final String sortBy,
	final String order, final String filter) {
		// (selectsum(f.price*f.quantity-f.discount) from foodorder as f where
		// f.deal and f.user_id = u.id)
		String sql = "select u.id, u.user_name, u.full_name, u.password,"
				+ "u.email, u.phone_number, "
				+ "u.bonus, u.account_expired,"
				+ " u.account_locked, u.account_enabled, u.credentials_expired, "
				+ "u.reg_at, u.last_login, u.ip, u.mail_reminder_on, "
				+ "u.usergroup_id, u.role_id, "
				+ " (select ifnull(p.amount,0)-ifnull(sum(f.price*f.quantity-f.discount),0) as money"
				+ " from foodorder as f where f.deal and f.user_id = u.id) as money"
				+ " from user as u left join  payment as p on p.user_id = u.id"
				+ " where u.user_name like :userName order by ";
		sql += sortBy + " " + order;

		SimplePagingList<User> pl = new SimplePagingList<User>(page, sql,
				new SqlResultBuilder<User>() {

					public List<User> build(List<SqlRow> sqlRows) {
						List<User> userList = new ArrayList<User>(sqlRows
								.size());
						for (SqlRow row : sqlRows) {
							User user = new User();
							if (row.getString("user_name") == null)
								break;
							user.id = row.getLong("id");
							user.userName = row.getString("user_name");
							user.fullName = row.getString("full_name");
							user.password = row.getString("password");
							user.email = row.getString("email");
							user.phoneNumber = row.getString("phone_number");
							user.money = row.getDouble("money");
							user.regAt = row.getDate("reg_at");
							user.bonus = row.getLong("bonus");
							user.accountEnabled = row
									.getBoolean("account_enabled");
							userList.add(user);
						}
						return userList;
					}

					public SqlQuery buildSql(String sql) {
						
						SqlQuery sq = Ebean.createSqlQuery(sql);
						sq.setParameter("userName", "%" + filter + "%");
								
						
						// List<SqlRow> list = sq.findList();
						return sq;
					}
				}, 10);
		return new SimplePage<User>(page, pl);
	}
	*/

	
	public static Page<User> page(int page, int pageSize, String sortBy,
			String order, String filter) {
		return findById.where().ilike("userName", "%" + filter + "%")
				.orderBy(sortBy + " " + order).findPagingList(pageSize)
				.getPage(page);
	}

	public static Map<String, String> options() {
		LinkedHashMap<String, String> options = new LinkedHashMap<String, String>();
		for (User c : findById.where().eq("accountEnabled", true)
				.orderBy("userName").findList()) {
			options.put(c.id.toString(), c.userName);
		}
		return options;
	}
}

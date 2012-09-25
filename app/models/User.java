package models;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import play.data.validation.Constraints.Email;
import play.db.ebean.Model;

@Entity
@Table(name = "user")
public class User extends Model {

	private static final long serialVersionUID = -5584181079093878215L;
	private Long id;
	private Integer version;
	private Usergroup usergroup;
	private Role role;
	private String userName;
	private String fullName;
	private String password;
	@Email
	private String email;
	private String phoneNumber;
	private Double money;
	private Long bonus;
	private Boolean accountExpired;
	private Boolean accountLocked;
	private Boolean accountEnabled;
	private Boolean credentialsExpired;
	private Date regAt;
	private Date lastLogin;
	private String ip;
	private Boolean mailReminderOn;
	private Set<MenuUser> menuUsers = new HashSet<MenuUser>(0);
	private Set<Foodorder> foodorders = new HashSet<Foodorder>(0);
	private Set<Schedule> schedules = new HashSet<Schedule>(0);
	private Set<Payment> payments = new HashSet<Payment>(0);
	private Set<Comment> comments = new HashSet<Comment>(0);

	public User() {

	}

	public User(String userName) {
		this.userName = userName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Version
	@Column(name = "version")
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usergroup_id")
	public Usergroup getUsergroup() {
		return this.usergroup;
	}

	public void setUsergroup(Usergroup usergroup) {
		this.usergroup = usergroup;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Column(name = "user_name", nullable = false, length = 50)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "full_name", length = 50)
	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(name = "password", nullable = false, length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "email", nullable = false, length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "phone_number", length = 50)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "money", precision = 22, scale = 0)
	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	@Column(name = "bonus")
	public Long getBonus() {
		return this.bonus;
	}

	public void setBonus(Long bonus) {
		this.bonus = bonus;
	}

	@Column(name = "account_expired")
	public Boolean getAccountExpired() {
		return this.accountExpired;
	}

	public void setAccountExpired(Boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	@Column(name = "account_locked")
	public Boolean getAccountLocked() {
		return this.accountLocked;
	}

	public void setAccountLocked(Boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	@Column(name = "account_enabled")
	public Boolean getAccountEnabled() {
		return this.accountEnabled;
	}

	public void setAccountEnabled(Boolean accountEnabled) {
		this.accountEnabled = accountEnabled;
	}

	@Column(name = "credentials_expired")
	public Boolean getCredentialsExpired() {
		return this.credentialsExpired;
	}

	public void setCredentialsExpired(Boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "reg_at", length = 19)
	public Date getRegAt() {
		return this.regAt;
	}

	public void setRegAt(Date regAt) {
		this.regAt = regAt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_login", length = 19)
	public Date getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Column(name = "ip", length = 20)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "mail_reminder_on")
	public Boolean getMailReminderOn() {
		return this.mailReminderOn;
	}

	public void setMailReminderOn(Boolean mailReminderOn) {
		this.mailReminderOn = mailReminderOn;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<MenuUser> getMenuUsers() {
		return this.menuUsers;
	}

	public void setMenuUsers(Set<MenuUser> menuUsers) {
		this.menuUsers = menuUsers;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Foodorder> getFoodorders() {
		return this.foodorders;
	}

	public void setFoodorders(Set<Foodorder> foodorders) {
		this.foodorders = foodorders;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Schedule> getSchedules() {
		return this.schedules;
	}

	public void setSchedules(Set<Schedule> schedules) {
		this.schedules = schedules;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Payment> getPayments() {
		return this.payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		User pojo = (User) o;

		if (version != null ? !version.equals(pojo.version)
				: pojo.version != null)
			return false;
		if (usergroup != null ? !usergroup.equals(pojo.usergroup)
				: pojo.usergroup != null)
			return false;
		if (userName != null ? !userName.equals(pojo.userName)
				: pojo.userName != null)
			return false;
		if (fullName != null ? !fullName.equals(pojo.fullName)
				: pojo.fullName != null)
			return false;
		if (password != null ? !password.equals(pojo.password)
				: pojo.password != null)
			return false;
		if (email != null ? !email.equals(pojo.email) : pojo.email != null)
			return false;
		if (phoneNumber != null ? !phoneNumber.equals(pojo.phoneNumber)
				: pojo.phoneNumber != null)
			return false;
		if (money != null ? !money.equals(pojo.money) : pojo.money != null)
			return false;
		if (bonus != null ? !bonus.equals(pojo.bonus) : pojo.bonus != null)
			return false;
		if (accountExpired != null ? !accountExpired
				.equals(pojo.accountExpired) : pojo.accountExpired != null)
			return false;
		if (accountLocked != null ? !accountLocked.equals(pojo.accountLocked)
				: pojo.accountLocked != null)
			return false;
		if (accountEnabled != null ? !accountEnabled
				.equals(pojo.accountEnabled) : pojo.accountEnabled != null)
			return false;
		if (credentialsExpired != null ? !credentialsExpired
				.equals(pojo.credentialsExpired)
				: pojo.credentialsExpired != null)
			return false;
		if (regAt != null ? !regAt.equals(pojo.regAt) : pojo.regAt != null)
			return false;
		if (lastLogin != null ? !lastLogin.equals(pojo.lastLogin)
				: pojo.lastLogin != null)
			return false;
		if (ip != null ? !ip.equals(pojo.ip) : pojo.ip != null)
			return false;
		if (mailReminderOn != null ? !mailReminderOn
				.equals(pojo.mailReminderOn) : pojo.mailReminderOn != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result = 0;
		result = (version != null ? version.hashCode() : 0);
		result = 31 * result + (usergroup != null ? usergroup.hashCode() : 0);
		result = 31 * result + (role != null ? role.hashCode() : 0);
		result = 31 * result + (userName != null ? userName.hashCode() : 0);
		result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result
				+ (phoneNumber != null ? phoneNumber.hashCode() : 0);
		result = 31 * result + (money != null ? money.hashCode() : 0);
		result = 31 * result + (bonus != null ? bonus.hashCode() : 0);
		result = 31 * result
				+ (accountExpired != null ? accountExpired.hashCode() : 0);
		result = 31 * result
				+ (accountLocked != null ? accountLocked.hashCode() : 0);
		result = 31 * result
				+ (accountEnabled != null ? accountEnabled.hashCode() : 0);
		result = 31
				* result
				+ (credentialsExpired != null ? credentialsExpired.hashCode()
						: 0);
		result = 31 * result + (regAt != null ? regAt.hashCode() : 0);
		result = 31 * result + (lastLogin != null ? lastLogin.hashCode() : 0);
		result = 31 * result + (ip != null ? ip.hashCode() : 0);
		result = 31 * result
				+ (mailReminderOn != null ? mailReminderOn.hashCode() : 0);

		return result;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer(getClass().getSimpleName());

		sb.append(" [");
		sb.append("id").append("='").append(getId()).append("', ");
		sb.append("version").append("='").append(getVersion()).append("', ");
		sb.append("usergroup").append("='").append(getUsergroup())
				.append("', ");
		sb.append("role").append("='").append(getRole()).append("', ");
		sb.append("userName").append("='").append(getUserName()).append("', ");
		sb.append("fullName").append("='").append(getFullName()).append("', ");
		sb.append("password").append("='").append(getPassword()).append("', ");
		sb.append("email").append("='").append(getEmail()).append("', ");
		sb.append("phoneNumber").append("='").append(getPhoneNumber())
				.append("', ");
		sb.append("money").append("='").append(getMoney()).append("', ");
		sb.append("bonus").append("='").append(getBonus()).append("', ");
		sb.append("accountExpired").append("='").append(getAccountExpired())
				.append("', ");
		sb.append("accountLocked").append("='").append(getAccountLocked())
				.append("', ");
		sb.append("accountEnabled").append("='").append(getAccountEnabled())
				.append("', ");
		sb.append("credentialsExpired").append("='")
				.append(getCredentialsExpired()).append("', ");
		sb.append("regAt").append("='").append(getRegAt()).append("', ");
		sb.append("lastLogin").append("='").append(getLastLogin())
				.append("', ");
		sb.append("ip").append("='").append(getIp()).append("', ");
		sb.append("mailReminderOn").append("='").append(getMailReminderOn())
				.append("', ");

		sb.append("]");

		return sb.toString();
	}

	@Transient
	public String getUsername() {
		return getUserName();
	}

	@Transient
	public boolean isAccountNonExpired() {
		return !getAccountExpired();
	}

	@Transient
	public boolean isAccountNonLocked() {
		return !getAccountLocked();
	}

	@Transient
	public boolean isCredentialsNonExpired() {

		return !getCredentialsExpired();
	}

	@Transient
	public boolean isEnabled() {
		return getAccountEnabled();
	}

	public static Model.Finder<String, User> find = new Model.Finder<String, User>(
			String.class, User.class);

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
}

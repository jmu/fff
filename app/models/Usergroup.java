package models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.ebean.Model;

@Entity
@Table(name = "usergroup")
public class Usergroup extends Model {
	private Long id;
	private String name;
	private Boolean imOn;
	private Boolean openReg;
	private Long luckyRule;
	private Boolean mailReminderOn;
	private Boolean menuAutoClose;
	private Boolean bonusOn;
	private Double bonusPayRatio;
	private Double bonusOrderRatio;
	private Double bonusManageRatio;
	private Double bonusCarryRatio;
	private String description;
	private Set<User> users = new HashSet<User>(0);
	private Set<Payment> payments = new HashSet<Payment>(0);
	private Set<Menu> menus = new HashSet<Menu>(0);

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "im_on")
	public Boolean getImOn() {
		return this.imOn;
	}

	public void setImOn(Boolean imOn) {
		this.imOn = imOn;
	}

	@Column(name = "open_reg")
	public Boolean getOpenReg() {
		return this.openReg;
	}

	public void setOpenReg(Boolean openReg) {
		this.openReg = openReg;
	}

	@Column(name = "lucky_rule")
	public Long getLuckyRule() {
		return this.luckyRule;
	}

	public void setLuckyRule(Long luckyRule) {
		this.luckyRule = luckyRule;
	}

	@Column(name = "mail_reminder_on")
	public Boolean getMailReminderOn() {
		return this.mailReminderOn;
	}

	public void setMailReminderOn(Boolean mailReminderOn) {
		this.mailReminderOn = mailReminderOn;
	}

	@Column(name = "menu_auto_close")
	public Boolean getMenuAutoClose() {
		return this.menuAutoClose;
	}

	public void setMenuAutoClose(Boolean menuAutoClose) {
		this.menuAutoClose = menuAutoClose;
	}

	@Column(name = "bonus_on")
	public Boolean getBonusOn() {
		return this.bonusOn;
	}

	public void setBonusOn(Boolean bonusOn) {
		this.bonusOn = bonusOn;
	}

	@Column(name = "bonus_pay_ratio", precision = 22, scale = 0)
	public Double getBonusPayRatio() {
		return this.bonusPayRatio;
	}

	public void setBonusPayRatio(Double bonusPayRatio) {
		this.bonusPayRatio = bonusPayRatio;
	}

	@Column(name = "bonus_order_ratio", precision = 22, scale = 0)
	public Double getBonusOrderRatio() {
		return this.bonusOrderRatio;
	}

	public void setBonusOrderRatio(Double bonusOrderRatio) {
		this.bonusOrderRatio = bonusOrderRatio;
	}

	@Column(name = "bonus_manage_ratio", precision = 22, scale = 0)
	public Double getBonusManageRatio() {
		return this.bonusManageRatio;
	}

	public void setBonusManageRatio(Double bonusManageRatio) {
		this.bonusManageRatio = bonusManageRatio;
	}

	@Column(name = "bonus_carry_ratio", precision = 22, scale = 0)
	public Double getBonusCarryRatio() {
		return this.bonusCarryRatio;
	}

	public void setBonusCarryRatio(Double bonusCarryRatio) {
		this.bonusCarryRatio = bonusCarryRatio;
	}

	@Column(name = "description", length = 1000)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usergroup")
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usergroup")
	public Set<Payment> getPayments() {
		return this.payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usergroup")
	public Set<Menu> getMenus() {
		return this.menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Usergroup pojo = (Usergroup) o;

		if (name != null ? !name.equals(pojo.name) : pojo.name != null)
			return false;
		if (imOn != null ? !imOn.equals(pojo.imOn) : pojo.imOn != null)
			return false;
		if (openReg != null ? !openReg.equals(pojo.openReg)
				: pojo.openReg != null)
			return false;
		if (luckyRule != null ? !luckyRule.equals(pojo.luckyRule)
				: pojo.luckyRule != null)
			return false;
		if (mailReminderOn != null ? !mailReminderOn
				.equals(pojo.mailReminderOn) : pojo.mailReminderOn != null)
			return false;
		if (menuAutoClose != null ? !menuAutoClose.equals(pojo.menuAutoClose)
				: pojo.menuAutoClose != null)
			return false;
		if (bonusOn != null ? !bonusOn.equals(pojo.bonusOn)
				: pojo.bonusOn != null)
			return false;
		if (bonusPayRatio != null ? !bonusPayRatio.equals(pojo.bonusPayRatio)
				: pojo.bonusPayRatio != null)
			return false;
		if (bonusOrderRatio != null ? !bonusOrderRatio
				.equals(pojo.bonusOrderRatio) : pojo.bonusOrderRatio != null)
			return false;
		if (bonusManageRatio != null ? !bonusManageRatio
				.equals(pojo.bonusManageRatio) : pojo.bonusManageRatio != null)
			return false;
		if (bonusCarryRatio != null ? !bonusCarryRatio
				.equals(pojo.bonusCarryRatio) : pojo.bonusCarryRatio != null)
			return false;
		if (description != null ? !description.equals(pojo.description)
				: pojo.description != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result = 0;
		result = (name != null ? name.hashCode() : 0);
		result = 31 * result + (imOn != null ? imOn.hashCode() : 0);
		result = 31 * result + (openReg != null ? openReg.hashCode() : 0);
		result = 31 * result + (luckyRule != null ? luckyRule.hashCode() : 0);
		result = 31 * result
				+ (mailReminderOn != null ? mailReminderOn.hashCode() : 0);
		result = 31 * result
				+ (menuAutoClose != null ? menuAutoClose.hashCode() : 0);
		result = 31 * result + (bonusOn != null ? bonusOn.hashCode() : 0);
		result = 31 * result
				+ (bonusPayRatio != null ? bonusPayRatio.hashCode() : 0);
		result = 31 * result
				+ (bonusOrderRatio != null ? bonusOrderRatio.hashCode() : 0);
		result = 31 * result
				+ (bonusManageRatio != null ? bonusManageRatio.hashCode() : 0);
		result = 31 * result
				+ (bonusCarryRatio != null ? bonusCarryRatio.hashCode() : 0);
		result = 31 * result
				+ (description != null ? description.hashCode() : 0);

		return result;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer(getClass().getSimpleName());

		sb.append(" [");
		sb.append("id").append("='").append(getId()).append("', ");
		sb.append("name").append("='").append(getName()).append("', ");
		sb.append("imOn").append("='").append(getImOn()).append("', ");
		sb.append("openReg").append("='").append(getOpenReg()).append("', ");
		sb.append("luckyRule").append("='").append(getLuckyRule())
				.append("', ");
		sb.append("mailReminderOn").append("='").append(getMailReminderOn())
				.append("', ");
		sb.append("menuAutoClose").append("='").append(getMenuAutoClose())
				.append("', ");
		sb.append("bonusOn").append("='").append(getBonusOn()).append("', ");
		sb.append("bonusPayRatio").append("='").append(getBonusPayRatio())
				.append("', ");
		sb.append("bonusOrderRatio").append("='").append(getBonusOrderRatio())
				.append("', ");
		sb.append("bonusManageRatio").append("='")
				.append(getBonusManageRatio()).append("', ");
		sb.append("bonusCarryRatio").append("='").append(getBonusCarryRatio())
				.append("', ");
		sb.append("description").append("='").append(getDescription()).append(
				"', ");

		sb.append("]");

		return sb.toString();
	}

}

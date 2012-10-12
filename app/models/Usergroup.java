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
import play.data.validation.Constraints.Required;
import play.data.validation.Constraints.MaxLength;

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
    @MaxLength(1000)
	public String description;

    @OneToMany(cascade=CascadeType.ALL)
	public Set<User> users = new HashSet<User>(0);
    @OneToMany(cascade=CascadeType.ALL)
	public Set<Payment> payments = new HashSet<Payment>(0);
    @OneToMany(cascade=CascadeType.ALL)
	public Set<Menu> menus = new HashSet<Menu>(0);

}

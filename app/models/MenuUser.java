package models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import play.data.validation.Constraints.Required;

import play.db.ebean.Model;

@Entity
@Table(name = "menu_user")
public class MenuUser extends Model {

	private static final long serialVersionUID = -8016373727805828275L;
    @Id
	public Long id;
    @Required
    @ManyToOne
	public User user;
    @Required
    @ManyToOne
	public Menu menu;

}

package models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
@Table(name = "role")
public class Role extends Model {
	private static final long serialVersionUID = -1258546785030761395L;
	@Id
	public Long id;
	@Required
	@Column(length = 50)
	public String name;
	public String description;

	@OneToMany(cascade = CascadeType.ALL)
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

}

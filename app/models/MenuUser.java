package models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.ebean.Model;

@Entity
@Table(name = "menu_user")
public class MenuUser extends Model {

	private static final long serialVersionUID = -8016373727805828275L;
	private Long id;
	private User user;
	private Menu menu;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_id", nullable = false)
	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		MenuUser pojo = (MenuUser) o;

		if (user != null ? !user.equals(pojo.user) : pojo.user != null)
			return false;
		if (menu != null ? !menu.equals(pojo.menu) : pojo.menu != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result = 0;
		result = (user != null ? user.hashCode() : 0);
		result = 31 * result + (menu != null ? menu.hashCode() : 0);

		return result;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer(getClass().getSimpleName());

		sb.append(" [");
		sb.append("id").append("='").append(getId()).append("', ");
		sb.append("user").append("='").append(getUser()).append("', ");
		sb.append("menu").append("='").append(getMenu()).append("'");
		sb.append("]");

		return sb.toString();
	}

}

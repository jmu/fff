package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.db.ebean.Model;

@Entity
@Table(name = "payment")
public class Payment extends Model {
	private Long id;
	private User user;
	private Usergroup usergroup;
	private double amount;
	private Date createdAt;
	private String description;

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
	@JoinColumn(name = "usergroup_id", nullable = false)
	public Usergroup getUsergroup() {
		return this.usergroup;
	}

	public void setUsergroup(Usergroup usergroup) {
		this.usergroup = usergroup;
	}

	@Column(name = "amount", nullable = false)
	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false)
	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Payment pojo = (Payment) o;

		if (user != null ? !user.equals(pojo.user) : pojo.user != null)
			return false;
		if (usergroup != null ? !usergroup.equals(pojo.usergroup)
				: pojo.usergroup != null)
			return false;
		if (amount != pojo.amount)
			return false;
		if (createdAt != null ? !createdAt.equals(pojo.createdAt)
				: pojo.createdAt != null)
			return false;
		if (description != null ? !description.equals(pojo.description)
				: pojo.description != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result = 0;
		result = (user != null ? user.hashCode() : 0);
		result = 31 * result + (usergroup != null ? usergroup.hashCode() : 0);
		result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
		result = 31 * result
				+ (description != null ? description.hashCode() : 0);

		return result;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer(getClass().getSimpleName());

		sb.append(" [");
		sb.append("id").append("='").append(getId()).append("', ");
		sb.append("user").append("='").append(getUser()).append("', ");
		sb.append("usergroup").append("='").append(getUsergroup())
				.append("', ");
		sb.append("amount").append("='").append(getAmount()).append("', ");
		sb.append("createdAt").append("='").append(getCreatedAt())
				.append("', ");
		sb.append("description").append("='").append(getDescription()).append(
				"'");
		sb.append("]");

		return sb.toString();
	}

}

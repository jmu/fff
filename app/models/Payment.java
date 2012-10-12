package models;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

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
import play.data.validation.Constraints.Required;

import com.avaje.ebean.Page;

@Entity
@Table(name = "payment")
public class Payment extends Model {
    @Id
	public Long id;
    @ManyToOne
	public User user;
    @ManyToOne
	public Usergroup usergroup;
    @Required
	public double amount;
	public Date createdAt;
	public String description;


	public static Finder<Long, Payment> find = new Finder<Long, Payment>(
			Long.class, Payment.class);

	/**
	 * Return a page of Payment
	 * 
	 * @param page
	 *            Page to display
	 * @param pageSize
	 *            Number of Payments per page
	 * @param sortBy
	 *            Payment property used for sorting
	 * @param order
	 *            Sort order (either or asc or desc)
	 * @param filter
	 *            Filter applied on the name column
	 */
	public static Page<Payment> page(int page, int pageSize, String sortBy,
			String order, String filter) {
		return find.where().ilike("user.userName", "%" + filter + "%")
				.orderBy(sortBy + " " + order).fetch("user").findPagingList(pageSize).getPage(page);
	}

}

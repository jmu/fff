package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Page;
import com.avaje.ebean.SqlQuery;

@Entity
@Table(name = "payment")
public class Payment extends Model {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8607251774394592803L;
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

	public static Page<Payment> myPage(int page, int pageSize, String sortBy,
			String order, Long userId) {
		return find.where().eq("user.id",userId) 
				.orderBy(sortBy + " " + order).findPagingList(pageSize).getPage(page);
	}
	
	public static Double countPaied(Long userId) {
		String sql = "SELECT ifnull(sum(amount),0) total_pay FROM payment where user_id = :userId";
		SqlQuery query = Ebean.createSqlQuery(sql);
		return query.setParameter("userId", userId).findUnique()
				.getDouble("total_pay");
	}
}

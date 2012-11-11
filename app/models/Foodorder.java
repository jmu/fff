package models;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.data.validation.Constraints.Min;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import buz.DateUtils;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Page;
import com.avaje.ebean.SqlQuery;

@Entity
@Table(name = "foodorder")
public class Foodorder extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9116888222839243299L;
	@Id
	public Long id;
	@Required
	@ManyToOne
	public User user;
	@ManyToOne
	public Menu menu;
	@ManyToOne
	public Ordertype ordertype;
	@Required
	@ManyToOne
	public Food food;
	@Required
	@Min(1)
	public Long quantity;
	public Double discount = 0D;
	public Date orderAt;
	public Boolean deal;
	public Double price;
	public String comments;

	public String validate() {
		if (this.deal == null) {
			this.deal = false;
		}

		if (user == null || user.id == null) {
			return "user can not be null";
		}

		if (food == null || food.id == null) {
			return "food can not be null";
		}
		return null;
	}

	public static Finder<Long, Foodorder> find = new Finder<Long, Foodorder>(
			Long.class, Foodorder.class);

	/**
	 * Return a page of Foodorder
	 * 
	 * @param page
	 *            Page to display
	 * @param pageSize
	 *            Number of Foodorders per page
	 * @param sortBy
	 *            Foodorder property used for sorting
	 * @param order
	 *            Sort order (either or asc or desc)
	 * @param filter
	 *            Filter applied on the name column
	 */
	public static Page<Foodorder> page(int page, int pageSize, String sortBy,
			String order, String filter) {
		return find.where().ilike("user.userName", "%" + filter + "%")
				.orderBy(sortBy + " " + order).fetch("user").fetch("menu")
				.fetch("food").findPagingList(pageSize).getPage(page);
	}

	public static Page<Foodorder> myPage(int page, int pageSize, String sortBy,
			String order, Long userId) {
		return find.where().eq("user.id", userId).orderBy(sortBy + " " + order)
				.fetch("menu").fetch("food").findPagingList(pageSize)
				.getPage(page);
	}

	public static Double sumPrice(Set<Foodorder> list) {
		Double result = 0D;
		for (Foodorder order : list) {
			result += order.price * order.quantity - order.discount;
		}

		return result;
	}

	public static Double sumFreeze(List<Foodorder> list) {
		Double result = 0D;
		if (list == null)
			return result;
		for (Foodorder order : list) {
			if (!order.menu.deal) {
				result += order.price * order.quantity - order.discount;
			}
		}

		return result;
	}

	public static List<Foodorder> todayByUser(Long userId) {
		return find.fetch("menu").fetch("food").where().eq("user_id", userId)
				.lt("menu.dateFor", DateUtils.tomorow())
				.gt("menu.dateFor", DateUtils.today()).findList();
	}

	public static List<Foodorder> getAllFoodorders(Long menuId) {
		return find.where().eq("menu_id", menuId).findList();
	}

	public static Double countCost(Long userId) {
		String sql = "SELECT ifnull(sum(f.price*f.quantity-discount),0) as total_cost FROM foodorder f where f.deal = true and f.user_id = :userId";
		SqlQuery query = Ebean.createSqlQuery(sql);
		return query.setParameter("userId", userId).findUnique()
				.getDouble("total_cost");
	}
}

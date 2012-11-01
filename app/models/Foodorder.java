package models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.data.validation.Constraints.Min;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

import com.avaje.ebean.Page;

@Entity
@Table(name="foodorder")
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
				.orderBy(sortBy + " " + order).fetch("user").fetch("menu").fetch("food").findPagingList(pageSize).getPage(page);
	}
	

}

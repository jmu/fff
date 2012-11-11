package buz;

import java.util.List;
import java.util.concurrent.Future;

import play.Logger;

import com.avaje.ebean.Page;
import com.avaje.ebean.PagingList;
import com.avaje.ebean.SqlQuery;
import com.avaje.ebean.SqlRow;

public class SimplePagingList<T> implements PagingList<T> {
	// private SqlQuery query;
	private final int pageSize;
	private final String sql;
	private List<T> list;
	//private boolean fetchAhead = true;
	private SqlResultBuilder<T> builder;
	private SqlResultCounter countSql = new DefaultSqlResultCounter();
	private int pageIndex = 0;

	public SimplePagingList(int pageIndex,String sql, SqlResultBuilder<T> buider,
			final int pageSize) {
		this.sql = sql;
		// this.query = builder.buildSql(sql);
		this.pageSize = pageSize;
		this.builder = buider;
		this.pageIndex = pageIndex;
	}

	public List<T> getAsList() {
		String pageSql = sql;
		//int limit = (pageIndex + 1) * pageSize;
//		int total = getTotalRowCount();
//		Logger.debug("total -->"+ total);
		pageSql += " limit " + pageSize;
		if (pageIndex > 0) {
			pageSql += " offset " + pageIndex * pageSize;
		}
		SqlQuery copy = builder.buildSql(pageSql);
		// copy.setFirstRow(pageIndex * pageSize);
		// copy.setMaxRows(pageSize);

		List<SqlRow> result = copy.findList();
		list = builder.build(result);
		return list;
	}

	public Future<Integer> getFutureRowCount() {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<T> getPage(int arg0) {
		pageIndex = arg0;
		Logger.debug("get next page"+ pageIndex);
		return new SimplePage<T>(arg0, this);
	}

	public List<T> getFutureList() {
		return list;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalPageCount() {
		int tc = getTotalRowCount();
		int pageCount = tc / pageSize;
		if (tc % pageSize != 0) {
			pageCount++;
		}
		return pageCount;
	}

	public int getTotalRowCount() {
		SqlQuery query = builder.buildSql(countSql.getCountSql(sql));
		return query.findUnique().getInteger("count(*)");
	}

	public void refresh() {
		// TODO Auto-generated method stub

	}

	public PagingList<T> setFetchAhead(boolean fetchAhead) {
//		this.fetchAhead = fetchAhead;
		return this;
	}

}

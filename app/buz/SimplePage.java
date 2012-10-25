package buz;

import java.util.List;

import com.avaje.ebean.Page;
import com.avaje.ebean.PagingList;

public class SimplePage<T> implements Page<T> {
	private final PagingList<T> list;
	private final int pageIndex;
	private final int pageSize;
	private final int pageCount;

	public SimplePage(int pageIndex, PagingList<T> list) {
		this.list = list;
		this.pageIndex = pageIndex;
		this.pageSize = list.getPageSize();

//		pageCount = list.size() / pageSize;
//		if (list.size() % pageSize != 0) {Long
//			pageCount++;
//		}
		pageCount = list.getTotalPageCount();
	}

	public String getDisplayXtoYofZ(String to, String of) {
		int first = pageIndex * pageSize + 1;
		int last = first + getList().size() - 1;
		int total = getTotalRowCount();
		return first + to + last + of + total;
	}

	public List<T> getList() {
		return list.getAsList();
	}

	public int getPageIndex() {

		return pageIndex;
	}

	public int getTotalPageCount() {
		return pageCount;
	}

	public int getTotalRowCount() {
		return list.getTotalRowCount();
	}

	public boolean hasNext() {
		return pageIndex +1 < pageCount;
	}

	public boolean hasPrev() {
		return pageIndex > 0;
	}

	public Page<T> next() {
		return list.getPage(pageIndex + 1);
		//return new SimplePage<T>(pageIndex + 1, list);
	}

	public Page<T> prev() {
		//return new SimplePage<T>(pageIndex - 1, list);
		return list.getPage(pageIndex - 1);
	}

}

package buz;

import java.util.List;

import com.avaje.ebean.SqlQuery;
import com.avaje.ebean.SqlRow;

public interface SqlResultBuilder<T> {
	public List<T> build(List<SqlRow> sqlRows);
	public SqlQuery buildSql(String sql);
}

package buz;

public class DefaultSqlResultCounter implements SqlResultCounter {
    public String getCountSql(String sql) {
        return "select count(*) from (" + sql + ") _default_count_sql";
    }
}


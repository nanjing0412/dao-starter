package com.github.jun.starter.dao.dsp.util;

public class SQLUtil {

    //构建分页的sql语句，页码从1开始
    public static String builderPageSQL(String sql, String dbName, PageParams pageParams) {
        StringBuffer querySql2 = new StringBuffer();
        int startPageNum = (pageParams.getPageNo() - 1) * pageParams.getPageSize() + 1;
        int endPageNum = (pageParams.getPageNo() - 1) * pageParams.getPageSize() + pageParams.getPageSize();
        if ("oracle".equals(dbName.toLowerCase())) {
            querySql2.append("SELECT * FROM (SELECT INNER.*, ROWNUM AS RECORDNUM FROM (");
            querySql2.append(sql.replace("\t", " ").replace("\n", " ").trim());
            querySql2.append(") INNER ) WRAPPED WHERE WRAPPED.RECORDNUM BETWEEN " + startPageNum + " AND " + endPageNum);
        } else {
            if (!"mysql".equals(dbName.toLowerCase())) {
                throw new NullPointerException("not find paging info for [" + dbName + "],please check it!");
            }

            querySql2.append(sql.replace("\t", " ").replace("\n", " ").trim());
            querySql2.append(" limit " + (startPageNum - 1) + "," + pageParams.getPageSize());
        }
        return querySql2.toString();
    }

    public static String builderCountSQL(String sql) {
        StringBuffer querySql2 = new StringBuffer();
        querySql2.append("SELECT COUNT(1) FROM (");
        querySql2.append(sql.replace("\t", " ").replace("\n", " ").trim());
        querySql2.append(")  T");
        return querySql2.toString();
    }

}

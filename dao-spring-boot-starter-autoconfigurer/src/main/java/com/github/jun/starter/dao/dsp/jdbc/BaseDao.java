package com.github.jun.starter.dao.dsp.jdbc;

import com.github.jun.starter.dao.dsp.jdbc.GenericJDBCDaoImpl;
import com.github.jun.starter.dao.dsp.sqlHandle.SqlTextBuilder;
import com.github.jun.starter.dao.dsp.util.PageBean;
import com.github.jun.starter.dao.dsp.util.PageParams;

import java.util.List;
import java.util.Map;

/**
 * @author: hujw
 * @date: 2019/1/23
 * @description:
 */
public class BaseDao {

    private String defDBStr = "mysql";

    public List findBySql(String sql, Object[] params) {
        return this.findBySql(sql, params, defDBStr);
    }

    public List findBySql(String sql, Object[] params, String dbStr) {
        List list = GenericJDBCDaoImpl.getInstance(dbStr).findBySql(sql, params);
        this.produceNullRecord(sql, list);
        return list;
    }

    public PageBean findPageList(String handlerId, String entryId, Map whereClause, PageParams pageParams, boolean startPageNoEqualsOne) {
        SqlTextBuilder sqlTextBuilder = new SqlTextBuilder(handlerId, entryId, whereClause);
        List contentList;
        if(startPageNoEqualsOne){
            //页码从1开始，分页查询（页码不需要转换）
            contentList = this.findBySql(sqlTextBuilder.getQueryPagingSql(pageParams), sqlTextBuilder.getQueryParams());
        }else{
            //页码从0开始，分页查询（页码需要转换）
            PageParams pageParams2 = PageParams.builder().pageNo(pageParams.getPageNo() + 1).pageSize(pageParams.getPageSize()).build();
            contentList = this.findBySql(sqlTextBuilder.getQueryPagingSql(pageParams2), sqlTextBuilder.getQueryParams());
        }
        //总记录数
        int totalCount = this.findForObject(sqlTextBuilder.getCountEntiesSql(), sqlTextBuilder.getQueryParams(), Integer.class);
        //总页数
        int totalPages = PageBean.calculateTotalPages(totalCount, pageParams.getPageSize());
        //不拘泥于页码从0开始还是从1开始，直接返回即可
        PageBean pageBean = new PageBean (pageParams.getPageNo(), pageParams.getPageSize(), totalCount,totalPages, contentList);
        return pageBean;
    }

    public <T> T findForObject(String sql, Object[] params, Class<T> requiredType) {
        return this.findForObject(sql, params, defDBStr, requiredType);
    }

    public <T> T findForObject(String sql, Object[] params, String dbStr, Class<T> requiredType) {
        return GenericJDBCDaoImpl.getInstance(dbStr).findForObject(sql, params, requiredType);
    }

    public int updateBySql(String sql, Object[] params) {
        return this.updateBySql(sql, params, defDBStr);
    }

    public int updateBySql(String sql, Object[] params, String dbStr) {
        return GenericJDBCDaoImpl.getInstance(dbStr).updateBySql(sql.trim(), params);
    }

    public int[] batchUpdateBySql(String sql, List<Object[]> paramsList) {
        return this.batchUpdateBySql(sql, paramsList, defDBStr);
    }

    public int[] batchUpdateBySql(String sql, List<Object[]> paramsList, String dbStr) {
        return GenericJDBCDaoImpl.getInstance(dbStr).batchUpdateBySql(sql.trim(), paramsList);
    }

    public void produceNullRecord(String sql, List list) {
        if (list != null) {
            if (list.size() == 1 && sql.toLowerCase().indexOf(" group ") > 0) {
                if (list.get(0) instanceof Map) {
                    Map<String, Object> map = (Map)list.get(0);
                    Object[] o = map.values().toArray();
                    if (o[0] == null) {
                        list.remove(0);
                    }
                } else if (list.get(0).getClass().isArray()) {
                    Object[] o = (Object[])((Object[])list.get(0));
                    if (o[0] == null) {
                        list.remove(0);
                    }
                }
            }
        }
    }

}

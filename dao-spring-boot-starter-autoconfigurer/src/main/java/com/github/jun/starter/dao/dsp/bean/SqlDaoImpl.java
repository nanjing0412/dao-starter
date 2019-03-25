package com.github.jun.starter.dao.dsp.bean;

import com.github.jun.starter.dao.dsp.jdbc.BaseDao;
import com.github.jun.starter.dao.dsp.sqlHandle.SqlTextBuilder;
import com.github.jun.starter.dao.dsp.util.PageBean;
import com.github.jun.starter.dao.dsp.util.PageParams;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @author: hujw
 * @date: 2019/1/23
 * @description:
 */
@Repository
public class SqlDaoImpl extends BaseDao implements SqlDao {

    @Override
    public List<Map> getList(String handlerId, String entryId, Map<String, Object> whereMap) {
        SqlTextBuilder sqlTextBuilder = new SqlTextBuilder(handlerId, entryId, whereMap);
        return super.findBySql(sqlTextBuilder.getQuerySql(), sqlTextBuilder.getQueryParams());
    }

    @Override
    public PageBean getPageList(String handlerId, String entryId, Map<String, Object> whereMap, PageParams pageParams, boolean startPageNoEqualsOne) {
        return super.findPageList(handlerId, entryId, whereMap, pageParams, startPageNoEqualsOne);
    }

    @Override
    public Map<String, Object> getMap(String handlerId, String entryId, Map<String, Object> whereMap) {
        SqlTextBuilder sqlTextBuilder = new SqlTextBuilder(handlerId, entryId, whereMap);
        List<Map> list = super.findBySql(sqlTextBuilder.getQuerySql(), sqlTextBuilder.getQueryParams());
        return (Map)(list != null && list.size() != 0 ? (Map)list.get(0) : new HashMap());
    }

    @Override
    public <T> T getObject(String handlerId, String entryId, Map<String, Object> whereMap, Class<T> requiredType) {
        SqlTextBuilder sqlTextBuilder = new SqlTextBuilder(handlerId, entryId, whereMap);
        return super.findForObject(sqlTextBuilder.getQuerySql(), sqlTextBuilder.getQueryParams(), requiredType);
    }

    @Override
    public int update(String handlerId, String entryId, Map<String, Object> whereMap) {
        SqlTextBuilder sqlTextBuilder = new SqlTextBuilder(handlerId, entryId, whereMap);
        return super.updateBySql(sqlTextBuilder.getQuerySql(), sqlTextBuilder.getQueryParams());
    }

    @Override
    public int[] batchUpdate(String handlerId, String entryId, List<Map<String, Object>> whereList) {
        String sql = "";
        List<Object[]> list = new ArrayList();
        if (whereList != null) {
            Iterator i$ = whereList.iterator();

            while(i$.hasNext()) {
                Map<String, Object> objMap = (Map)i$.next();
                SqlTextBuilder sqlTextBuilder = new SqlTextBuilder(handlerId, entryId, objMap);
                sql = sqlTextBuilder.getQuerySql();
                list.add(sqlTextBuilder.getQueryParams());
            }
        }

        return list.size() > 0 ? super.batchUpdateBySql(sql, list) : new int[0];
    }

    @Override
    public int getCount(String handlerId, String entryId, Map<String, Object> whereMap) {
        SqlTextBuilder sqlTextBuilder = new SqlTextBuilder(handlerId, entryId, whereMap);
        int totalNumber = super.findForObject(sqlTextBuilder.getCountEntiesSql(), sqlTextBuilder.getQueryParams(), Integer.class);
        return totalNumber;
    }

    @Override
    public boolean exists(String handlerId, String entryId, Map<String, Object> whereMap) {
        SqlTextBuilder sqlTextBuilder = new SqlTextBuilder(handlerId, entryId, whereMap);
        int totalNumber = super.findForObject(sqlTextBuilder.getCountEntiesSql(), sqlTextBuilder.getQueryParams(), Integer.class);
        return totalNumber > 0;
    }

}

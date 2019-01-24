package com.github.jun.starter.dao.dsp.bean;

import com.github.jun.starter.dao.dsp.jdbc.BaseDao;
import com.github.jun.starter.dao.dsp.sqlHandle.SqlTextBuilder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

}

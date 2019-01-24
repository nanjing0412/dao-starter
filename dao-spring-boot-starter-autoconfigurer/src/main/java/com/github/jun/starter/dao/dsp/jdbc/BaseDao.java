package com.github.jun.starter.dao.dsp.jdbc;

import com.github.jun.starter.dao.dsp.jdbc.GenericJDBCDaoImpl;

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

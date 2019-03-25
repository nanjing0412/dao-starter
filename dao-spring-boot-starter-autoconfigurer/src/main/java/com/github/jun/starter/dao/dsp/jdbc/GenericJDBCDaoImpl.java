package com.github.jun.starter.dao.dsp.jdbc;

import com.github.jun.starter.dao.dsp.util.SpringHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: hujw
 * @date: 2019/1/23
 * @description:
 */
@Slf4j
public class GenericJDBCDaoImpl extends JdbcDaoSupport {

    private static Map instances = new HashMap();

    protected static final String DEFAULT = "mysql";

    public static GenericJDBCDaoImpl getInstance(String dbStr) {
        if (dbStr == null || "".equals(dbStr) || "null".equals(dbStr)) {
            dbStr = DEFAULT;
        }

        if (!instances.containsKey(dbStr)) {
            DataSource ds = (DataSource)DataSourceConfig.dataSourceMap.get(dbStr);
            if (ds == null) {
                log.error("dataSourceMap config error!");
            }

            GenericJDBCDaoImpl impl = new GenericJDBCDaoImpl();
            impl.setDataSource(ds);
            instances.put(dbStr, impl);
            return impl;
        } else {
            log.debug("GenericJDBCDaoImpl  instances.get(" + dbStr + ")");
            return (GenericJDBCDaoImpl)instances.get(dbStr);
        }
    }

    public List findBySql(String sql, Object[] params) {
        log.info("sql:" + sql);
        log.info("sql-params:" + Arrays.toString(params));
        return this.getJdbcTemplate().queryForList(sql, params);
    }

    public <T> T findForObject(String sql, Object[] params, Class<T> requiredType) {
        logger.info("sql:" + sql);
        logger.info("sql-params:" + Arrays.toString(params));
        return this.getJdbcTemplate().queryForObject(sql, params, requiredType);
    }

    public int updateBySql(String sql, Object[] params) {
        logger.info("sql:" + sql);
        logger.info("sql-params:" + Arrays.toString(params));
        return this.getJdbcTemplate().update(sql, params);
    }

    public int[] batchUpdateBySql(String sql, List<Object[]> paramsList) {
        logger.info("sql:" + sql);
        logger.info("sql-params:" + paramsList);
        return this.getJdbcTemplate().batchUpdate(sql, paramsList);
    }

}

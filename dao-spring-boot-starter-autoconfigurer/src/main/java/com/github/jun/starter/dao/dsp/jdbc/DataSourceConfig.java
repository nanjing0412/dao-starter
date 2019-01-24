package com.github.jun.starter.dao.dsp.jdbc;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: hujw
 * @date: 2019/1/24
 * @description:
 */
@Configuration
public class DataSourceConfig {

    public static Map dataSourceMap = new HashMap();

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource mysqlDataSource() {
        DataSource mysqlDataSource = DataSourceBuilder.create().build();
        dataSourceMap.put("mysql", mysqlDataSource);
        return mysqlDataSource;
    }

}
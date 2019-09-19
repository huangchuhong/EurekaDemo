package com.unipower.alarm.util;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Properties;

/**
 * 自动注入无法使用，使用手动加载的方式加载JdbcTemplate
 */
public class JdbcTemUtil {

    private static JdbcTemplate jdbcTemplate = null;
    private static DruidDataSource druidDataSource = null;

    public static synchronized JdbcTemplate getJdbcTemplate() {
        if (null == jdbcTemplate){
            try {
                //读取配置文件
                Resource resource = new ClassPathResource("/application.properties");
                Properties properties = PropertiesLoaderUtils.loadProperties(resource);

                druidDataSource = new DruidDataSource();
                druidDataSource.setPassword(properties.getProperty("spring.datasource.password"));
                druidDataSource.setUrl(properties.getProperty("spring.datasource.url"));
                druidDataSource.setUsername(properties.getProperty("spring.datasource.username"));
                druidDataSource.setDriverClassName(properties.getProperty("spring.datasource.driverClassName"));

                jdbcTemplate = new JdbcTemplate();
                // 设置数据源
                jdbcTemplate.setDataSource(druidDataSource);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jdbcTemplate;
    }
}

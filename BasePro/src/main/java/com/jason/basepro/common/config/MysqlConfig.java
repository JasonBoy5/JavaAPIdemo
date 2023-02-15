package com.jason.basepro.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@MapperScan(basePackages = "com.jason.basepro.base.mapper")
public class MysqlConfig {

    @Value("${first.datasource.filters}")
    private String filters;
    @Value("${first.datasource.driver-class-name}")
    private String driverClass;
    @Value("${first.datasource.url}")
    private String url;
    @Value("${first.datasource.username}")
    private String user;
    @Value("${first.datasource.password}")
    private String password;
    @Value("${first.datasource.initial-size}")
    private int initial_size;
    @Value("${first.datasource.min-idle}")
    private int min_idle;
    @Value("${first.datasource.max-active}")
    private int max_active;
    @Value("${first.datasource.max-wait}")
    private long max_wait;
    @Value("${first.datasource.time-between-eviction-runs-millis}")
    private long time_between_eviction_runs_millis;
    @Value("${first.datasource.min-evictable-idle-time-millis}")
    private long min_evictable_idle_time_millis;
    @Value("${first.datasource.validation-query}")
    private String validation_query;
    @Value("${first.datasource.test-while-idle}")
    private boolean test_while_idle;
    @Value("${first.datasource.test-on-borrow}")
    private boolean test_on_borrow;
    @Value("${first.datasource.test-on-return}")
    private boolean test_on_return;
    @Value("${first.datasource.pool-prepared-statements}")
    private boolean pool_prepared_statements;
    @Value("${first.datasource.max-pool-prepared-statement-per-connection-size}")
    private int max_pool_prepared_statement_per_connection_size;


    @Bean(name = "genericDataSource")
    @Primary //必须加注解，不然会报错，下一个类不需要添加
    public DataSource genericDataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setFilters(filters);
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setInitialSize(initial_size);
        dataSource.setMinIdle(min_idle);
        dataSource.setMaxActive(max_active);
        dataSource.setMaxWait(max_wait);
        dataSource.setTimeBetweenConnectErrorMillis(time_between_eviction_runs_millis);
        dataSource.setMinEvictableIdleTimeMillis(min_evictable_idle_time_millis);
        dataSource.setValidationQuery(validation_query);
        dataSource.setTestWhileIdle(test_while_idle);
        dataSource.setTestOnBorrow(test_on_borrow);
        dataSource.setTestOnReturn(test_on_return);
        dataSource.setPoolPreparedStatements(pool_prepared_statements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(max_pool_prepared_statement_per_connection_size);
        return dataSource;
    }

}

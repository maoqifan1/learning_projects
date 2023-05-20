package com.maoqifan.mq.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.maoqifan.mq.mapper.ds1"}, sqlSessionFactoryRef = "sqlSessionFactory1")
public class DataSource1Config {
    @Value("${mybatis.mapper-locations}")
    private String mapperLocation;
    @Value("${spring.datasource.ds1.url}")
    private String jdbcUrl;
    @Value("${spring.datasource.ds1.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.ds1.username}")
    private String username;
    @Value("${spring.datasource.ds1.password}")
    private String password;

    @Bean(name = "dataSource1")
    @Primary
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(this.jdbcUrl);
        dataSource.setDriverClassName(this.driverClassName);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        return dataSource;
    }

    @Bean(name = "sqlSessionFactory1")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource1") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 设定数据源
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 设定mapper存放地址
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(this.mapperLocation));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean("sqlSessionTemplate1")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory1") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean("transactionManager1")
    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource1") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}

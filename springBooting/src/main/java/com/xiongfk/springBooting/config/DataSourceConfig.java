package com.xiongfk.springBooting.config;//package com.example.xiongfk.config;
//
//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
//
///**
// * 功能描述 TODO
// *
// * @Author xiongfk
// * @Date 2020/5/21
// * @Version 1.0
// **/
//@Configuration
//@MapperScan("com.example.xiongfk.mapper")
//public class DataSourceConfig {
//
//    /**
//     * DataSource 自动配置并注册
//     * @return data source
//     */
//    @Bean("master")
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource.master")
//    public DataSource masterDataSource() {
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    /**
//     * DataSource 自动配置并注册
//     *
//     * @return data source
//     */
//    @Bean("slaver")
//    @ConfigurationProperties(prefix = "spring.datasource.slaver")
//    public DataSource slaverDataSource() {
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    /**
//     * 注册动态数据源
//     *
//     * @return
//     */
//    @Bean("dynamicDataSource")
//    public DataSource dynamicDataSource() {
//        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
//        Map<Object, Object> dataSourceMap = new HashMap<>();
//        dataSourceMap.put("master", masterDataSource());
//        dataSourceMap.put("slaver", slaverDataSource());
//        dynamicRoutingDataSource.setDefaultTargetDataSource(masterDataSource());// 设置默认数据源
//        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);
//        return dynamicRoutingDataSource;
//    }
//
//    /**
//     * Sql session factory bean.
//     * Here to config datasource for SqlSessionFactory
//     * <p>
//     * You need to add @{@code @ConfigurationProperties(prefix = "mybatis")}, if you are using *.xml file,
//     * the {@code 'mybatis.type-aliases-package'} and {@code 'mybatis.mapper-locations'} should be set in
//     * {@code 'application.properties'} file, or there will appear invalid bond statement exception
//     * @return the sql session factory bean
//     */
//    @Bean
//    @ConfigurationProperties(prefix = "mapper")
//    public SqlSessionFactoryBean sqlSessionFactoryBean() {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        // 必须将动态数据源添加到 sqlSessionFactoryBean
//        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
//        return sqlSessionFactoryBean;
//    }
//
//    /**
//     * 事务管理器
//     * @return the platform transaction manager
//     */
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        return new DataSourceTransactionManager(dynamicDataSource());
//    }
//}

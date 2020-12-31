package com.xiongfk.springBooting.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.xiongfk.springBooting.mapper.slave",sqlSessionTemplateRef = "slaverSqlSessionTemplate")
public class SlaverDataSourceConfig {
    /*
     配置dataSource数据源
    */
    @Bean(name = "slaverDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slaver")
    public DataSource masterDataSource(){
        return DataSourceBuilder.create().build();
    }
    /*
      配置sqlSessionFactory
     */
    @Bean(name = "slaverSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("slaverDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //对应mybatis.type-aliases-package配置
//        bean.setTypeAliasesPackage("com.example.xiongfk.model");
        //对应mybatis.mapper-locations配置
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/slave/*.xml"));
        //开启驼峰映射
        bean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return bean.getObject();
    }
    /*
      配置事务管理
     */
    @Bean(name = "slaverTransactionManager")
    public DataSourceTransactionManager masterTransactionManager(@Qualifier("slaverDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    /*
      配置sqlSessionTemplate
     */
    @Bean(name = "slaverSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("slaverSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

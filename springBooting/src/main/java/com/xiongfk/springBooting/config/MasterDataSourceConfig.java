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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration //注册到springboot容器，相当于原来xml文件里的<beans>
//下面要进行扫包，目的是标清楚为谁添加的数据源，这样对应的包里函数执行数据库操作的时候，就知道要执行的数据库账号
@MapperScan(basePackages = "com.xiongfk.springBooting.mapper.master",sqlSessionTemplateRef = "masterSqlSessionTemplate")
public class MasterDataSourceConfig {
    /*
      配置dataSource数据源
     */
    @Bean(name = "masterDataSource") //注入到这个容器
    //表示取application.properties配置文件中的前缀
    @ConfigurationProperties(prefix = "spring.datasource.master")
    //primary是设置优先，因为有多个数据源，在没有明确指定用哪个的情况下，
    //会用带有primary的，这个注解必须有一个数据源要添加
    @Primary
    public DataSource masterDataSource(){
       return DataSourceBuilder.create().build();
    }
    /*
      配置sqlSessionFactory
     */
    @Bean(name = "masterSqlSessionFactory")
    @Primary
    //@Qualifier("xxx")的含义是告诉他使用哪个DataSource
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //对应mybatis.mapper-locations配置
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/master/*.xml"));
        //开启驼峰映射
        bean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return bean.getObject();
    }
    /*
      配置事务管理
     */
    @Bean(name = "masterTransactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManager(@Qualifier("masterDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    /*
      配置sqlSessionTemplate
     */
    @Bean(name = "masterSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

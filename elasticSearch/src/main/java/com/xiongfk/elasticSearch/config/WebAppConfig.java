package com.xiongfk.elasticSearch.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 功能描述 TODO 注册拦截器
 * @Author xiongfk
 * @Date 2020/8/13
 * @Version 1.0
 **/
@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor());
    }

    @Bean
    public FilterRegistrationBean filterRegist() {
        FilterRegistrationBean frBean = new FilterRegistrationBean();
        frBean.setFilter(new MyFilter());
//        frBean.setOrder(1);//多个过滤器时指定过滤器的执行顺序
        frBean.addUrlPatterns("/*");
        System.out.println("filter");
        return frBean;
    }
}

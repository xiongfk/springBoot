package com.xiongfk.springBooting.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 功能描述 TODO
 * 拦截配置类
 * @Author xiongfk
 * @Date 2020/5/19
 * @Version 1.0
 **/
@Component
//@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private AutoIdempotentInterceptor autoIdempotentInterceptor;

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(autoIdempotentInterceptor);
//        super.addInterceptors(registry);
    }

}

package com.xiongfk.springBooting.config;

import java.lang.annotation.*;

/**
 * 功能描述 TODO 作用于类、接口或者方法上
 * @Author xiongfk
 * @Date 2019/11/7
 * @Version 1.0
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {

    String name();
}

package com.xiongfk.springBooting.customAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 功能描述 TODO
 * 在类上表示此类中有不能为空的字段
 * 在get方法上表示返回值不能为null，字符串不能为空
 * 如果返回值是集合size不能为0
 * @Author xiongfk
 * @Date 2020/5/18
 * @Version 1.0
 **/
@Target(value = { ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckParam {

    public String value() default "";

    public String fieldName() default "";

    /** 字段名称*/
    public String msg();

}

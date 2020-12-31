package com.xiongfk.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* 类描述 TODO 用于控制定时任务的开启与关闭
* @author xiongfk
* @date 2020/9/9
* @return
*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AspectLog {

}

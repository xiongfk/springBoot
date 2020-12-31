package com.xiongfk.springBooting.customAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* 类描述 TODO 实现幂等
* 元注解ElementType.METHOD表示它只能放在方法上，
* RetentionPolicy.RUNTIME表示它在运行时
* @author xiongfk
* @date 2020/5/19
* @param
* @return
*/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface AutoIdempotent {

}

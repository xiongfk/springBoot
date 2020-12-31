package com.xiongfk.springBooting.customAnnotation;

import net.bytebuddy.asm.Advice;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties;

import java.lang.reflect.Method;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2020/5/18
 * @Version 1.0
 **/
public class TestAnnotation {
    public static void main(String[] args) {
        Class<?> clazz = DemoAnnotation.class;
        DemoAnnotation demoAnnotation = new DemoAnnotation();
        Method [] methods = clazz.getMethods();
        if(demoAnnotation instanceof Object){
            System.out.println(true);
        }else{
            System.out.println(false);
        }
//        for (Method method : methods) {
//            if(method.isAnnotationPresent(MyAnnotation.class)){
//                try {
//                    System.out.println(method.getName());
//                    method.invoke(clazz.newInstance(),"hello");
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                } catch (InvocationTargetException e) {
//                    e.printStackTrace();
//                } catch (InstantiationException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }
}

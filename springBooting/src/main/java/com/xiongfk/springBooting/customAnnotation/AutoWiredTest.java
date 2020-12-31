package com.xiongfk.springBooting.customAnnotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2020/6/19
 * @Version 1.0
 **/
public class AutoWiredTest {
    public static void main(String[] args) {
        Class<?> demoAnnotationClass = DemoAnnotation.class;
        Method[] methods = demoAnnotationClass.getMethods();
        for (Method method : methods) {
            if(method.isAnnotationPresent(MyAutoWired.class)){
                try {
                    Object invoke = method.invoke(demoAnnotationClass.newInstance(), "hello world");
                    System.out.println(invoke.toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

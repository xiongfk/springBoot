package com.xiongfk.springBooting.springProxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 功能描述 TODO
 * CGLIB 动态代理实现
 * @Author xiongfk
 * @Date 2020/6/18
 * @Version 1.0
 **/
public class CGLIBDaoProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object object, Method method, Object[] objects, MethodProxy proxy) throws Throwable {
        String methodName = method.getName();
        if("insert".equals(methodName) || "update".equals(methodName)) {
            System.out.println(methodName + "()方法开始时间:" + System.currentTimeMillis());
            proxy.invokeSuper(object, objects);
            System.err.println(methodName + "()结束时间:" + System.currentTimeMillis());
            return object;
        }
        proxy.invokeSuper(object, objects);
        return object;
    }

    public static void main(String[] args) {
        CGLIBDaoProxy daoProxy = new CGLIBDaoProxy();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(DaoImpl.class);
        enhancer.setCallback(daoProxy);
        Dao dao = (DaoImpl) enhancer.create();
        dao.insert();
        System.out.println("----------分割线----------");
        dao.delete();
        System.out.println("----------分割线----------");
        dao.update();
    }
}

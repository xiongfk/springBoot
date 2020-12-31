package com.xiongfk.springBooting.springProxy;

import com.xxl.job.core.util.JacksonUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 功能描述 TODO
 * JDK动态代理实现
 * @Author xiongfk
 * @Date 2020/6/18
 * @Version 1.0
 **/
public class InvocationHandlerImpl implements InvocationHandler {

    private Object target;// 这其实业务实现类对象，用来调用具体的业务方法

    // 通过构造函数传入目标对象
    public InvocationHandlerImpl(Object target) {
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        if("insert".equals(methodName) || "update".equals(methodName)) {
            System.out.println(methodName + "开始执行::" + System.currentTimeMillis() );
            Object result = method.invoke(target, args);
            System.out.println(methodName + "执行结束:" + System.currentTimeMillis());
            return result;
        }
        return method.invoke(target, args);
    }

    public static void main(String[] args) {

        Dao dao = new DaoImpl();
        //Proxy内库的newProxyInstance方法返回被代理对象(DaoImpl)的一个实例,然后向上转型转化为对应的接口
        Dao proxyDao = (Dao) Proxy.newProxyInstance(InvocationHandlerImpl.class.getClassLoader(),new Class<?>[] { Dao.class }, new InvocationHandlerImpl(dao));
        proxyDao.insert();
        System.out.println("----------分割线----------");
        proxyDao.delete();
        System.out.println("----------分割线----------");
        proxyDao.update();
    }
}
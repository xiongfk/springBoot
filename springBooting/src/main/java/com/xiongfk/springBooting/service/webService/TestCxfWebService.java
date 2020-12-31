package com.xiongfk.springBooting.service.webService;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2019/9/12
 * @Version 1.0
 **/
public class TestCxfWebService {

    public static void main(String[] args) {
        TestCxfWebService.dynamicInvoke();
    }

    /**
     * 1.代理类工厂的方式,需要拿到对方的接口地址
     */
    public static void staticInvoke() {
        try {
            // 接口地址
            String address = "http://127.0.0.1:8088/webService/api?wsdl";
            // 代理工厂
            JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
            // 设置代理地址
            jaxWsProxyFactoryBean.setAddress(address);
            // 设置接口类型
            jaxWsProxyFactoryBean.setServiceClass(UserService.class);
            // 创建一个代理接口实现
            UserService us = (UserService) jaxWsProxyFactoryBean.create();
            // 数据准备
            String userId = "maple";
            // 调用代理接口的方法调用并返回结果
            String result = us.listUser();
            System.out.println("返回结果:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 2:动态调用
     */
    public static void dynamicInvoke() {
        //创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        String address = "http://127.0.0.1:8088/webService/api?wsdl";
        Client client = dcf.createClient(address);
        // 需要密码的情况需要加上用户名和密码
//         client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));
        Object[] objects = new Object[0];
        try {
            // invoke("方法名",参数1,参数2,参数3....);
//            objects = client.invoke("getUser", "maple");
            objects = client.invoke("listUser");
            System.out.println("返回数据:" + objects[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

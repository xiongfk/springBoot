package com.xiongfk.springBooting.config;

import javax.xml.ws.Endpoint;

import com.xiongfk.springBooting.service.webService.UserService;
import com.xiongfk.springBooting.service.webService.impl.UserServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2019/9/12
 * @Version 1.0
 **/
@Configuration
public class WebServiceConfig {

    /**
    * 方法描述 TODO
    * 此方法作用是改变项目中服务名的前缀名，此处127.0.0.1或者localhost不能访问时，
    * 请使用ipconfig查看本机ip来访问
    * 此方法被注释后:wsdl访问地址为http://127.0.0.1:8080/services/user?wsdl
    *去掉注释后：wsdl访问地址为：http://127.0.0.1:8080/soap/user?wsdl
    * @author xiongfk
    * @date 2019/9/12
    * @param
    * @return org.springframework.boot.web.servlet.ServletRegistrationBean<org.apache.cxf.transport.servlet.CXFServlet>
    */
    //成功集成cxf后，发现只有webservice服务可以正常使用，其他请求url全部无法正常访问。
    //是因为 public ServletRegistrationBean dispatcherServlet() 把默认映射覆盖掉了，
    //把这个名字改掉，控制类方法就能访问了。
    @Bean
    public ServletRegistrationBean<CXFServlet> dispatcherServlets() {
        String urlMappings = "/webService/*";
        return new ServletRegistrationBean<CXFServlet>(new CXFServlet(),urlMappings);
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    /**
    * 方法描述 TODO JAX-WS 站点服务
    * @author xiongfk
    * @date 2019/9/12
    * @param
    * @return javax.xml.ws.Endpoint
    */
    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), userService());
        endpoint.publish("/api");
        return endpoint;
    }
}

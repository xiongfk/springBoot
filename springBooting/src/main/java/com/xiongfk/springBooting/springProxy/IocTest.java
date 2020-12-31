package com.xiongfk.springBooting.springProxy;

import com.xiongfk.springBooting.model.Student;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2020/6/19
 * @Version 1.0
 **/
public class IocTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:testBean.xml");
        Student testBeans = (Student)context.getBean("testBeans");
        System.out.println(testBeans.getStuName());
    }
}

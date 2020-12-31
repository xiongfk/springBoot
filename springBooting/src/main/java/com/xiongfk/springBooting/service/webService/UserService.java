package com.xiongfk.springBooting.service.webService;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * UserService
 * 接口描述 TODO
 *
 * @Author xiongfk
 * @Date 2019/9/12
 * @Version 1.0
 **/
@WebService(name="UserService",targetNamespace="http://webService.xiongfk.example.com")
public interface UserService {
    @WebMethod
    //标注该方法为webservice暴露的方法,用于向外公布，
    // 它修饰的方法是webservice方法，去掉也没影响的，类似一个注释信息。
    public String getUser(String name);

    @WebMethod
    public String listUser();

    @WebMethod
    public String deleteUser(String id);

    @WebMethod
    public String updateUser(String id);
}

package com.xiongfk.springBooting.controller;

import com.xiongfk.springBooting.model.User;
import com.xiongfk.springBooting.service.TestUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2020/8/24
 * @Version 1.0
 **/

@RestController
@RequestMapping(value = "/user/")
public class UserController {

    @Autowired
    private TestUserService testUserService;

    @RequestMapping(value = "addUser",method = RequestMethod.POST)
    public String addUser(@RequestBody @Valid User user) {
        // 如果有参数校验失败，会将错误信息封装成对象组装在BindingResult里
//        Map<String,Object> map = new HashMap<>();
//        for (ObjectError error : bindingResult.getAllErrors()) {
//            map.put("code","200");
//            map.put("msg",error.getDefaultMessage());
//            return JSONObject.toJSONString(map);
//        }
        return testUserService.addUser(user);
    }
}

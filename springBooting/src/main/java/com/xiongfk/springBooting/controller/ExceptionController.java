package com.xiongfk.springBooting.controller;

import com.xiongfk.springBooting.config.TargetDataSource;
import com.xiongfk.springBooting.exception.BizException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述 TODO
 * 全局异常演示
 * @Author xiongfk
 * @Date 2019/10/24
 * @Version 1.0
 **/
@Validated
@RestController
public class ExceptionController {

    @GetMapping("/test3")
    @TargetDataSource(name = "")
    public String test3(Integer num) {
        // TODO 演示需要，实际上参数是否为空通过 @RequestParam(required = true)  就可以控制
        if (num == null) {
            throw new BizException("500", "num不能为空");
        }
        int i = 10 / num;
        return "result:" + i;
    }
}

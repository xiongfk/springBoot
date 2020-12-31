package com.xiongfk.springBooting.controller;

import com.xiongfk.springBooting.model.Account;
import com.xiongfk.springBooting.service.restService.AccountRestService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2019/9/30
 * @Version 1.0
 **/
@RestController
@RequestMapping("/accountRest/")
public class AccountRestController {
    @Autowired
    private AccountRestService accountRestService;

    @PostMapping(value = "listRest")
    public PageInfo<Account> getAccounts() {
        return accountRestService.findAccountList();
    }
}

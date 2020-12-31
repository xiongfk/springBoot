package com.xiongfk.springBooting.service.restService.impl;

import com.xiongfk.springBooting.mapper.slave.AccountSlaveMapper;
import com.xiongfk.springBooting.model.Account;
import com.xiongfk.springBooting.service.restService.AccountRestService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2019/9/30
 * @Version 1.0
 **/
@Service
public class AccountRestServiceImpl implements AccountRestService {
    @Autowired
    private AccountSlaveMapper accountMapper;

    @Override
    public PageInfo<Account> findAccountList() {
        PageHelper.startPage(0, 5);
        List<Account> list = accountMapper.findAccountList();
        PageInfo<Account> appsPageInfo = new PageInfo<>(list);
        return appsPageInfo;
    }
}

package com.xiongfk.springBooting.service.impl;

import com.xiongfk.springBooting.mapper.master.AccountMasterMapper;
import com.xiongfk.springBooting.mapper.slave.AccountSlaveMapper;
import com.xiongfk.springBooting.model.Account;
import com.xiongfk.springBooting.service.AccountService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * AccountServiceImpl
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2019/7/14 11:05
 * @Version 1.0
 **/
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMasterMapper accountMapper;
    @Autowired
    private AccountSlaveMapper accountSlaveMapper;

    @Override
    public PageInfo<Account> findAccountList() {
        PageHelper.startPage(0, 15);
        List<Account> list = accountSlaveMapper.findAccountList();
        PageInfo<Account> appsPageInfo = new PageInfo<>(list);
        return appsPageInfo;
    }

    @Override
    public Integer updateByName(String accountName) {
        return accountMapper.updateByName(accountName);
    }
}

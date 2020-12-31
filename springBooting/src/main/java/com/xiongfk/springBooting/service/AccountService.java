package com.xiongfk.springBooting.service;

import com.xiongfk.springBooting.model.Account;
import com.github.pagehelper.PageInfo;

/**
 * AccountService
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2019/7/14 10:42
 * @Version 1.0
 **/
public interface AccountService {
    /**
     * 方法描述 TODO 查询所有用户信息
     *
     * @param
     * @return java.util.List<com.example.xiongfk.model.Account>
     * @author xiongfk
     * @date 2019/7/17
     */
    PageInfo<Account> findAccountList();

    /**
     * 方法描述 TODO 根据用户名更新用户信息
     *
     * @param accountName
     * @return java.lang.Integer
     * @author xiongfk
     * @date 2019/7/17
     */
    Integer updateByName(String accountName);
}
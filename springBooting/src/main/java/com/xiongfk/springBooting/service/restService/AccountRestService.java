package com.xiongfk.springBooting.service.restService;

import com.xiongfk.springBooting.model.Account;
import com.github.pagehelper.PageInfo;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2019/9/30
 * @Version 1.0
 **/
public interface AccountRestService {

    PageInfo<Account> findAccountList();
}

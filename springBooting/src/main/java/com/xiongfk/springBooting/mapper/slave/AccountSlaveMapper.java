package com.xiongfk.springBooting.mapper.slave;

import com.xiongfk.springBooting.model.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * AccountMapper
 * 接口描述 TODO
 *
 * @Author xiongfk
 * @Date 2019/7/14 10:41
 * @Version 1.0
 **/
@Mapper
public interface AccountSlaveMapper {
    List<Account> findAccountList();
}

package com.xiongfk.springBooting.mapper.master;

import org.apache.ibatis.annotations.Mapper;

/**
 * AccountMapper
 * 接口描述 TODO
 *
 * @Author xiongfk
 * @Date 2019/7/14 10:41
 * @Version 1.0
 **/
@Mapper
public interface AccountMasterMapper {
    Integer updateByName(String accountName);
}

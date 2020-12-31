package com.xiongfk.springBooting.mapper.master;

import com.xiongfk.springBooting.model.EbizRedisLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * CommMapper
 * 接口描述 TODO
 *
 * @Author xiongfk
 * @Date 2019/7/16
 * @Version 1.0
 **/
@Mapper
public interface EbizRedisLogMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(EbizRedisLog record);

    int insertSelective(EbizRedisLog record);

    EbizRedisLog selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(EbizRedisLog record);

    int updateByPrimaryKey(EbizRedisLog record);

    EbizRedisLog selectByTableName(@Param("tableName") String tableName);
}
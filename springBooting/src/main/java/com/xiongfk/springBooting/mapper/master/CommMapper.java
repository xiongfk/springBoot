package com.xiongfk.springBooting.mapper.master;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * CommMapper
 * 接口描述 TODO
 *
 * @Author xiongfk
 * @Date 2019/7/16
 * @Version 1.0
 **/
@Mapper
public interface CommMapper {

    List<HashMap<String, Object>> superSelect(@Param("sql") String sql);

    int selectCount(@Param("tableName") String tableName);

}

package com.xiongfk.springBooting.mapper.master;

import com.xiongfk.springBooting.model.SysDict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * CommMapper
 * 接口描述 TODO
 *
 * @Author xiongfk
 * @Date 2019/7/16
 * @Version 1.0
 **/
@Mapper
public interface SysDictMapper {

    List<SysDict> selectAll();

    List<SysDict> selectOne(Map<String, String> where);

    List<TreeMap<String, Object>> selectGroupBy(@Param("selProPerties") String selProPerties);

    /**
     * 根据类型查询redis的请求路径
     *
     * @param type
     * @return
     */
    List<SysDict> selectByType(String type);
}
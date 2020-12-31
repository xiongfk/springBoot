package com.xiongfk.springBooting.mapper.master;

import com.xiongfk.springBooting.model.EbizProductRisk;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
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
public interface EbizProductRiskMapper {

    List<EbizProductRisk> selectAll();

    List<TreeMap<String, Object>> selectGroupBy(@Param("properties") String properties);
}
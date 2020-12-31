package com.xiongfk.springBooting.mapper.master;


import com.xiongfk.springBooting.model.EbizCommonCode;
import org.apache.ibatis.annotations.Mapper;

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
public interface EbizCommonCodeMapper {

    List<EbizCommonCode> selectAll();
}
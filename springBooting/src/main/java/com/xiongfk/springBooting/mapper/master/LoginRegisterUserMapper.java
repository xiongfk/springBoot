package com.xiongfk.springBooting.mapper.master;

import com.xiongfk.springBooting.model.LoginRegisterUser;
import com.xiongfk.springBooting.model.LoginRegisterUserExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginRegisterUserMapper {
    int countByExample(LoginRegisterUserExample example);

    int deleteByExample(LoginRegisterUserExample example);

    int insert(LoginRegisterUser record);

    int insertSelective(LoginRegisterUser record);

    List<LoginRegisterUser> selectByExample(LoginRegisterUserExample example);

    int updateByExampleSelective(@Param("record") LoginRegisterUser record, @Param("example") LoginRegisterUserExample example);

    int updateByExample(@Param("record") LoginRegisterUser record, @Param("example") LoginRegisterUserExample example);
}
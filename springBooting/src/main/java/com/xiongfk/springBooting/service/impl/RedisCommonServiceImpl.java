package com.xiongfk.springBooting.service.impl;

import com.xiongfk.springBooting.base.BaseCommonLog;
import com.xiongfk.springBooting.base.EntityClassName;
import com.xiongfk.springBooting.mapper.master.UserInfoMapper;
import com.xiongfk.springBooting.model.UserInfo;
import com.xiongfk.springBooting.model.UserInfoExample;
import com.xiongfk.springBooting.service.RedisCommonSevice;
import com.xiongfk.springBooting.utils.BaseDateRedisUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2019/7/17
 * @Version 1.0
 **/
@Service
public class RedisCommonServiceImpl extends BaseCommonLog implements RedisCommonSevice {
    @Autowired
    private BaseDateRedisUtil baseDateRedisUtil;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo queryByName(Integer sid, String userName) {
        long ee = System.currentTimeMillis();
        UserInfo userInfo = new UserInfo();
        List<Map<String, Object>> userInfoList = baseDateRedisUtil.getPojo(EntityClassName.UserInfo, "sid:" + sid + ":userName:" + userName);
        if (CollectionUtils.isNotEmpty(userInfoList)) {
            logger.info("----------缓存查询----------");
            Map<String, Object> userInfoMap = userInfoList.get(0);
            userInfoMap.get("sid");
            userInfoMap.get("userName");
            userInfoMap.get("age");
            userInfoMap.get("sex");
            userInfoMap.get("phone");
            userInfoMap.get("email");
            userInfoMap.get("depart");
            userInfoMap.get("address");
            userInfoMap.get("money");
            long rr = System.currentTimeMillis();
            long eee = rr - ee;
            logger.info("============== COMMON REDIS queryByName time:" + eee + "=================");
            return userInfo;
        } else {
            logger.info("----------数据库查询----------");
            UserInfoExample userInfoExample = new UserInfoExample();
            UserInfoExample.Criteria criteria = userInfoExample.createCriteria();
            criteria.andSidEqualTo(sid);
            List<UserInfo> userInfos = userInfoMapper.selectByExample(userInfoExample);
            if (CollectionUtils.isNotEmpty(userInfos)) {
                return userInfos.get(0);
            }
        }
        long rr = System.currentTimeMillis();
        long eee = rr - ee;
        logger.info("==============  COMMON REDIS queryByName time:" + eee + "=================");
        return null;
    }
}

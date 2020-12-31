package com.xiongfk.springBooting.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xiongfk.springBooting.exception.BizException;
import com.xiongfk.springBooting.exception.enums.CommonEnum;
import com.xiongfk.springBooting.service.TokenService;
import com.xiongfk.springBooting.utils.RandomUtil;
import com.xiongfk.springBooting.utils.RedisUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述 TODO
 * token服务接口实现类
 * @Author xiongfk
 * @Date 2020/5/19
 * @Version 1.0
 **/
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private RedisUtils redisUtils;

    private static String TOKEN_PREFIX = "token_";
    private static String TOKEN_NAME = "token";

    @Override
    public String createToken() {
        Map<String,Object> resMap = new HashMap<>();
        try {
            String radomStr = RandomUtil.generateString(30);
            StringBuffer token = new StringBuffer();
            token.append(TOKEN_PREFIX).append(radomStr);

            Long timeout = 7200L;//设置过期时长为2小时
            String token_name = token.toString();
            boolean notEmpty = StringUtils.isNotEmpty(token_name);
            if (notEmpty) {
                redisUtils.setKVByTimes(TOKEN_NAME,token_name,timeout);
                resMap.put("code", CommonEnum.SUCCESS.getResultCode());
                resMap.put("expiration_time",timeout);
                resMap.put(TOKEN_NAME,token_name);
                resMap.put("msg","成功");
            }else{
                resMap.put("code",CommonEnum.TOKEN_CREAT_FAIL.getResultCode());
                resMap.put("msg",CommonEnum.TOKEN_CREAT_FAIL);
            }
        } catch (Exception e){
            resMap.put("code",CommonEnum.INTERNAL_SERVER_ERROR.getResultCode());
            resMap.put("msg",CommonEnum.INTERNAL_SERVER_ERROR);
        }
        return JSONObject.toJSONString(resMap);
    }

    @Override
    public boolean checkToken(HttpServletRequest request) throws BizException {
        String token = request.getHeader(TOKEN_NAME);
        if(StringUtils.isBlank(token)){ // header中不存在token
            token = request.getParameter(TOKEN_NAME);
            if (StringUtils.isBlank(token)) {// parameter中也不存在token
                throw new BizException(CommonEnum.TOKEN_NOT_EMPTY);
            }
        }
        //redis中未获取到token
        if(StringUtils.isBlank(redisUtils.get(TOKEN_NAME))){
            throw new BizException(CommonEnum.REPEAT_REQUEST);
        }
        checkIsExist(token);
        return true;
    }

    //比较token是否一致 如果一致请求过后进行删除防止重复请求
    public void checkIsExist(String token){
        if(redisUtils.get(TOKEN_NAME).equals(token)){
            //请求完成时删除token
            boolean remove = redisUtils.delete(TOKEN_NAME);
            if (!remove) {
                throw new BizException(CommonEnum.TOKEN_NOT_EXISTS);
            }
        }else{
            throw new BizException(CommonEnum.TOKEN_NOT_EXISTS);
        }
    }
}

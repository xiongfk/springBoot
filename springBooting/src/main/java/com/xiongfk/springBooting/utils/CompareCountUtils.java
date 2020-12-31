package com.xiongfk.springBooting.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xiongfk.springBooting.mapper.master.SysDictMapper;
import com.xiongfk.springBooting.model.SysDict;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 处理redis缓存数量和数据库数量不一致情况
 *
 * @author xiongfukun
 * Description:TODO
 * @version V.1.0
 * @date 2018年10月22日
 */
@Service
public class CompareCountUtils {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    private SysDictMapper sysDictMapper;

    /**
     * 处理redis和数据库数量不一致的情况
     *
     * @param redisCounts redis数量
     * @param dbCounts    数据库数量
     * @param tableName   表名
     */
    public boolean isSameCounts(Integer redisCounts, Integer dbCounts, String tableName) {
        boolean b = true;
        if (0 != redisCounts.compareTo(dbCounts)) {
//			String str = delCache(tableName);
//			logger.info(str+"redis缓存数量和数据库数量不一致的表名"+tableName);
            b = false;
        }
        return b;
    }

    /**
     * 清除单表的缓存
     * 方法功能描述
     *
     * @param optionsVer 要清除缓存的表名
     * @return
     * @throws Exception
     */
    public String delCache(String optionsVer) throws Exception {
        Map<String, String> jsonMap = new HashMap<String, String>();
        List<SysDict> sysDictList = sysDictMapper.selectByType("redisUrl");
        String cavUrl = "";
        //获取请求redis连接
        if (null != sysDictList && sysDictList.size() != 0) {
            cavUrl = sysDictList.get(0).getValue();
        }

        String redisUrl = cavUrl + "basedata/" + optionsVer;
        logger.info("缓存请求路径:" + redisUrl);
        jsonMap.put("key", "ok");
        jsonMap.put("isDel", "Y");
        String redisReturn = "";
        try {
//            redisReturn = Http.postData(redisUrl,jsonMap,"UTF-8");
//			logger.info("redis缓存数量和数据库数量不一致的表名"+redisReturn);
        } catch (Exception e) {
            logger.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return redisReturn;
    }
}
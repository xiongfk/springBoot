package com.xiongfk.springBooting.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiongfk.springBooting.mapper.master.*;
import com.xiongfk.springBooting.model.*;
import com.xiongfk.springBooting.utils.*;
import com.xiongfk.springBooting.mapper.master.*;
import com.xiongfk.springBooting.model.*;
import com.xiongfk.springBooting.utils.BaseDateRedisUtil;
import com.xiongfk.springBooting.utils.RedisUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * BaseDateProductService
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2019/7/14 10:43
 * @Version 1.0
 **/
@Service
public class BaseDateProductService extends BaseDateRedisUtil {

    @Autowired
    private RedisUtils baseDateRedis;
    @Autowired
    private EbizProductRiskMapper ebizProductRiskMapper;
    @Autowired
    private EbizRedisLogMapper ebizRedisLogMapper;
    @Autowired
    private SysDictMapper sysDictMapper;
    @Autowired
    private EbizCommonCodeMapper ebizCommonCodeMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * ebizProductRisk  表初始化到redis
     *
     * @return
     */
    public int ebizProductRisk(String isDel) {
        System.out.println("=========ebizProductRisk开始时间:============" + new Date());
        Map<String, Object> map = new HashMap<>();
        if ("Y".equals(isDel)) {
            delRedis(EbizProductRisk.class);
        }
        int n = 0;
        EbizRedisLog redisLog = new EbizRedisLog();
        //risk_code riskcode,main_flag mainflag,stand_by_flag1 standbyflag1
        List<EbizProductRisk> ebizProductRisks = ebizProductRiskMapper.selectAll();
        n = ebizProductRisks.size();
        String key = EbizProductRisk.class.getName();
        for (EbizProductRisk ebizProductRisk : ebizProductRisks) {
            TreeMap<String, String> treeMap = new TreeMap<>();
            treeMap.put("sid", ebizProductRisk.getSid() + "");
            treeMap.put("riskcode", ebizProductRisk.getRiskCode());
            treeMap.put("mainflag", ebizProductRisk.getMainFlag());
            treeMap.put("standbyflag1", ebizProductRisk.getStandByFlag1());
            String keySet = key + ":" + createKeySet(treeMap);

            String json = objectToJson(ebizProductRisk);
            baseDateRedis.addSet(key, keySet);
            baseDateRedis.set(keySet, json);
        }
        System.out.println("=========ebizProductRisk结束时间:============" + new Date());
        try {
            Integer result = redisRecordLog(n, "Ebiz_Product_Risk", EbizProductRisk.class);
            logger.info(result);
        } catch (Exception e) {
            System.out.println("Ebiz_Product_Risk 表 redis 日志记录异常" + e.getMessage());
        }
        return n;
    }

    public int sysDict(String isDel) {
        System.out.println("=========sysDict开始时间:============" + new Date());
        if ("Y".equals(isDel)) {
            delRedis(SysDict.class);
        }
        int n = 0;
        List<SysDict> sysDicts = sysDictMapper.selectAll();
        n = sysDicts.size();
        String key = SysDict.class.getName();
        EbizRedisLog ebizRedisLog = new EbizRedisLog();
        for (SysDict sysDict : sysDicts) {
            TreeMap<String, String> treeMap = new TreeMap<>();
            treeMap.put("sid", sysDict.getSid());
            treeMap.put("remark", sysDict.getRemark());
            treeMap.put("value", sysDict.getValue());
            treeMap.put("type", sysDict.getType());
            String keySet = key + ":" + createKeySet(treeMap);
            String json = objectToJson(sysDict);

            baseDateRedis.addSet(key, keySet);
            baseDateRedis.set(keySet, json);
        }
        System.out.println("=========sysDict结束时间:============" + new Date());
        try {
            Integer result = redisRecordLog(n, "Sys_Dict", SysDict.class);
            logger.info(result);
        } catch (Exception e) {
            logger.info("Sys_Dict表 redis 日志记录异常" + e.getMessage());
        }
        return n;
    }

    public int ebizCommonCode(String isDel) {
        System.out.println("=========ebizCommonCode开始时间:============" + new Date());
        if ("Y".equals(isDel)) {
            delRedis(EbizCommonCode.class);
        }

        int n;
        String key = EbizCommonCode.class.getName();
        List<EbizCommonCode> ebizCommonCodes = ebizCommonCodeMapper.selectAll();
        n = ebizCommonCodes.size();
        for (EbizCommonCode ebizCommonCode : ebizCommonCodes) {
            TreeMap<String, String> treeMap = new TreeMap<>();
            treeMap.put("sid", ebizCommonCode.getSid() + "");
            treeMap.put("codetype", ebizCommonCode.getCodeType());
            treeMap.put("syscode", ebizCommonCode.getSysCode());

            String keySet = key + ":" + createKeySet(treeMap);
            String json = objectToJson(ebizCommonCode);

            baseDateRedis.addSet(key, keySet);
            baseDateRedis.set(keySet, json);
        }
        System.out.println("=========ebizCommonCode结束时间:============" + new Date());
        try {
            Integer result = redisRecordLog(n, "Ebiz_Common_Code", EbizCommonCode.class);
            logger.info(result);
        } catch (Exception e) {
            logger.info("Ebiz_Common_Code 表 redis 日志保存异常" + e.getMessage());
        }
        return n;
    }

    public int usreInfo(String isDel) {
        System.out.println("=========usreInfo开始时间:============" + new Date());
        if ("Y".equals(isDel)) {
            delRedis(UserInfo.class);
        }

        int n;
        String key = UserInfo.class.getName();
        List<UserInfo> userInfoList = userInfoMapper.selectUserInfoAll();
        n = userInfoList.size();
        for (UserInfo userInfo : userInfoList) {
            TreeMap<String, String> treeMap = new TreeMap<>();
            treeMap.put("sid", userInfo.getSid() + "");
            treeMap.put("userName", userInfo.getUserName());

            String keySet = key + ":" + createKeySet(treeMap);
            String json = objectToJson(userInfo);

            baseDateRedis.addSet(key, keySet);
            baseDateRedis.set(keySet, json);
        }
        System.out.println("=========usreInfo结束时间:============" + new Date());
        try {
            Integer result = redisRecordLog(n, "user_info", UserInfo.class);
            logger.info(result);
        } catch (Exception e) {
            logger.info("user_info 表 redis 日志保存异常" + e.getMessage());
        }
        return n;
    }

    /**
     * 以表为单位清空redis
     *
     * @param c
     */
    public void delRedis(Class c) {
        String key = c.getName();
        Set<String> set = baseDateRedis.getSet(key);
        //删除单个json
        for (String s : set) {
            baseDateRedis.delete(s);
        }
        //删除set
        baseDateRedis.delete(key);
    }

    /**
     * @param standard 标准
     * @param treeMap  被比较
     */
    TreeMap<String, Object> compareTreeMap(TreeMap<String, String> standard, TreeMap<String, Object> treeMap) {
        Set<String> keySet = standard.keySet();
        if (treeMap == null) {
            treeMap = new TreeMap<>();
            for (String key : keySet) {
                treeMap.put(key, null);
            }
        }
        for (String key : keySet) {
            if (!treeMap.containsKey(key)) {
                treeMap.put(key, null);
            }
        }
        return treeMap;
    }

    /**
     * @param properties key实体属性,value表字段
     * @return
     */
    public String createProperties(TreeMap<String, String> properties) {
        StringBuffer sb = new StringBuffer();
        Set<String> keySet = properties.keySet();
        for (String key : keySet) {
            sb.append(properties.get(key) + " " + key + ",");
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    /**
     * 保存rsdis码表日志
     *
     * @param ebizRedisLog
     * @return
     */
    public int saveRedisLog(EbizRedisLog ebizRedisLog) {
        ebizRedisLog.setIsDelete("N");
        ebizRedisLog.setModifyDate(new Date());
        String tableName = ebizRedisLog.getTableName().toLowerCase();
        ebizRedisLog.setTableName(tableName);
        EbizRedisLog ebizRedisLog1 = ebizRedisLogMapper.selectByTableName(tableName);
        if (ebizRedisLog1 != null) {
            ebizRedisLog.setCreateDate(ebizRedisLog1.getCreateDate());
            ebizRedisLog.setUpdateCount(ebizRedisLog1.getUpdateCount() + 1);
            ebizRedisLog.setSid(ebizRedisLog1.getSid());
            return ebizRedisLogMapper.updateByPrimaryKeySelective(ebizRedisLog);
        } else {
            ebizRedisLog.setUpdateCount(1);
            ebizRedisLog.setCreateDate(new Date());
            return ebizRedisLogMapper.insertSelective(ebizRedisLog);
        }
    }

    /**
     * 获得redis码表的数量
     *
     * @param c
     * @return
     */
    public int getRedisCount(Class c) {
        String key = c.getName();
        Set<String> keySet = baseDateRedis.getSet(key);
        int count = 0;
        for (String set : keySet) {
            String json = baseDateRedis.get(set);
            List pojoList = getPojoList(c, json);
            count += pojoList.size();
        }
        return count;
    }


    public static void main(String[] args) {
//        TreeMap<String, String> treeMap1 = new TreeMap();
//        treeMap1.put("bbb", "njb1");
//        treeMap1.put("appno", "123121");
//        treeMap1.put("ssdaad", "asda1");
//        TreeMap<String, String> treeMap2 = new TreeMap();
//        treeMap2.put("bbb", "njb2");
//        treeMap2.put("appno", "123122");
//        treeMap2.put("ssdaad", "asda2");
//        TreeMap<String, String> treeMap3 = new TreeMap();
//        treeMap3.put("bbb", "njb3");
//        treeMap3.put("appno", "123123");
//        treeMap3.put("ssdaad", "asda3");
//        TreeMap<String, String> treeMap4 = new TreeMap();
//        treeMap4.put("bbb", "njb3");
//        treeMap4.put("appno", "123124");
//        treeMap4.put("ssdaad", "asda4");
//        Set<String> set = new HashSet<>();
//        set.add(createKeySet(treeMap1));
//        set.add(createKeySet(treeMap2));
//        set.add(createKeySet(treeMap3));
//        set.add(createKeySet(treeMap4));
//        // String
//        Set<String> keySet = getKeySet(set, "bBb:njb3:ssdaad:asda3");
//        System.out.println(keySet.toString());
    }

    public List getPojoByKey(Class c, String key) {
        String s = baseDateRedis.get(key);
        if (StringUtils.isEmpty(s)) {
            return null;
        } else {
            if (s.startsWith("[")) {
                List list = JSONObject.parseArray(s, c);
                return list;
            } else {
                Object o = JSONObject.parseObject(s, c);
                List list = new ArrayList();
                list.add(o);
                return list;
            }
        }
    }

    /**
     * 筛选查询条件筛选需要的key
     *
     * @param set
     * @param where
     * @return
     */
    private static Set<String> getKeySet(Set<String> set, String where) {
        String[] wh = where.split(":");
        Set<String> set1 = new HashSet<>();
        for (String s : set) {
            s += ":";
            for (int i = 0; i < wh.length; i++) {
                if (!s.contains(wh[i].toLowerCase() + ":" + wh[i + 1] + ":")) {
                    break;
                }
                i++;
                if (i == wh.length - 1) {
                    s = StringUtils.removeEnd(s, ":");
                    set1.add(s);
                }
            }
        }
        return set1;
    }

    String createKeySet(TreeMap treeMap) {
        StringBuffer ss = new StringBuffer();
        Set<String> set = treeMap.keySet();
        for (String s : set) {
            ss.append(s.toLowerCase() + ":" + treeMap.get(s) + ":");
        }
        return ss.toString().substring(0, ss.toString().length() - 1);
    }

    private String objectToJson(Object ebizProductPlanMain) {
        return JSON.toJSONString(ebizProductPlanMain);
    }

    /**
     * 刪除redis中的码表数据
     *
     * @param c
     * @param where
     */
    public void delPojo(Class c, String where) {
        String key = c.getName();
        Set<String> set = baseDateRedis.getSet(key);
        Set<String> keySet = getKeySet(set, where);
        for (String k : keySet) {
            baseDateRedis.delSet(key, k);
            baseDateRedis.delete(k);
        }

    }

    public void setPojo(Object o, TreeMap<String, String> treeMap) {
        String key = o.getClass().getName();
        String keySet = key + ":" + createKeySet(treeMap);
        String json = objectToJson(o);
        baseDateRedis.addSet(key, keySet);
        baseDateRedis.set(keySet, json);
    }

    public EbizRedisLog getRedisInfo(String tableName) {
        return ebizRedisLogMapper.selectByTableName(tableName);
    }

    public Integer redisRecordLog(Integer counts, String tableName, Object object) {
        EbizRedisLog ebizRedisLog = new EbizRedisLog();
        int redisCount = getRedisCount(object.getClass());
        ebizRedisLog.setRedisCount(redisCount);
        ebizRedisLog.setDbCount(counts);
        ebizRedisLog.setTableName(tableName);
        Integer result = saveRedisLog(ebizRedisLog);
        return result;
    }
}

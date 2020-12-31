package com.xiongfk.springBooting.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiongfk.springBooting.model.EbizRiskamntDaily;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * BaseDateRedisUtil
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2019/7/14 21:31
 * @Version 1.0
 **/
@Component
public class BaseDateRedisUtil {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    protected final Logger logger = LogManager.getLogger(this.getClass());
    private final String twoRediKey = "TWO_REDIS_KEY";

    private static HashMap<String, Object> twoCache = new HashMap<>();

    /**
     * 筛选符合条件的所有结果
     *
     * @param c
     * @param where
     * @return
     */
    public List getPojo(Class c, String where) {
        long ss = System.currentTimeMillis();
        String key = c.getName();
        long getsetS = System.currentTimeMillis();
        String mapKey = key + ":" + where;
        String pojosJson = get(mapKey);
        if (StringUtils.isNotEmpty(pojosJson)) {
            logger.info("===================twoRedis取值用时" + (System.currentTimeMillis() - getsetS));
            return getPojoList(c, pojosJson);
        }
        Set<String> set = getSet(key);
        logger.info("===================getSet用时" + (System.currentTimeMillis() - getsetS));
        long getkeySetS = System.currentTimeMillis();
        Set<String> keySet = getKeySet(set, where);  //筛选后结果
        logger.info("===================getkeySet用时" + (System.currentTimeMillis() - getkeySetS));
        List list = new ArrayList();
        for (String k1 : keySet) {
            logger.info("=============key========" + k1);
            String value = get(k1);
            // json 转 list
            list.addAll(getPojoList(c, value));
        }
        logger.info("===================getPojo用时" + (System.currentTimeMillis() - ss));
        //保存到二级缓存
        ss = System.currentTimeMillis();
        if (!list.isEmpty()) {
            String jsonString = JSON.toJSONString(list);
            putPojoList(mapKey, jsonString);
        }
        logger.info("===================存入二级缓存消耗时间:" + (System.currentTimeMillis() - ss));
        return list;
    }

    public List<Map<String, Object>> getPojo(String className, String where) {
        long ss = System.currentTimeMillis();
        long getsetS = System.currentTimeMillis();
        Class<Map> c = Map.class;
        List<Map<String, Object>> list = new ArrayList<>();
        String mapKey = "\\xAC\\xED\\x00\\x05t\\x00:" + className + ":" + where;
        String pojosJson = get(mapKey);
        if (StringUtils.isNotEmpty(pojosJson)) {
            logger.info("===================twoRedis取值用时" + (System.currentTimeMillis() - getsetS));
            return getPojoList(c, pojosJson);
        }
        Set<String> set = getSet(className);
        logger.info("===================getSet用时" + (System.currentTimeMillis() - getsetS));
        long getkeySetS = System.currentTimeMillis();
        Set<String> keySet = getKeySet(set, where);  //筛选后结果
        logger.info("===================getkeySet用时" + (System.currentTimeMillis() - getkeySetS));
        for (String k1 : keySet) {
            String value = get(k1);
            // json 转 list
            list.addAll(getPojoList(c, value));
        }
        logger.info("===================getPojo用时" + (System.currentTimeMillis() - ss));
        //保存到二级缓存
        ss = System.currentTimeMillis();
        if (!list.isEmpty()) {
            String jsonString = JSON.toJSONString(list);
            putPojoList(mapKey, jsonString);
        }
        logger.info("===================存入二级缓存消耗时间:" + (System.currentTimeMillis() - ss));
        return list;
    }

    //将查出的数据再次封装放到redis
    private void putPojoList(String mapKey, String jsonString) {
        setSet(twoRediKey, mapKey);
        set(mapKey, jsonString);
    }

    /**
     * 清理redis二次缓存
     */
    public void cleanTwoRedis() {
        long ss = System.currentTimeMillis();
        Set<String> set = getSet(twoRediKey);
        if (set != null && set.size() > 0) {
            for (String s : set) {
                logger.info("清理redis 二次缓存=========" + s);
                delete(s);
                delSet(twoRediKey, s);
            }
        }
        logger.info("==========清理redis 二次缓存耗时=============" + (System.currentTimeMillis() - ss));
    }

    private void delSet(String twoRediKey, String... s) {
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
        setOperations.remove(twoRediKey, s);
    }

    /**
     * json 转 list
     *
     * @param c
     * @param pojosJson
     * @return
     */
    public List getPojoList(Class c, String pojosJson) {
        List list = new ArrayList();
        if (pojosJson.startsWith("{")) {
            Object o = JSONObject.parseObject(pojosJson, c);
            list.add(o);
        } else if (pojosJson.startsWith("[")) {
            List list1 = JSONObject.parseArray(pojosJson, c);
            list.addAll(list1);
        }
        return list;
    }


    public void set(String key, String value) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
    }

    /**
     * 获得一个结果
     *
     * @param c
     * @param where
     * @return
     */
    public List getOnePojo(Class c, String where) {
//        String key = c.getName();
//        String mapKey = key + ":" + where;
//        if (twoCache.get(mapKey) != null)
//            return (List)twoCache.get(mapKey);
//        Set<String> set = getSet(key);
//        Set<String> keySet = getKeySet(set, where);  //筛选后结果
//        List list = new ArrayList();
//        if (keySet.isEmpty()) {
//            return list;
//        }
//        Iterator<String> iterator = keySet.iterator();
//        String next = iterator.next();
//        String value = get(next);
//        if (value.startsWith("{")) {
//            Object o = JSONObject.parseObject(value, c);
//            list.add(o);
//        } else if (value.startsWith("[")) {
//            List list1 = JSONObject.parseArray(value, c);
//            list.addAll(list1);
//        }
        return getPojo(c, where);
    }

    /**
     * 通过key直接获得实体
     *
     * @param c
     * @param treeMap
     * @return
     */
    public List getPojoByKey(Class c, TreeMap<String, String> treeMap) {
        String key = c.getName() + ":" + getKey(treeMap);
        String s = get(key);
        if (StringUtils.isEmpty(s)) {
            return null;
        } else {
            if (s.startsWith("[")) {
                List list = JSONObject.parseArray(s, c);
                //  twoCache.put(key, list);
                return list;
            } else {
                Object o = JSONObject.parseObject(s, c);
                List list = new ArrayList();
                list.add(o);
                // twoCache.put(key, list);
                return list;
            }
        }
    }

    private String getKey(TreeMap<String, String> treeMap) {
        StringBuffer ss = new StringBuffer();
        Set<String> set = treeMap.keySet();
        for (String s : set) {
            ss.append(s.toLowerCase() + ":" + treeMap.get(s) + ":");
        }
        return ss.toString().substring(0, ss.toString().length() - 1);
    }

    public Set<String> getSet(String key) {
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
        return setOperations.members(key);
    }

    public long setSet(String key, String... values) {
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
        return setOperations.add(key, values);
    }

    public void hmset(String key, Map<String, String> map) {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        hashOperations.putAll(key, map);
    }

    public void hmset(String key, String item, String value) {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        hashOperations.put(key, item, value);
    }

    public String hget(String key, String item) {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        return hashOperations.get(key, item);
    }

    public void hdel(String key, String item) {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(key, item);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 筛选查询条件筛选需要的key
     *
     * @param set
     * @param where
     * @return
     */
    private Set<String> getKeySet(Set<String> set, String where) {
        String[] wh = where.split(":");
        String[] kk;
        if (wh.length % 2 != 0) {
            kk = new String[wh.length + 1];
            System.arraycopy(wh, 0, kk, 0, wh.length);
            kk[wh.length] = "";
            wh = kk;
        }
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

    public String get(String key) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    public Object getRedisYWPojo(Class c, Map<String, Object> contMap, Integer sid) {
        Set<String> keySet = contMap.keySet();
        for (String key : keySet) {
            if (c.getName().equals(key)) {
                String json = contMap.get(key).toString();
                if (json.startsWith("[")) {
                    List list = JSONObject.parseArray(json, c);
                    return list;
                } else if (json.startsWith("{")) {
                    Object o = JSONObject.parseObject(json, c);
                    return o;
                }
            }
        }
        String key = c.getName() + "_" + sid;
        if (contMap.get(key) == null)
            return null;
        String json = contMap.get(key).toString();
        if (StringUtils.isBlank(json)) {
            return null;
        } else {
            if (json.startsWith("[")) {
                List list = JSONObject.parseArray(json, c);
                return list;
            } else if (json.startsWith("{")) {
                Object o = JSONObject.parseObject(json, c);
                return o;
            }
            return null;
        }
    }

    /**
     * 根据投保单号删除该保单风险保额
     *
     * @param appNo 投保单号
     */
    public void delContRedis(String appNo) {
        //通过投保单号查询本单本单该客户五要素
        String fiveElement = hget(EbizRiskamntDaily.class.getName() + "_appno", appNo);
        if (StringUtils.isNotBlank(fiveElement)) {
            String riskAmntStr = get(fiveElement);
            List<EbizRiskamntDaily> newDailies = new ArrayList<>();
            if (StringUtils.isNotBlank(riskAmntStr)) {
                List<EbizRiskamntDaily> dailies = JSONObject.parseArray(riskAmntStr, EbizRiskamntDaily.class);
                if (null != dailies && !CollectionUtils.isEmpty(dailies)) {
                    for (EbizRiskamntDaily riskamntDaily : dailies) {
                        if (appNo.equals(riskamntDaily.getApplyNo())) {
                            continue;
                        }
                        newDailies.add(riskamntDaily);
                    }
                    //更新该客户本单风险保额数据
                    set(fiveElement, JSONObject.toJSONString(newDailies));
                }
            }
            //删除索引五要素的投保单号key
            hdel(EbizRiskamntDaily.class.getName() + "_appno", appNo);
        }
        //删除保单数据
        delete("APP_" + appNo);
    }
}
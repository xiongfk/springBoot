package com.xiongfk.springBooting.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
* 类描述 TODO redis操作工具类
* @author xiongfk
* @date 2020/2/19
* @param
* @return
*/
@Service
public class RedisUtils {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private String userKey = "userKey";

    /**
     * set类型获得  value
     * @param key
     * @return
     */
    public Set<String> getSet(String key) {
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
        return setOperations.members(key);
    }

    /**
     * set类型  set值
     * @param key
     * @param values
     * @return
     */
    public long addSet(String key, String... values) {
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
        return setOperations.add(key, values);
    }

    /**
     *key-value   存储
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
        redisTemplate.expire(key,60, TimeUnit.MINUTES);//设置过期时间为1小时
    }


    /**
     * 存储自带过期时间限制的key-value
     * @param key
     * @param value
     */
    public void setKVByTimes(String key, String value,Long timeout) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
        redisTemplate.expire(key,timeout, TimeUnit.SECONDS);//设置过期时间
    }
    /**
     * key-value 取值
     * @param key
     * @return
     */
   public String get(String key) {
       ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
       return valueOperations.get(key);
   }

    /**
    * 方法描述 TODO 计数器(自增)
    * @author xiongfk
    * @date 2020/2/25
    * @param key01,key02
    * @return java.lang.Object
    */
    public Object counter(String key01,String key02) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key01, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.getAndIncrement();
        increment = increment+1;
        //第一个计数器数量达到1000使用第二个计数器
        if(0!=increment && increment%10==0){
            entityIdCounter = new RedisAtomicLong(key02, redisTemplate.getConnectionFactory());
            Long increments = entityIdCounter.getAndIncrement()+1;
            String key = key01 + increments.toString();
            //存储key避免重启数据丢失
            delete(userKey);
            set(userKey,key);
            return key;
        }else{
            return increment;
        }
    }

    /**
    * 方法描述 TODO 计数器(自减)
    * @author xiongfk
    * @date 2020/2/26
    * @param key
    * @return java.lang.Long
    */
    public Long counter(String key) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.getAndIncrement();
        return increment;
    }

    /**
    * 方法描述 TODO 模糊查询
    * @author xiongfk
    * @date 2020/2/25
    * @param key
    * @return java.util.Set<java.lang.String>
    */
    public Set<String> keys(String key){
        return stringRedisTemplate.keys("*" + key + "*");
    }

    /**
     * redis刪除
     * @param key
     */
   public boolean delete(String key) {
       return redisTemplate.delete(key);
   }


    public void delSet(String key, String... k) {
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
        setOperations.remove(key, k);
    }

    public void addMap(String key, Object keySet, Object size) {
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        hashOperations.put(key, keySet, size);

    }

    public Map<String, Object> getMap(String keyCount) {
        HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
        return hashOperations.entries(keyCount);
    }

    public void delMap(String keyCount, String key) {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(keyCount, key);
    }

    /**
    * 方法描述 TODO 存储hash map
    * @author xiongfk
    * @date 2020/3/12
    * @param key, mapValue
    * @return void
    */
    public void setHash(String key,Map<String,Object> mapValue){
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.putAll(key,mapValue);
    }

    /**
    * 方法描述 TODO 存储hash 对象
    * @author xiongfk
    * @date 2020/3/12
    * @param objKey, hashKey, value
    * @return void
    */
    public void setHash(String objKey,String hashKey,String value){
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.put(objKey,hashKey,value);
    }

    /**
    * 方法描述 TODO 获取hash对象
    * @author xiongfk
    * @date 2020/3/12
    * @param objKey
    * @return java.util.Map<java.lang.String,java.lang.Object>
    */
    public Map<String,Object> getHash(String objKey){
        HashOperations hashOperations = redisTemplate.opsForHash();
        Map<String,Object> map = hashOperations.entries(objKey);
        return map;
    }

    /**
    * 方法描述 TODO 获取某个具体hash
    * @author xiongfk
    * @date 2020/3/12
    * @param objKey, hashKey
    * @return java.lang.String
    */
    public String getHash(String objKey,String hashKey){
        HashOperations hashOperations = redisTemplate.opsForHash();
        Object obj = hashOperations.get(objKey,hashKey);
        if(null!=obj){
            return obj.toString();
        }
        return null;
    }

    /**
    * 方法描述 TODO 删除某个具体的hash
    * @author xiongfk
    * @date 2020/3/12
    * @param objKey, hashKey]
    * @return void
    */
    public void delHash(String objKey,String hashKey){
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(objKey,hashKey);
    }

    /**
    * 方法描述 TODO 删除整个hash
    * @author xiongfk
    * @date 2020/3/12
    * @param objKey
    * @return void
    */
    public void delHash(String objKey){
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(objKey);
    }

    /**
    * 方法描述 TODO 加锁
    * @author xiongfk
    * @date 2020/3/12
    * @param key, value
    * @return boolean
    */
    public boolean lock(String key, String value) {
        //setIfAbsent相当于jedis中的setnx，如果能赋值就返回true，如果已经有值了，就返回false
        //即：在判断这个key是不是第一次进入这个方法
        if (stringRedisTemplate.opsForValue().setIfAbsent(key, value)) {
            stringRedisTemplate.expire(key,5,TimeUnit.SECONDS);//设置时长为5秒避免出现异常造成死锁或未及时解锁占用内存
            //第一次，即：这个key还没有被赋值的时候
            return true;
        }
        //获取上一次的时间戳
        String current_value = stringRedisTemplate.opsForValue().get(key);
        // 判断是否为空，不为空的情况下，如果被其他线程设置了值，则第二个条件判断是过不去的
        if (org.apache.commons.lang.StringUtils.isNotEmpty(current_value) && Long.parseLong(current_value) < System.currentTimeMillis()) {
            String old_value = stringRedisTemplate.opsForValue().getAndSet(key, value);//②
            // 获取上一个锁到期时间，并设置现在的锁到期时间，
            // 只有一个线程才能获取上一个线程的设置时间，因为jedis.getSet是同步的（原子的）
            if (StringUtils.isNotEmpty(old_value) && current_value.equals(old_value)) {
                // 如过这个时候，多个线程恰好都到了这里，但是只有一个线程的设置值和当前值相同，他才有权利获取锁
                return true;
            }
        }
        return false;
    }

    /**
    * 方法描述 TODO 解锁
    * @author xiongfk
    * @date 2020/3/12
    * @param key, value
    * @return void
    */
    public void unlock(String key, String value) {
        try {
            if (stringRedisTemplate.opsForValue().get(key).equals(value)) {
                stringRedisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

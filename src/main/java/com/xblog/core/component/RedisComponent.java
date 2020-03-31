package com.xblog.core.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisComponent
 * @Description: Redis操作组件
 * @Author hungkuei
 * @Date 2020/3/23
 * @Version V1.0
 **/
@Component
public class RedisComponent {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    //=============================common============================
    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param timeout 时间(秒)
     * @return
     */
    public boolean expire(String key, Long timeout) {
        try {
            if (timeout != null) {
                redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    //============================ String =============================
    /**
     * String无失效时间
     * @param key
     * @param value
     * @return
     */
    public boolean setForValue(String key, String value){
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 普通缓存放入并设置时间
     * @param key
     * @param value
     * @param timeout
     * @return
     */
    public boolean setForValue(String key, String value, Long timeout){
        try {
            if (timeout != null){
                redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
                return true;
            }
            setForValue(key, value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * String缓存获取
     * @param key
     * @return
     */
    public String getForValue(String key){
        return key == null ? null : stringRedisTemplate.opsForValue().get(key);
    }

    //================================Map=================================
    /**
     * HashSet
     *
     * @param key 键
     * @param value 对应多个键值
     * @return true 成功 false 失败
     */
    public boolean setForHash(String key, Map<String, Object> value){
        try{
            redisTemplate.opsForHash().putAll(key, value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * HashSet 并设置时间
     * @param key 键
     * @param value 对应多个键值
     * @param timeout 时间(秒)
     * @return true成功 false失败
     */
    public boolean setForHash(String key, Map<String, Object> value, Long timeout){
        try{
            redisTemplate.opsForHash().putAll(key, value);
            if (timeout != null){
                expire(key, timeout);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public Object getForHash(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object, Object> getForHash(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    //================================List=================================

    /**
     * 获取list缓存的内容
     * @param key 键
     * @param start 开始
     * @param end 结束 0 到 -1代表所有值
     * @return
     */

    public List<Object> getForList(String key, long start, long end){
        try{
            return redisTemplate.opsForList().range(key, start, end);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取list缓存的所有内容
     * @param key 键
     * @return
     */
    public List<?> getForListAll(String key){
        try{
            return redisTemplate.opsForList().range(key, 0, -1);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取list缓存的长度
     * @param key
     * @return
     */
    public Long getForListSize(String key){
        try {
            return redisTemplate.opsForList().size(key);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @return
     */
    public boolean setForList(String key, Collection value){
        try {
            redisTemplate.opsForList().leftPushAll(key, value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存并设置失效时间
     * @param key
     * @param value
     * @param timeout
     * @return
     */
    public boolean setForList(String key, List<Object> value, Long timeout){
        try {
            redisTemplate.opsForList().leftPushAll(key, value);
            if (timeout != null){
                expire(key, timeout);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}

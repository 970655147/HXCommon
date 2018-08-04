package com.hx.common.cache;


import com.hx.common.interf.common.Page;
import com.hx.common.interf.common.Result;

import java.util.List;

/**
 * 提供缓存相关服务
 *
 * @author Jerry.X.He
 * @date 2018/7/12 11:18
 */
public interface CacheService {

    /**
     * 向缓存中添加 key, value
     *
     * @param key   key
     * @param value value
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    String set(String key, String value);

    /**
     * 向缓存中添加 key, value, 带上过期时间
     *
     * @param key    key
     * @param value  value
     * @param expire expire
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    String set(String key, String value, int expire);

    /**
     * 给定的 key 缓存的数据 原子累增1
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long incr(String key);

    /**
     * 给定的 key 缓存的数据 原子累减1
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long decr(String key);

    /**
     * 给定的 key 缓存的数据 原子累增inc
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long incrBy(String key, long inc);

    /**
     * 给定的 key 缓存的数据 原子累减inc
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long decrBy(String key, long inc);


    /**
     * 给定的 key 缓存的数据 原子累增1, 带上过期时间
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long incr(String key, int expire);

    /**
     * 给定的 key 缓存的数据 原子累减1, 带上过期时间
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long decr(String key, int expire);

    /**
     * 给定的 key 缓存的数据 原子累增inc, 带上过期时间
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long incrBy(String key, long inc, int expire);

    /**
     * 给定的 key 缓存的数据 原子累减inc, 带上过期时间
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long decrBy(String key, long inc, int expire);

    /**
     * 获取给定的 key 对应的缓存的值
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    String get(String key);

    /**
     * 设置给定的 key 对应的过期时间
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long expire(String key, int expire);

    /**
     * 判断给定的 key 是否存在
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    boolean exists(String key);

    /**
     * 删除给定的 key
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long remove(String key);

    // --------------------------------------------------- 带上前缀 ----------------------------------------------------

    /**
     * 向缓存中添加 key, value
     *
     * @param key   key
     * @param value value
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    String set(String prefix, String key, String value);

    /**
     * 向缓存中添加 key, value, 带上过期时间
     *
     * @param key    key
     * @param value  value
     * @param expire expire
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    String set(String prefix, String key, String value, int expire);

    /**
     * 给定的 key 缓存的数据 原子累增1
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long incr(String prefix, String key);

    /**
     * 给定的 key 缓存的数据 原子累减1
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long decr(String prefix, String key);

    /**
     * 给定的 key 缓存的数据 原子累增inc
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long incrBy(String prefix, String key, long inc);

    /**
     * 给定的 key 缓存的数据 原子累减inc
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long decrBy(String prefix, String key, long inc);


    /**
     * 给定的 key 缓存的数据 原子累增1, 带上过期时间
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long incr(String prefix, String key, int expire);

    /**
     * 给定的 key 缓存的数据 原子累减1, 带上过期时间
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long decr(String prefix, String key, int expire);

    /**
     * 给定的 key 缓存的数据 原子累增inc, 带上过期时间
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long incrBy(String prefix, String key, long inc, int expire);

    /**
     * 给定的 key 缓存的数据 原子累减inc, 带上过期时间
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long decrBy(String prefix, String key, long inc, int expire);

    /**
     * 获取给定的 key 对应的缓存的值
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    String get(String prefix, String key);

    /**
     * 设置给定的 key 对应的过期时间
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long expire(String prefix, String key, int expire);

    /**
     * 判断给定的 key 是否存在
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    boolean exists(String prefix, String key);

    /**
     * 删除给定的 key
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long remove(String prefix, String key);

    // --------------------------------------------------- 转换相关 ----------------------------------------------------

    /**
     * 根据 key 获取缓存的字符串, 转换为 给定的 clazz 对象
     *
     * @param key   key
     * @param clazz clazz
     * @return
     * @author Jerry.X.He
     * @date 2018/7/16 15:46
     */
    <T> T getOrNull(String key, Class<T> clazz);

    /**
     * 根据 key 获取缓存的字符串, 转换为 给定的 clazz 对象
     *
     * @param prefix prefix
     * @param key    key
     * @param clazz  clazz
     * @return
     * @author Jerry.X.He
     * @date 2018/7/16 15:46
     */
    <T> T getOrNull(String prefix, String key, Class<T> clazz);

    /**
     * 根据 key 获取缓存的字符串, 转换为 给定的 clazz 对象
     *
     * @param key   key
     * @param clazz clazz
     * @return
     * @author Jerry.X.He
     * @date 2018/7/16 15:46
     */
    <T> List<T> getListOrNull(String key, Class<T> clazz);

    /**
     * 根据 key 获取缓存的字符串, 转换为 给定的 clazz 对象
     *
     * @param prefix prefix
     * @param key    key
     * @param clazz  clazz
     * @return
     * @author Jerry.X.He
     * @date 2018/7/16 15:46
     */
    <T> List<T> getListOrNull(String prefix, String key, Class<T> clazz);

    /**
     * 根据 key 获取缓存的字符串, 转换为 给定的 clazz 对象
     *
     * @param key   key
     * @param clazz clazz
     * @return
     * @author Jerry.X.He
     * @date 2018/7/16 15:46
     */
    <T> Page<T> getPageOrNull(String key, Class<T> clazz);

    /**
     * 根据 key 获取缓存的字符串, 转换为 给定的 clazz 对象
     *
     * @param prefix prefix
     * @param key    key
     * @param clazz  clazz
     * @return
     * @author Jerry.X.He
     * @date 2018/7/16 15:46
     */
    <T> Page<T> getPageOrNull(String prefix, String key, Class<T> clazz);

    /**
     * 根据 key 获取缓存的字符串, 转换为 给定的 clazz 对象
     *
     * @param key   key
     * @param clazz clazz
     * @return
     * @author Jerry.X.He
     * @date 2018/7/16 15:46
     */
    <T> Result getResultOrNull(String key, Class<T> clazz);

    /**
     * 根据 key 获取缓存的字符串, 转换为 给定的 clazz 对象
     *
     * @param prefix prefix
     * @param key    key
     * @param clazz  clazz
     * @return
     * @author Jerry.X.He
     * @date 2018/7/16 15:46
     */
    <T> Result getResultOrNull(String prefix, String key, Class<T> clazz);

    /**
     * 根据 key 获取缓存的字符串, 转换为 给定的 clazz 对象
     *
     * @param key   key
     * @param clazz clazz
     * @return
     * @author Jerry.X.He
     * @date 2018/7/16 15:46
     */
    <T> Result getListResultOrNull(String key, Class<T> clazz);

    /**
     * 根据 key 获取缓存的字符串, 转换为 给定的 clazz 对象
     *
     * @param prefix prefix
     * @param key    key
     * @param clazz  clazz
     * @return
     * @author Jerry.X.He
     * @date 2018/7/16 15:46
     */
    <T> Result getListResultOrNull(String prefix, String key, Class<T> clazz);

    /**
     * 根据 key 获取缓存的字符串, 转换为 给定的 clazz 对象
     *
     * @param key   key
     * @param clazz clazz
     * @return
     * @author Jerry.X.He
     * @date 2018/7/16 15:46
     */
    <T> Result getPageResultOrNull(String key, Class<T> clazz);

    /**
     * 根据 key 获取缓存的字符串, 转换为 给定的 clazz 对象
     *
     * @param prefix prefix
     * @param key    key
     * @param clazz  clazz
     * @return
     * @author Jerry.X.He
     * @date 2018/7/16 15:46
     */
    <T> Result getPageResultOrNull(String prefix, String key, Class<T> clazz);

}

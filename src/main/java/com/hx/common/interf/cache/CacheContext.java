package com.hx.common.interf.cache;

/**
 * 缓存相关的 Context
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 6/8/2017 7:46 PM
 */
public interface CacheContext<K, V> {

    /**
     * 获取关联的 Cache
     *
     * @author Jerry.X.He
     * @date 6/8/2017 7:46 PM
     * @since 1.0
     */
    Cache<K, V> getCache();

    /**
     * 获取关联的 cacheEntry
     *
     * @author Jerry.X.He
     * @date 6/8/2017 7:46 PM
     * @since 1.0
     */
    CacheEntryFacade<K, V> cacheEntry();

}

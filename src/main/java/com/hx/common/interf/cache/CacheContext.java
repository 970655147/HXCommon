package com.hx.common.interf.cache;

/**
 * ������ص� Context
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 6/8/2017 7:46 PM
 */
public interface CacheContext<K, V> {

    /**
     * ��ȡ������ Cache
     *
     * @author Jerry.X.He
     * @date 6/8/2017 7:46 PM
     * @since 1.0
     */
    Cache<K, V> getCache();

    /**
     * ��ȡ������ cacheEntry
     *
     * @author Jerry.X.He
     * @date 6/8/2017 7:46 PM
     * @since 1.0
     */
    CacheEntryFacade<K, V> cacheEntry();

}

package com.hx.common.interf.cache;

/**
 * CacheEntry的factory
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 4/13/2017 12:13 PM
 */
public interface CacheEntryFactory {

    /**
     * 根据给定的kv, 创建一个CacheEntry
     *
     * @param key   给定的key
     * @param value 给定的value
     * @return the cacheEntry that created by this factory
     * @author Jerry.X.He
     * @date 4/13/2017 12:14 PM
     * @since 1.0
     */
    <K, V> CacheEntry<K, V> create(K key, V value, long ttl);

    /**
     * 根据给定的cacheEntry, 创建一个cacheEntryFacade
     *
     * @param entry   给定的cacheEntry
     * @return the cacheEntry that created by this factory
     * @author Jerry.X.He
     * @date 4/13/2017 12:14 PM
     * @since 1.0
     */
    <K, V> CacheEntryFacade<K, V> createFacade(CacheEntry<K, V> entry);

    /**
     * 创建 CacheContext
     *
     * @param cache cache
     * @param entry entry
     * @return
     * @author Jerry.X.He
     * @date 6/8/2017 8:21 PM
     * @since 1.0
     */
    <K, V> CacheContext<K, V> createContext(Cache<K, V> cache, CacheEntry<K, V> entry);

}

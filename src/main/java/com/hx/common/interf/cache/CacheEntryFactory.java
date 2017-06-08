package com.hx.common.interf.cache;

/**
 * CacheEntry��factory
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 4/13/2017 12:13 PM
 */
public interface CacheEntryFactory {

    /**
     * ���ݸ�����kv, ����һ��CacheEntry
     *
     * @param key   ������key
     * @param value ������value
     * @return the cacheEntry that created by this factory
     * @author Jerry.X.He
     * @date 4/13/2017 12:14 PM
     * @since 1.0
     */
    <K, V> CacheEntry<K, V> create(K key, V value, long ttl);

    /**
     * ���ݸ�����cacheEntry, ����һ��cacheEntryFacade
     *
     * @param entry   ������cacheEntry
     * @return the cacheEntry that created by this factory
     * @author Jerry.X.He
     * @date 4/13/2017 12:14 PM
     * @since 1.0
     */
    <K, V> CacheEntryFacade<K, V> createFacade(CacheEntry<K, V> entry);

    /**
     * ���� CacheContext
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

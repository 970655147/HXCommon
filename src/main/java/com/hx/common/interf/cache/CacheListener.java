package com.hx.common.interf.cache;

/**
 * 监听 Cache 相关的事件, 并处理
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 6/8/2017 7:43 PM
 */
public interface CacheListener<K, V> {

    /**
     * 监听添加数据之前
     *
     * @param context context
     * @return
     * @author Jerry.X.He
     * @date 6/8/2017 8:00 PM
     * @since 1.0
     */
    void beforeGet(CacheContext<K, V> context);

    /**
     * 监听添加数据之后
     *
     * @param context context
     * @return
     * @author Jerry.X.He
     * @date 6/8/2017 8:00 PM
     * @since 1.0
     */
    void afterHitted(CacheContext<K, V> context);


    /**
     * 监听添加数据之前
     *
     * @param context context
     * @return
     * @author Jerry.X.He
     * @date 6/8/2017 8:00 PM
     * @since 1.0
     */
    void beforeAdd(CacheContext<K, V> context);

    /**
     * 监听添加数据之后
     *
     * @param context context
     * @return
     * @author Jerry.X.He
     * @date 6/8/2017 8:00 PM
     * @since 1.0
     */
    void afterAdd(CacheContext<K, V> context);

    /**
     * 监听修改数据之前
     *
     * @param context context
     * @return
     * @author Jerry.X.He
     * @date 6/8/2017 8:00 PM
     * @since 1.0
     */
    void beforeUpdate(CacheContext<K, V> context);

    /**
     * 监听修改数据之后
     *
     * @param context context
     * @return
     * @author Jerry.X.He
     * @date 6/8/2017 8:00 PM
     * @since 1.0
     */
    void afterUpdate(CacheContext<K, V> context);

    /**
     * 监听删除数据之前
     *
     * @param context context
     * @return
     * @author Jerry.X.He
     * @date 6/8/2017 8:00 PM
     * @since 1.0
     */
    void beforeEvict(CacheContext<K, V> context);

    /**
     * 监听删除数据之后
     *
     * @param context context
     * @return
     * @author Jerry.X.He
     * @date 6/8/2017 8:00 PM
     * @since 1.0
     */
    void afterEvict(CacheContext<K, V> context);

    /**
     * 监听 clear数据之前
     *
     * @param context context
     * @return
     * @author Jerry.X.He
     * @date 6/8/2017 8:00 PM
     * @since 1.0
     */
    void beforeClear(CacheContext<K, V> context);

    /**
     * 监听 clear数据之后
     *
     * @param context context
     * @return
     * @author Jerry.X.He
     * @date 6/8/2017 8:00 PM
     * @since 1.0
     */
    void afterClear(CacheContext<K, V> context);

    /**
     * 监听 destroy数据之前
     *
     * @param context context
     * @return
     * @author Jerry.X.He
     * @date 6/8/2017 8:00 PM
     * @since 1.0
     */
    void beforeDestroy(CacheContext<K, V> context);

    /**
     * 监听 destroy数据之后
     *
     * @param context context
     * @return
     * @author Jerry.X.He
     * @date 6/8/2017 8:00 PM
     * @since 1.0
     */
    void afterDestroy(CacheContext<K, V> context);

}

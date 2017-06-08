package com.hx.common.interf.cache;

/**
 * ���� Cache ��ص��¼�, ������
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 6/8/2017 7:43 PM
 */
public interface CacheListener<K, V> {

    /**
     * �����������֮ǰ
     *
     * @param context context
     * @return
     * @author Jerry.X.He
     * @date 6/8/2017 8:00 PM
     * @since 1.0
     */
    void beforeGet(CacheContext<K, V> context);

    /**
     * �����������֮��
     *
     * @param context context
     * @return
     * @author Jerry.X.He
     * @date 6/8/2017 8:00 PM
     * @since 1.0
     */
    void afterHitted(CacheContext<K, V> context);


    /**
     * �����������֮ǰ
     *
     * @param context context
     * @return
     * @author Jerry.X.He
     * @date 6/8/2017 8:00 PM
     * @since 1.0
     */
    void beforeAdd(CacheContext<K, V> context);

    /**
     * �����������֮��
     *
     * @param context context
     * @return
     * @author Jerry.X.He
     * @date 6/8/2017 8:00 PM
     * @since 1.0
     */
    void afterAdd(CacheContext<K, V> context);

    /**
     * �����޸�����֮ǰ
     *
     * @param context context
     * @return
     * @author Jerry.X.He
     * @date 6/8/2017 8:00 PM
     * @since 1.0
     */
    void beforeUpdate(CacheContext<K, V> context);

    /**
     * �����޸�����֮��
     *
     * @param context context
     * @return
     * @author Jerry.X.He
     * @date 6/8/2017 8:00 PM
     * @since 1.0
     */
    void afterUpdate(CacheContext<K, V> context);

    /**
     * ����ɾ������֮ǰ
     *
     * @param context context
     * @return
     * @author Jerry.X.He
     * @date 6/8/2017 8:00 PM
     * @since 1.0
     */
    void beforeEvict(CacheContext<K, V> context);

    /**
     * ����ɾ������֮��
     *
     * @param context context
     * @return
     * @author Jerry.X.He
     * @date 6/8/2017 8:00 PM
     * @since 1.0
     */
    void afterEvict(CacheContext<K, V> context);

    /**
     * ���� clear����֮ǰ
     *
     * @param context context
     * @return
     * @author Jerry.X.He
     * @date 6/8/2017 8:00 PM
     * @since 1.0
     */
    void beforeClear(CacheContext<K, V> context);

    /**
     * ���� clear����֮��
     *
     * @param context context
     * @return
     * @author Jerry.X.He
     * @date 6/8/2017 8:00 PM
     * @since 1.0
     */
    void afterClear(CacheContext<K, V> context);

    /**
     * ���� destroy����֮ǰ
     *
     * @param context context
     * @return
     * @author Jerry.X.He
     * @date 6/8/2017 8:00 PM
     * @since 1.0
     */
    void beforeDestroy(CacheContext<K, V> context);

    /**
     * ���� destroy����֮��
     *
     * @param context context
     * @return
     * @author Jerry.X.He
     * @date 6/8/2017 8:00 PM
     * @since 1.0
     */
    void afterDestroy(CacheContext<K, V> context);

}

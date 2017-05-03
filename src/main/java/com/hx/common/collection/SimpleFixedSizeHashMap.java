package com.hx.common.collection;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 固定容量的HashMap
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 5/2/2017 10:08 PM
 */
public class SimpleFixedSizeHashMap<K, V> extends LinkedHashMap<K, V> {

    /**
     * 固定的容量
     */
    private int fixedSize;

    /**
     * 初始化
     *
     * @param initialCapacity 初始容量
     * @param loadFactor      loadFactor
     * @return
     * @author
     * @date
     * @since 1.0
     */
    public SimpleFixedSizeHashMap(int initialCapacity, float loadFactor, int fixedSize) {
        super(initialCapacity, loadFactor, true);
        this.fixedSize = fixedSize;
    }

    public SimpleFixedSizeHashMap(int initialCapacity, int fixedSize) {
        this(initialCapacity, 0.75F, fixedSize);
    }

    public SimpleFixedSizeHashMap(int fixedSize) {
        this(16, 0.75F,fixedSize);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > fixedSize;
    }
}

package com.hx.common.collection;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * �̶�������HashMap
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 5/2/2017 10:08 PM
 */
public class SimpleFixedSizeHashMap<K, V> extends LinkedHashMap<K, V> {

    /**
     * �̶�������
     */
    private int fixedSize;

    /**
     * ��ʼ��
     *
     * @param initialCapacity ��ʼ����
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

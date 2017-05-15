package com.hx.common.interf.seprator;

import java.util.Iterator;

/**
 * Seprator
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 5/15/2017 8:09 PM
 */
public interface Seprator<T> extends Iterator<T> {

    /**
     * �Ƿ�����һ��Ԫ��
     *
     * @return void
     * @author Jerry.X.He
     * @date 5/15/2017 8:15 PM
     * @since 1.0
     */
    boolean hasNext();

    /**
     * ���������һ��Ԫ�صĻ�, ������һ��Ԫ��
     *
     * @return T
     * @author Jerry.X.He
     * @date 5/15/2017 8:15 PM
     * @since 1.0
     */
    T next();

    /**
     * �Ƴ���ǰ������Ԫ��
     *
     * @return void
     * @author Jerry.X.He
     * @date 5/15/2017 8:15 PM
     * @since 1.0
     */
    void remove();

    /**
     * ��ȡ��һ��next() ���ص�Ԫ��
     *
     * @return T
     * @author Jerry.X.He
     * @date 5/15/2017 8:15 PM
     * @since 1.0
     */
    T current();

    /**
     * ��ȡ��һ��next()����һ��next() ���ص�Ԫ��
     *
     * @return T
     * @author Jerry.X.He
     * @date 5/15/2017 8:15 PM
     * @since 1.0
     */
    T prev();

    /**
     * ��ȡ��ǰSeprator�ĳ���[��������Լ��]
     *
     * @return void
     * @author Jerry.X.He
     * @date 5/15/2017 8:15 PM
     * @since 1.0
     */
    int length();

    /**
     * ��ȡ��һ��next()��Ҫ���ص�����, ���� ��ƫ��ָ��
     *
     * @return void
     * @author Jerry.X.He
     * @date 5/15/2017 8:15 PM
     * @since 1.0
     */
    T seek();

}

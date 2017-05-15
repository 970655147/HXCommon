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
     * 是否还有下一个元素
     *
     * @return void
     * @author Jerry.X.He
     * @date 5/15/2017 8:15 PM
     * @since 1.0
     */
    boolean hasNext();

    /**
     * 如果还有下一个元素的话, 返回下一个元素
     *
     * @return T
     * @author Jerry.X.He
     * @date 5/15/2017 8:15 PM
     * @since 1.0
     */
    T next();

    /**
     * 移除当前遍历的元素
     *
     * @return void
     * @author Jerry.X.He
     * @date 5/15/2017 8:15 PM
     * @since 1.0
     */
    void remove();

    /**
     * 获取上一个next() 返回的元素
     *
     * @return T
     * @author Jerry.X.He
     * @date 5/15/2017 8:15 PM
     * @since 1.0
     */
    T current();

    /**
     * 获取上一个next()的上一个next() 返回的元素
     *
     * @return T
     * @author Jerry.X.He
     * @date 5/15/2017 8:15 PM
     * @since 1.0
     */
    T prev();

    /**
     * 获取当前Seprator的长度[子类自行约定]
     *
     * @return void
     * @author Jerry.X.He
     * @date 5/15/2017 8:15 PM
     * @since 1.0
     */
    int length();

    /**
     * 获取下一个next()将要返回的数据, 但是 不偏移指针
     *
     * @return void
     * @author Jerry.X.He
     * @date 5/15/2017 8:15 PM
     * @since 1.0
     */
    T seek();

}

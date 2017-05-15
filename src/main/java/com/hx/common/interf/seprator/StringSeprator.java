package com.hx.common.interf.seprator;

/**
 * StringSeprator
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 5/15/2017 8:14 PM
 */
public interface StringSeprator extends Seprator<String> {

    /**
     * 获取上一个next() 的元素的索引
     *
     * @return int
     * @author Jerry.X.He
     * @date 5/15/2017 8:15 PM
     * @since 1.0
     */
    int currentStartIdx();

    /**
     * 获取上一个next()的上一个next() 的元素的索引
     *
     * @return int
     * @author Jerry.X.He
     * @date 5/15/2017 8:15 PM
     * @since 1.0
     */
    int prevStartIdx();

    /**
     * 获取剩余的未处理的字符串
     *
     * @return void
     * @author Jerry.X.He
     * @date 5/15/2017 8:15 PM
     * @since 1.0
     */
    String rest();

    /**
     * 获取剩余的未处理的字符串[+上一个next()返回的数据]
     *
     * @return void
     * @author Jerry.X.He
     * @date 5/15/2017 8:15 PM
     * @since 1.0
     */
    String currentAndRest();

    /**
     * 获取给定的索引处的剩余的未处理的字符串
     *
     * @return void
     * @author Jerry.X.He
     * @date 5/15/2017 8:15 PM
     * @since 1.0
     */
    String rest(int idx);

}

package com.hx.common.interf.common;

/**
 * Result
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 5/3/2017 8:42 PM
 */
public interface Result {

    /**
     * 当前结果是否成功
     *
     * @return the status binding on current result
     * @author Jerry.X.He
     * @date 5/3/2017 8:43 PM
     * @since 1.0
     */
    boolean isSuccess();

    /**
     * 结果响应码
     *
     * @return the code binding on current result
     * @author Jerry.X.He
     * @date 5/3/2017 8:43 PM
     * @since 1.0
     */
    int getCode();

    /**
     * 结果消息
     *
     * @return the msg binding on current result
     * @author Jerry.X.He
     * @date 5/3/2017 8:43 PM
     * @since 1.0
     */
    String getMsg();

    /**
     * 当前结果的数据
     *
     * @return the data binding on current result
     * @author Jerry.X.He
     * @date 5/3/2017 8:43 PM
     * @since 1.0
     */
    Object getData();

    /**
     * 当前结果的额外数据
     *
     * @return the extra data binding on current result
     * @author Jerry.X.He
     * @date 5/3/2017 8:43 PM
     * @since 1.0
     */
    Object getExtra();

}

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

    /**
     * 设置结果是否成功
     *
     * @return the status binding on current result
     * @author Jerry.X.He
     * @date 5/3/2017 8:43 PM
     * @since 1.0
     */
    void setSuccess(boolean isSuccess);

    /**
     * 设置结果响应码
     *
     * @return the code binding on current result
     * @author Jerry.X.He
     * @date 5/3/2017 8:43 PM
     * @since 1.0
     */
    void setCode(int code);

    /**
     * 设置结果消息
     *
     * @return the msg binding on current result
     * @author Jerry.X.He
     * @date 5/3/2017 8:43 PM
     * @since 1.0
     */
    void setMsg(String msg);

    /**
     * 设置结果的数据
     *
     * @return the data binding on current result
     * @author Jerry.X.He
     * @date 5/3/2017 8:43 PM
     * @since 1.0
     */
    void setData(Object data);

    /**
     * 设置结果的额外数据
     *
     * @return the extra data binding on current result
     * @author Jerry.X.He
     * @date 5/3/2017 8:43 PM
     * @since 1.0
     */
    void setExtra(Object extra);

    /**
     * 设置 code 和响应消息
     *
     * @return the msg binding on current result
     * @author Jerry.X.He
     * @date 5/3/2017 8:43 PM
     * @since 1.0
     */
    void setCode2Msg(Code2Msg<Integer, String> code2Msg);

}

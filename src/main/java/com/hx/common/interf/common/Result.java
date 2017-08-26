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
     * ��ǰ����Ƿ�ɹ�
     *
     * @return the status binding on current result
     * @author Jerry.X.He
     * @date 5/3/2017 8:43 PM
     * @since 1.0
     */
    boolean isSuccess();

    /**
     * �����Ӧ��
     *
     * @return the code binding on current result
     * @author Jerry.X.He
     * @date 5/3/2017 8:43 PM
     * @since 1.0
     */
    int getCode();

    /**
     * �����Ϣ
     *
     * @return the msg binding on current result
     * @author Jerry.X.He
     * @date 5/3/2017 8:43 PM
     * @since 1.0
     */
    String getMsg();

    /**
     * ��ǰ���������
     *
     * @return the data binding on current result
     * @author Jerry.X.He
     * @date 5/3/2017 8:43 PM
     * @since 1.0
     */
    Object getData();

    /**
     * ��ǰ����Ķ�������
     *
     * @return the extra data binding on current result
     * @author Jerry.X.He
     * @date 5/3/2017 8:43 PM
     * @since 1.0
     */
    Object getExtra();

    /**
     * ���ý���Ƿ�ɹ�
     *
     * @return the status binding on current result
     * @author Jerry.X.He
     * @date 5/3/2017 8:43 PM
     * @since 1.0
     */
    void setSuccess(boolean isSuccess);

    /**
     * ���ý����Ӧ��
     *
     * @return the code binding on current result
     * @author Jerry.X.He
     * @date 5/3/2017 8:43 PM
     * @since 1.0
     */
    void setCode(int code);

    /**
     * ���ý����Ϣ
     *
     * @return the msg binding on current result
     * @author Jerry.X.He
     * @date 5/3/2017 8:43 PM
     * @since 1.0
     */
    void setMsg(String msg);

    /**
     * ���ý��������
     *
     * @return the data binding on current result
     * @author Jerry.X.He
     * @date 5/3/2017 8:43 PM
     * @since 1.0
     */
    void setData(Object data);

    /**
     * ���ý���Ķ�������
     *
     * @return the extra data binding on current result
     * @author Jerry.X.He
     * @date 5/3/2017 8:43 PM
     * @since 1.0
     */
    void setExtra(Object extra);

    /**
     * ���� code ����Ӧ��Ϣ
     *
     * @return the msg binding on current result
     * @author Jerry.X.He
     * @date 5/3/2017 8:43 PM
     * @since 1.0
     */
    void setCode2Msg(Code2Msg<Integer, String> code2Msg);

}

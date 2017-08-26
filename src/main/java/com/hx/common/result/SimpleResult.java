package com.hx.common.result;

import com.hx.common.interf.common.Code2Msg;
import com.hx.common.interf.common.Result;
import com.hx.common.util.InnerTools;

/**
 * SimpleResult
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 5/3/2017 8:40 PM
 */
public class SimpleResult implements Result {

    /**
     * �Ƿ�ɹ�
     */
    private boolean success;
    /**
     * ��Ӧ��
     */
    private int code;
    /**
     * ��Ӧ��Ϣ
     */
    private String msg;
    /**
     * ��Ӧ����
     */
    private Object data;
    /**
     * ���������[��չ]
     */
    private Object extra;

    /**
     * ��ʼ��
     *
     * @param success �Ƿ�ɹ�
     * @param code    ��Ӧ��
     * @param msg     ��Ӧ��Ϣ
     * @param data    result������
     * @param extra   result���������
     * @since 1.0
     */
    public SimpleResult(boolean success, int code, String msg, Object data, Object extra) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.extra = extra;
    }

    public SimpleResult(boolean success, int code, String msg, Object data) {
        this(success, code, msg, data, null);
    }

    public SimpleResult(boolean success, int code, String msg) {
        this(success, code, msg, null);
    }

    public SimpleResult(boolean success, Code2Msg<Integer, String> code2Msg, Object data, Object extra) {
        InnerTools.assert0(code2Msg != null, "'code2Msg' can't be null !");
        this.success = success;
        this.code = code2Msg.code();
        this.msg = code2Msg.msg();
        this.data = data;
        this.extra = extra;
    }

    public SimpleResult(boolean success, Code2Msg<Integer, String> code2Msg, Object data) {
        this(success, code2Msg, data, null);
    }

    public SimpleResult(boolean success, Code2Msg<Integer, String> code2Msg) {
        this(success, code2Msg, null);
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public Object getData() {
        return data;
    }

    @Override
    public Object getExtra() {
        return extra;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public void setExtra(Object extra) {
        this.extra = extra;
    }

    @Override
    public void setCode2Msg(Code2Msg<Integer, String> code2Msg) {
        InnerTools.assert0(code2Msg != null, "'code2Msg' can't be null !");
        setCode(code2Msg.code());
        setMsg(code2Msg.msg());
    }
}

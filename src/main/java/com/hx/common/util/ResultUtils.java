package com.hx.common.util;

import com.hx.common.interf.common.Code2Msg;
import com.hx.common.interf.common.Result;
import com.hx.common.result.SimpleErrorCode;
import com.hx.common.result.SimpleResult;
import com.hx.common.util.InnerTools;

/**
 * ResultUtils
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 5/3/2017 8:57 PM
 */
public final class ResultUtils {

    // disable constructor
    private ResultUtils() {
        InnerTools.assert0("can't instantiate !");
    }

    /**
     * ���ݸ����������װһ���ɹ��Ľ��
     *
     * @param code  ��Ӧ��
     * @param msg   ��Ӧ��Ϣ
     * @param data  ��Ӧ����
     * @param extra ��������
     * @return com.hx.common.result.SimpleResult
     * @author Jerry.X.He
     * @date 5/3/2017 9:00 PM
     * @since 1.0
     */
    public static Result success(int code, String msg, Object data, Object extra) {
        return new SimpleResult(true, code, msg, data, extra);
    }

    public static Result success(Code2Msg<Integer, String> code2Msg, Object data, Object extra) {
        return success(code2Msg.code(), code2Msg.msg(), data, extra);
    }

    public static Result success(int code, String msg, Object data) {
        return success(code, msg, data, null);
    }

    public static Result success(Code2Msg<Integer, String> code2Msg, Object data) {
        return success(code2Msg.code(), code2Msg.msg(), data);
    }

    public static Result success(Object data, Object extra) {
        return success(SimpleErrorCode.SUCCESS, data, extra);
    }

    public static Result success(Object data) {
        return success(data, null);
    }

    public static Result success() {
        return success(null);
    }

    /**
     * ���ݸ����������װһ��ʧ�ܵĽ��
     *
     * @param code  ��Ӧ��
     * @param msg   ��Ӧ��Ϣ
     * @param data  ��Ӧ����
     * @param extra ��������
     * @return com.hx.common.result.SimpleResult
     * @author Jerry.X.He
     * @date 5/3/2017 9:00 PM
     * @since 1.0
     */
    public static Result failed(int code, String msg, Object data, Object extra) {
        return new SimpleResult(false, code, msg, data, extra);
    }

    public static Result failed(Code2Msg<Integer, String> code2Msg, Object data, Object extra) {
        return failed(code2Msg.code(), code2Msg.msg(), data, extra);
    }

    public static Result failed(int code, String msg, Object data) {
        return failed(code, msg, data, null);
    }

    public static Result failed(Code2Msg<Integer, String> code2Msg, Object data) {
        return failed(code2Msg.code(), code2Msg.msg(), data);
    }

    public static Result failed(Object data, Object extra) {
        return failed(SimpleErrorCode.FAILED, data, extra);
    }

    public static Result failed(Object data) {
        return failed(data, null);
    }

    public static Result failed() {
        return failed(null);
    }

}

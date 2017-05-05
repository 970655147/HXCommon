/**
 * file name : AssertUtils.java
 * created at : 20:32:00 2016-12-30
 * created by 970655147
 */

package com.hx.common.util;

public final class AssertUtils {

    // disable constructor
    private AssertUtils() {
        assert0("can't instantiate !");
    }

    // ------------ assert��� ------- 2016.03.22 -------------
    // ���߷���

    /**
     * ȷ��booΪtrue, ���� �׳��쳣
     *
     * @param boo ����
     * @param msg �쳣��ҪЯ������Ϣ
     * @return void
     * @author Jerry.X.He
     * @date 5/5/2017 8:26 PM
     * @since 1.0
     */
    public static void assert0(boolean boo, String msg) {
        if (!boo) {
            throw new RuntimeException("assert0Exception : " + msg);
        }
    }

    public static void assert0(String msg) {
        assert0(false, msg);
    }

    // add at 2016.05.02

    /**
     * ȷ��booΪtrue, ���� �׳��쳣
     *
     * @param boo ����
     * @param e   �쳣��ҪЯ������Ϣ
     * @return void
     * @author Jerry.X.He
     * @date 5/5/2017 8:26 PM
     * @since 1.0
     */
    public static void assert0(boolean boo, Exception e) {
        assert0(e != null, "'e' can't be null ");
        if (!boo) {
            throw new RuntimeException(e);
        }
    }

    public static void assert0(Exception e) {
        assert0(false, e);
    }

    /**
     * ȷ��val ��expect ���/�����, ���� �׳��쳣
     *
     * @param val      ֵ1
     * @param expect   ������ֵ
     * @param isEquals �Ƿ����
     * @param errorMsg �쳣��ҪЯ������Ϣ
     * @return void
     * @author Jerry.X.He
     * @date 5/5/2017 8:26 PM
     * @since 1.0
     */
    public static void assert0(int val, int expect, boolean isEquals, String errorMsg) {
        if (isEquals ^ (val == expect)) {
            String symbol = null;
            if (isEquals) {
                symbol = "!=";
            } else {
                symbol = "==";
            }
            assert0("assert0Exception : " + val + " " + symbol + ", expected : " + expect + ", MSG : " + errorMsg);
        }
    }

    // ȷ��val ��expected��ͬ, ���� �׳��쳣
    public static void assert0(int val, int expect, String errorMsg) {
        assert0(val, expect, true, errorMsg);
    }

    /**
     * ȷ��val ��expect ���/�����, ���� �׳��쳣
     *
     * @param val      ֵ1
     * @param expect   ������ֵ
     * @param isEquals �Ƿ����
     * @param errorMsg �쳣��ҪЯ������Ϣ
     * @return void
     * @author Jerry.X.He
     * @date 5/5/2017 8:26 PM
     * @since 1.0
     */
    public static <T> void assert0(T val, T expect, boolean isEquals, String errorMsg) {
        if (val == null) {
            if (expect != null) {
                assert0("assert0Exception : " + val + " == null, expected : " + expect + ", MSG : " + errorMsg);
            }
            return;
        }

        if (isEquals ^ (val.equals(expect))) {
            String symbol = null;
            if (isEquals) {
                symbol = "!=";
            } else {
                symbol = "==";
            }
            assert0("assert0Exception : " + String.valueOf(val) + " " + symbol + " " + String.valueOf(expect) + ", expected : " + String.valueOf(expect) + ", MSG : " + errorMsg);
        }
    }

    public static <T> void assert0(T val, T expect, String errorMsg) {
        assert0(val, expect, true, errorMsg);
    }


}

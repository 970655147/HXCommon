/**
 * file name : StringIntercepter.java
 * created at : 10:47:13 AM Oct 1, 2015
 * created by 970655147
 */

package com.hx.common.str;

import com.hx.common.util.InnerTools;

/**
 * ��ȡ�ַ����Ĺ���
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 5/5/2017 8:58 PM
 */
public class StringInterceptor {
    /**
     * Ŀ���ַ���
     */
    private String val;
    /**
     * �Լ���ǰ������
     */
    private int idx;

    /**
     * ��ʼ��
     *
     * @param val �������ַ���
     * @since 1.0
     */
    public StringInterceptor(String val) {
        this.val = val;
        idx = 0;
    }

    /**
     * ��������Ŀ���ַ���, ����������
     *
     * @param val �������ַ���
     * @return void
     * @author Jerry.X.He
     * @date 5/5/2017 8:58 PM
     * @since 1.0
     */
    public void setVal(String val) {
        this.val = val;
        resetIdx();
    }

    /**
     * ��������
     *
     * @return void
     * @author Jerry.X.He
     * @date 5/5/2017 8:58 PM
     * @since 1.0
     */
    public void resetIdx() {
        setIdx(0);
    }

    /**
     * ��������λ��
     *
     * @param idx ��Ҫ���õ�����
     * @return void
     * @author Jerry.X.He
     * @date 5/5/2017 8:58 PM
     * @since 1.0
     */
    public void setIdx(int idx) {
        this.idx = idx;
    }

    /**
     * ��ȡ����λ��
     *
     * @return void
     * @author Jerry.X.He
     * @date 5/5/2017 8:58 PM
     * @since 1.0
     */
    public int getIdx() {
        return this.idx;
    }

    /**
     * �Ƿ񻹴�����һ��Ԫ��??
     *
     * @return void
     * @author Jerry.X.He
     * @date 5/5/2017 8:58 PM
     * @since 1.0
     */
    public boolean hasNext() {
        return idx < val.length();
    }

    /**
     * ��ȡ��һ��starts, ends֮����ַ���, includeStarts, includeEnds��ʾ����Ƿ����starts, ends
     * ���Ȼ�ȡ��һ��start��λ��, ���û���ҵ�, ֱ�ӷ���""
     * ��ȡstartIdx֮�����һ��ends��λ��, ���û���ҵ�, ֱ�ӷ���""
     * ����Ƿ���Ҫ����starts, ends, ��������, ��ȡ�Ӵ�
     *
     * @param starts        ǰ׺�Ӵ�
     * @param ends          ��׺�Ӵ�
     * @param includeStarts �Ƿ����ǰ׺
     * @param includeEnds   �Ƿ������׺
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 5/5/2017 9:00 PM
     * @since 1.0
     */
    public String nextStrInRange(String starts, String ends, boolean includeStarts, boolean includeEnds) {
        int startIdx = val.indexOf(starts, idx);
        if (startIdx == -1) {
            idx = val.length();
            return InnerTools.EMPTY_STR;
        }

        int endIdx = val.indexOf(ends, startIdx + starts.length());
        if (endIdx == -1) {
            idx = val.length();
            return InnerTools.EMPTY_STR;
        }

        if (!includeStarts) {
            startIdx += starts.length();
        }
        if (includeEnds) {
            endIdx += ends.length();
        }
        idx = endIdx + ends.length();
        return val.substring(startIdx, endIdx);
    }

    public String nextStrInRange(String starts, String ends) {
        return nextStrInRange(starts, ends, false, false);
    }

    /**
     * ��ȡ����һ��idxStr֮����ַ���, includeIdx��ʾ����Ƿ����idxStr
     * ���Ȼ�ȡ��һ��idxStr��λ��, ���û���ҵ�, ֱ�ӷ���""
     * ����Ƿ���Ҫ����starts, ends, ��������, ��ȡ�Ӵ�
     *
     * @param idxStr     ǰ׺�Ӵ�
     * @param includeIdx �Ƿ����ǰ׺
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 5/5/2017 9:01 PM
     * @since 1.0
     */
    public String nextStrInRange(String idxStr, boolean includeIdx) {
        int idxStrIdx = val.indexOf(idxStr, idx);
        if (idxStrIdx == -1) {
            idx = val.length();
            return InnerTools.EMPTY_STR;
        }

        if (includeIdx) {
            idxStrIdx += idxStr.length();
        }
        int oldIdx = idx;
        idx = idxStrIdx + idxStr.length();
        return val.substring(oldIdx, idxStrIdx);
    }

    // ��ȡ����һ��idxStr֮����ַ���, Ĭ�ϲ�����idxStr\
    /**
     * ��ȡ����һ��idxStr֮����ַ���, Ĭ�ϲ�����idxStr\
     *
     * @param idxStr     ǰ׺�Ӵ�
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 5/5/2017 9:01 PM
     * @since 1.0
     */
    public String nextStrInRange(String idxStr) {
        return nextStrInRange(idxStr, false);
    }

}

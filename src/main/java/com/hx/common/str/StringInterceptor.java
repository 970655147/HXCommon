/**
 * file name : StringIntercepter.java
 * created at : 10:47:13 AM Oct 1, 2015
 * created by 970655147
 */

package com.hx.common.str;

import com.hx.common.util.InnerTools;

/**
 * 截取字符串的工具
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 5/5/2017 8:58 PM
 */
public class StringInterceptor implements com.hx.common.interf.seprator.StringInterceptor {
    /**
     * 目标字符串
     */
    private String val;
    /**
     * 以及当前的索引
     */
    private int idx;

    /**
     * 初始化
     *
     * @param val 给定的字符串
     * @since 1.0
     */
    public StringInterceptor(String val) {
        this.val = val;
        idx = 0;
    }

    @Override
    public void setVal(String val) {
        this.val = val;
        resetIdx();
    }

    @Override
    public void resetIdx() {
        setIdx(0);
    }

    @Override
    public void setIdx(int idx) {
        this.idx = idx;
    }

    @Override
    public int getIdx() {
        return this.idx;
    }

    @Override
    public boolean hasNext() {
        return idx < val.length();
    }

    @Override
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

    @Override
    public String nextStrInRange(String starts, String ends) {
        return nextStrInRange(starts, ends, false, false);
    }

    @Override
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

    @Override
    public String nextStrInRange(String idxStr) {
        return nextStrInRange(idxStr, false);
    }

}

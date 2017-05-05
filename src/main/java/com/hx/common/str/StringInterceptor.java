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
public class StringInterceptor {
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

    /**
     * 重新配置目标字符串, 并重置索引
     *
     * @param val 给定的字符串
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
     * 重置索引
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
     * 设置索引位置
     *
     * @param idx 需要配置的索引
     * @return void
     * @author Jerry.X.He
     * @date 5/5/2017 8:58 PM
     * @since 1.0
     */
    public void setIdx(int idx) {
        this.idx = idx;
    }

    /**
     * 获取索引位置
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
     * 是否还存在下一个元素??
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
     * 截取下一个starts, ends之间的字符串, includeStarts, includeEnds表示结果是否包含starts, ends
     * 首先获取下一个start的位置, 如果没有找到, 直接返回""
     * 获取startIdx之后的下一个ends的位置, 如果没有找到, 直接返回""
     * 解决是否需要包含starts, ends, 更新索引, 截取子串
     *
     * @param starts        前缀子串
     * @param ends          后缀子串
     * @param includeStarts 是否包含前缀
     * @param includeEnds   是否包含后缀
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
     * 截取到下一个idxStr之间的字符串, includeIdx表示结果是否包含idxStr
     * 首先获取下一个idxStr的位置, 如果没有找到, 直接返回""
     * 解决是否需要包含starts, ends, 更新索引, 截取子串
     *
     * @param idxStr     前缀子串
     * @param includeIdx 是否包含前缀
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

    // 截取到下一个idxStr之间的字符串, 默认不包含idxStr\
    /**
     * 截取到下一个idxStr之间的字符串, 默认不包含idxStr\
     *
     * @param idxStr     前缀子串
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 5/5/2017 9:01 PM
     * @since 1.0
     */
    public String nextStrInRange(String idxStr) {
        return nextStrInRange(idxStr, false);
    }

}

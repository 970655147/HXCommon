package com.hx.common.interf.seprator;

/**
 * StringInterceptor
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 5/15/2017 8:21 PM
 */
public interface StringInterceptor {

    /**
     * 重新配置目标字符串, 并重置索引
     *
     * @param val 给定的字符串
     * @return void
     * @author Jerry.X.He
     * @date 5/5/2017 8:58 PM
     * @since 1.0
     */
    void setVal(String val);

    /**
     * 重置索引
     *
     * @return void
     * @author Jerry.X.He
     * @date 5/5/2017 8:58 PM
     * @since 1.0
     */
    void resetIdx();

    /**
     * 设置索引位置
     *
     * @param idx 需要配置的索引
     * @return void
     * @author Jerry.X.He
     * @date 5/5/2017 8:58 PM
     * @since 1.0
     */
    void setIdx(int idx);

    /**
     * 获取索引位置
     *
     * @return void
     * @author Jerry.X.He
     * @date 5/5/2017 8:58 PM
     * @since 1.0
     */
    int getIdx();

    /**
     * 是否还存在下一个元素??
     *
     * @return void
     * @author Jerry.X.He
     * @date 5/5/2017 8:58 PM
     * @since 1.0
     */
    boolean hasNext();

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
    String nextStrInRange(String starts, String ends, boolean includeStarts, boolean includeEnds);

    /**
     * 截取下一个starts, ends之间的字符串
     *
     * @param starts        前缀子串
     * @param ends          后缀子串
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 5/5/2017 9:00 PM
     * @since 1.0
     */
    String nextStrInRange(String starts, String ends);

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
    String nextStrInRange(String idxStr, boolean includeIdx);

    /**
     * 截取到下一个idxStr之间的字符串, 默认不包含idxStr\
     *
     * @param idxStr     前缀子串
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 5/5/2017 9:01 PM
     * @since 1.0
     */
    String nextStrInRange(String idxStr);

}

package com.hx.common.interf.idx;

/**
 * 迭代索引过程中的filter
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 4/12/2017 10:04 PM
 */
public interface IdxFilter {

    /**
     * 判断给定的索引是否需要被过滤掉
     *
     * @param idx 给定的索引
     * @return true if idx will be filtered
     * @author Jerry.X.He
     * @date 4/12/2017 10:05 PM
     * @since 1.0
     */
    boolean filter(int idx);

    /**
     * 复制一个向前的 IdxFilter
     *
     * @return
     * @author Jerry.X.He
     * @date 6/8/2017 7:15 PM
     * @since 1.0
     */
    IdxFilter copy();

}

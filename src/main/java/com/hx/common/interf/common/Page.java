package com.hx.common.interf.common;

import java.util.List;

/**
 * 分页相关
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 5/21/2017 4:05 PM
 */
public interface Page<T> {

    /**
     * 获取当前页数
     *
     * @author Jerry.X.He
     * @date 5/21/2017 4:07 PM
     * @since 1.0
     */
    int getPageNow();

    /**
     * 获取每一页的大小
     *
     * @author Jerry.X.He
     * @date 5/21/2017 4:07 PM
     * @since 1.0
     */
    int getPageSize();

    /**
     * 获取当前页的第一条记录的偏移
     *
     * @author Jerry.X.He
     * @date 5/21/2017 4:07 PM
     * @since 1.0
     */
    int recordOffset();

    /**
     * 获取总共的页数
     *
     * @author Jerry.X.He
     * @date 5/21/2017 4:07 PM
     * @since 1.0
     */
    int getTotalPage();

    /**
     * 获取总共的记录数量
     *
     * @author Jerry.X.He
     * @date 5/21/2017 4:07 PM
     * @since 1.0
     */
    int getTotalRecord();

    /**
     * 获取当前页面的数据
     *
     * @author Jerry.X.He
     * @date 5/21/2017 4:07 PM
     * @since 1.0
     */
    List<T> getList();

    /**
     * 设置当前页数
     *
     * @author Jerry.X.He
     * @date 5/21/2017 4:07 PM
     * @since 1.0
     */
    void setPageNow(int pageNow);

    /**
     * 设置每一页的大小
     *
     * @author Jerry.X.He
     * @date 5/21/2017 4:07 PM
     * @since 1.0
     */
    void setPageSize(int pageSize);

    /**
     * 设置总记录数
     *
     * @author Jerry.X.He
     * @date 5/21/2017 4:07 PM
     * @since 1.0
     */
    void setTotalRecord(int pageSize);

    /**
     * 配置当前页面的数据
     *
     * @author Jerry.X.He
     * @date 5/21/2017 4:07 PM
     * @since 1.0
     */
    void setList(List<T> list);

}

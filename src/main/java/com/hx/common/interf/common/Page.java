package com.hx.common.interf.common;

import java.util.List;

/**
 * ��ҳ���
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 5/21/2017 4:05 PM
 */
public interface Page<T> {

    /**
     * ��ȡ��ǰҳ��
     *
     * @author Jerry.X.He
     * @date 5/21/2017 4:07 PM
     * @since 1.0
     */
    int getPageNow();

    /**
     * ��ȡÿһҳ�Ĵ�С
     *
     * @author Jerry.X.He
     * @date 5/21/2017 4:07 PM
     * @since 1.0
     */
    int getPageSize();

    /**
     * ��ȡ��ǰҳ�ĵ�һ����¼��ƫ��
     *
     * @author Jerry.X.He
     * @date 5/21/2017 4:07 PM
     * @since 1.0
     */
    int recordOffset();

    /**
     * ��ȡ�ܹ���ҳ��
     *
     * @author Jerry.X.He
     * @date 5/21/2017 4:07 PM
     * @since 1.0
     */
    int getTotalPage();

    /**
     * ��ȡ�ܹ��ļ�¼����
     *
     * @author Jerry.X.He
     * @date 5/21/2017 4:07 PM
     * @since 1.0
     */
    int getTotalRecord();

    /**
     * ��ȡ��ǰҳ�������
     *
     * @author Jerry.X.He
     * @date 5/21/2017 4:07 PM
     * @since 1.0
     */
    List<T> getList();

    /**
     * ���õ�ǰҳ��
     *
     * @author Jerry.X.He
     * @date 5/21/2017 4:07 PM
     * @since 1.0
     */
    void setPageNow(int pageNow);

    /**
     * ����ÿһҳ�Ĵ�С
     *
     * @author Jerry.X.He
     * @date 5/21/2017 4:07 PM
     * @since 1.0
     */
    void setPageSize(int pageSize);

    /**
     * �����ܼ�¼��
     *
     * @author Jerry.X.He
     * @date 5/21/2017 4:07 PM
     * @since 1.0
     */
    void setTotalRecord(int pageSize);

    /**
     * ���õ�ǰҳ�������
     *
     * @author Jerry.X.He
     * @date 5/21/2017 4:07 PM
     * @since 1.0
     */
    void setList(List<T> list);

}

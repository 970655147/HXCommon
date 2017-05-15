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
     * ��������Ŀ���ַ���, ����������
     *
     * @param val �������ַ���
     * @return void
     * @author Jerry.X.He
     * @date 5/5/2017 8:58 PM
     * @since 1.0
     */
    void setVal(String val);

    /**
     * ��������
     *
     * @return void
     * @author Jerry.X.He
     * @date 5/5/2017 8:58 PM
     * @since 1.0
     */
    void resetIdx();

    /**
     * ��������λ��
     *
     * @param idx ��Ҫ���õ�����
     * @return void
     * @author Jerry.X.He
     * @date 5/5/2017 8:58 PM
     * @since 1.0
     */
    void setIdx(int idx);

    /**
     * ��ȡ����λ��
     *
     * @return void
     * @author Jerry.X.He
     * @date 5/5/2017 8:58 PM
     * @since 1.0
     */
    int getIdx();

    /**
     * �Ƿ񻹴�����һ��Ԫ��??
     *
     * @return void
     * @author Jerry.X.He
     * @date 5/5/2017 8:58 PM
     * @since 1.0
     */
    boolean hasNext();

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
    String nextStrInRange(String starts, String ends, boolean includeStarts, boolean includeEnds);

    /**
     * ��ȡ��һ��starts, ends֮����ַ���
     *
     * @param starts        ǰ׺�Ӵ�
     * @param ends          ��׺�Ӵ�
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 5/5/2017 9:00 PM
     * @since 1.0
     */
    String nextStrInRange(String starts, String ends);

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
    String nextStrInRange(String idxStr, boolean includeIdx);

    /**
     * ��ȡ����һ��idxStr֮����ַ���, Ĭ�ϲ�����idxStr\
     *
     * @param idxStr     ǰ׺�Ӵ�
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 5/5/2017 9:01 PM
     * @since 1.0
     */
    String nextStrInRange(String idxStr);

}

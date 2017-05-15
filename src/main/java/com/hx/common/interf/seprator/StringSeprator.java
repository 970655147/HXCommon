package com.hx.common.interf.seprator;

/**
 * StringSeprator
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 5/15/2017 8:14 PM
 */
public interface StringSeprator extends Seprator<String> {

    /**
     * ��ȡ��һ��next() ��Ԫ�ص�����
     *
     * @return int
     * @author Jerry.X.He
     * @date 5/15/2017 8:15 PM
     * @since 1.0
     */
    int currentStartIdx();

    /**
     * ��ȡ��һ��next()����һ��next() ��Ԫ�ص�����
     *
     * @return int
     * @author Jerry.X.He
     * @date 5/15/2017 8:15 PM
     * @since 1.0
     */
    int prevStartIdx();

    /**
     * ��ȡʣ���δ������ַ���
     *
     * @return void
     * @author Jerry.X.He
     * @date 5/15/2017 8:15 PM
     * @since 1.0
     */
    String rest();

    /**
     * ��ȡʣ���δ������ַ���[+��һ��next()���ص�����]
     *
     * @return void
     * @author Jerry.X.He
     * @date 5/15/2017 8:15 PM
     * @since 1.0
     */
    String currentAndRest();

    /**
     * ��ȡ��������������ʣ���δ������ַ���
     *
     * @return void
     * @author Jerry.X.He
     * @date 5/15/2017 8:15 PM
     * @since 1.0
     */
    String rest(int idx);

}

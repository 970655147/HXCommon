package com.hx.common.cache;


import com.hx.common.interf.common.Page;
import com.hx.common.interf.common.Result;

import java.util.List;

/**
 * �ṩ������ط���
 *
 * @author Jerry.X.He
 * @date 2018/7/12 11:18
 */
public interface CacheService {

    /**
     * �򻺴������ key, value
     *
     * @param key   key
     * @param value value
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    String set(String key, String value);

    /**
     * �򻺴������ key, value, ���Ϲ���ʱ��
     *
     * @param key    key
     * @param value  value
     * @param expire expire
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    String set(String key, String value, int expire);

    /**
     * ������ key ��������� ԭ������1
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long incr(String key);

    /**
     * ������ key ��������� ԭ���ۼ�1
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long decr(String key);

    /**
     * ������ key ��������� ԭ������inc
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long incrBy(String key, long inc);

    /**
     * ������ key ��������� ԭ���ۼ�inc
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long decrBy(String key, long inc);


    /**
     * ������ key ��������� ԭ������1, ���Ϲ���ʱ��
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long incr(String key, int expire);

    /**
     * ������ key ��������� ԭ���ۼ�1, ���Ϲ���ʱ��
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long decr(String key, int expire);

    /**
     * ������ key ��������� ԭ������inc, ���Ϲ���ʱ��
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long incrBy(String key, long inc, int expire);

    /**
     * ������ key ��������� ԭ���ۼ�inc, ���Ϲ���ʱ��
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long decrBy(String key, long inc, int expire);

    /**
     * ��ȡ������ key ��Ӧ�Ļ����ֵ
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    String get(String key);

    /**
     * ���ø����� key ��Ӧ�Ĺ���ʱ��
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long expire(String key, int expire);

    /**
     * �жϸ����� key �Ƿ����
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    boolean exists(String key);

    /**
     * ɾ�������� key
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long remove(String key);

    // --------------------------------------------------- ����ǰ׺ ----------------------------------------------------

    /**
     * �򻺴������ key, value
     *
     * @param key   key
     * @param value value
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    String set(String prefix, String key, String value);

    /**
     * �򻺴������ key, value, ���Ϲ���ʱ��
     *
     * @param key    key
     * @param value  value
     * @param expire expire
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    String set(String prefix, String key, String value, int expire);

    /**
     * ������ key ��������� ԭ������1
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long incr(String prefix, String key);

    /**
     * ������ key ��������� ԭ���ۼ�1
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long decr(String prefix, String key);

    /**
     * ������ key ��������� ԭ������inc
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long incrBy(String prefix, String key, long inc);

    /**
     * ������ key ��������� ԭ���ۼ�inc
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long decrBy(String prefix, String key, long inc);


    /**
     * ������ key ��������� ԭ������1, ���Ϲ���ʱ��
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long incr(String prefix, String key, int expire);

    /**
     * ������ key ��������� ԭ���ۼ�1, ���Ϲ���ʱ��
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long decr(String prefix, String key, int expire);

    /**
     * ������ key ��������� ԭ������inc, ���Ϲ���ʱ��
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long incrBy(String prefix, String key, long inc, int expire);

    /**
     * ������ key ��������� ԭ���ۼ�inc, ���Ϲ���ʱ��
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long decrBy(String prefix, String key, long inc, int expire);

    /**
     * ��ȡ������ key ��Ӧ�Ļ����ֵ
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    String get(String prefix, String key);

    /**
     * ���ø����� key ��Ӧ�Ĺ���ʱ��
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long expire(String prefix, String key, int expire);

    /**
     * �жϸ����� key �Ƿ����
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    boolean exists(String prefix, String key);

    /**
     * ɾ�������� key
     *
     * @param key key
     * @return
     * @author Jerry.X.He
     * @date 2018/7/12 11:23
     */
    Long remove(String prefix, String key);

    // --------------------------------------------------- ת����� ----------------------------------------------------

    /**
     * ���� key ��ȡ������ַ���, ת��Ϊ ������ clazz ����
     *
     * @param key   key
     * @param clazz clazz
     * @return
     * @author Jerry.X.He
     * @date 2018/7/16 15:46
     */
    <T> T getOrNull(String key, Class<T> clazz);

    /**
     * ���� key ��ȡ������ַ���, ת��Ϊ ������ clazz ����
     *
     * @param prefix prefix
     * @param key    key
     * @param clazz  clazz
     * @return
     * @author Jerry.X.He
     * @date 2018/7/16 15:46
     */
    <T> T getOrNull(String prefix, String key, Class<T> clazz);

    /**
     * ���� key ��ȡ������ַ���, ת��Ϊ ������ clazz ����
     *
     * @param key   key
     * @param clazz clazz
     * @return
     * @author Jerry.X.He
     * @date 2018/7/16 15:46
     */
    <T> List<T> getListOrNull(String key, Class<T> clazz);

    /**
     * ���� key ��ȡ������ַ���, ת��Ϊ ������ clazz ����
     *
     * @param prefix prefix
     * @param key    key
     * @param clazz  clazz
     * @return
     * @author Jerry.X.He
     * @date 2018/7/16 15:46
     */
    <T> List<T> getListOrNull(String prefix, String key, Class<T> clazz);

    /**
     * ���� key ��ȡ������ַ���, ת��Ϊ ������ clazz ����
     *
     * @param key   key
     * @param clazz clazz
     * @return
     * @author Jerry.X.He
     * @date 2018/7/16 15:46
     */
    <T> Page<T> getPageOrNull(String key, Class<T> clazz);

    /**
     * ���� key ��ȡ������ַ���, ת��Ϊ ������ clazz ����
     *
     * @param prefix prefix
     * @param key    key
     * @param clazz  clazz
     * @return
     * @author Jerry.X.He
     * @date 2018/7/16 15:46
     */
    <T> Page<T> getPageOrNull(String prefix, String key, Class<T> clazz);

    /**
     * ���� key ��ȡ������ַ���, ת��Ϊ ������ clazz ����
     *
     * @param key   key
     * @param clazz clazz
     * @return
     * @author Jerry.X.He
     * @date 2018/7/16 15:46
     */
    <T> Result getResultOrNull(String key, Class<T> clazz);

    /**
     * ���� key ��ȡ������ַ���, ת��Ϊ ������ clazz ����
     *
     * @param prefix prefix
     * @param key    key
     * @param clazz  clazz
     * @return
     * @author Jerry.X.He
     * @date 2018/7/16 15:46
     */
    <T> Result getResultOrNull(String prefix, String key, Class<T> clazz);

    /**
     * ���� key ��ȡ������ַ���, ת��Ϊ ������ clazz ����
     *
     * @param key   key
     * @param clazz clazz
     * @return
     * @author Jerry.X.He
     * @date 2018/7/16 15:46
     */
    <T> Result getListResultOrNull(String key, Class<T> clazz);

    /**
     * ���� key ��ȡ������ַ���, ת��Ϊ ������ clazz ����
     *
     * @param prefix prefix
     * @param key    key
     * @param clazz  clazz
     * @return
     * @author Jerry.X.He
     * @date 2018/7/16 15:46
     */
    <T> Result getListResultOrNull(String prefix, String key, Class<T> clazz);

    /**
     * ���� key ��ȡ������ַ���, ת��Ϊ ������ clazz ����
     *
     * @param key   key
     * @param clazz clazz
     * @return
     * @author Jerry.X.He
     * @date 2018/7/16 15:46
     */
    <T> Result getPageResultOrNull(String key, Class<T> clazz);

    /**
     * ���� key ��ȡ������ַ���, ת��Ϊ ������ clazz ����
     *
     * @param prefix prefix
     * @param key    key
     * @param clazz  clazz
     * @return
     * @author Jerry.X.He
     * @date 2018/7/16 15:46
     */
    <T> Result getPageResultOrNull(String prefix, String key, Class<T> clazz);

}

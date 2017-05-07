package com.hx.common.util;

import java.util.*;

/**
 * InnerTools
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 5/1/2017 12:44 PM
 */
public final class InnerTools {

    /**
     * ���ַ���
     */
    public static final String EMPTY_STR = "";
    /**
     * ���� + �س�
     */
    public static final String CRLF = "\r\n";
    /**
     * true��������
     */
    public static final String TRUE = "true";
    /**
     * false��������
     */
    public static final String FALSE = "false";
    /**
     * null��������
     */
    public static final String NULL = "null";
    /**
     * \
     */
    public static final String SLASH = "\\";
    /**
     * /
     */
    public static final String INV_SLASH = "/";
    /**
     * ��Ҫ��ת����ַ�
     */
    public static final Set<Character> NEED_BE_TRANSFER = asSet('"', '\'', '\\');

    // disable constructor
    private InnerTools() {
        AssertUtils.assert0(false, "can't instantiate !");
    }

    // ----------------- ȥ�������Ĳ��ַ��� -----------------------

    /**
     * �жϸ������ַ����Ƿ�Ϊ��
     *
     * @param str �������ַ���
     * @return
     */
    public static boolean isEmpty(String str) {
        return (str == null) || (str.trim().length() == 0);
    }

    public static boolean isEmpty(Collection<?> coll) {
        return (coll == null) || (coll.size() == 0);
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return (map == null) || (map.size() == 0);
    }

    public static boolean isEmpty(Object[] arr) {
        return (arr == null) || (arr.length == 0);
    }

    /**
     * ȷ��booΪtrue, ���� �׳��쳣
     *
     * @param boo �����ı��ʽ
     * @param msg ������ʽΪfalse, ��Ҫ�׳����쳣���ӵĶ���Ϣ
     * @return void
     * @author Jerry.X.He
     * @date 5/1/2017 11:31 AM
     * @since 1.0
     */
    public static void assert0(boolean boo, String msg) {
        AssertUtils.assert0(boo, msg);
    }

    public static void assert0(String msg) {
        AssertUtils.assert0(false, msg);
    }

    /**
     * ��ӡ��������Ϣ[System.out]
     *
     * @param msg ��������Ϣ
     * @return void
     * @author Jerry.X.He
     * @date 5/2/2017 8:41 PM
     * @since 1.0
     */
    public static void log(Object msg) {
        System.out.println(String.valueOf(msg));
    }

    public static void err(Object msg) {
        System.err.println(String.valueOf(msg));
    }

    /**
     * ����������ַ�����startsWith��ͷ, ���Ƴ�startsWith
     *
     * @param str        �������ַ���
     * @param startsWith ������ǰ׺
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 5/5/2017 4:36 PM
     * @since 1.0
     */
    public static String removeIfStartsWith(String str, String startsWith) {
        assert0(str != null, "'str' can't be null ");
        assert0(startsWith != null, "'startsWith' can't be null ");

        if (str.startsWith(startsWith)) {
            return str.substring(startsWith.length());
        }

        return str;
    }

    /**
     * ����������ַ�����endsWith��ͷ, ���Ƴ�endsWith
     *
     * @param str      �������ַ���
     * @param endsWith �����ĺ�׺
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 5/5/2017 4:54 PM
     * @since 1.0
     */
    public static String removeIfEndsWith(String str, String endsWith) {
        assert0(str != null, "'str' can't be null ");
        assert0(endsWith != null, "'endsWith' can't be null ");

        if (str.endsWith(endsWith)) {
            return str.substring(0, str.length() - endsWith.length());
        }

        return str;
    }

    /**
     * ����������ַ�������startsWith��ͷ, �����startsWith
     *
     * @param str        �������ַ���
     * @param startsWith ������ǰ׺
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 5/5/2017 4:55 PM
     * @since 1.0
     */
    public static String addIfNotStartsWith(String str, String startsWith) {
        assert0(str != null, "'str' can't be null ");
        assert0(startsWith != null, "'startsWith' can't be null ");

        if (!str.startsWith(startsWith)) {
            return startsWith + str;
        }

        return str;
    }

    /**
     * ����������ַ�������endsWith��ͷ, �����endsWith
     *
     * @param str      �������ַ���
     * @param endsWith �����ĺ�׺
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 5/5/2017 4:54 PM
     * @since 1.0
     */
    public static String addIfNotEndsWith(String str, String endsWith) {
        assert0(str != null, "'str' can't be null ");
        assert0(endsWith != null, "'endsWith' can't be null ");

        if (!str.endsWith(endsWith)) {
            return str + endsWith;
        }

        return str;
    }

    /**
     * �Ƴ���sb����ӵ����һ���ָ���
     *
     * @param sb      �������ַ���
     * @param lastSep ���һ���ָ���
     * @return void
     * @author Jerry.X.He
     * @date 5/5/2017 4:55 PM
     * @since 1.0
     */
    public static void removeLastSep(StringBuilder sb, String lastSep) {
        assert0(sb != null, "'sb' can't be null ");
        assert0(lastSep != null, "'lastSep' can't be null ");

        if (sb.length() > lastSep.length()) {
            sb.delete(sb.length() - lastSep.length(), sb.length());
        }
    }

    /**
     * �ж�str01 ��str02�Ƿ���ͬ[���Դ�Сд]
     *
     * @param str01 ����1
     * @param str02 ����2
     * @return boolean
     * @author Jerry.X.He
     * @date 5/2/2017 9:24 PM
     * @since 1.0
     */
    public static boolean equalsIgnoreCase(String str01, String str02) {
        if (str01 == null && str02 == null) {
            return true;
        } else if (str01 != null) {
            return str01.equalsIgnoreCase(str02);
        } else if (str02 != null) {
            return str02.equalsIgnoreCase(str01);
        }

        // can't got there
        return false;
    }

    /**
     * ����������ַ���������ĸ�Ǵ�д�Ļ�, ����ת��ΪСд
     *
     * @param str �������ַ���
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 5/2/2017 9:29 PM
     * @since 1.0
     */
    public static String lowerCaseFirstChar(String str) {
        assert0(!isEmpty(str), "'str' is null ");
        if (str.length() == 1) {
            return str.toLowerCase();
        }
        if (Character.isUpperCase(str.charAt(0))) {
            return Character.toLowerCase(str.charAt(0)) + str.substring(1);
        }

        return str;
    }

    /**
     * ��������Ԫ�ؼ�����ӵ�Ŀ��List��, ������
     *
     * @param eles ������Ԫ�ؼ���
     * @return java.util.Set<T>
     * @author Jerry.X.He
     * @date 5/1/2017 12:53 AM
     * @since 1.0
     */
    public static <T> List<T> asList(T... eles) {
        if (eles == null) return new ArrayList<>();

        List<T> result = new ArrayList<>();
        for (T ele : eles) {
            result.add(ele);
        }
        return result;
    }

    /**
     * ��������Ԫ�ؼ�����ӵ�Ŀ��Set��, ������
     *
     * @param eles ������Ԫ�ؼ���
     * @return java.util.Set<T>
     * @author Jerry.X.He
     * @date 5/1/2017 12:53 AM
     * @since 1.0
     */
    public static <T> Set<T> asSet(T... eles) {
        if (eles == null) return new LinkedHashSet<>();

        Set<T> result = new LinkedHashSet<>();
        for (T ele : eles) {
            result.add(ele);
        }
        return result;
    }

    /**
     * ��������Ԫ�ؼ�����ӵ�Ŀ��Map��, ������
     *
     * @param eles ������Ԫ�ؼ���
     * @return java.util.Set<T>
     * @author Jerry.X.He
     * @date 5/1/2017 12:53 AM
     * @since 1.0
     */
    public static <K, V> Map<K, V> asMap(K[] keys, V... eles) {
        if ((keys == null) || (eles == null)) return new HashMap<>();
        assert0(keys.length == eles.length, "keys's length must 'eq' vals's length !");

        Map<K, V> result = new LinkedHashMap<>();
        for (int i = 0, len = keys.length; i < len; i++) {
            result.put(keys[i], eles[i]);
        }
        return result;
    }

    /**
     * ת��������ַ���
     *
     * @param str �������ַ���
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 5/7/2017 2:18 PM
     * @since 1.0
     */
    public static String transfer(String str) {
        if (InnerTools.isEmpty(str)) {
            return str;
        }

        StringBuilder sb = new StringBuilder(str.length() << 1);
        for (int i = 0, len = str.length(); i < len; i++) {
            Character ch = str.charAt(i);
            if (NEED_BE_TRANSFER.contains(ch)) {
                sb.append(SLASH);
            }
            sb.append(ch);
        }
        return sb.toString();
    }


}

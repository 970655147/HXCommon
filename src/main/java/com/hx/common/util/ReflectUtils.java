/**
 * file name : ReflectTools.java
 * created at : 6:08:46 PM Jun 18, 2016
 * created by 970655147
 */

package com.hx.common.util;

/**
 * ������صĹ���
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 5/5/2017 8:26 PM
 */
public final class ReflectUtils {

    // disable constructor
    private ReflectUtils() {
        InnerTools.assert0("can't instantiate !");
    }

    /**
     * �жϸ�����type�Ƿ�ʵ���˸�����clazz�ӿ�
     * 1. self
     * 2. implements
     * 3. recurse superTypes [1. incase of 'PrimitiveTypes', 2. incase of 'java.lang.Object' ]
     *
     * @param type  ����������
     * @param clazz ��Ҫ�ж���Ŀ��clazz
     * @return boolean
     * @author Jerry.X.He
     * @date 5/5/2017 8:25 PM
     * @since 1.0
     */
    public static boolean implements0(Class<?> type, Class clazz) {
        if (type.getName().equals(clazz.getName())) {
            return true;
        }
        Class[] implemented = type.getInterfaces();
        for (int i = 0; i < implemented.length; i++) {
            if (implemented[i].getName().equals(clazz.getName())) {
                return true;
            }
        }

        Class superType = type.getSuperclass();
        // incase of 'PrimitiveTypes'
        if (superType == null) {
            return false;
        }
        if ("java.lang.Object".equals(superType.getName())) {
            return false;
        }

        return implements0(superType, clazz);
    }

}

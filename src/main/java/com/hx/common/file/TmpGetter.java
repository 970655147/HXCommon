/**
 * file name : TmpGetter.java
 * created at : 9:31:46 PM Nov 24, 2015
 * created by 970655147
 */

package com.hx.common.file;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ��ȡ��ʱ�ļ���ص�����
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 5/5/2017 8:11 PM
 */
public class TmpGetter {

    /**
     * ��ʱ�ļ���
     */
    public String tmpDir;
    /**
     * ��ʱ�ļ���
     */
    public String tmpName;
    /**
     * ��ʱ�ļ�������
     */
    public final AtomicInteger tmpIdx;
    /**
     * ��ʱ�ļ��ĺ�׺
     */
    public String suffix;

    /**
     * ��ʼ��
     *
     * @param tmpDir  ��ʱ�ļ���
     * @param tmpName ��ʱ�ļ���
     * @param tmpIdx  ��ʱ�ļ�������
     * @param suffix  ��ʱ�ļ��ĺ�׺
     * @since 1.0
     */
    public TmpGetter(String tmpDir, String tmpName, int tmpIdx, String suffix) {
        this.tmpDir = tmpDir;
        this.tmpName = tmpName;
        this.tmpIdx = new AtomicInteger(tmpIdx);
        this.suffix = suffix;
    }

    /**
     * ��ȡ��һ����ʱ�ļ�·��
     *
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 5/5/2017 8:12 PM
     * @since 1.0
     */
    public String getNextTmpPath() {
        return tmpDir + "\\" + getNextTmpName() + suffix;
    }

    /**
     * ��ȡ��һ����ʱ�ļ�·��, ָ����׺
     *
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 5/5/2017 8:12 PM
     * @since 1.0
     */
    public String getNextTmpPath(String suffix) {
        return tmpDir + "\\" + getNextTmpName() + suffix;
    }

    /**
     * ��ȡ��һ����ʱ�ļ�·��, ָ���ļ���, ��׺
     *
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 5/5/2017 8:12 PM
     * @since 1.0
     */
    public String getNextTmpPath(String fileName, String suffix) {
        return tmpDir + "\\" + fileName + suffix;
    }

    /**
     * ��ȡһ����ʱ�ļ�·��, ָ���ļ���
     *
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 5/5/2017 8:12 PM
     * @since 1.0
     */
    public String getTmpPath(int idx) {
        return tmpDir + "\\" + tmpName + idx + suffix;
    }

    /**
     * ��ȡһ����ʱ�ļ�·��, ָ���ļ���, ��׺
     *
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 5/5/2017 8:12 PM
     * @since 1.0
     */
    public String getTmpPath(int idx, String suffix) {
        return tmpDir + "\\" + tmpName + idx + suffix;
    }

    /**
     * ��ȡһ����ʱ�ļ�·��, ָ���ļ���
     *
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 5/5/2017 8:12 PM
     * @since 1.0
     */
    public String getTmpPath(String name) {
        return tmpDir + "\\" + name + suffix;
    }

    /**
     * ��ȡһ����ʱ�ļ�·��, ָ���ļ���, ��׺
     *
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 5/5/2017 8:12 PM
     * @since 1.0
     */
    public String getTmpPath(String name, String suffix) {
        return tmpDir + "\\" + name + suffix;
    }

    /**
     * ��ȡ��һ����ʱ�ļ���
     *
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 5/5/2017 8:12 PM
     * @since 1.0
     */
    public String getNextTmpDir() {
        return tmpDir + "\\" + getNextTmpName();
    }

    /**
     * ��ȡ��һ����ʱ�ļ���, ָ���ļ�����
     *
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 5/5/2017 8:12 PM
     * @since 1.0
     */
    public String getTmpDir(int idx) {
        return tmpDir + "\\" + tmpName + idx;
    }

    /**
     * ��ȡ��һ����ʱ�ļ���, ָ���ļ�����
     *
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 5/5/2017 8:12 PM
     * @since 1.0
     */
    public String getTmpDir(String name) {
        return tmpDir + "\\" + name;
    }

    /**
     * setter
     */
    public void setTmpIdx(int tmpIdx) {
        this.tmpIdx.set(tmpIdx);
    }

    public void setTmpDir(String tmpDir) {
        this.tmpDir = tmpDir;
    }

    public void setTmpName(String tmpName) {
        this.tmpName = tmpName;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    /**
     * ��ȡ��һ����ʱ�ļ���
     *
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 5/5/2017 8:13 PM
     * @since 1.0
     */
    protected String getNextTmpName() {
        return tmpName + (String.valueOf(tmpIdx.getAndIncrement()));
    }
}

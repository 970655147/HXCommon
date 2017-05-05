/**
 * file name : TmpGetter.java
 * created at : 9:31:46 PM Nov 24, 2015
 * created by 970655147
 */

package com.hx.common.file;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 获取临时文件相关的数据
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 5/5/2017 8:11 PM
 */
public class TmpGetter {

    /**
     * 临时文件夹
     */
    public String tmpDir;
    /**
     * 临时文件名
     */
    public String tmpName;
    /**
     * 临时文件的索引
     */
    public final AtomicInteger tmpIdx;
    /**
     * 临时文件的后缀
     */
    public String suffix;

    /**
     * 初始化
     *
     * @param tmpDir  临时文件夹
     * @param tmpName 临时文件名
     * @param tmpIdx  临时文件的索引
     * @param suffix  临时文件的后缀
     * @since 1.0
     */
    public TmpGetter(String tmpDir, String tmpName, int tmpIdx, String suffix) {
        this.tmpDir = tmpDir;
        this.tmpName = tmpName;
        this.tmpIdx = new AtomicInteger(tmpIdx);
        this.suffix = suffix;
    }

    /**
     * 获取下一个临时文件路径
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
     * 获取下一个临时文件路径, 指定后缀
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
     * 获取下一个临时文件路径, 指定文件名, 后缀
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
     * 获取一个临时文件路径, 指定文件名
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
     * 获取一个临时文件路径, 指定文件名, 后缀
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
     * 获取一个临时文件路径, 指定文件名
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
     * 获取一个临时文件路径, 指定文件名, 后缀
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
     * 获取下一个临时文件夹
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
     * 获取下一个临时文件夹, 指定文件夹名
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
     * 获取下一个临时文件夹, 指定文件夹名
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
     * 获取下一个临时文件名
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

package com.hx.common.consumer;

import com.hx.common.interf.consumer.FileLineConsumer;

import java.io.File;

/**
 * FileLineContext
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 11/3/2018 8:46 AM
 */
public class FileLineContext<T> {

    /** 给定的文件 */
    private File file;
    /** 打开文件使用的编码 */
    private String charset;
    /** consumer */
    private FileLineConsumer<T> consumer;
    /** 当前行的索引 */
    private int lineIdx;
    /** 当前行的内容 */
    private String line;

    public FileLineContext() {
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public FileLineConsumer<T> getConsumer() {
        return consumer;
    }

    public void setConsumer(FileLineConsumer<T> consumer) {
        this.consumer = consumer;
    }

    public int getLineIdx() {
        return lineIdx;
    }

    public void setLineIdx(int lineIdx) {
        this.lineIdx = lineIdx;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
}

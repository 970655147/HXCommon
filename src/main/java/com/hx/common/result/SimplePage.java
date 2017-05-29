package com.hx.common.result;

import com.hx.common.interf.common.Page;

import java.util.List;

/**
 * ��ǰ��ҳ��
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 5/21/2017 4:18 PM
 */
public class SimplePage<T> implements Page<T> {

    /**
     * ��ǰҳ��
     */
    private int pageNow;
    /**
     * ÿһҳ������
     */
    private int pageSize;
    /**
     * �ܹ���ҳ��
     */
    private int totalPage;
    /**
     * �ܹ��ļ�¼
     */
    private int totalRecord;
    /**
     * ��ǰ Page ��װ������
     */
    private List<T> list;

    public SimplePage(int pageNow, int pageSize) {
        this.pageNow = pageNow;
        this.pageSize = pageSize;
    }

    public SimplePage(int pageNow, int pageSize, int totalRecord) {
        this.pageNow = pageNow;
        this.pageSize = pageSize;
        setTotalRecord(totalRecord);
    }

    public SimplePage() {
        this(1, 10);
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
        this.totalPage = (totalRecord - 1) / pageSize + 1;
    }

    @Override
    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    @Override
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public int getPageNow() {
        return pageNow;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int recordOffset() {
        return (pageNow - 1) * pageSize;
    }

    @Override
    public int getTotalPage() {
        return totalPage;
    }

    @Override
    public int getTotalRecord() {
        return totalRecord;
    }

    @Override
    public List<T> getList() {
        return list;
    }

}

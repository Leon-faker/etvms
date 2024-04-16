package com.lihang.etvms.adapter.controller.request;

/**
 * 分页参数
 *
 * @date 2023/1/3
 **/
public class PageableParam {

    /**
     * 每页显示数量
     *
     * @mock 50
     * @required
     */
    private int pageSize;

    /**
     * 页码，从1开始
     *
     * @mock 1
     * @required
     */
    private int pageNumber;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}

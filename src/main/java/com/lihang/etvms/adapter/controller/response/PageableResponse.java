package com.lihang.etvms.adapter.controller.response;

import java.util.List;

/**
 * 分页响应结果集
 *
 * @date 2023/1/3
 **/
public class PageableResponse<T> {

    private long total;
    private int pageSize;
    private int pageNumber;
    private List<T> dataList;

    public static <T> PageableResponse<T> of(long total, int pageSize, int pageNumber, List<T> dataList) {
        PageableResponse<T> response = new PageableResponse<>();
        response.setTotal(total);
        response.setPageSize(pageSize);
        response.setPageNumber(pageNumber);
        response.setDataList(dataList);
        return response;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

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

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}

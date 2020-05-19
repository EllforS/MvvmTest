package com.ellfors.mvvmtest.bean;

/**
 * BaseResponse
 * 2020-05-06 16:45
 */
public class BaseResponse<T> {

    private int counts;
    private int status;
    private T data;
    private String url;

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getUrl() {
        return url == null ? "" : url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

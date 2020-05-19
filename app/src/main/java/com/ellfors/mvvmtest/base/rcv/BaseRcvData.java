package com.ellfors.mvvmtest.base.rcv;

/**
 * BaseRcvData
 * 2020-04-15 11:01
 */
public class BaseRcvData {
    private Object data;
    private int type;

    public BaseRcvData(Object data, int type) {
        this.data = data;
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

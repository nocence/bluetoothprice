package com.gkqx.bluetoothprice.dto;

import java.util.List;

/**
 * @ClassName Result
 * @Description 返回数据实体类
 * @Author Innocence
 * @Date 2019/4/22 002215:30
 * @Version 1.0
 **/
public class Result {

    private Integer code;

    private String msg;

    private Object obj;

    private List list;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}

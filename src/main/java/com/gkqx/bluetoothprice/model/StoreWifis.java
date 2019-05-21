package com.gkqx.bluetoothprice.model;

/**
 * @ClassName StoreWifis
 * @Description 用于前端查询wifi列表时传值
 * @Author Innocence
 * @Date 2019/5/9 000920:17
 * @Version 1.0
 **/
public class StoreWifis {

    private Integer storeId;

    private String storeName;

    private Integer wifiId;

    private String wifiIp;

    private Integer type;

    //用来给前端传递“是否使用”的信息
    private String state;

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getWifiId() {
        return wifiId;
    }

    public void setWifiId(Integer wifiId) {
        this.wifiId = wifiId;
    }

    public String getWifiIp() {
        return wifiIp;
    }

    public void setWifiIp(String wifiIp) {
        this.wifiIp = wifiIp;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

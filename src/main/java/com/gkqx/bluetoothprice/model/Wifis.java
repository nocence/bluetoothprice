package com.gkqx.bluetoothprice.model;


import java.io.Serializable;

public class Wifis implements Serializable {

    private static final long serialVersionUID = -5685331409958244265L;
    private Integer wifiId;

    private String wifiIp;

    private Integer storeId;

    private Integer type;

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

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}

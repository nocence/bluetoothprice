package com.gkqx.bluetoothprice.model;

import java.io.Serializable;

/**
 * @ClassName FontTag
 * @Description 用于传递前端查询所有价签的信息实体类
 * @Author Innocence
 * @Date 2019/5/15 001511:15
 * @Version 1.0
 **/
public class FontTag implements Serializable {

    private static final long serialVersionUID = 920026429257222474L;

    private String macAddress;

    private String storeName;

    private String wifiIp;

    private String goodsName;

    private String goodsPrice;

    private String goodsSpecs;

    private String goodsUnit;

    private Integer type;

    private String state;

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getWifiIp() {
        return wifiIp;
    }

    public void setWifiIp(String wifiIp) {
        this.wifiIp = wifiIp;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsSpecs() {
        return goodsSpecs;
    }

    public void setGoodsSpecs(String goodsSpecs) {
        this.goodsSpecs = goodsSpecs;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
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

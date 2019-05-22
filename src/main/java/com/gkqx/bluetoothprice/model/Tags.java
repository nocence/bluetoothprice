package com.gkqx.bluetoothprice.model;

/**
 * @ClassName Tags
 * @Description 价签的实体类
 * @Author Innocence
 * @Date 2019/5/14 001415:56
 * @Version 1.0
 **/
public class Tags {

    private Integer tagId;

    private Integer wifiId;

    private String goodsId;

    //价签的mac地址
    private String macAddress;

    //价签的分辨率（能显示的图片尺寸）
    private String picPx;

    private Integer type;

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getWifiId() {
        return wifiId;
    }

    public void setWifiId(Integer wifiId) {
        this.wifiId = wifiId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getPicPx() {
        return picPx;
    }

    public void setPicPx(String picPx) {
        this.picPx = picPx;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}

package com.gkqx.bluetoothprice.model;

/**
 * @ClassName ImageToWifi
 * @Description 用来接收前端发送图片时上传的对象
 * @Author Innocence
 * @Date 2019/5/10 001011:21
 * @Version 1.0
 **/
public class ImageToWifi {

    private String imageName;

    private String wifiIp;

    private String macAddress;

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getWifiIp() {
        return wifiIp;
    }

    public void setWifiIp(String wifiIp) {
        this.wifiIp = wifiIp;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
}

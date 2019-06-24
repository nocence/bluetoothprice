package com.gkqx.bluetoothprice.model;

import java.io.Serializable;

/**
 * @ClassName ImgInfo
 * @Description 图像信息实体类
 * @Author Innocence
 * @Date 2019/4/19 001916:32
 * @Version 1.0
 **/
public class ImgInfo implements Serializable {

    private static final long serialVersionUID = -8434273984031256168L;
    private Integer imgId;//对应数据库的主键对象

    private String title;//标题

    private String unit;//单位

    private String specs;//规格

    private String place;//产地

    private String code;//编码

    private String price;//价格

    private String barcode;//条形码编号

    private String tellphone;//电话

    private String path;//图片路径

    private String name;//图片名

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getTellphone() {
        return tellphone;
    }

    public void setTellphone(String tellphone) {
        this.tellphone = tellphone;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.gkqx.bluetoothprice.model;

import java.io.Serializable;

/**
 * @ClassName GoodsImages
 * @Description 用于传送到前端显示图片的实体类
 * @Author Innocence
 * @Date 2019/5/8 000815:37
 * @Version 1.0
 **/
public class GoodsImages implements Serializable {

    private static final long serialVersionUID = 5743441048517912926L;
    private String goodsName;

    private String goodsSpecs;

    private String goodsUnit;

    private String imageName;

    private String imageUrl;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

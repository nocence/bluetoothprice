package com.gkqx.bluetoothprice.model;

/**
 * @ClassName Doods
 * @Description 商品实体类
 * @Author Innocence
 * @Date 2019/5/7 000714:00
 * @Version 1.0
 **/
public class Goods {

    private String goodsId;

    private String goodsName;

    //商品规格
    private String goodsSpecs;

    //商品计量单位
    private String goodsUnit;

    //商品产地
    private String goodsPlace;

    //商品编码
    private String goodsCode;

    private String goodsPrice;

    //条形码编号
    private String goodsBarcode;

    //厂家电话
    private String goodsTell;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

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

    public String getGoodsPlace() {
        return goodsPlace;
    }

    public void setGoodsPlace(String goodsPlace) {
        this.goodsPlace = goodsPlace;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsBarcode() {
        return goodsBarcode;
    }

    public void setGoodsBarcode(String goodsBarcode) {
        this.goodsBarcode = goodsBarcode;
    }

    public String getGoodsTell() {
        return goodsTell;
    }

    public void setGoodsTell(String goodsTell) {
        this.goodsTell = goodsTell;
    }
}

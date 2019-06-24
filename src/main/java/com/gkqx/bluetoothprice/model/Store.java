package com.gkqx.bluetoothprice.model;

import java.io.Serializable;

/**
 * @ClassName Store
 * @Description 门店信息实体类
 * @Author Innocence
 * @Date 2019/5/6 000618:37
 * @Version 1.0
 **/
public class Store implements Serializable {

    private static final long serialVersionUID = -5307263122516116116L;
    private Integer storeId;

    private String storeName;

    private String storeProvince;

    private String storeUrban;

    private String storeAddress;

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

    public String getStoreProvince() {
        return storeProvince;
    }

    public void setStoreProvince(String storeProvince) {
        this.storeProvince = storeProvince;
    }

    public String getStoreUrban() {
        return storeUrban;
    }

    public void setStoreUrban(String storeUrban) {
        this.storeUrban = storeUrban;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }
}

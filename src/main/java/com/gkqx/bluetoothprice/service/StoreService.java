package com.gkqx.bluetoothprice.service;

import com.gkqx.bluetoothprice.model.Store;

import java.util.List;

public interface StoreService {
    Integer addStore(Store store);
    List<Store> getAllStores(Store store);
}

package com.gkqx.bluetoothprice.service;

import com.gkqx.bluetoothprice.model.StoreWifis;
import com.gkqx.bluetoothprice.model.Wifis;

import java.util.List;

public interface WifisService {
    Integer addWifi(Wifis wifis);

    List<StoreWifis> getAllWifis(StoreWifis storeWifis);

    Wifis getOneByWifisIp(Wifis wifis);
}

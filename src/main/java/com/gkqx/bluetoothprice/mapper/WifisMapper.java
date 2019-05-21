package com.gkqx.bluetoothprice.mapper;

import com.gkqx.bluetoothprice.model.StoreWifis;
import com.gkqx.bluetoothprice.model.Wifis;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WifisMapper {

    Integer addWifi(Wifis wifis);

    List<StoreWifis> getAllWifis(StoreWifis storeWifis);

    Wifis getOneByWifisIp(Wifis wifis);
}

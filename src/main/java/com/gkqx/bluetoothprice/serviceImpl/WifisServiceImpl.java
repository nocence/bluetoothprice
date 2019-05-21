package com.gkqx.bluetoothprice.serviceImpl;

import com.gkqx.bluetoothprice.mapper.WifisMapper;
import com.gkqx.bluetoothprice.model.StoreWifis;
import com.gkqx.bluetoothprice.model.Wifis;
import com.gkqx.bluetoothprice.service.WifisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName WifisServiceImpl
 * @Description TODD
 * @Author Innocence
 * @Date 2019/5/9 000917:22
 * @Version 1.0
 **/
@Service
@Transactional
public class WifisServiceImpl implements WifisService {

    @Autowired
    private WifisMapper wifisMapper;

    @Override
    public Integer addWifi(Wifis wifis) {
        return wifisMapper.addWifi(wifis);
    }

    @Override
    public List<StoreWifis> getAllWifis(StoreWifis storeWifis) {
        return wifisMapper.getAllWifis(storeWifis);
    }

    @Override
    public Wifis getOneByWifisIp(Wifis wifis) {
        return wifisMapper.getOneByWifisIp(wifis);
    }


}

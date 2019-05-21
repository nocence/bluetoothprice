package com.gkqx.bluetoothprice.serviceImpl;

import com.gkqx.bluetoothprice.mapper.StoreMapper;
import com.gkqx.bluetoothprice.model.Store;
import com.gkqx.bluetoothprice.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreMapper storeMapper;

    @Override
    public Integer addStore(Store store) {
        return storeMapper.addStore(store);
    }

    @Override
    public List<Store> getAllStores(Store store) {
        return storeMapper.getAllStores(store);
    }
}

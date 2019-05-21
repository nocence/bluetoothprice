package com.gkqx.bluetoothprice.service;

import com.gkqx.bluetoothprice.model.Goods;

import java.util.List;

public interface GoodsService {
    Integer addGoodsInfo(Goods goods);
    List<Goods> getAllGoods(Goods goods);
    Goods getOneGoodsByNameAndSpces(Goods goods);
}

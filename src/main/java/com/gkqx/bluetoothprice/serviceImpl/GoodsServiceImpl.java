package com.gkqx.bluetoothprice.serviceImpl;

import com.gkqx.bluetoothprice.mapper.GoodsMapper;
import com.gkqx.bluetoothprice.model.Goods;
import com.gkqx.bluetoothprice.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GoodsServiceImpl  implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public Integer addGoodsInfo(Goods goods) {
        return goodsMapper.addGoodsInfo(goods);
    }

    @Override
    public List<Goods> getAllGoods(Goods goods) {
        return goodsMapper.getAllGoods(goods);
    }

    @Override
    public Goods getOneGoodsByNameAndSpces(Goods goods) {
        return goodsMapper.getOneGoodsByNameAndSpces(goods);
    }
}

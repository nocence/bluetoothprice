package com.gkqx.bluetoothprice.mapper;

import com.gkqx.bluetoothprice.model.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsMapper {

    /**
    * 添加商品信息
    * @author Innocence
    * @date 2019/5/7 000714:45
    * @param
    * @return
    */
    Integer addGoodsInfo(Goods goods);

    List<Goods> getAllGoods(Goods goods);

    Goods getOneGoodsByNameAndSpces(Goods goods);
}

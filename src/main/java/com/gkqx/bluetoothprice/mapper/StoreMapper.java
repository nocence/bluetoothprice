package com.gkqx.bluetoothprice.mapper;

import com.gkqx.bluetoothprice.model.Store;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreMapper {

    /**
    * 添加门店信息
    * @author Innocence
    * @date 2019/5/6 000618:40
    * @param
    * @return
    */
    Integer addStore(Store store);

    /**
    * 查询门店列表
    * @author Innocence
    * @date 2019/5/7 000710:32
    * @param
    * @return
    */
    List<Store> getAllStores(Store store);
}

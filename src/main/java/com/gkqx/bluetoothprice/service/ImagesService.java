package com.gkqx.bluetoothprice.service;

import com.gkqx.bluetoothprice.model.Goods;
import com.gkqx.bluetoothprice.model.GoodsImages;
import com.gkqx.bluetoothprice.model.Images;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ImagesService {

    Integer insertImageInfomation(Images images);

    List<GoodsImages> getAllImages(@Param("images")Images images, @Param("goods") Goods goods);

    //根据图片名查询单张图片,主要是为了拿到图片路径进行发送
    Images getImage(Images images);

    Integer deleteImageByImageName(Images images);
}

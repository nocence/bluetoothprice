package com.gkqx.bluetoothprice.mapper;

import com.gkqx.bluetoothprice.model.Goods;
import com.gkqx.bluetoothprice.model.GoodsImages;
import com.gkqx.bluetoothprice.model.ImageToWifi;
import com.gkqx.bluetoothprice.model.Images;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ImagesMapper {

    /**
    * 插入图片信息
    * @author Innocence
    * @date 2019/5/8 000811:40
    * @param
    * @return
    */
    Integer insertImageInfomation(Images images);

    /**
    * 遍历图片信息
    * @author Innocence
    * @date 2019/5/8 000811:49
    * @param
    * @return
    */
    List<GoodsImages> getAllImages(@Param("images")Images images, @Param("goods") Goods goods);

    /**
    * 根据图片属性查询单张图片
    * @author Innocence
    * @date 2019/5/10 001011:35
    * @param
    * @return com.gkqx.bluetoothprice.model.Images
    */
    Images getImage(Images images);
}

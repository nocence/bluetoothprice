package com.gkqx.bluetoothprice.mapper;

import com.gkqx.bluetoothprice.model.ImgInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ImgInfoMapper {

    //插入图片信息
    Integer insertImgInfo(@Param("imgInfo") ImgInfo imgInfo);

    //查询图片信息
    ImgInfo queryImg(@Param("imgInfo") ImgInfo imgInfo);
}

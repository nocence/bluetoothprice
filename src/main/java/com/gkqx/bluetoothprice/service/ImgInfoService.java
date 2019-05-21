package com.gkqx.bluetoothprice.service;

import com.gkqx.bluetoothprice.model.ImgInfo;

public interface ImgInfoService {

    //插入图片信息
    Integer insertImgInfo(ImgInfo imgInfo);

    //查询图片信息
    ImgInfo queryImg(ImgInfo imgInfo);
}

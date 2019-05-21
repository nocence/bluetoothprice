package com.gkqx.bluetoothprice.serviceImpl;

import com.gkqx.bluetoothprice.mapper.ImgInfoMapper;
import com.gkqx.bluetoothprice.model.ImgInfo;
import com.gkqx.bluetoothprice.service.ImgInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName ImgInfoServiceImpl
 * @Description TODD
 * @Author Innocence
 * @Date 2019/4/23 002311:57
 * @Version 1.0
 **/
@Service
@Transactional
public class ImgInfoServiceImpl implements ImgInfoService {

    @Autowired
    private ImgInfoMapper imgInfoMapper;

    @Override
    public Integer insertImgInfo(ImgInfo imgInfo) {
        return imgInfoMapper.insertImgInfo(imgInfo);
    }

    @Override
    public ImgInfo queryImg(ImgInfo imgInfo) {
        return imgInfoMapper.queryImg(imgInfo);
    }
}
